package com.unique.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uemail = request.getParameter("username");
		String upwd = request.getParameter("password");
		RequestDispatcher dispatcher = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem","root","");
			PreparedStatement pst = con.prepareStatement("select id from users where uemail=? and upwd=?");
			
			pst.setString(1, uemail);
			pst.setString(2, upwd);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				
				int id = rs.getInt("id");
				
				int userId = id;
		        request.setAttribute("UserId",id);
		        
		        // Dispatch the request to the ReceiverServlet
//		        dispatcher = request.getRequestDispatcher("/WelcomeServlet");
//		        dispatcher.forward(request, response);
//		
				response.sendRedirect("welcome.html");
				//dispatcher = request.getRequestDispatcher("index.html");
			}
			else {
				response.sendRedirect("login.html");
//				request.setAttribute("status", "failed");
//				dispatcher = request.getRequestDispatcher("login.html");
			}
		
		}catch(Exception e){
			
			e.printStackTrace();
		
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

	}
}
