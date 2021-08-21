package kr.smhrd.model;
//JDBC -> X -> MyBatis Framework
import java.sql.*;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
public class BoardDAO {
	
	private PreparedStatement ps;
	private ResultSet rs;
	
	private static Connection getConnect() throws Exception{
		String URL = "jdbc:mysql://localhost:3306/mysql";
		String user = "root";
		String password = "12345";
		//MySQL Driver Loading(jar)
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn= DriverManager.getConnection(URL,user,password);
		return conn;
	}
	
	//bordList()
	public ArrayList<BoardVO> boardList() throws Exception{
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn =getConnect();
		String sql = "select idx,title,count ,contents, writer, DATE_FORMAT(indate,'%Y-%m-%d')AS indate from tbl_board;";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			int idx = rs.getInt("idx");
			String title = rs.getString("title");
			String contents = rs.getString("contents");
			String writer = rs.getString("writer");
			int count = rs.getInt("count");
			String indate = rs.getString("indate");
			
			BoardVO vo = new BoardVO();
			vo.setIdx(idx);
			vo.setTitle(title);
			vo.setContents(contents);
			vo.setWriter(writer);
			vo.setCount(count);
			vo.setIndate(indate); //Date -> String
			list.add(vo);
		}
		return list;
		
	}
	//boardInsert()
	public int boardInsert(BoardVO vo) throws Exception{	
		Connection conn =getConnect();
		String sql = "insert into tbl_board(title,contents,writer) values(?,?,?);";
		ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getTitle());
		ps.setString(2, vo.getContents());
		ps.setString(3, vo.getWriter());
		
		int cnt = ps.executeUpdate();
		return cnt;
	}
	
	//boardDelete()
	public int boardDelete(int idx) throws Exception{
		Connection conn =getConnect();
		String sql = "delete from tbl_board where idx=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, idx);
		int cnt = ps.executeUpdate();
		return cnt;
	}

	//boardContent()
	public BoardVO boardContent(int idx)throws Exception{
		Connection conn =getConnect();
		String sql = "select idx,title,count ,contents, writer, DATE_FORMAT(indate,'%Y-%m-%d')AS indate from tbl_board where idx=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, idx);
		rs = ps.executeQuery();
		BoardVO vo = null;
		if(rs.next()) {
			//vo에 묶어주면 됨
			idx = rs.getInt("idx");
			String title = rs.getString("title");
			String contents = rs.getString("contents");
			String writer = rs.getString("writer");
			int count = rs.getInt("count");
			String indate = rs.getString("indate");
			vo = new BoardVO();
			vo.setIdx(idx);
			vo.setTitle(title);
			vo.setContents(contents);
			vo.setWriter(writer);
			vo.setCount(count);
			vo.setIndate(indate); 
			
		}
		return vo;
	}
	
	//boardUpdate()
	public int boardUpdate(BoardVO vo) throws Exception{
		Connection conn =getConnect();
		String sql ="update tbl_board set title = ?, contents = ? where idx = ?";
	      ps = conn.prepareStatement(sql);
	      ps.setString(1, vo.getTitle());
	      ps.setString(2, vo.getContents());
	      ps.setInt(3, vo.getIdx());
	      int cnt = ps.executeUpdate();
	      return cnt;
	   }

	}
	
	
	
	

/*
 JDBC -> (JAVA+SQL) : 유지보수하기 어렵다 ->SQL문장 수정하려면 java소스코드를 열어서 수정해야됨(개발속도 느림)
      -> (JAVA)<---분리--->(SQL:메퍼파일(-XML)
 
 */