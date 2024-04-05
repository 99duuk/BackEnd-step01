package spms.vo;

import java.util.Date;


/*	Projects ㅔ이블 설계한 후 
 * 	1개 행 정보 입출력 위해 Vo 생성 ㄴ
 * 
 * 	필드의 정보 확인 위해 ToString 오버라이딩. 
 * 	연속적 setter초기화 위해 return this; 
 * 
 */

public class Project {
	private int no;
	private String title;
	private String content;
	private Date StartDate;
	private Date endDate;
	private int state; 
	private Date createdDate;
	private String tags;
	public int getNo() {
		return no;
	}
	public Project setNo(int no) {
		return this;
	}
	public String getTitle() {
		return title;
	}
	public Project setTitle(String title) {
		return this;
	}
	public String getContent() {
		return content;
	}
	public Project setContent(String content) {
		return this;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public Project setStartDate(Date startDate) {
		return this;
	}
	public Date getEndDate() {
		return endDate;
	}
	public Project setEndDate(Date endDate) {
		return this;
	}
	public int getState() {
		return state;
	}
	public Project setState(int state) {
		return this;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public Project setCreatedDate(Date createdDate) {
		return this;
	}
	public String getTags() {
		return tags;
	}
	public Project setTags(String tags) {
		return this;
	}
	
	public Project(int no, String title, String content, Date startDate, Date endDate, int state, Date createdDate,
			String tags) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		StartDate = startDate;
		this.endDate = endDate;
		this.state = state;
		this.createdDate = createdDate;
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "Project [no=" + no + ", title=" + title + ", content=" + content + ", StartDate=" + StartDate
				+ ", endDate=" + endDate + ", state=" + state + ", createdDate=" + createdDate + ", tags=" + tags + "]";
	}
	
	
	
	}
