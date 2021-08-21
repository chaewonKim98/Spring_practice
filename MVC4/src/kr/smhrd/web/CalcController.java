package kr.smhrd.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet     //여기 대문자로 시작해서 값이 안떴고!
public class CalcController  implements Controller {
	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int su = Integer.parseInt(request.getParameter("su"));
		int sum = 0;
		for(int i=1; i<=su; i++) {
			sum+=i;
		}
		PrintWriter out = response.getWriter(); //여기랑
		out.println(sum); //여기 빠져있었으!
		
		return null;
	}

}
