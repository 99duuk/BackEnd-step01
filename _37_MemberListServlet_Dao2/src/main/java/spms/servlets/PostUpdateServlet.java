
package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/post/update")
@SuppressWarnings("serial")
public class PostUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("PostUpdateServlet::doGet() 호출");

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			ServletContext sc = getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			String sql = "SELECT post_id, post_title, email, post_content, post_cre_date"
					+ " FROM posts WHERE post_id = ?";

			stmt = conn.prepareStatement(sql); // PreparedStatement 객체 생성
			stmt.setInt(1, Integer.parseInt(req.getParameter("post_id")));
			rs = stmt.executeQuery();

			Map<String, Object> post = new HashMap<>();
			if (rs.next()) {
				post.put("post_id", rs.getInt("post_id"));
				post.put("post_title", rs.getString("post_title"));
				post.put("email", rs.getString("email"));
				post.put("post_content", rs.getString("post_content"));
				post.put("post_cre_date", rs.getTimestamp("post_cre_date"));
				 req.setAttribute("post", post);
			} else {
				throw new Exception("해당 번호의 게시물 찾을 수 없습니다.");
			}

			RequestDispatcher rd = req.getRequestDispatcher("/post/PostUpdateForm.jsp");
			rd.forward(req, resp);

		} catch (

		Exception e) {
			// throw new ServletException(e);
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberUpdateServlet::doPost() 호출");

		// CharacterEncodingFilter로 전처리 했으므로 이제 안해도 됨
		// req.setCharacterEncoding("UTF-8");

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			ServletContext sc = getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			String sql = "UPDATE posts SET post_title=?, post_content=? WHERE post_id = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, req.getParameter("post_title"));
			stmt.setString(2, req.getParameter("post_content"));
			stmt.setInt(3, Integer.parseInt(req.getParameter("post_id")));
			stmt.executeUpdate();

			resp.sendRedirect("list");

		} catch (Exception e) {
			// throw new ServletException(e);
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
		}
	}
}
