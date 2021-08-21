package kr.smhrd.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.smhrd.model.BoardDAO;
import kr.smhrd.model.BoardDAOMybatis;
import kr.smhrd.model.BoardVO;


public class BoardUpdateControoler implements Controller{
	public String requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int idx = Integer.parseInt(request.getParameter("idx")); 
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		BoardVO vo = new BoardVO();// 객체만들기
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setIdx(idx); // ????엔젤
		String view = null;
		BoardDAOMybatis dao = new BoardDAOMybatis();
		
		try {
			int cnt = dao.boardUpdate(vo);
			if(cnt>0) {
				//response.sendRedirect("list.do");
				view = "redirect:/list.do";
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return view;    //list.do or null
		
	}

}
