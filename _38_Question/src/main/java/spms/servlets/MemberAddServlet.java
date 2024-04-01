package spms.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/add")
@SuppressWarnings("serial")
public class MemberAddServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberAddServlet::doGet() 호출");
		
		RequestDispatcher rd = req.getRequestDispatcher(
				"/member/MemberForm.jsp");
		rd.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberAddServlet::doPost() 호출");
		Connection conn = null;
		
		try {
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			
			Member member = new Member();
			member.setEmail(req.getParameter("email"));
			member.setPassword(req.getParameter("password"));
			member.setName(req.getParameter("name"));
			
			
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			memberDao.insert(member);
			
			
			
//			stmt = conn.prepareStatement(
//					"INSERT INTO members(email,pwd,mname,cre_date,mod_date)" + 
//					" VALUES(?,?,?,NOW(),NOW())"
//					);
//			stmt.setString(1,  req.getParameter("email"));
//			stmt.setString(2,  req.getParameter("password"));
//			stmt.setString(3,  req.getParameter("name"));
//			stmt.executeUpdate();
//			
			/*
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<html><head><title>회원등록결과</title></head>");
			// 아래와 같은 코드이다. 1초 후에 Refresh
			//out.println("<meta http-equiv='Refresh' content='1;url=list'>");
			out.println("<body>");
			out.println("<p>등록 성공입니다</p>");
			out.println("</body></html>");
			*/
			
			// Refresh대신 Redirect를 처리한다.
			// 시간을 지체 하지 않고, 바로 다시 상대경로 이동
			// 서버 -> 브라우저한테 명령
			// 브라우저는 list로 다시 접속
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
		}
	}

}































