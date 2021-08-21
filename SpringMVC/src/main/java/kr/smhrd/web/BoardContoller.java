package kr.smhrd.web;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.smhrd.domain.BoardVO;
import kr.smhrd.domain.MinVO;
import kr.smhrd.domain.SearchVO;
import kr.smhrd.mapper.BoardMapper;
//alt+f5 = 리프레쉬
//POJO
@Controller
public class BoardContoller { //new BoardController(); => Spring Container(DI)
	//@Autowired      //DI->이거 해주면 알아서 찾아감
	//@RequestMapping이거를 겟방식인지 포스트방식인지에 따라서 겟매핑, 포스트매핑 이러케 바꿔도 됨!!
	@Inject
	private BoardMapper boardmapper;
   //게시판 리스트를 가져오는 동작
   @GetMapping("/boardList.do") //핸들러 매핑 ,jsp이름이 된다.그래서 jsp이름과 똑같이 해야됨
   public void boardList(Model model) {//리턴문을 쓰지 않아도 저이름으로 된 jsp로 찾아간다.void타입으로 해도됨!
      // TO-DO             
	  List<BoardVO> list = boardmapper.boardList();
      model.addAttribute("list", list);   //객체바인딩  -> ModelAndView -> Model
     // return "boardList"; //  -->ViewResolver-->/WEB-INF/views/boardList.jsp
   }
   
   @RequestMapping("/boardListAjax.do")
   public @ResponseBody List<BoardVO> boardListAjax(){ //@ResponseBody: JSON으로 반환하겠다(문자열로 반환)
	   List<BoardVO> list = boardmapper.boardListAjax(); //게시판 전체 리스트 가져오기
	   return list;  //객체를 리턴 ---JSON(API)형식으로!--> String으로는 보낼수 있다!그래서 변환!
   }
   
   @RequestMapping("/boardForm.do") //여러개의 컨트롤러 필요없이 하나 더 써주면 됨
   public void boardForm() {
	   //return "boardForm";    //boardForm.jsp
   }
   
   @PostMapping("/boardInsert.do")
   public String boardInsert(BoardVO vo) {  //파라메터수집(자동) -> new BoardVO();
      boardmapper.boardInsert(vo);
      return "redirect:/boardList.do";
   }
   
   @RequestMapping("/boardContent.do")
   public void boardContent(int idx, Model model) {  //파라메터수집(자동) -> new BoardVO();
	  BoardVO vo = boardmapper.boardContent(idx);
	  model.addAttribute("vo", vo);      //model : request.setAtribute랑 같음! 요청받으면 저장을 해서 넘겨준다 
      //return "boardContent";
   }
   
   @RequestMapping("/boardDelete.do")
   public String boardDelete(int idx) {  //파라메터수집(자동) -> new BoardVO();
	  boardmapper.boardDelete(idx);
      return "redirect:/boardList.do";
   }
   //@ResponseBody: json형태로 보내려고! 문자열로 변환시켜서 바로 요청을 하는 쪽으로 callback함수로 보내짐
   
   @RequestMapping("/boardDeleteAjax.do")
   public @ResponseBody int boardDeleteAjax(int idx) {  //파라메터수집(자동) -> new BoardVO();
	  int cnt = boardmapper.boardDeleteAjax(idx);
      return cnt;
   }
   
   
   @RequestMapping("/boardUpdate.do")
   public String boardUpdate(BoardVO vo) {
	   boardmapper.boardUpdate(vo);
      return "redirect:/boardList.do";
   }
   @RequestMapping("/MinInsert.do")
   public String MinInsert(MinVO vo) {  //파라메터수집(자동) -> new BoardVO();
      boardmapper.MinInsert(vo);
      return "redirect:/gggggggg.do";
   }
   @RequestMapping("/boardSerch.do")
   public String boardSearch(SearchVO vo, Model model) {
	   List<BoardVO> list = boardmapper.boardSearch(vo);
	   model.addAttribute("list", list);
	 
	   return "boardList";
   }
   
   
}