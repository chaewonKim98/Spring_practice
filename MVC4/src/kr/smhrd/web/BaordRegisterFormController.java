package kr.smhrd.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.catalina.servlet4preview.RequestDispatcher; // 다른 class ?


public class BaordRegisterFormController implements Controller{
	public String requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//글쓰기 화면 이동
		
		//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/boardForm.jsp");
		//rd.forward(request, response);
		return"boardForm";
	}

}
