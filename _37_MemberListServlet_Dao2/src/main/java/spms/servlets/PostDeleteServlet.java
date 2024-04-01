package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/post/delete")
public class PostDeleteServlet extends HttpServlet {

	@Override
	public void doGet(
			HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("PostDeleteServlet::doGet() 호출");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
            ServletContext sc = getServletContext();
            conn = (Connection) sc.getAttribute("conn");
            
            String sql = "DELETE FROM posts WHERE post_id =?";
            
            stmt = conn.prepareStatement(sql); // PreparedStatement 객체 생성
            stmt.setInt(1, Integer.parseInt(req.getParameter("post_id")));

					
			
					stmt.executeUpdate();
					
			resp.sendRedirect("list");
			
		} catch (Exception e) {
			//throw new ServletException(e);
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
			
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
		}

	}
}
