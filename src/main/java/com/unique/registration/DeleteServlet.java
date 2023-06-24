package com.unique.registration;

import java.io.IOException;
import java.io.PrintWriter;
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

import org.apache.catalina.realm.AuthenticatedUserRealm;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/deleteUser")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uemail = request.getParameter("username");
		String upwd = request.getParameter("password");
		
		PrintWriter out= response.getWriter();
		out.print(uemail);
//		String id = request.getParameter("UserID");
//		 int uid=Integer.parseInt(id); 
		
		RequestDispatcher dispatcher = null;
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem","root","");
			PreparedStatement pst = con.prepareStatement("delete from users where uemail=? and upwd=?");
			
			pst.setString(1, uemail);
			pst.setString(2, upwd);
			
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



