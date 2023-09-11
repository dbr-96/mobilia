package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	public static ArrayList <Object[]> getContractByParam(Connection myConnection, String contractParam) throws SQLException {
		boolean receivedConection = true;

		if (myConnection == null) {
			myConnection = getConnection();
			receivedConection = false;
		}
		String query = "SELECT "
				+ "co.contractCode, "
				+ "co.state, "
				+ "pr.address, "
				+ "pr.type, "
				+ "cbp.role, "
				+ "pe.firstName, "
				+ "pe.secondName, "
				+ "pe.lastName, "
				+ "pe.secondLastName "
				+ "FROM "
				+ "persons pe "
				+ "LEFT JOIN contractbyperson cbp ON "
				+ "pe.personId = cbp.personId "
				+ "LEFT JOIN contracts co ON "
				+ "co.contractId = cbp.contractId "
				+ "LEFT JOIN properties pr ON "
				+ "pr.propertyId = co.propertyId "
				+ "WHERE "
				+ "pe.firstName LIKE ? OR pe.secondName LIKE ? OR pe.lastName LIKE ? OR "
				+ "pe.secondLastName LIKE ? OR pe.identityDocument LIKE ? OR pe.email LIKE ? OR "
				+ "pr.address LIKE ? OR co.contractCode LIKE ? "
				+ "ORDER BY co.contractCode DESC ";
				
		try {
			PreparedStatement myStatement = myConnection.prepareStatement(query);
			for (int i = 1; i <= 8; i++) {
				myStatement.setString(i, contractParam);	
			}
			
			ResultSet myResultSet = myStatement.executeQuery();

			ArrayList <Object[]> contracts = new ArrayList<>();
			Object[] contractData = null;
			while (myResultSet.next()) {
				String contractCode = myResultSet.getString("contractCode");
				String state = myResultSet.getString("state");
				String address = myResultSet.getString("address");
				String type = myResultSet.getString("type");
				String role = myResultSet.getString("role");
				String firstName = myResultSet.getString("firstName");
				String secondName = myResultSet.getString("secondName");
				String lastName = myResultSet.getString("lastName");
				String secondLastName = myResultSet.getString("secondLastName");
				contractData = new Object [] {contractCode,state, address, type, role, 
						firstName, secondName, lastName, secondLastName};
				contracts.add(contractData);
			}
			myResultSet.close();
			myStatement.close();
			return contracts;
		} finally {
			if (!receivedConection) {
				myConnection.close();
			}
		}
	}
}
