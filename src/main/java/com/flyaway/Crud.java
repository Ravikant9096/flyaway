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

public class Crud extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	response.setContentType("text/html");
	PrintWriter out =response.getWriter();
	String radio =request.getParameter("admin");
	String table=request.getParameter("table");
	out.println(radio);
	out.println(table);
	
	// DATA BASE CONNECTION
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/flyaway","root","password");
	
	if(radio.equals("insert")& table.equals("booking1")) {
		out.println("YOU WANT insert TABLE ="+table);
	
		RequestDispatcher rd = request.getRequestDispatcher("insert_booking1.html");
		rd.include(request, response);
	
	
	}
	if(radio.equals("insert")& table.equals("enquiry")) {
		out.println("YOU WANT insert TABLE ="+table);
	
		RequestDispatcher rd = request.getRequestDispatcher("insert_enquiry.html");
		rd.include(request, response);
	
	
	}
	
	if(radio.equals("insert")& table.equals("flight")) {
		out.println("YOU WANT insert TABLE ="+table);
	
		RequestDispatcher rd = request.getRequestDispatcher("insert_flight.html");
		rd.include(request, response);
	
	
	}
	
	if(radio.equals("insert")& table.equals("registration")) {
		out.println("YOU WANT insert TABLE ="+table);
	
	
		RequestDispatcher rd = request.getRequestDispatcher("NewRegistration.html");
		rd.include(request, response);
	
	}
	
	if(radio.equals("insert")& table.equals("route")) {
		out.println("YOU WANT insert TABLE ="+table);
		RequestDispatcher rd = request.getRequestDispatcher("insert_route.html");
		rd.include(request, response);
	
	
	
	
	}
	
	
	// read operation
	
	if(radio.equals("read")& table.equals("booking1")) {
		out.println("YOU WANT Read TABLE ="+table);
	
		RequestDispatcher rd = request.getRequestDispatcher("Read_Admin");
		rd.forward(request, response);
	
	
	}
	if(radio.equals("read")& table.equals("enquiry")) {
		out.println("YOU WANT Read TABLE ="+table);
	
		RequestDispatcher rd = request.getRequestDispatcher("Read_Admin");
		rd.forward(request, response);
	
	
	}
	
	if(radio.equals("read")& table.equals("flight")) {
		out.println("YOU WANT Read TABLE ="+table);
	
		RequestDispatcher rd = request.getRequestDispatcher("Read_Admin");
		rd.forward(request, response);
	
	
	}
	
	if(radio.equals("read")& table.equals("registration")) {
		out.println("YOU WANT Read TABLE ="+table);
	
	
		RequestDispatcher rd = request.getRequestDispatcher("Read_Admin");
		rd.forward(request, response);
	
	}
	
	if(radio.equals("read")& table.equals("route")) {
		out.println("YOU WANT Read TABLE ="+table);
		RequestDispatcher rd = request.getRequestDispatcher("Read_Admin");
		rd.forward(request, response);
	
	
	
	
	}
	
	//Update table
	
	if(radio.equals("update")) {
		out.println("YOU WANT Update TABLE ="+table);
		RequestDispatcher rd = request.getRequestDispatcher("update_admin.html");
		rd.include(request, response);
	
		
	
	
	}
	//Delete table
	if(radio.equals("delete")) {
		out.println("YOU WANT Delete TABLE ="+table);
	   if(table.equals("enquiry")) {
		String q= ("delete from enquiry");
		PreparedStatement pstmt= con.prepareStatement(q);
		pstmt.executeUpdate();
		RequestDispatcher rd = request.getRequestDispatcher("admin.html");
		rd.include(request, response);
		out.println("<center>");
		out.println("<h1>Sucessfull Delete TABLE ="+table+"</h1>");
		out.println("</center>");
		return;
		}
	   else {
		   RequestDispatcher rd = request.getRequestDispatcher("admin.html");
			rd.include(request, response);
			out.println("<center>");
		   out.println("<h3>This table can not delete this time Sorry, its affect all oprational function</h3> ");
		   out.println("</center>");
	   }
		
		
		
		
	}
	
	
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

}
