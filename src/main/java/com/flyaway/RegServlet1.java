package com.flyaway;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class RegServlet1 extends HttpServlet{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String p =request.getParameter("user_pass");
		String cp =request.getParameter("user_cpass");
		
		
		try {Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/flyaway","root","password");
		 String q= "insert into registration(name,email,dob,password) values(?,?,?,?)";
		 PreparedStatement pstmt= con.prepareStatement(q);
		 pstmt.setString(1, request.getParameter("user_name"));
		 pstmt.setString(2, request.getParameter("user_email"));
		 pstmt.setString(3, request.getParameter("user_dob"));
		 pstmt.setString(4, request.getParameter("user_pass"));
		 if (p.equals(cp)) {
			 pstmt.executeUpdate();
			out.println("<center><h2>Thank You For Registration</h2><center>");
			RequestDispatcher rd=request.getRequestDispatcher("Login.html");
			rd.include(request, response);
		} else {
			out.println("<h2><center>Password not equal so Try Again</center></h2>");
			RequestDispatcher rd=request.getRequestDispatcher("NewRegistration.html");
			rd.include(request, response);
			
		}
		
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		out.println("<h3>"+ e +"</h3>");
		out.println("<h2><center>you are all ready register</center></h2>");
		RequestDispatcher rd=request.getRequestDispatcher("Login.html");
		rd.include(request, response);
		}
	
}
}