package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/add")
@SuppressWarnings("serial")
public class MemberAddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>회원 등록</title>");
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
		out.println("form {");
		out.println("  width: 50%;");
		out.println("  margin: 20px auto;");
		out.println("  background-color: #fff;");
		out.println("  padding: 20px;");
		out.println("  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);");
		out.println("}");
		out.println("input[type='text'], input[type='password'] {");
		out.println("  width: calc(100% - 20px);");
		out.println("  padding: 10px;");
		out.println("  margin: 5px 0;");
		out.println("  border: 1px solid #ccc;");
		out.println("  border-radius: 5px;");
		out.println("}");
		out.println("input[type='submit'], input[type='reset'] {");
		out.println("  width: calc(50% - 5px);");
		out.println("  padding: 10px;");
		out.println("  margin-top: 10px;");
		out.println("  border: none;");
		out.println("  border-radius: 5px;");
		out.println("  background-color: #4caf50;");
		out.println("  color: #fff;");
		out.println("  cursor: pointer;");
		out.println("}");
		out.println("input[type='submit']:hover, input[type='reset']:hover {");
		out.println("  background-color: #45a049;");
		out.println("}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>회원 등록</h1>");
		out.println("<form action='add' method='post'>");
		out.println("  <label for='name'>이름:</label><br>");
		out.println("  <input type='text' id='name' name='name'><br>");
		out.println("  <label for='email'>이메일:</label><br>");
		out.println("  <input type='text' id='email' name='email'><br>");
		out.println("  <label for='password'>암호:</label><br>");
		out.println("  <input type='password' id='password' name='password'><br>");
		out.println("  <input type='submit' value='추가'>");
		out.println("  <input type='reset' value='취소'>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//파라미터 꺼내기 전에 해야함. 설정 안해주면 한글 깨져서 저장, 이 설정 추가해줄 것 
		req.setCharacterEncoding("UTF-8");
		
		Connection conn = null;
		/* Statement
		 * - 질의할 때마다 sql을 컴파일
		 * - 입력 매개변수가 여러 개 필요할 때 문자열 결합 연산자인 +를 이용해 해야함. 
		 * 
		 * PreparedStatement
		 * - sql문을 미리 입력하여 컴파일한 상태에서 객체 받음	
		 * - 만약 sql 구조가 변경되지 않고, 파라미터값만 바뀌는 경우 Statement보다 훨씬 빠르다. 
		 * - 입력 매개변수가 여러 개 필요할 때 ?로 sql의 파라미터를 표시하고, 나중에 전달하므로 편하다.
		 * - Statement < PreparedStatement 를 사용한다. 
		 * 
		 * */
		
		PreparedStatement stmt = null;
		
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"),
					sc.getInitParameter("password"));

			stmt = conn.prepareStatement(
					"INSERT INTO members(email, pwd, mname, cre_date, mod_date)" +
					" VALUES (?,?,?,NOW(),NOW())"
					);
			stmt.setString(1, req.getParameter("email"));
			stmt.setString(2, req.getParameter("password"));
			stmt.setString(3, req.getParameter("name"));
			stmt.executeUpdate();
			
			resp.setContentType("text/html;charset=UTF-8");

			PrintWriter out = resp.getWriter();
			out.println("<html><head><title>회원등록결과</title></head>");
			// 아래와 같은 코드. 1초 후에 Refresh
//			out.println("<meta http-equiv='Refresh' content='1;url=list'>");
			out.println("<body>");
			out.println("<p>등록 성공입니다.</p>");
			out.println("</body></html>");
			
			
			// Refresh 대신 Redirect를 처리한다.
			// 시간 지체하지 않고, 바로 다시 상대경로 이동 
			resp.sendRedirect("list");
			
			
			//1초 후에 화면 바뀌며 상대경로 list 로 이동 .
			// 위에 처럼 해도 
//			resp.addHeader("Refresh", "1;url=list");
		} catch(Exception e) {
			throw new ServletException(e);
		} finally {
			try {if(stmt!=null) stmt.close();} catch(Exception e) {}
			try {if(conn!=null) conn.close();} catch(Exception e) {}
		}
	
	}
	
	

}
