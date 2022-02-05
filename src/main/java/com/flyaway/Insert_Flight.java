package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Insert_Flight extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
				
		// data base 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/flyaway","root","password");
		String q= ("insert into flight(flight_no,price,seats,ari_time,dep_time,route_id) values( ?, ?, ?, ?, ?, ?)");
		PreparedStatement pstmt= con.prepareStatement(q);
		pstmt.setString(1, request.getParameter("flight_no"));
		pstmt.setString(2, request.getParameter("price"));
		pstmt.setString(3, request.getParameter("seats"));
		pstmt.setString(4, request.getParameter("ari_time"));
		pstmt.setString(5, request.getParameter("dep_time"));
		pstmt.setString(6, request.getParameter("route_id"));
		
		pstmt.executeUpdate();
		out.println("DATA INSERTED SUCESSFUL");
		RequestDispatcher rd= request.getRequestDispatcher("insert_flight.html");
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
