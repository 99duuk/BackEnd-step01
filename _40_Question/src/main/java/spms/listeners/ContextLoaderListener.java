package spms.listeners;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import spms.dao.MemberDao;

/*	웹 어플리케이션이 실행되었을 때 자동을 호출되는 클래스 
 	ServeltContext 영역이 준비 되었습니다.....
 * */

@WebListener
public class ContextLoaderListener implements ServletContextListener {
	Connection conn;

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ContextLoaderListener:: contextDestroyed 호출 ");
		try {
			if(conn != null && conn.isClosed() ==false)
				conn.close();
		} catch(Exception e){
	
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ContextLoaderListener:: contextInitialized 호출 ");
		
		try {
			ServletContext sc = sce.getServletContext();
			
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password")
					);
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			sc.setAttribute("memberDao", memberDao);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
