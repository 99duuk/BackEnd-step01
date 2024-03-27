package study01.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 서블릿 인터페이스 (서블릿 생태 주기 최상위)
 * 		- 나머지도 모두 구현해야
 * 		-service()
 * GenericServer 추상 클래스 implement Servlet
 * 		-service()만 구현
 * 		- service()로 get/post모두 전달 
 * HttpServlet 추상 클래스 extends GenericServlet
 * 		- doGet()은 get요청 처리	
 * 		- doPost()는 post요청 처
 * 
 * */

@WebServlet("/Hello")
@SuppressWarnings("serial")
public class HelloWorld extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doget 호출 ");
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}




	
}
