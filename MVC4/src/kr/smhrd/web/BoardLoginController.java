package kr.smhrd.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.smhrd.model.BoardDAOMybatis;
import kr.smhrd.model.UserVO;

public class BoardLoginController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");

		UserVO vo = new UserVO();
		vo.setUser_id(user_id);
		vo.setPassword(password);

		BoardDAOMybatis dao = new BoardDAOMybatis();

		UserVO userVO = dao.loginMember(vo);
		
		if (userVO != null) {
			// 로그인 성공
			HttpSession session = request.getSession(); // 요청을 해야 메모리(session)에 세션객체를 만드는것
			// getSession세션이 없으면 만들어주고 있으면 그걸 가져와준다
			session.setAttribute("userVO", userVO);
		} else {
			// 로그인 실패
			HttpSession session = request.getSession(); // 요청을 해야 메모리(session)에 세션객체를 만드는것
			// getSession세션이 없으면 만들어주고 있으면 그걸 가져와준다
			session.setAttribute("msg", "사용자 정보가 올바르지 않습니다.");
		}

		return "redirect:/list.do";
	}

}
