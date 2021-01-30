package com.sw.core.helpers;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResults;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.*;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApplitoolsTestResultsHandler {

    private static final String VERSION = "1.1.0";
    private static final String STEP_RESULT_API_FORMAT = "/api/sessions/batches/%s/%s/?ApiKey=%s&format=json";
    private static final String RESULT_REGEX = "(?<serverURL>^.+)\\/app\\/batches\\/(?<batchId>\\d+)\\/(?<sessionId>\\d+).*$";
    private static final String IMAGE_TMPL = "%s/step %s %s-%s.png";
    private static final int DEFAULT_TIME_BETWEEN_FRAMES = 500;
    private static final String DiffsUrlTemplate = "%s/api/sessions/batches/%s/%s/steps/%s/diff?ApiKey=%s";
    private List<String> animatedGiffPath = new ArrayList<>();
    private String applitoolsViewKey;
    private String serverURL;
    private String batchID;
    private String sessionID;
    private TestResults testResults;
    private String[] stepsNames;
    private ResultStatus[] stepsState;
    private JSONObject testData;
    private String prefix = "";

    public ApplitoolsTestResultsHandler(TestResults testResults, String viewKey) throws Exception {
        this.applitoolsViewKey = viewKey;
        this.testResults = testResults;
        Pattern pattern = Pattern.compile(RESULT_REGEX);
        Matcher matcher = pattern.matcher(testResults.getUrl());
        if (!matcher.find())
            throw new Exception("Unexpected result URL - Not parsable");
        this.batchID = matcher.group("batchId");
        this.sessionID = matcher.group("sessionId");
        this.serverURL = matcher.group("serverURL");

        String url = String.format(serverURL + STEP_RESULT_API_FORMAT, this.batchID, this.sessionID,
                this.applitoolsViewKey);
        String json = readJsonStringFromUrl(url);
        this.testData = new JSONObject(json);

        this.stepsNames = calculateStepsNames();
        this.stepsState = prepareStepResults();

    }

    private static void createAnimatedGif(List<BufferedImage> images, File target, int timeBetweenFramesMS) throws IOException {
        GifSequenceWriter writer = null;
        try (ImageOutputStream output = new FileImageOutputStream(target)) {
            RectangleSize max = getMaxSize(images);
            for (BufferedImage image : images) {
                BufferedImage normalized = new BufferedImage(max.getWidth(), max.getHeight(), image.getType());
                normalized.getGraphics().drawImage(image, 0, 0, null);
                if (writer == null)
                    writer = new GifSequenceWriter(output, image.getType(), timeBetweenFramesMS, true);
                writer.writeToSequence(normalized);
            }
        } finally {
            Objects.requireNonNull(writer).close();
        }
    }

    private static RectangleSize getMaxSize(List<BufferedImage> images) {
        int maxWidth = 0;
        int maxHeight = 0;

        for (BufferedImage image : images) {
            if (maxHeight < image.getHeight())
                maxHeight = image.getHeight();
            if (maxWidth < image.getWidth())
                maxWidth = image.getWidth();
        }
        return new RectangleSize(maxWidth, maxHeight);
    }

    public TestResults getTestResults() {
        return testResults;
    }

    private String preparePath(String Path) {
        Path += "/" + prefix;
        if (!Path.contains("/" + batchID + "/" + sessionID)) {
            Path = Path + "/" + batchID + "/" + sessionID;
            File folder = new File(Path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
        }
        return Path;

    }

    private URL[] getDiffUrls() {
        URL[] urls = new URL[stepsState.length];
        for (int step = 0; step < this.testResults.getSteps(); ++step) {
            if (stepsState[step] == ResultStatus.UNRESOLVED) {
                try {
                    urls[step] = new URL(String.format(DiffsUrlTemplate, this.serverURL, this.batchID, this.sessionID,
                            step + 1, this.applitoolsViewKey));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else
                urls[step] = null;
        }
        return urls;
    }

    public ResultStatus[] calculateStepResults() {
        if (stepsState == null)
            try {
                stepsState = prepareStepResults();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return stepsState;
    }

    public String getLinkToStep(int step) {

        String link = testResults.getUrl().replaceAll("batches", "sessions");
        StringBuilder buf = new StringBuilder(link);
        int index = link.indexOf("?accountId=");
        return (buf.insert(index, "/steps/" + step).toString());
    }

    private ResultStatus[] prepareStepResults() throws Exception {
        JSONArray expected = this.testData.getJSONArray("expectedAppOutput");
        JSONArray actual = this.testData.getJSONArray("actualAppOutput");

        int steps = Math.max(expected.length(), actual.length());
        ResultStatus[] retStepResults = new ResultStatus[steps];

        for (int i = 0; i < steps; i++) {

            if (expected.get(i) == JSONObject.NULL) {
                retStepResults[i] = ResultStatus.NEW;
            } else if (actual.get(i) == JSONObject.NULL) {
                retStepResults[i] = ResultStatus.MISSING;
            } else if (actual.getJSONObject(i).getBoolean("isMatching")) {
                retStepResults[i] = ResultStatus.PASSED;
            } else {
                retStepResults[i] = ResultStatus.UNRESOLVED;
            }

        }
        return retStepResults;
    }

    public String[] getStepsNames() {
        return this.stepsNames;
    }

    private String[] calculateStepsNames() throws Exception {
        ResultStatus[] stepResults = calculateStepResults();
        JSONArray expected = this.testData.getJSONArray("expectedAppOutput");
        JSONArray actual = this.testData.getJSONArray("actualAppOutput");
        int steps = expected.length();
        String[] StepsNames = new String[steps];

        for (int i = 0; i < steps; i++) {
            if (stepResults[i] != ResultStatus.NEW) {
                StepsNames[i] = expected.getJSONObject(i).optString("tag");
            } else {
                StepsNames[i] = actual.getJSONObject(i).optString("tag");
            }
        }
        return StepsNames;
    }

    @SuppressWarnings("restriction")
    private String readJsonStringFromUrl(String url) throws Exception {
        HttpsURLConnection.setDefaultSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            return readAll(rd);
        }
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public void downloadDiffs(String path) throws Exception {
        saveImagesInFolder(preparePath(path), "Diff", getDiffUrls());
    }

    private void downloadBaselineImages(String path) throws IOException, JSONException {
        saveImagesInFolder(preparePath(path), "Baseline", getBaselineImagesURLS());
    }

    private void downloadCurrentImages(String path) throws IOException, JSONException {
        saveImagesInFolder(preparePath(path), "Current", getCurrentImagesURLS());
    }

    public void downloadImages(String path) throws Exception {
        downloadBaselineImages(path);
        downloadCurrentImages(path);
    }

    private void saveImagesInFolder(String path, String imageType, URL[] imageURLS)
            throws IOException {
        for (int i = 0; i < imageURLS.length; i++) {
            if (imageURLS[i] == null) {
                System.out.println("No " + imageType + " image in step " + (i + 1) + ": " + stepsNames[i]);
            } else {
                FileUtils.copyURLToFile(imageURLS[i],
                        new File(String.format(IMAGE_TMPL, path, (i + 1), stepsNames[i], imageType)));
            }
        }
    }

    private URL[] getDownloadImagesURLSByType(String imageType) throws JSONException {
        String[] imageIds = getImagesUIDs(this.sessionID, this.batchID, imageType);
        URL[] URLS = new URL[calculateStepResults().length];
        for (int i = 0; i < Objects.requireNonNull(imageIds).length; i++) {
            if (imageIds[i] == null) {
                URLS[i] = null;
            } else
                try {
                    URLS[i] = new URL(String.format("%s/api/images/%s?apiKey=%s", this.serverURL, imageIds[i],
                            this.applitoolsViewKey));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
        }
        return URLS;
    }

    private URL[] getCurrentImagesURLS() throws JSONException {
        return getDownloadImagesURLSByType("Current");
    }

    private URL[] getBaselineImagesURLS() throws JSONException {
        return getDownloadImagesURLSByType("Baseline");
    }

    private String[] getImagesUIDs(String sessionId, String batchId, String imageType) throws JSONException {
        String sessionInfo = null;
        try {
            sessionInfo = getSessionInfo(sessionId, batchId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject obj = new JSONObject(Objects.requireNonNull(sessionInfo));
        if (imageType.equals("Baseline")) {
            return getImagesUIDs(obj.getJSONArray("expectedAppOutput"));
        } else if (imageType.equals("Current")) {
            return getImagesUIDs(obj.getJSONArray("actualAppOutput"));
        }
        return null;
    }

    private String[] getImagesUIDs(JSONArray infoTable) throws JSONException {
        String[] retUIDs = new String[infoTable.length()];

        for (int i = 0; i < infoTable.length(); i++) {
            if (infoTable.isNull(i)) {
                retUIDs[i] = null;
            } else {
                JSONObject entry = infoTable.getJSONObject(i);
                JSONObject image = entry.getJSONObject("image");
                retUIDs[i] = (image.getString("id"));
            }
        }
        return retUIDs;
    }

    public void downloadAnimateGiff(String path) throws JSONException {
        downloadAnimateGiff(path, DEFAULT_TIME_BETWEEN_FRAMES);
    }

    public List<String> downloadAnimateGiff(String path, int timeBetweenFramesMS) throws JSONException {

        if (testResults.getMismatches() + testResults.getMatches() > 0) // only if the test isn't new and not all of his
        // steps are missing
        {
            URL[] baselienImagesURL = getBaselineImagesURLS();
            URL[] currentImagesURL = getCurrentImagesURLS();
            URL[] diffImagesURL = getDiffUrls();

            for (int i = 0; i < stepsState.length; i++) {
                if (stepsState[i] == ResultStatus.UNRESOLVED) {
                    List<BufferedImage> list = new ArrayList<>();
                    try {
                        if (currentImagesURL[i] != null)
                            list.add(ImageIO.read(currentImagesURL[i]));
                        if (baselienImagesURL[i] != null)
                            list.add(ImageIO.read(baselienImagesURL[i]));
                        if (diffImagesURL[i] != null)
                            list.add(ImageIO.read(diffImagesURL[i]));
                        String tempPath = preparePath(path) + "/" + (i + 1) + " - AnimatedGiff.gif";
                        animatedGiffPath.add(tempPath);
                        createAnimatedGif(list, new File(tempPath), timeBetweenFramesMS);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("No Animated GIf created for Step " + (i + 1) + " " + stepsNames[i]
                            + " as it is " + stepsState[i]);
                }
            }
        }
        return animatedGiffPath;
    }

    private String getSessionInfo(String sessionId, String batchId) throws IOException {
        URL url = new URL(String.format("%s/api/sessions/batches/%s/%s?apiKey=%s&format=json", this.serverURL, batchId,
                sessionId, this.applitoolsViewKey));

        try (InputStream stream = url.openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            return readAll(rd);
        }
    }

    public void SetPathPrefixStructure(String pathPrefix) throws JSONException {
        pathPrefix = pathPrefix.replaceAll("TestName", this.getTestName());
        pathPrefix = pathPrefix.replaceAll("AppName", this.getAppName());
        pathPrefix = pathPrefix.replaceAll("viewport", this.getViewportSize());
        pathPrefix = pathPrefix.replaceAll("hostingOS", this.getHostingOS());
        pathPrefix = pathPrefix.replaceAll("hostingApp", this.getHostingApp());
        prefix = pathPrefix;
    }

    private String getTestName() throws JSONException {
        return this.testData.getJSONObject("startInfo").optString("scenarioIdOrName");
    }

    private String getAppName() throws JSONException {
        return this.testData.getJSONObject("startInfo").optString("appIdOrName");
    }

    private String getViewportSize() throws JSONException {
        return this.testData.getJSONObject("startInfo").getJSONObject("environment").getJSONObject("displaySize")
                .optString("width") + "x"
                + this.testData.getJSONObject("startInfo").getJSONObject("environment").getJSONObject("displaySize")
                .optString("height");
    }

    private String getHostingOS() throws JSONException {
        return this.testData.getJSONObject("startInfo").getJSONObject("environment").optString("os");
    }

    private String getHostingApp() throws JSONException {
        return this.testData.getJSONObject("startInfo").getJSONObject("environment").optString("hostingApp");
    }

    private static class GifSequenceWriter {
        protected ImageWriter gifWriter;
        protected ImageWriteParam imageWriteParam;
        protected IIOMetadata imageMetaData;

        /**
         * Creates a new GifSequenceWriter
         *
         * @param outputStream        the ImageOutputStream to be written to
         * @param imageType           one of the imageTypes specified in BufferedImage
         * @param timeBetweenFramesMS the time between frames in miliseconds
         * @param loopContinuously    wether the gif should loop repeatedly
         * @throws IIOException if no gif ImageWriters are found
         * @author Elliot Kroo (elliot[at]kroo[dot]net)
         */
        private GifSequenceWriter(ImageOutputStream outputStream, int imageType, int timeBetweenFramesMS,
                                  boolean loopContinuously) throws IOException {
            // my method to create a writer
            gifWriter = getWriter();
            imageWriteParam = gifWriter.getDefaultWriteParam();
            ImageTypeSpecifier imageTypeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(imageType);

            imageMetaData = gifWriter.getDefaultImageMetadata(imageTypeSpecifier, imageWriteParam);

            String metaFormatName = imageMetaData.getNativeMetadataFormatName();

            IIOMetadataNode root = (IIOMetadataNode) imageMetaData.getAsTree(metaFormatName);

            IIOMetadataNode graphicsControlExtensionNode = getNode(root, "GraphicControlExtension");

            graphicsControlExtensionNode.setAttribute("disposalMethod", "none");
            graphicsControlExtensionNode.setAttribute("userInputFlag", "FALSE");
            graphicsControlExtensionNode.setAttribute("transparentColorFlag", "FALSE");
            graphicsControlExtensionNode.setAttribute("delayTime", Integer.toString(timeBetweenFramesMS / 10));
            graphicsControlExtensionNode.setAttribute("transparentColorIndex", "0");

            IIOMetadataNode commentsNode = getNode(root, "CommentExtensions");
            commentsNode.setAttribute("CommentExtension", "Created by MAH");

            IIOMetadataNode appEntensionsNode = getNode(root, "ApplicationExtensions");

            IIOMetadataNode child = new IIOMetadataNode("ApplicationExtension");

            child.setAttribute("applicationID", "NETSCAPE");
            child.setAttribute("authenticationCode", "2.0");

            int loop = loopContinuously ? 0 : 1;

            child.setUserObject(new byte[]{0x1, (byte) (loop & 0xFF), (byte) ((loop >> 8) & 0xFF)});
            appEntensionsNode.appendChild(child);

            imageMetaData.setFromTree(metaFormatName, root);

            gifWriter.setOutput(outputStream);

            gifWriter.prepareWriteSequence(null);
        }

        /**
         * Returns the first available GIF ImageWriter using
         * ImageIO.getImageWritersBySuffix("gif").
         *
         * @return a GIF ImageWriter object
         * @throws IIOException if no GIF image writers are returned
         */
        private static ImageWriter getWriter() throws IIOException {
            Iterator<ImageWriter> iter = ImageIO.getImageWritersBySuffix("gif");
            if (!iter.hasNext()) {
                throw new IIOException("No GIF Image Writers Exist");
            } else {
                return iter.next();
            }
        }

        /**
         * Returns an existing child node, or creates and returns a new child node (if
         * the requested node does not exist).
         *
         * @param rootNode the <tt>IIOMetadataNode</tt> to search for the child node.
         * @param nodeName the name of the child node.
         * @return the child node, if found or a new node created with the given name.
         */
        private static IIOMetadataNode getNode(IIOMetadataNode rootNode, String nodeName) {
            int nNodes = rootNode.getLength();
            for (int i = 0; i < nNodes; i++) {
                if (rootNode.item(i).getNodeName().compareToIgnoreCase(nodeName) == 0) {
                    return ((IIOMetadataNode) rootNode.item(i));
                }
            }
            IIOMetadataNode node = new IIOMetadataNode(nodeName);
            rootNode.appendChild(node);
            return (node);
        }

        /**
         * public GifSequenceWriter( BufferedOutputStream outputStream, int imageType,
         * int timeBetweenFramesMS, boolean loopContinuously) {
         */
        public static void main(String[] args) throws Exception {
            if (args.length > 1) {
                // grab the output image type from the first image in the sequence
                BufferedImage firstImage = ImageIO.read(new File(args[0]));

                // create a new BufferedOutputStream with the last argument
                ImageOutputStream output = new FileImageOutputStream(new File(args[args.length - 1]));

                // create a gif sequence with the type of the first image, 1 second
                // between frames, which loops continuously
                GifSequenceWriter writer = new GifSequenceWriter(output, firstImage.getType(), 1, false);

                // write out the first image to our sequence...
                writer.writeToSequence(firstImage);
                for (int i = 1; i < args.length - 1; i++) {
                    BufferedImage nextImage = ImageIO.read(new File(args[i]));
                    writer.writeToSequence(nextImage);
                }

                writer.close();
                output.close();
            } else {
                System.out.println("Usage: java GifSequenceWriter [list of gif files] [output file]");
            }
        }

        private void writeToSequence(RenderedImage img) throws IOException {
            gifWriter.writeToSequence(new IIOImage(img, null, imageMetaData), imageWriteParam);
        }

        /**
         * Close this GifSequenceWriter object. This does not close the underlying
         * stream, just finishes off the GIF.
         */
        public void close() throws IOException {
            gifWriter.endWriteSequence();
        }
    }
}