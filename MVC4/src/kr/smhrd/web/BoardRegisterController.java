package kr.smhrd.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.smhrd.model.BoardDAO;
import kr.smhrd.model.BoardDAOMybatis;
import kr.smhrd.model.BoardVO;


public class BoardRegisterController implements Controller{
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 0. 요청객체에 한글을 인코딩처리
		//frontController에서 인코딩을 해줬기때문에 필요없음
		//request.setCharacterEncoding("utf-8");

		// 1.파라메터수집(vo)
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String writer = request.getParameter("writer");

		BoardVO vo = new BoardVO();// 객체만들기
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setWriter(writer);
		String view = null;
		BoardDAOMybatis dao = new BoardDAOMybatis();
		try {
			int cnt = dao.boardInsert(vo);
			if(cnt>0) {
				// 저장성공 후 -> 다시 리스트페이지로 가기(list.do)
				//redirect 기법(서버에서 전화돌리기 -> 요청돌리기)
				//response.sendRedirect("list.do");
				//redirect:/를 앞에 붙여주면(spring)
				view = "redirect:/list.do";
			}else {
				throw new ServletException("error"); //예외처리
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return view;
		
}}
