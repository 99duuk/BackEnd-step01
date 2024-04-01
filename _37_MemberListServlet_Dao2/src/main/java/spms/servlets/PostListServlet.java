package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Post;

@WebServlet("/post/list")
@SuppressWarnings("serial")
public class PostListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("PostListServlet::doGet() 호출");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // ServletContext에서 Connection 객체를 가져옴
            ServletContext sc = getServletContext();
            conn = (Connection) sc.getAttribute("conn");
            
            // SQL 쿼리문 실행
            String sql = "SELECT post_id, post_title, email, post_content, post_cre_date " +
                         "FROM posts " +
                         "ORDER BY post_id ASC";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // 게시물 리스트 생성
            List<Post> posts = new ArrayList<>();

            while (rs.next()) {
                posts.add(new Post().setPost_id(rs.getInt("post_id"))
                                     .setEmail(rs.getString("email"))
                                     .setPost_title(rs.getString("post_title"))
                                     .setPost_content(rs.getString("post_content"))
                                     .setPost_cre_date(rs.getDate("post_cre_date")));
            }

            // 게시물 리스트를 request 객체에 저장
            req.setAttribute("posts", posts);

            // 뷰 페이지로 포워딩
            RequestDispatcher rd = req.getRequestDispatcher("../post/PostList.jsp");
            rd.forward(req, res);
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
            rd.forward(req, res);
        } finally {
            // 자원 해제
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
