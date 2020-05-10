package projectmanager.constants;

public class DatabaseProps {
    public static final String sqliteDatabasePath = "project-manager-sqlite-database.db";
    public static final String sqliteConnectionString = "jdbc:sqlite:" + sqliteDatabasePath;
    public static final String defaultConnectionString = sqliteConnectionString;
    public static final String defaultDatabasePath = sqliteDatabasePath;
    public static final String templatePath = "/database/template.sql";
}
