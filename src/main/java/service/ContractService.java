package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import manager.ContractManager;


public class ContractService extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String action = request.getParameter("action");

		if ("getContractByParam".equals(action)) {
			try {
				getContractByParam(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getContractByParam (HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
	    String contractParam = request.getParameter("contractParam");
	    
	    
	    JSONArray contracts = ContractManager.getContractByParam(contractParam);
	    JSONObject jsonResponse = new JSONObject();
	    jsonResponse.put("success", true);
	    jsonResponse.put("Sending response...", contracts);
	    
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    PrintWriter out = response.getWriter();
	    out.print(jsonResponse.toString());
	    out.flush();
	}

}