package com.sw.core.database;

import java.sql.*;
import java.util.TimeZone;


public class DBConn {

    Connection conn = null;
    Statement statement = null;

    public DBConn() {
    }

    //Connect to Automation database
    public void setUpAutomationConnection() throws SQLException, ClassNotFoundException {

        Class.forName("oracle.jdbc.driver.OracleDriver");
        if (this.conn == null || this.conn.isClosed()) {
            TimeZone timeZone = TimeZone.getTimeZone("America/New_York");
            TimeZone.setDefault(timeZone);
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@excd-scan.sherwin.com:1521/SWWEBD", "AUTOMATION", "auT0mat10n");
            this.statement = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }
    }
}