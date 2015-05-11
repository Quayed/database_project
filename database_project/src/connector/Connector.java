package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import daointerfaces.DALException;

public class Connector {

	private static Connector instance;
	
	private Connection conn;
	private Statement stm;
	
	
	
	/**
	 * To connect to a MySQL-server
	 * 
	 * @param url
	 *            must have the form "jdbc:mysql://<server>/<database>" for
	 *            default port (3306) OR
	 *            "jdbc:mysql://<server>:<port>/<database>" for specific port
	 *            more formally "jdbc:subprotocol:subname"
	 * 
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SQLException
	 */
	
	private static Connector getInstance() throws SQLException {
		if(instance == null)
			instance = new Connector();
		return instance;
	}
	
	public static Connection connectToDatabase(String url, String username, String password) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		// call the driver class' no argument constructor
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		// get Connection-object via DriverManager
		return (Connection) DriverManager.getConnection(url, username, password);
	}

	private Connector(String server, int port, String database, String username, String password) throws SQLException{
		try {
			conn = connectToDatabase("jdbc:mysql://" + server + ":" + port + "/" + database, username, password);
			stm = conn.createStatement();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			throw new SQLException(e);
		}
	}

	private Connector() throws SQLException {
		this(Constant.server, Constant.port, Constant.database, Constant.username, Constant.password);
	}

	public static void close()  throws SQLException {
		getInstance().conn.close();
	}
	
	public static ResultSet doQuery(String cmd) throws DALException {
		try {
			return getInstance().stm.executeQuery(cmd);
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	public static int doUpdate(String cmd) throws DALException {
		try {
			return getInstance().stm.executeUpdate(cmd);
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	public static PreparedStatement prepare(String sql) throws SQLException {
		return getInstance().conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	}
	
	public static int getLastInsert(PreparedStatement ps) throws SQLException {
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		return rs.getInt(1);		
	}

	// only for test
	public static void setAutoIncrement(String table, int autoIncrement) throws SQLException{
		getInstance().conn.createStatement().execute("ALTER TABLE "+table+" AUTO_INCREMENT = "+autoIncrement);
	}
}