package in.astro.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {
	private JdbcUtil() {
		
	}
	static {
		// Step 1: load and register the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException, IOException {
		HikariConfig config = new HikariConfig("src\\in\\astro\\properties\\application.properties");
		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource.getConnection();
	}
	public static void cleanUp(Connection con,ResultSet resultset,Statement statement) throws SQLException {
		if(resultset!=null) resultset.close();
		if(statement!=null) statement.close();
		if(con!=null) con.close();
	}
}