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
	
	/**
	* Manages HTTP GET requests. This method is responsible for processing GET requests
	* and take specific actions based on the parameters received.
	*
	* @param request The incoming HTTP request.
	* @param response The HTTP response to send to the client.
	* @throws IOException If an I/O error occurs when interacting with the request or response.
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		
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
	
	/**
	 * Retrieves contracts based on a parameter and sends a JSON response to the client.
	 *
	 * This method takes an HTTP request containing a "contractParam" parameter,
	 * uses that parameter to search for contracts and sends a JSON response to the client
	 * which contains the information of the contracts found.
	 *
	 * @param request The incoming HTTP request containing the "contractParam" parameter.
	 * @param response The HTTP response that will be used to send the JSON response to the client.
	 * @throws IOException If an error occurs while handling input/output.
	 * @throws SQLException If an error occurs while interacting with the database.
	 */
	public void getContractByParam (HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
	    String contractParam = request.getParameter("contractParam");
	   
	    JSONArray contracts = ContractManager.getContractByParam(contractParam);
	    JSONObject jsonResponse = new JSONObject();
	    jsonResponse.put("success", true);
	    jsonResponse.put("contracts", contracts);
	    
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    PrintWriter out = response.getWriter();
	    out.print(jsonResponse.toString());
	    out.flush();
	}
}