package com.sw.core.database;

import com.sw.core.helpers.ConsoleLog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccessorBase extends DBConn {

    protected String siudStatement = null;
    protected String fromStatement = null;
    protected String whereClause = null;
    protected Statement userStatement = null;
    protected boolean isUpdate = false;

    public DBAccessorBase() {
        super();
    }

    protected void setSIUDStatement(String siudStatement) {
        this.siudStatement = siudStatement;
    }

    protected void setFROMStatement(String fromStatement) {
        this.fromStatement = fromStatement;
    }

    protected void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    protected void setStatement(Statement statement) {
        this.statement = statement;
    }

    public boolean execute() {
        try {
            if (this.userStatement == null) {
                setUpAutomationConnection();
            }

            String sql = this.siudStatement + this.fromStatement + this.whereClause;
            ConsoleLog.info(sql);
            if (!this.isUpdate) {
                if (statement.execute(sql)) {
                    ResultSet result = statement.getResultSet();
                    return processResult(result);
                } else {
                    return false;
                }
            } else {
                return statement.executeUpdate(sql) > -1;
            }
        } catch (ClassNotFoundException ignored) {

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.commit();
                conn.close();
            } catch (SQLException ignored) {
            }
        }
        return false;
    }

    protected boolean processResult(ResultSet resultSet) throws SQLException {
        return false;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate() {
        this.isUpdate = true;
    }
}