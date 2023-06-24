package com.unique.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUser")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		int uid = (int) request.getAttribute("UserId");
		
		String Cname = request.getParameter("Cname");
		String Cpwd = request.getParameter("Cpass");
		String nName = request.getParameter("Nemail");
		String nPwd = request.getParameter("Npass");
		String nEmail = request.getParameter("Nemail");
		String nContact = request.getParameter("Ncontact");
		
	
		
		RequestDispatcher dispatcher = null;
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem","root","");
			PreparedStatement pst = con.prepareStatement("UPDATE users SET uname = ?, upwd = ?, uemail =?, umobile = ? WHERE uemail=? and upwd = ?");
			
			pst.setString(1, nName);
			pst.setString(2, nPwd);
			pst.setString(3, nEmail);
			pst.setString(4, nContact);
			pst.setString(5, Cname);
			pst.setString(6, Cpwd);
			
			int rowCount = pst.executeUpdate();
			
			if(rowCount>0) {
				response.sendRedirect("login.html");
			}
		} catch (Exception e) {
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
	
