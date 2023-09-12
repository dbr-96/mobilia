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
		ArrayList <Object[]> contracts = ContractDAO.getContractByParam(myConnection, contractParam);
		JSONArray jsonContracts = new JSONArray();
	
		String contractCode = "";
		JSONObject jsonContract = null;
		for (Object[] contract : contracts) {
			String currentContractCode = (String)contract[0];
			if(!contractCode.equals(currentContractCode)) {
				contractCode = currentContractCode;
				jsonContract = new JSONObject();
				jsonContract.put("contractCode", contract[0]);
				jsonContract.put("state", contract[1]);
				jsonContract.put("address", contract[2]);
				jsonContract.put("type", contract[3]);
				jsonContract.put("proprietor", new JSONArray());				
				jsonContract.put("lessee", new JSONArray());				
				jsonContract.put("solidaryDebtor", new JSONArray());				
				jsonContracts.put(jsonContract);
			} 
			String personRole = (String) contract[4];
			JSONArray personsByRole = (JSONArray) jsonContract.get(personRole);	
			String personFullName = (String) contract[5] + (contract[6] != null ? " " +(String) contract[6]
					: "") + " " + (String) contract[7] + " " + (String) contract[8];
			personsByRole.put(personFullName);		
			System.out.println(personsByRole.toString());
		}
		
		myConnection.close();
		return jsonContracts;
	}
}
