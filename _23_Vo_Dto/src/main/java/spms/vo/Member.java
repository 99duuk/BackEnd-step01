package spms.vo;

import java.util.Date;

// 테이블의 한 개 행의 데이터를 모두 묶어서 담는 클래스 객체를 의미한다. 
// 이것을 VO(Value Object)라고 함

// MVC 역할을 나누면 (테이블)데이터를 묶어서 전달하는 객체 필요함
// 이 때 VO를 주고 받으면 VO는 DTO(data transfer object)라고 부른다. 

// VO와 DTO는 구성이 같지만 가끔 용도에 따라 필드를 약간 달리할 때가 있다. 
// 또 getter와 setter를 달리할 때도 있다. 
// 이럴 때는 Vo와 DTO를 따로 만드는 경우도 있다. 
// 그래도 ModelMappler로 VO <--> DTO를 상호 변환해서 사용하기도 한다. 
public class Member {
	private int no;
	private String name;
	private String email;
	private String password;
	private Date createDate;
	private Date modifiedDate;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Member(int no, String name, String email, String password, Date createDate, Date modifiedDate) {
		super();
		this.no = no;
		this.name = name;
		this.email = email;
		this.password = password;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}
	
	//로그 확인용으로 보통 씀 
	@Override
	public String toString() {
		return "Member [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", createDate="
				+ createDate + ", modifiedDate=" + modifiedDate + "]";
	}
	
	

	
	
	
}
