package smpms;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
		out.println("<html><head><title>회원등록</title></head>");
		out.println("<body><h1>회원등록</h1>");
		out.println("<form action = 'add' method='post'>");
		out.println("이름: <input type='text' name='name'> <br>");
		out.println("이메일: <input type='text' name='email'><br>");
		out.println("암호: <input type='text' name='password'><br>");
		out.println("<input type='submit' value='추가'>");
		out.println("<input type='reset' value='취소'>");
		out.println("</form>");
		out.println("</body></html>");
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
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection(
				    "jdbc:mysql://localhost:4306/studydb?useUnicode=true&characterEncoding=UTF-8",
				    "study",
				    "study"
				);

			stmt = conn.prepareStatement(
					"INSERT INTO member(email, pwd, mname, cre_date, mod_date)" +
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
