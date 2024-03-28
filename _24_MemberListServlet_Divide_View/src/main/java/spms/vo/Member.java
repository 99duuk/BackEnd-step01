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
	public Member setNo(int no) {
		this.no = no;
		return this;
	}
	public String getName() {
		return name;
	}
	public Member setName(String name) {
		this.name = name;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public Member setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Member setPassword(String password) {
		this.password = password;
		return this;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public Member setCreateDate(Date createDate) {
		this.createDate = createDate;
		return this;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public Member setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
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
	
	public Member() {
	}
	//로그 확인용으로 보통 씀 
	@Override
	public String toString() {
		return "Member [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", createDate="
				+ createDate + ", modifiedDate=" + modifiedDate + "]";
	}
	
	

	
	
	
}
