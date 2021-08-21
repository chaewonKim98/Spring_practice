package kr.smhrd.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.smhrd.model.BoardDAO;
import kr.smhrd.web.BaordRegisterFormController;
import kr.smhrd.web.BoardContentController;
import kr.smhrd.web.BoardDeleteController;
import kr.smhrd.web.BoardListController;
import kr.smhrd.web.BoardRegisterController;
import kr.smhrd.web.BoardUpdateControoler;
import kr.smhrd.web.Controller;

@WebServlet("*.do")
//FrontController(클라이언트의 모든 요청을 받는곳)패턴
public class BoardFrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 인코딩
		request.setCharacterEncoding("utf-8");
		// 1. 클라이언트가 어떤 요청을 했는지 알아보는 것
		// http://127.0.0.1:8081/MVC02/list.do  
		//MVC02/list.do : 요청경로(클라이언트가 요청한것을 리케스트에 저장이 되어있더)
		//reqUrl에 요청한 것이 들어가있도록 get
		
		//전체 경로
		String reqUrl = request.getRequestURI();
		//컨텍스트경로(context path)를 얻어옵니다.
		String cpath = request.getContextPath();
		//클라이언트의 명령 이름(요청(url)이름)
		String command = reqUrl.substring(cpath.length());
        //System.out.println(cpath);
		//System.out.println(reqUrl);
		//System.out.println(command);
		

		//2. 요청에 따른 분기작업(어디로 가야하는지 안내해주는 역할)->if~else if~(조건)
		
		//모델은 다 필요하니까! 모델 불러오기!
		BoardDAO dao = new BoardDAO();
		Controller controller = null;
		String view = null;
		
		//HanderMapping(핸들러매핑) ->/뭔가를 연결시키는 느낌
		HandlerMapping mappings = new HandlerMapping();       //객체생성
		controller = mappings.getController(command);
		
		//---------------------------------------------------------------
		
		view = controller.requestHandler(request, response);
		if(view!=null) {
			if(view.indexOf("redirect:/")!=-1) {   
				//indexOf("text") == -1 : 이 문자열(text)을 포함하지 않는다
				//delete, update,register 이친구들은 리다이렉트임
				response.sendRedirect(view.split(":/")[1]);     
				//view = redirect:/list.do
				//split : ""를 이용해서 문자열을 자르겠다
				//([0] : redirect, [1] : list.do)
			}else {
			RequestDispatcher rd = request.getRequestDispatcher(ViewResolver.makeUr(view)); // BoardList
			rd.forward(request, response);
		
			}
		}
		
		//indexOf : String에서 글자가 있는지 찾는것
		//글자가 있으면 첫번째 인덱스 값
		//-1이면 실패임 리다이렉트가 맞다는것!
		
		
		
		
	}

}
