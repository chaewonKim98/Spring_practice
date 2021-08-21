package kr.smhrd.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BoardDAOMybatis {
	// config.xml을 읽어서 프로포티스의 객체를 가르키는 메서드를 만남
	//
	private static SqlSessionFactory sqlSessionFactory; // SqlSessionFactory(커넥션들이 들어있음)커넥션풀을 만드는 작업
	// 0 0 0 0 0

	static { // 초기화 블록 이클래스가 실행할때 무조건 딱한번 자동으로 실행됨
		try { // 아이디비번이 다를수 있기때문에 예외처리가 필요하다
			String resource = "kr/smhrd/mybatis/config.xml"; // resource변수에 경로를줌 여기서 읽어들임 밑에 input
			InputStream inputStream = Resources.getResourceAsStream(resource); // inputStream는 읽어들이는변수(input)
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); // 빌드가 읽어서 컨피그에 있는일을하고
																					// sqlSessionFactory에 결과를 넣어줌
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// static
		// getResourceAsStream: 파일을 얻어오는것

	public List<BoardVO> boardList() {
		SqlSession sqlSession = sqlSessionFactory.openSession(); // 커넥션 꺼내옴
		// String spl = "select idx,title,count ,contents, writer,
		// DATE_FORMAT(indate,'%Y-%m-%d')AS indate from tbl_board;";
		List<BoardVO> list = sqlSession.selectList("boardList"); // 보드매퍼의 아이디값을 찾아서 그 sql을 실행해준다
		sqlSession.close(); // 반납
		return list;
	}

	public int boardInsert(BoardVO vo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int cnt = sqlSession.insert("boardInsert", vo);
		sqlSession.commit(); // select빼고는 커밋을 해줘야함!(완료)
		sqlSession.close();
		return cnt;
	}

	public int boardDelete(int idx) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int cnt = sqlSession.delete("boardDelete", idx);
		sqlSession.commit(); // select빼고는 커밋을 해줘야함!(완료)
		sqlSession.close();
		return cnt;
	}

	public BoardVO boardContent(int idx) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		BoardVO vo = sqlSession.selectOne("boardContent", idx);
		sqlSession.close();
		return vo;
	}

	public int boardUpdate(BoardVO vo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int cnt = sqlSession.update("boardUpdate", vo);
		sqlSession.commit(); // select빼고는 커밋을 해줘야함!(완료)
		sqlSession.close();
		return cnt;
	}

	public UserVO loginMember(UserVO vo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		vo = sqlSession.selectOne("loginMember",vo);
		sqlSession.close();
		return vo;
		

	}
}
