package com.flyaway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

public class Flight_Price extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			PrintWriter out= response.getWriter();
		response.setContentType("text/html");
		//out.println("<h2>welcome your Flight_Price</h2>");
		
		
		String flight_no=request.getParameter("agree");
		//out.println(flight_no);
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
		
		while(rs1.next())
		
		{
			
			user_id =rs1.getInt(1);
			user_scity =rs1.getString(2);
			user_dcity =rs1.getString(3);
			user_date =rs1.getString(4);
			user_no=rs1.getInt(5);
			user_rid=rs1.getString(6);
			//out.println(user_id+" HI   " +user_scity+"   "+user_dcity+"     "+user_date+"     "+user_no+ "  "+user_rid+"<br>");
			
		}
		
		
		pstmt1.close();
		//fetch user selection of flight.
		String user_fno=request.getParameter("user_fno");
		out.println(user_fno);
		//fetch about flight information.
		String q= ("select * from flight where flight_no= ?");
		 PreparedStatement pstmt= con.prepareStatement(q);
		 
		 pstmt.setString(1, user_fno);
		 ResultSet rs=pstmt.executeQuery();
		 
		 
		 String val=null;
		 double  val1=0;
		 double val2=0;
		 String val3=null;
		 String val4=null;
		 
		 	 out.println("<center>");
		     out.println("<form action=\"Final.html\" method=\"post\">" );
			 out.println("<h1>");
			 out.println("<table id = \"main_section\" cellspacing=\"4\" bgcolor=\"yellow\" > ");
			 out.println ("<tr> ");
			 out.println("</tr>");
			 
			 
			 while(rs.next())  
			 {  
				 
				 out.println("<tr>");
				 out.println("<center>");
				  val=rs.getString(1);
				  val1=rs.getInt(2);
				  val2=rs.getInt(3);
				  val3=rs.getString(4);
				  val4=rs.getString(5);
			    
			     out.println("</center>"); 
			     out.println("</tr>");
			     
			     	
			     out.println("<td>"+ val+ "</td>");
			 
			     out.println("</tr>");
			     out.println("</form>");
			     out.println("</center>");
			     
			 }
			   
			     	
			  // REPORT SHOW 
			 out.println("<body text=\"green\">");
			 out.println("<tr text=\"red\">");
			 out.println("<h3>");
			 
			        out.println("<td>YOUR FLIGHT NUMBER</td>");
			     	out.println("<td>YOUR SOURCE CITY</td>"); 
			     	out.println("<td>YOUR DESTINATION CITY</td>");
			     	out.println("<td>JOURNEY DATE</td>");
			     	out.println("<td>NUMBER OF PASSENGER</td> ");
			     	out.println("<td>FLIGHT ARRIVAL TIME</td> ");
			     	out.println("<td>FLIGHT DEPARTURE TIME </td>");
			     	out.println("<td>AVAILABLE SEATS </td>");
			     	out.println("<td>TOTAL PRICE </td>");
			     	out.println("</h3>");
			     	out.println("</tr>");
			    out.println("<tr>");	
			     	out.println("<td>"+ val+ "</td>");
			     	out.println("<td>"+ user_scity+"</td>"); 
			     	out.println("<td>"+ user_dcity+"</td>");
			     	out.println("<td>"+ user_date+"</td>");
			     	out.println("<td> "+ user_no+"</td>");
			     	out.println("<td>"+ val3+"</td>");
			     	out.println("<td>"+ val4+"</td>");
			     	out.println("<td>"+ val2+"</td>");
			     	out.println("<td>RS "+ val1*user_no+"</td>");
			     	out.println("</body>");
			     	//out.println("</tr>");
			     //RequestDispatcher rd=request.getRequestDispatcher("Final.html");
				 //rd.include(request, response);
				 RequestDispatcher rd=request.getRequestDispatcher("Ticket_Print");
				 rd.forward(request, response);
			 
		 
						
				 pstmt.close();	 
		}
		
		
		catch (Exception e) {
		
		e.printStackTrace();
		out.println("<h1>"+ e +"</h1>");
		
		}
		

		
		
		
		
		
	}

}
