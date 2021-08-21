package kr.smhrd.frontcontroller;

import java.util.HashMap;

import kr.smhrd.web.AjaxBoardContentController;
import kr.smhrd.web.AjaxBoardDeleteController;
import kr.smhrd.web.AjaxBoardListController;
import kr.smhrd.web.AjaxBoardRegisterController;
import kr.smhrd.web.AjaxBoardUpdateController;
import kr.smhrd.web.AjaxloginController;
import kr.smhrd.web.AjaxlogoutController;
import kr.smhrd.web.BaordRegisterFormController;
import kr.smhrd.web.BoardContentController;
import kr.smhrd.web.BoardDeleteController;
import kr.smhrd.web.BoardListController;
import kr.smhrd.web.BoardLoginController;
import kr.smhrd.web.BoardLogoutController;
import kr.smhrd.web.BoardRegisterController;
import kr.smhrd.web.BoardUpdateControoler;
import kr.smhrd.web.CalcController;
import kr.smhrd.web.Controller;

public class HandlerMapping {
	//<K, V> = key, value(알바생) 
	//BoardListController()들의 부모는 controller(인터페이스)
	//여러가지 타입을 받는 다형성이다
	//frontcontroller에게 알바생을 찾아주는 역할! (해쉬테이블(해쉬매핑))
	private HashMap<String, Controller> mappings;
	
	public HandlerMapping() {    //생성자
		mappings = new HashMap<String, Controller>();
		//              key              value                        
		mappings.put("/list.do", new BoardListController());
		mappings.put("/ajaxlist.do", new AjaxBoardListController());
		mappings.put("/Register.do", new BoardRegisterController());
		mappings.put("/ajaxregister.do", new AjaxBoardRegisterController());
		mappings.put("/RegisterForm.do", new BaordRegisterFormController());
		mappings.put("/update.do", new BoardUpdateControoler());
		mappings.put("/ajaxupdate.do", new AjaxBoardUpdateController());
		mappings.put("/content.do", new BoardContentController());
		mappings.put("/ajaxcontent.do", new AjaxBoardContentController());
		mappings.put("/delete.do", new BoardDeleteController());
		mappings.put("/ajaxdelete.do", new AjaxBoardDeleteController());
		mappings.put("/calc.do", new CalcController());
		mappings.put("/ajaxlogin.do", new AjaxloginController());
		mappings.put("/ajaxlogout.do", new AjaxlogoutController());
		mappings.put("/login.do", new BoardLoginController());
		mappings.put("/logout.do", new BoardLogoutController());
		
	}
	public Controller getController(String command) {   //command-> List.do(key)
		return mappings.get(command);          //여기서 value값을 리턴?
	}
	
}
/*				
		if(command.equals("/list.do")) {
			controller = new BoardListController();
		}else if(command.equals("/Register.do")) {   //insert
			controller = new BoardRegisterController();
			//.do로 갈때는 sendRedirect  jsp로갈때는 포워드!
		}else if(command.equals("/RegisterForm.do")) {
			controller = new BaordRegisterFormController();
		}else if(command.equals("/update.do")) {
			controller = new BoardUpdateControoler();
		}else if(command.equals("/content.do")) {
			controller = new BoardContentController();
		}else if(command.equals("/delete.do")) {
			controller = new BoardDeleteController();
			//.do로 갈때는 sendRedirect  jsp로갈때는 포워드!
		}  */
