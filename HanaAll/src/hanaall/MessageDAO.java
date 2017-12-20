package hanaall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 * Data access object encapsulating all JDBC operations for a person.
 */
public class MessageDAO {
	private DataSource dataSource;
	private String DEVICE_D = "4b1287da-2ef7-4650-88cb-5209f8a4ad16";
	private String DEVICE_D1 = "4f8e0630-042a-46ea-9dfa-30daf6f9cda1";
	/**
	 * Create new data access object with data source.
	 */
	public MessageDAO(DataSource newDataSource) throws SQLException {
		setDataSource(newDataSource);
	}

	/**
	 * Get data source which is used for the database operations.
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Set data source to be used for the database operations.
	 */
	public void setDataSource(DataSource newDataSource) throws SQLException {
		this.dataSource = newDataSource;
		// checkTable();
	}

	/**
	 * Get all persons from the table.
	 */
	public List<Message> selectAllMessageFromDevice() throws SQLException {
		final String tableName = "T_IOT_" + Message.MESSAGE_TYPE;
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement pstmt = connection
					.prepareStatement("SELECT G_DEVICE, G_CREATED, C_DATO " + " FROM " + tableName);
			ResultSet rs = pstmt.executeQuery();
			ArrayList<Message> list = new ArrayList<Message>();
			while (rs.next()) {
				Message mex = new Message();
				mex.setDevice(rs.getString(1));
				mex.setSysTimestamp(rs.getDate(2));
				mex.setDato(rs.getInt(3));
				list.add(mex);
			}
			return list;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public List<Message> selectAllFromDevice_D() throws SQLException {
		//device id 4b1287da-2ef7-4650-88cb-5209f8a4ad16 (DEVICE_D)
		final String tableName = "T_IOT_" + Message.MESSAGE_TYPE;
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement pstmt = connection
					.prepareStatement("SELECT G_DEVICE, G_CREATED, C_DATO " + " FROM " + tableName + 
									" WHERE G_DEVICE='" + DEVICE_D + "'");
			ResultSet rs = pstmt.executeQuery();
			ArrayList<Message> list = new ArrayList<Message>();
			while (rs.next()) {
				Message mex = new Message();
				mex.setDevice(rs.getString(1));
				mex.setSysTimestamp(rs.getDate(2));
				mex.setDato(rs.getInt(3));
				list.add(mex);
			}
			return list;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public List<Message> selectAllFromDevice_D1() throws SQLException {
		//device id 4f8e0630-042a-46ea-9dfa-30daf6f9cda1 (DEVICE_D1)
		final String tableName = "T_IOT_" + Message.MESSAGE_TYPE;
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement pstmt = connection
					.prepareStatement("SELECT G_DEVICE, G_CREATED, C_DATO " + " FROM " + tableName + 
									" WHERE G_DEVICE='" + DEVICE_D1 + "'");
			ResultSet rs = pstmt.executeQuery();
			ArrayList<Message> list = new ArrayList<Message>();
			while (rs.next()) {
				Message mex = new Message();
				mex.setDevice(rs.getString(1));
				mex.setSysTimestamp(rs.getDate(2));
				mex.setDato(rs.getInt(3));
				list.add(mex);
			}
			return list;
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	/**
	 * Check if the person table already exists and create it if not.
	 */
	@SuppressWarnings("unused")
	private void checkTable() throws SQLException {
		Connection connection = null;

		try {
			// SE VUOI FARE QUALCHE QUERY PREPARATORIA
			connection = dataSource.getConnection();
			PreparedStatement pstmt = connection.prepareStatement("SELECT * ....");
			pstmt.executeUpdate();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
}
