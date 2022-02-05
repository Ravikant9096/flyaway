package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet1 extends HttpServlet{
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out =response.getWriter();
		response.setContentType("text/html");
		String email =request.getParameter("user_email");
		String pass =request.getParameter("user_pass");
		String apass ="admin";
		try {Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/flyaway","root","password");
		 String q= ("select * from registration where email=?");
		 PreparedStatement pstmt= con.prepareStatement(q);
		 pstmt.setString(1, email);
		 ResultSet rs=pstmt.executeQuery();
		 
		 while(rs.next())  
		 {  
			 String pass2=rs.getString("password");
			 
			 if(pass.equals(pass2)) {
				 if(pass.equals(apass)){
					 out.println("<CENTER><h2>WELCOME" + email +"</h2></CENTER>");
					 RequestDispatcher rd=request.getRequestDispatcher("admin.html");
						rd.include(request, response);
					 
				 }else {
					 out.println("<CENTER><h2>Login Succesfull</h2>");
					 out.println("<h3>WELCOME "+ email +"</h3></CENTER>");
					 	RequestDispatcher rd=request.getRequestDispatcher("flight.html");
						rd.include(request, response);
				}
				 	
					}else {
						 out.println("<CENTER><h2>Login not Succesfull</h2>");
						 out.println("<h3>TRY AGAIN "+ email +"</h3></CENTER>");
						 	RequestDispatcher rd=request.getRequestDispatcher("Login.html");
							rd.include(request, response);
					}
			
		 }
		
		
		} catch (Exception e) {
			
		e.printStackTrace();
		out.println("<h1>"+ e +"</h1>");
		
		}
	}


}
