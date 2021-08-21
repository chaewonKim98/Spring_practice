package kr.smhrd.domain;

import lombok.Data;

//게시판 1개의 구조(vo)
@Data       //데이터를 쓰면 롬복을 통해서  getter, setter를 자동으로 가져올수있음
public class BoardVO {
	private int idx;
	private String title;
	private String contents;
	private int count;
	private String writer;
	private String indate;
	//getter, setter, toString()
	//"String" 클래스나 "File"클래스에서는 "toString"에 메소드를 재정의 하여 의미있는 값을 리턴


	
	
	
}
