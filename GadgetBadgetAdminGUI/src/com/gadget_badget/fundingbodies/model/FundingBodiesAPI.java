package com.gadget_badget.fundingbodies.model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


@WebServlet("/FundingBodiesAPI")
public class FundingBodiesAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FundingBodiesServlet FundingBodiesObj = new FundingBodiesServlet();
	
	public FundingBodiesAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		String outputString = FundingBodiesObj.insertFundingBodies(request.getParameter("FundingBodiesCode"), 
				request.getParameter("customerID"),
				request.getParameter("customerEmail"), 
				request.getParameter("customerName"), 
				request.getParameter("totalAmount"),
				request.getParameter("cardNo"),
				request.getParameter("cvvNo"));

		response.getWriter().write(outputString);
	}	

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map paras = getParasMap(request);

		String outputString = FundingBodiesObj.updateFundingBodies(
				paras.get("FundingBodiesID").toString(),
				paras.get("FundingBodiesCode").toString(),
				paras.get("customerID").toString(), 
				paras.get("customerEmail").toString(), 
				paras.get("customerName").toString(),
				paras.get("totalAmount").toString(),
				paras.get("cardNo").toString(),
				paras.get("cvvNo").toString()); 

		response.getWriter().write(outputString);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = FundingBodiesObj.deleteFundingBodies(paras.get("FundingBodiesID").toString());
		response.getWriter().write(output); 		
	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		try {			
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
			
		} catch (Exception e) {
		  }
		
		return map;
	}
}
