package kr.smhrd.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.smhrd.model.BoardDAOMybatis;
import kr.smhrd.model.BoardVO;

public class AjaxBoardUpdateController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int idx = Integer.parseInt(request.getParameter("idx")); 
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		BoardVO vo = new BoardVO();// 객체만들기
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setIdx(idx); 
		String view = null;
		BoardDAOMybatis dao = new BoardDAOMybatis();
		int cnt = dao.boardUpdate(vo);
		PrintWriter out = response.getWriter();
		out.println(cnt); //$ajax()  -> 형식적으로 응답 : success
		
		return null;
	}

}
