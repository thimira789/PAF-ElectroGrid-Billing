package com;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BillsAPI")
public class BillsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Bill billingObj = new Bill();
	
    public BillsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
			
			String output = billingObj.insertbill (request.getParameter("billCName"),
			request.getParameter("billAccNO"),
			request.getParameter("billDate"),
			request.getParameter("billUnits"),
			request.getParameter("billAmount"));
			response.getWriter().write(output);
	}
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			
			for (String param : params)
			{
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
			
		}
		catch (Exception e)
		{
		}
		return map;
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Map paras = getParasMap(request);
		
		String output = billingObj.updateBill(
						paras.get("hidItemIDSave").toString(),
						paras.get("billCName").toString(),
						paras.get("billAccNO").toString(),
						paras.get("billDate").toString(),
						paras.get("billUnits").toString(),
						paras.get("billAmount").toString());
		
		response.getWriter().write(output);
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Map paras = getParasMap(request);
		
		String output = billingObj.deleteBill(paras.get("billID").toString());
		
		response.getWriter().write(output);
	}
}
