package spms.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;

/* DispatchServlet은 Spring에서 사용하는 DesignPattern을 사용한 클래스 명칭이다.
 * 이 역할은 *.do로 들어오는 모든 주소를ㅇ ㅣㄹ단 받아서 분기시켜주는 역할이다. 
 * 이 서블릿을 설계상에서 FrontController라고 부른다. 
 */

@WebServlet("*.do")
@SuppressWarnings("serial")
public class DispatchServlet extends HttpServlet {

	// get요청과 Post요청 모두 받기 위해 구현하는 메서드
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html; charset=UTF-8");

		// 요청 주소를 얻는다. 이 주소에 따라 Page Controller를 찾아서 분기처리한다.
		String servletPath = req.getServletPath();
		System.out.println("DispatchServlet::service() - servletPath =" + servletPath);

		try {
			String pageConterollerPath = null;
			/*	
			 * 1. 주소에 따라 처리할 PateControllerPath를 지정한다. 
			 * 2. 주소에 따라 약속된 Parameter를 꺼내서 request 공유 공간에 저장한다. 
			 * 
			 * 	현재 각 pageController에 해당하는 Servlet 클래스들은 
			 * request공간에서 Parameter를 꺼내도록 구현되어 있다.
			 * 하지만 Servlet의 종속성을 약화하고 독립성을 강화하기 위해 
			 * DispatchServlet을 제외한 나머지 Servlet클래스들은 
			 * HttpServlet을 상속받지 않은 Pojo방식의 java클래스들로 변경할 예정이다. 
			 * 그렇게 되면, request 공유 공간을 사용할 수 없으므로 
			 * 이곳 DispatchServlet에서는 먼저 Parameter를 꺼내서 처리하도록 하고,
			 * 나중에는 다른 방식으로 각 pageCntroller에 전달할 예정이다. 
			 *  
			 */
			if ("/member/list.do".equals(servletPath)) {
				pageConterollerPath = "/member/list";
			} else if ("/member/add.do".equals(servletPath)) {
				pageConterollerPath = "/member/add";
				if (req.getParameter("email") != null) {
					req.setAttribute("member", new Member()
							.setEmail(req.getParameter("email"))
							.setPassword(req.getParameter("password"))
							.setName(req.getParameter("name")));
				}
			} else if ("/member/update.do".equals(servletPath)) {
				pageConterollerPath = "/member/update";
				if (req.getParameter("email") != null) {
					req.setAttribute("member",  new Member()
							.setNo(Integer.parseInt(req.getParameter("no")))
							.setEmail(req.getParameter("email"))
							.setName(req.getParameter("name"))
							);
				}
			} else if ("/member/delete.do".equals(servletPath)) {
				pageConterollerPath = "/member/delete";
				
				
			} else if ("/member/login.do".equals(servletPath)) {
				pageConterollerPath = "/auth/login";
//				if(req.getParameter("email") != null) {
//					req.setAttribute("member", new Member()
//							.setEmail(req.getParameter("email"))
//							.setPassword(req.getParameter("password"))
//							);
//				}
			} else if ("/member/logout.do".equals(servletPath)) {
				pageConterollerPath = "/auth/logout";
			}
			System.out.println("DispatchServlet:: service() - pageConteroller = " + pageConterollerPath);
			
			
			// pageController 에 처리를 넘긴다.
			// include 방식이므로 pageController가 처리한 후에 제어권이 다시 넘어온다
			RequestDispatcher rd = req.getRequestDispatcher(pageConterollerPath);
			rd.include(req, resp);

			// pageControllerPath가 지정해준 viewUrl값을 꺼내서 화면 생성을 맡긴다. 
			String viewUrl = (String)req.getAttribute("viewUrl");
			System.out.println("DispatchServlet:: service() - viewUrl = " + viewUrl);
			
			// viewUrl이 'redirect'로 시작되는 문자열이면 redirect요청이라고 판단하고 처리한다.
			if(viewUrl.startsWith("redirect:")) {
				//viewUrl에서 'redirect:'글자를 제외한 나머지 url을 추출해서
				// 브라우저에는 redirect 요청을 보낸다. 
				// 브라우저에는 redirect 요청을 받으면 해당 url로 새롭게 다시 접속한다. 
				
			resp.sendRedirect(viewUrl.substring("redirect:".length()));
			return;
			}
			//만약 'redirect:'로 시작하는 viewurl이 아니면 내부 jsp이동으로 판단한다.
			else {
				rd = req.getRequestDispatcher(viewUrl);
				rd.include(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}
	}

}