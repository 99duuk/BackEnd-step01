package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/list")
@SuppressWarnings("serial")
public class MemberListServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberListServlet::doGet() 호출 ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"),
				
					sc.getInitParameter("password"));
			pstmt = conn.prepareStatement("SELECT mno, mname, email, cre_date FROM members ORDER BY mno ASC");
			rs = pstmt.executeQuery();
			// 		CharacterEncodingFilter로 전처리해서 안해도 댐 
			//		resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>회원 목록</title>");
			out.println("<style>");
			out.println("body {");
			out.println("  font-family: Arial, sans-serif;");
			out.println("  background-color: #f4f4f4;");
			out.println("  margin: 0;");
			
			out.println("  padding: 0;");
			out.println("}");
			out.println("h1 {");
			out.println("  text-align: center;");
			out.println("  padding: 20px 0;");
			out.println("}");
			out.println("table {");
			out.println("  width: 80%;");
			out.println("  margin: 20px auto;");
			out.println("  border-collapse: collapse;");
			out.println("  background-color: #fff;");
			out.println("  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);");
			out.println("}");
			out.println("th, td {");
			out.println("  padding: 12px 15px;");
			out.println("  border-bottom: 1px solid #ddd;");
			out.println("}");
			out.println("tr:hover {");
			out.println("  background-color: #f2f2f2;");
			out.println("}");
			out.println("a.button {");
			out.println("  display: inline-block;");
			out.println("  padding: 10px 20px;");
			out.println("  text-decoration: none;");
			out.println("  background-color: #4caf50;");
			out.println("  color: #fff;");
			out.println("  border-radius: 5px;");
			out.println("}");
			out.println("a.button:hover {");
			out.println("  background-color: #45a049;");
			out.println("}");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>회원 목록</h1>");
			out.println("<a href='add' class='button'>신규 회원 추가</a>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>번호</th>");
			out.println("<th>이름</th>");
			out.println("<th>이메일</th>");
			out.println("<th>가입일</th>");
			out.println("<th>삭제</th>");
			out.println("</tr>");

			while (rs.next()) {
			    out.println("<tr>");
			    out.println("<td>" + rs.getInt("mno") + "</td>");
			    out.println("<td><a href='update?no=" + rs.getInt("mno") + "'>" + rs.getString("mname") + "</a></td>");
			    out.println("<td>" + rs.getString("email") + "</td>");
			    out.println("<td>" + rs.getDate("cre_date") + "</td>");
			    out.println("<td><form action='delete' method='post'>" +
			            "<input type='hidden' name='no' value='" + rs.getInt("mno") + "'>" +
			            "<input type='submit' value='삭제'>" +
			            "</form></td>");
			    out.println("</tr>");
			}

			out.println("</table>");
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
				if (pstmt != null)
					pstmt.close();
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
