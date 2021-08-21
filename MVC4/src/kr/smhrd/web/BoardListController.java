package kr.smhrd.web;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.smhrd.model.BoardDAO;
import kr.smhrd.model.BoardDAOMybatis;
import kr.smhrd.model.BoardVO;


public class BoardListController implements Controller{
	public String requestHandler(HttpServletRequest request, HttpServletResponse response) 
			                                            throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=euc-kr");
		//1. Model
		BoardDAOMybatis dao = new BoardDAOMybatis();
		try {
			List<BoardVO> list = dao.boardList();
			//요청한 클라이언트로 게시판 목록을 응답하기(jsp)
			//System.out.println(list.toString());
			//boardList.jsp : 요청의뢰(forward)
			request.setAttribute("list", list); //객체바인딩
			//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/BoardList.jsp");
			//rd.forward(request, response);   //jsp에게 넘겨주는것, jsp에 내장객체로 둘다 있음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "BoardList";
		//경로를 보내준디. 
		//View페이지의 경로를 리턴!String으로!
		
		
		
		
		
		
	}

}
