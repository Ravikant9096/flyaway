package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import org.apache.catalina.connector.Response;



public class Insert_Route extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/flyaway","root","password");
			String q= ("insert into route(route_id,source_city,des_city) values( ?, ?, ?)");
			PreparedStatement pstmt= con.prepareStatement(q);
			pstmt.setString(1, request.getParameter("route_id"));
			pstmt.setString(2, request.getParameter("source_city"));
			pstmt.setString(3,request.getParameter("des_city"));
			pstmt.executeUpdate();
			out.println("DATA INSERTED SUCESSFUL");
			RequestDispatcher rd =request.getRequestDispatcher("admin.html");
			rd.include(request, response);
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
