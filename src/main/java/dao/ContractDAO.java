package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContractDAO {

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobilia", "root", "");
			return myConnection;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Object[] getContractByParam(Connection myConnection, String contractParam) throws SQLException {
		boolean receivedConection = true;

		if (myConnection == null) {
			myConnection = getConnection();
			receivedConection = false;
		}
		String query = "SELECT state FROM contracts WHERE contractCode = ?";
		try {
			PreparedStatement myStatement = myConnection.prepareStatement(query);
			myStatement.setString(1, contractParam);
			ResultSet myResultSet = myStatement.executeQuery();

			Object[] stateData = null;
			while (myResultSet.next()) {
				String state = myResultSet.getString("state");
				stateData = new Object [] {state};
			}
			myResultSet.close();
			myStatement.close();
			return stateData;
		} finally {
			if (!receivedConection) {
				myConnection.close();
			}
		}
	}
}
