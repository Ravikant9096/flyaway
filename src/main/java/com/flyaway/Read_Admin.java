package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Read_Admin extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/flyaway","root","password");
			//String q= ("select * from (?)");
		//	PreparedStatement pstmt= con.prepareStatement(q);
			
			String table =request.getParameter("table");
			//out.println("Read_Admin ="+ table);
			//pstmt.setString(1, table);
			RequestDispatcher rd =request.getRequestDispatcher("admin.html");
			rd.include(request, response);
			
			if(table.equals("booking1")) {
				String q= ("select * from booking1");
				PreparedStatement pstmt= con.prepareStatement(q);
				ResultSet rs =pstmt.executeQuery();
				out.println("<center>");
				out.println("<table border=1 width=50% height=50%>");
				out.println("<tr>");
				out.println("<td>Booking number</td> "+  "<td>Travling Date</td>"+"<td>Nor of passenger</td>"+"<td>Flight Number</td>"+"</br>");
				out.println("</tr>");
			while (rs.next()) {
				int    val1 = rs.getInt(1);
				String val2 = rs.getString(2);
				int    val3 = rs.getInt(3);
				String val4 = rs.getString(4);
				out.println("<tr>");
				out.println("<td>"+val1+"</td><td>"+val2+"</td><td>"+val3+"</td><td>"+val4+"</td><br>");
				out.println("</tr>");
			}
			out.println("</center>");
			}
			
			
			//read table enquiry...
			if(table.equals("enquiry")) {
				String q= ("select * from enquiry");
				PreparedStatement pstmt= con.prepareStatement(q);
				ResultSet rs =pstmt.executeQuery();
				out.println("<center>");
				out.println("<table border=1 width=50% height=50%>");
				out.println("<tr>");
				out.println("<td>User Id</td> "+"<td>User Source city</td> "+"<td>User Destination  city</td> "+  "<td>Travling Date</td>"+"<td>Nor of passenger</td>"+"<td>Route Id</td>"+"</br>");
				out.println("</tr>");
			while (rs.next()) {
				int    val1 = rs.getInt(1);
				String val2 = rs.getString(2);
				String val3 = rs.getString(3);
				String val4 = rs.getString(4);
				int    val5 = rs.getInt(5);
				String val6 = rs.getString(6);
				out.println("<tr>");
				out.println("<td>"+val1+"</td><td>"+val2+"</td><td>"+val3+"</td><td>"+val4+"</td><td>"+val5+"</td><td>"+val6+"</td><br>");
				out.println("</tr>");
			}
			out.println("</center>");
			}
			
			//read table flight...
			if(table.equals("flight")) {
				String q= ("select * from flight");
				PreparedStatement pstmt= con.prepareStatement(q);
				ResultSet rs =pstmt.executeQuery();
				out.println("<center>");
				out.println("<table border=1 width=50% height=50%>");
				out.println("<tr>");
				out.println("<td>Flight no</td> "+"<td>Price </td> "+"<td>Flight Capacity</td> "+  "<td>Arival Time</td>"+"<td>Departure Time</td>"+"<td>Route Id</td>"+"</br>");
				out.println("</tr>");
			while (rs.next()) {
				String val1 = rs.getString(1);
				int    val2 = rs.getInt(2);
				int    val3 = rs.getInt(3);
				String val4 = rs.getString(4);
				String val5 = rs.getString(5);
				String val6 = rs.getString(6);
				
			
				out.println("<tr>");
				out.println("<td>"+val1+"</td><td>"+val2+"</td><td>"+val3+"</td><td>"+val4+"</td><td>"+val5+"</td><td>"+val6+"</td><br>");
				out.println("</tr>");
			}
			out.println("</center>");
			}
			
			//read table Registration...
			if(table.equals("registration")) {
				String q= ("select * from registration");
				PreparedStatement pstmt= con.prepareStatement(q);
				ResultSet rs =pstmt.executeQuery();
				out.println("<center>");
				out.println("<table border=1 width=50% height=50%>");
				out.println("<tr>");
				out.println("<td>Name</td> "+"<td>D.O.B. </td> "+"<td>Password</td> "+  "<td>Email</td>"+"</br>");
				out.println("</tr>");
			while (rs.next()) {
				String val1 = rs.getString(1);				
				String val2 = rs.getString(2);
				String val3 = rs.getString(3);
				String val4 = rs.getString(4);
				
			
				out.println("<tr>");
				out.println("<td>"+val1+"</td><td>"+val2+"</td><td>"+val3+"</td><td>"+val4+"</td><br>");
				out.println("</tr>");
			}
			out.println("</center>");
			}
			
			//read table Route...
			if(table.equals("route")) {
				String q= ("select * from route");
				PreparedStatement pstmt= con.prepareStatement(q);
				ResultSet rs =pstmt.executeQuery();
				out.println("<center>");
				out.println("<table border=1 width=50% height=50%>");
				out.println("<tr>");
				out.println("<td>Route ID</td> "+"<td>Source City </td> "+"<td>Destination City</td>"+"</br>");
				out.println("</tr>");
			while (rs.next()) {
				String val1 = rs.getString(1);				
				String val2 = rs.getString(2);
				String val3 = rs.getString(3);
				
			
				out.println("<tr>");
				out.println("<td>"+val1+"</td><td>"+val2+"</td><td>"+val3+"</td><br>");
				out.println("</tr>");
			}
			out.println("</center>");
			}
			

			
			//out.println("DATA INSERTED SUCESSFUL");
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
