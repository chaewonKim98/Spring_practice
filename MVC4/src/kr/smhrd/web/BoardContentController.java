package kr.smhrd.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.smhrd.model.BoardDAO;
import kr.smhrd.model.BoardDAOMybatis;
import kr.smhrd.model.BoardVO;

//POJO: front Controller가 해야할 일을 재신 해줌(알바생임)
public class BoardContentController implements Controller{
	public String requestHandler (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardDAOMybatis dao = new BoardDAOMybatis();
		try {
			BoardVO vo =  dao.boardContent(idx);
			//상세보기페이지로 이동:boardContent.jsp
			request.setAttribute("vo", vo);
			//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/boardContent.jsp");
			//rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "boardContent";
		
		//Viewname(논리적인뷰의이름)-> /WEB-INF/views/boardContent.jsp(물리적인경로)
		//viewResolver(논리적인경로를 물리적으로 바꿔주는)
		//return "/WEB-INF/views/boardContent.jsp";
		      //접두사                         viewname     접미사  
		//접두사 + viewname + 접미사   (spring)
		//유지보수가 어려운 부분은 쉽게 바꿔줘야됨
		//경로땜에 유지보수가 어려울수있다
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
