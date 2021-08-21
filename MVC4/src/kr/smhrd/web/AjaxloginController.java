package kr.smhrd.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.smhrd.model.BoardDAOMybatis;
import kr.smhrd.model.UserVO;

public class AjaxloginController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		
		UserVO vo = new UserVO();
		vo.setUser_id(user_id);
		vo.setPassword(password);
		
		BoardDAOMybatis dao = new BoardDAOMybatis();
		
		UserVO userVO=dao.loginMember(vo);
		
		PrintWriter out = response.getWriter();
		if(userVO!=null) {
			out.print("Yes");     //인증에성공
			//객체바인딩: 로그인에 성공했다는 사실을 모든 웹페이지가 공유할 수 있도록 해야한다.
			//HttpServletRequest, HttpSession둘다 객체 바인딩하는  것 
			HttpSession session = request.getSession();    //요청을 해야 메모리(session)에 세션객체를 만드는것
			                              //getSession세션이 없으면 만들어주고 있으면 그걸 가져와준다
			session.setAttribute("userVO", userVO);    //setAttribute를 하면getAttribute를 할 수있음
			//request.setAttribute("userVO", userVO);
			//${userVO}->이게 getAttribute임!
		}else {  
			out.print("No");      //인증실패
		}
		
		return null;
	}

}
