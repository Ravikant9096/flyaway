package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.mysql.cj.exceptions.RSAException;
public class HomeServlet extends HttpServlet {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	
	response.setContentType("text/html");
	PrintWriter out=response.getWriter();
	
	try {Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/flyaway","root","password");
	 String user_rid1=null;
     String q= "insert into enquiry(s_city,d_city,t_date,no_trav,route_id) values(?, ?, ?, ?, ?)";
	 
	 PreparedStatement pstmt= con.prepareStatement(q);
	 
	 pstmt.setString(1, request.getParameter("user_scity"));
	 pstmt.setString(2, request.getParameter("user_dcity"));
	 pstmt.setString(3, request.getParameter("user_date"));
	 pstmt.setString(4, request.getParameter("user_no"));
	 

	 
	 String q1="select route_id from route where source_city=? && des_city=?";
	 PreparedStatement pstmt1= con.prepareStatement(q1);
	 
	 
	 
	 pstmt1.setString(1, request.getParameter("user_scity"));
	 pstmt1.setString(2, request.getParameter("user_dcity"));
	 
	 
	 
	 
	 
	 ResultSet rs1=pstmt1.executeQuery(); 
		 	 
	 
	 
	 
	 		while(rs1.next())  
	 			{  
	 				user_rid1=rs1.getString("route_id");
		
		 
		//add cookie
	 					Cookie c= new Cookie("user_rid1",user_rid1);
	 					response.addCookie(c);
				
					
	 			}
	 pstmt.setString(5, user_rid1);
	 pstmt.executeUpdate();
	 pstmt.close(); 
			} catch (Exception e) {
		// TODO: handle exception
										e.printStackTrace();
										out.println("<h1>"+ e +"</h1>");
	
									}
	
	 	RequestDispatcher rd1=request.getRequestDispatcher("NewRegistration.html");
		rd1.include(request, response);
}
}
