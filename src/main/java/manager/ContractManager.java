package manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.ContractDAO;

public class ContractManager {

	public static JSONArray getContractByParam (String contractParam) throws SQLException {
		Connection myConnection = ContractDAO.getConnection();
//		JSONObject state = new JSONObject();
		ArrayList <Object[]> contracts = ContractDAO.getContractByParam(myConnection, contractParam);
		JSONArray jsonContracts = new JSONArray();
		
		for (Object[] contract : contracts) {
			JSONObject jsonContract = new JSONObject();
			jsonContract.put("contractCode", contract[0]);
			jsonContract.put("state", contract[1]);
			jsonContract.put("address", contract[2]);
			jsonContract.put("role", contract[3]);
			jsonContract.put("firstName", contract[4]);
			jsonContract.put("secondName", contract[5]);
			jsonContract.put("lastName", contract[6]);
			jsonContract.put("secondLastName", contract[7]);
			jsonContracts.put(jsonContract);
		}
		
		myConnection.close();
		return jsonContracts;
	}
}
