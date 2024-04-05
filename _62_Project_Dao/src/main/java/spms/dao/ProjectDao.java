package spms.dao;

import java.util.List;

import spms.vo.Member;
import spms.vo.Project;

/*	데이터 입출력은 단순한 것은 
 * 	1단계(즉, Controller - Dao)
 * 
 * 	로직이 복잡해지면	
 * 	2단계 (Controller - Service - Dao)
 * 
 * 	Controller 클라이언트의 통신 정보, 데이터 입출력 전달.
 * 	Service 다양한 비즈니스 로직, 여러 개 테이블 vo에 입출력
 * 	Dao는 테이블과 보통 1대1 입출력 기능  
 * 	
 * CRUD 외 기능은 필요하면 더 넣거나
 * 	혹시 복잡한 로직이면 Dao보다 Service에서 구현할 것을 결정해야 한다.
 */
public interface ProjectDao {
	List<Project> selectList() throws Exception;
	int insert (Project project) throws Exception;
	int delete (int no) throws Exception;
	Project selectOne (int no)throws Exception;
	int update(Project project)throws Exception;
}
