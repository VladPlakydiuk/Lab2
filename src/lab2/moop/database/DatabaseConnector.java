package lab2.moop.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnector {

    private final Connection dbConnection;
    private final Statement dbStatement;

    public DatabaseConnector(final String DBName, final String ip, int port) throws Exception {
        final String url = "jdbc:mysql://" + ip + ":" + port + "/" + DBName;
        dbConnection = DriverManager.getConnection(url, "root", "password");
        dbStatement = dbConnection.createStatement();
    }

    public Statement getDbStatement() {
        return this.dbStatement;
    }

    public void closeConnection() throws Exception {
        dbConnection.close();
    }
}
