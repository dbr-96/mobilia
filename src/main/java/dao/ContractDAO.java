package dao;

import java.sql.Connection;
import java.sql.DriverManager;

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
}
