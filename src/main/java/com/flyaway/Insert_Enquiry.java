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

public class Insert_Enquiry extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
				
		// data base 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/flyaway","root","password");
		String q= ("insert into enquiry(s_city,d_city,t_date,no_trav,route_id) values( ?, ?, ?, ?, ?)");
		PreparedStatement pstmt= con.prepareStatement(q);
		pstmt.setString(1, request.getParameter("s_city"));
		pstmt.setString(2, request.getParameter("d_city"));
		pstmt.setString(3, request.getParameter("t_date"));
		pstmt.setString(4, request.getParameter("no_trav"));
		pstmt.setString(5, request.getParameter("route_id"));
		pstmt.executeUpdate();
		out.println("DATA INSERTED SUCESSFUL");
		RequestDispatcher rd= request.getRequestDispatcher("insert_enquiry.html");
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
