package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		
		String crud= request.getParameter("admin");
		String rid= request.getParameter("route_id");
		String scity= request.getParameter("route_scity");
		String dcity= request.getParameter("route_dcity");
		out.println("fucution u want "+ crud +"route Id "+ rid +"Source City "+ scity+" Destination City"+ dcity);
		
		try {Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/flyaway","root","password");
		 if(crud.equals("create")) {
			 
		String q= "insert into route(route_id,source_city,des_city) values(?,?,?)";
		 PreparedStatement pstmt= con.prepareStatement(q);
		 pstmt.setString(1, rid);
		 pstmt.setString(2, scity);
		 pstmt.setString(3, dcity);
		 pstmt.executeUpdate();
		 out.println("<h3>DATA INSERTED SUCCESSFULLY</h3>");
		 RequestDispatcher rd=request.getRequestDispatcher("admin.html");
			rd.include(request, response);
		 }
		 if(crud.equals("read")) {
			 
			out.println("<h1>Thank You For Read</h1>");
			RequestDispatcher rd=request.getRequestDispatcher("admin.html");
			rd.include(request, response);
		 }
		 
			/*
			 * else {
			 * out.println("<h5><marquee>Password not equal so Try Again</marquee></h5>");
			 * RequestDispatcher rd=request.getRequestDispatcher("NewRegistration.html");
			 * rd.include(request, response);
			 * 
			 * }
			 */
		} catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		out.println("<h4>"+ e +"</h4>");
		
		RequestDispatcher rd=request.getRequestDispatcher("admin.html");
		rd.include(request, response);
		}
	}

}
