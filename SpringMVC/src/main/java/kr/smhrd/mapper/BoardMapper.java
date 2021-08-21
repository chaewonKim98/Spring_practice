package kr.smhrd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.smhrd.domain.BoardVO;
import kr.smhrd.domain.MinVO;
import kr.smhrd.domain.SearchVO;
@Mapper
public interface BoardMapper {
	//SQL -- X --> SQL Mapper XML File(XML)
	public List<BoardVO> boardList(); 
	
	@Select("select * from tbl_board order by idx desc")
	public List<BoardVO> boardListAjax();
	
	public void boardInsert(BoardVO vo);  //insert SQL
	
	@Select("select * from tbl_board where idx=#{idx}")
	public BoardVO boardContent(int idx);  //Select SQL
	
	public void boardDelete(int idx);
	
	@Update("update tbl_board set title = #{title},contents = #{contents} where idx = #{idx}")
	public void boardUpdate(BoardVO vo);
	
	@Delete("delete from tbl_board where idx=#{idx}")
	public int boardDeleteAjax(int idx);
	
	@Insert("insert into min_board(id,pw,name,age) values(#{id},#{pw},#{name},#{age})")
	public void MinInsert(MinVO vo);
	
	public List<BoardVO> boardSearch(SearchVO vo);    //part=[title, writer,contents]
}
