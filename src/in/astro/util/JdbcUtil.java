package in.astro.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {

	public static Connection getConnection() throws SQLException, IOException {
		FileInputStream fis = new FileInputStream("src\\in\\astro\\properties\\application.properties");
		Properties properties = new Properties();
		properties.load(fis);
		Connection con = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("username"),properties.getProperty("password"));
		// Create statement object and execute query
		return con;
	}
	public static void cleanUp(Connection con,ResultSet resultset,Statement statement) throws SQLException {
		if(resultset!=null) resultset.close();
		if(statement!=null) statement.close();
		if(con!=null) con.close();
	}
}