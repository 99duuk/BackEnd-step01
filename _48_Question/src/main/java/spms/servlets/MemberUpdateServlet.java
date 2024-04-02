
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

@WebServlet("/member/update")
@SuppressWarnings("serial")
public class MemberUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberUpdateServlet::doGet() 호출");

		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");

			Member member = memberDao.selectOne(Integer.parseInt(req.getParameter("no")));
			
			req.setAttribute("member", member);
			
			req.setAttribute("viewUrl", "/member/MemberUpdateForm.jsp");

		} catch (Exception e) {
			 throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberUpdateServlet::doPost() 호출");

		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
			
			Member member = (Member) req.getAttribute("member");
			memberDao.update(member);

			/*
			 * memberDao.update(new Member()
			 * .setNo(Integer.parseInt(req.getParameter("no")))
			 * .setEmail(req.getParameter("email")) .setName(req.getParameter("name")) );
			 */
			req.setAttribute("viewUrl", "redirect:list.do");

		} catch (Exception e) {
			 throw new ServletException(e);
			
		}
	}
}
