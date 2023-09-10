package manager;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONObject;

import dao.ContractDAO;

public class ContractManager {

	public static JSONObject getContractByParam (String contractParam) throws SQLException {
		JSONObject state = new JSONObject();
		Connection myConnection = ContractDAO.getConnection();
		
		Object [] stateData = ContractDAO.getContractByParam(myConnection, contractParam);
		state.put("stateData", stateData[0]);
		myConnection.close();
		return state;
	}
}
