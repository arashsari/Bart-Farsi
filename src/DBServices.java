import java.sql.*;

class DBServices
{
	static String dbUrl = "jdbc:odbc:BART";
	static String user = "";
	static String password = "";

	static Connection c;

	public static Connection getConnection() throws Exception
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

		if (c == null) c = DriverManager.getConnection(dbUrl, user, password);

		return c;
	}
}