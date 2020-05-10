package projectmanager.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import projectmanager.constants.DatabaseProps;
import projectmanager.dao.DatabaseConnection;


public class DatabaseUtils {
    public static boolean isDatabaseCreated(String path) {
        return DatabaseUtils.class.getResource(path) != null;
    }
    
    public static boolean isDatabaseCreated() {
        return isDatabaseCreated(DatabaseProps.defaultDatabasePath);
    }
    
    public static boolean createNewSQLiteDatabase(String path) {
        DatabaseMetaData meta = null;
        
        try {
            DatabaseConnection dbConn = new DatabaseConnection(path);
            Connection conn = dbConn.getConnectionObject();
            
            if (conn != null) {
                meta = conn.getMetaData();
                conn.close();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
        
        return meta != null;
    }
    
    public static boolean createNewSQLiteDatabase() {
        return createNewSQLiteDatabase(DatabaseProps.sqliteConnectionString);
    }
    
    public static boolean createNewSQLiteDatabaseFile() {
        URL url = DatabaseUtils.class.getResource("/");
        File folder = null;
        
        try {
            folder = new File(url.toURI());
        } catch (URISyntaxException ex) {
            return false;
        }
        
        String absolute = folder.getAbsolutePath() + File.separator + DatabaseProps.sqliteDatabasePath;
        
        return createNewSQLiteDatabaseFile(absolute);
    }
    
    public static boolean createNewSQLiteDatabaseFile(String path) {
        File dbFile = new File(path);
        
        return !dbFile.exists();
    }
    
    public static boolean initDatabaseFromResources(String templatePath) throws ClassNotFoundException, SQLException, URISyntaxException, IOException {
        InputStream stream = DatabaseUtils.class.getResourceAsStream(templatePath);
        
        if (stream == null || DatabaseProps.defaultConnectionString == null) {
            return false;
        }
        
        DatabaseConnection dbConnection = new DatabaseConnection(DatabaseProps.defaultConnectionString);
        
        Connection connection = dbConnection.getConnectionObject();
        
        Scanner scanner = new Scanner(stream);
        StringBuilder builder = new StringBuilder();
        
        while(scanner.hasNext()){
           builder.append(scanner.nextLine());
        }
        
        Statement stmt = connection.createStatement();
        
        String content = builder.toString();
        
        stmt.executeUpdate(content);
        
        connection.close();
        
        return true;
    }
}
