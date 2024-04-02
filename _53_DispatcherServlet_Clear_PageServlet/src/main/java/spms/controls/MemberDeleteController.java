package spms.controls;

import java.util.Map;

import spms.dao.MemberDao;

public class MemberDeleteController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		System.out.println("MemberDeleteController::execute() 호출");
		//request 공간 대신 model로부터 꺼내어 사용한다.
		MemberDao memberDao = (MemberDao)model.get("memberDao");
		
		Integer no = (Integer) model.get("no");
		
		memberDao.delete(no);
		
		
		return "redirect:list.do";
	}
}
