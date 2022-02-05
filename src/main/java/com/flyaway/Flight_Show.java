package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.PostConstruct;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Flight_Show extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
				
		// data base 
		try {Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/flyaway","root","password");
		String q1= ("select * from enquiry order by id desc limit 1");
		PreparedStatement pstmt1= con.prepareStatement(q1);
		ResultSet rs1=pstmt1.executeQuery();
		int user_id=0;
		String user_scity=null;
		String user_dcity=null;
		String user_date=null;
		int user_no=0;
		String user_rid=null;
		while(rs1.next()) {
			
			user_id =rs1.getInt(1);
			user_scity =rs1.getString(2);
			user_dcity =rs1.getString(3);
			user_date =rs1.getString(4);
			user_no=rs1.getInt(5);
			user_rid=rs1.getString(6);
			// it is optional if we want print user detail on web-page.
			//out.println(user_id+"    " +user_scity+"   "+user_dcity+"     "+user_date+"     "+user_no+ "  "+user_rid+"<br>");
			}
		
		
		
		
		String q= ("select * from flight where route_id= ?");
		 PreparedStatement pstmt= con.prepareStatement(q);
		 pstmt.setString(1, user_rid);
		 ResultSet rs=pstmt.executeQuery();
		 
		     out.println("<center>");
		     out.println("<table border=1 width=50% height=50%>");
		     out.println("<form action=\"Flight_Price\" method=\"post\">" );
			 out.println("<h1>");
			 out.println("<table id = \"main_section\" cellspacing=\"4\" bgcolor=\"yellow\" > ");
			 out.println ("<tr> ");
			 out.println("<td >FLIGHT NUMBER</td>");
			 out.println("<td >PRICE</td>");
			 out.println("<td >AVAILABLE SEATS</td>");
			 out.println("<td >ARIVAL TIME </td>");
			 out.println("<td >DEPARTURE TIME </td>");
			 //out.println("<td >PLEASE SELECT AND <H3>TYPE ITS IN BOX</H3></td>");
			 out.println("</tr>");
			 while(rs.next())  
			 {  
				 out.println("<tr>");
				 out.println("<center>");
				 String val=rs.getString(1);
				 String val1=rs.getString(2);
				 String val2=rs.getString(3);
				 String val3=rs.getString(4);
				 String val4=rs.getString(5);
			     out.println("<h2><td > " + val + "</td>"+"<td > " + val1 + "</td>"+"<td > " 
				 + val2 + "</td>"+"<td > " + val3 + "</td>"+"<td > " + val4 + "</td></h2");
			     
			     
			     out.println("</center>"); 
			     out.println("</tr>");
			     
			     	
			      
			 }
			     out.println("</tr>");
			     out.println("</form>");
			     out.println("</center>");
			     //out.println("<button>SUBMIT</button>");
			     
			     
			     
			 
			     RequestDispatcher rd=request.getRequestDispatcher("FlightP1.html");
				 rd.include(request, response);
			 
		 
						
				 pstmt.close();	 
		}
		
		
		catch (Exception e) {
		
		e.printStackTrace();
		out.println("<h1>"+ e +"</h1>");
		
		}
		
}
}