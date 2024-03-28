package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/update")
@SuppressWarnings("serial")
public class MemberUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT mno,email,mname,cre_date FROM members" + " WHERE mno=" + req.getParameter("no"));
			/*
			 * mno값은 unique하므로 결과는 1개가 나오므로 while문은 필요없다. ResultSet의 내부에는 row를 가리키는 Cursor가
			 * 존재하는데 처음에는 1번째 행 이전을 가리키고 있다. 그러므로 rs.next()를 해야 1번째 행을 가리키고 값을 꺼낼 수가 있다.
			 */

			rs.next();

			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>회원정보</title>");
			out.println("<style>");
			out.println("body {");
			out.println("  font-family: Arial, sans-serif;");
			out.println("  background-color: #f4f4f4;");
			out.println("  margin: 0;");
			out.println("  padding: 0;");
			out.println("}");
			out.println(".container {");
			out.println("  width: 50%;");
			out.println("  margin: 50px auto;");
			out.println("  background-color: #fff;");
			out.println("  padding: 20px;");
			out.println("  border-radius: 5px;");
			out.println("  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);");
			out.println("}");
			out.println("h1 {");
			out.println("  text-align: center;");
			out.println("}");
			out.println("form {");
			out.println("  max-width: 400px;");
			out.println("  margin: 0 auto;");
			out.println("}");
			out.println("input[type=text] {");
			out.println("  width: 100%;");
			out.println("  padding: 12px 20px;");
			out.println("  margin: 8px 0;");
			out.println("  box-sizing: border-box;");
			out.println("  border: 1px solid #ccc;");
			out.println("  border-radius: 4px;");
			out.println("}");
			out.println("input[type=submit], input[type=button] {");
			out.println("  width: 100%;");
			out.println("  background-color: #4caf50;");
			out.println("  color: white;");
			out.println("  padding: 14px 20px;");
			out.println("  margin: 8px 0;");
			out.println("  border: none;");
			out.println("  border-radius: 4px;");
			out.println("  cursor: pointer;");
			out.println("}");
			out.println("input[type=submit]:hover, input[type=button]:hover {");
			out.println("  background-color: #45a049;");
			out.println("}");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class='container'>");
			out.println("<h1>회원정보</h1>");
			out.println("<form action='update' method='post'>");
			out.println("<label for='no'>번호:</label>");
			out.println("<input type='text' id='no' name='no' value='" + req.getParameter("no") + "' readonly><br>");
			out.println("<label for='name'>이름:</label>");
			out.println("<input type='text' id='name' name='name' value='" + rs.getString("mname") + "'><br>");
			out.println("<label for='email'>이메일:</label>");
			out.println("<input type='text' id='email' name='email' value='" + rs.getString("email") + "'><br>");
			out.println("<label for='creDate'>가입일:</label>");
			out.println(rs.getDate("CRE_DATE") + "<br>");
			out.println("<input type='submit' value='저장'>");
			out.println("<input type='button' value='삭제' onclick='location.href=\"delete?no=" + req.getParameter("no") + "\";'>");
			out.println("<input type='button' value='취소' onclick='location.href=\"list\"'>");
			out.println("</form>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");


		} catch (Exception e) {
			throw new ServletException(e);
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
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글이 깨지지 않기 위해 값을 꺼내기 전에 설정
		req.setCharacterEncoding("UTF-8");

		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			stmt = conn.prepareStatement("UPDATE members SET email=?, mname=?, mod_date=NOW()" + " WHERE mno=?");
			stmt.setString(1, req.getParameter("email"));
			stmt.setString(2, req.getParameter("name"));
			stmt.setInt(3, Integer.parseInt(req.getParameter("no")));
			stmt.executeUpdate();

			resp.sendRedirect("list");

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}
}