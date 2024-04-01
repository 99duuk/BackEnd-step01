package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/post/add")
@SuppressWarnings("serial")
public class PostAddServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("PostAddServlet::doGet() 호출");
		
		RequestDispatcher rd = req.getRequestDispatcher(
				"/post/PostForm.jsp");
		rd.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberAddServlet::doPost() 호출");
		

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
            ServletContext sc = getServletContext();
            conn = (Connection) sc.getAttribute("conn");

			String sql = "INSERT INTO posts(email,post_title, post_content, post_cre_date)" 
					+ " VALUES(?, ?, ?, NOW())";
			  stmt = conn.prepareStatement(sql); // PreparedStatement 객체 생성
			  
			stmt.setString(1,  req.getParameter("email"));
			stmt.setString(2,  req.getParameter("post_title"));
			stmt.setString(3,  req.getParameter("post_content"));
			stmt.executeUpdate();
			
			
			resp.sendRedirect("list");
			
			// 1초 후에 화면이 바뀌면서 상대경로 list로 이동하라
			// 위에 처럼 해도 된다.
			//resp.addHeader("Refresh", "1;url=list");
		}catch(Exception e) {
			//throw new ServletException(e);
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
			
		}finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
		}
	}

}































