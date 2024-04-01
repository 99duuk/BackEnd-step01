package spms.vo;

import java.util.Date;

public class Post {
	private int post_id;
	private String post_title;
	private String post_content;
	private String email;
	private Date post_cre_date;

	public int getPost_id() {
		return post_id;
	}

	public Post setPost_id(int post_id) {
		this.post_id = post_id;
		return this;
	}

	public String getPost_title() {
		return post_title;
	}

	public Post setPost_title(String post_title) {
		this.post_title = post_title;
		return this;
	}

	public String getPost_content() {
		return post_content;
	}

	public Post setPost_content(String post_content) {
		this.post_content = post_content;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Post setEmail(String email) {
		this.email = email;
		return this;
	}

	public Date getPost_cre_date() {
		return post_cre_date;
	}

	public Post setPost_cre_date(Date post_cre_date) {
		this.post_cre_date = post_cre_date;
		return this;
	}

	@Override
	public String toString() {
		return "Post [post_id=" + post_id + ", post_title=" + post_title + ", post_content=" + post_content + ", email="
				+ email + ", post_cre_date=" + post_cre_date + "]";
	}

}
