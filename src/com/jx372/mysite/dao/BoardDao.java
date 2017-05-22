package com.jx372.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jx372.mysite.vo.BoardVo;
import com.jx372.mysite.vo.UserVo;

public class BoardDao {

	private Connection getConnection() throws SQLException {

		Connection conn = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2.connection 하기
			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("sql driver를 찾을 수 없습니다.");
		}
		return conn;

	}

	public List<BoardVo> getList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<BoardVo> list = new ArrayList<BoardVo>();

		try {
			conn = getConnection();

			stmt = conn.createStatement();

			String sql = "select b.no, b.user_no, u.name, b.title, b.date, b.hit, b.g_no, b.o_no, b.depth"
					+ " from board b, user u" + " where b.user_no=u.no" + " order by b.no";

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Long no = rs.getLong(1);
				Long userNo = rs.getLong(2);
				String name = rs.getString(3);
				String title = rs.getString(4);
				String date = rs.getString(5);
				int hit = rs.getInt(6);
				int gNo = rs.getInt(7);
				int oNo = rs.getInt(8);
				int depth = rs.getInt(9);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setUserno(userNo);
				vo.setName(name);
				vo.setTitle(title);
				vo.setDate(date);
				vo.setHit(hit);
				vo.setGno(gNo);
				vo.setOno(oNo);
				vo.setDepth(depth);

				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public BoardVo get(Long no) {

		BoardVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//System.out.println("vo 가지러 오기" + "no : "+no);
		try {
			conn = getConnection();

			String sql = "select no, user_no, title, content" + " from board" + " where no=" + no;

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				Long no2 = rs.getLong(1);
				Long userNo=rs.getLong(2);
				String title = rs.getString(3);
				String content = rs.getString(4);
				vo = new BoardVo();

				vo.setNo(no2);
				vo.setUserno(userNo);
				vo.setTitle(title);
				vo.setContent(content);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	public boolean update(BoardVo vo, Long no) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			
			String sql="update board set"
					+ " title=?,"
					+ " content=?,"
					+ " date=now()"
					+ " where no="+no;

			pstmt = conn.prepareStatement(sql); 

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			
			int count = pstmt.executeUpdate(); 

			return (count == 1);

		} catch (SQLException e) {
			System.out.println("error" + e);
			return false;
		} finally {
			try {

				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	public boolean insert(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			//System.out.println("insert 작업");
			
			String sql="insert into board"
					+ " values ( null, ?, ?, ?, now(), 0,"
					+ " (select * from (select ifnull(max(g_no),0)+1 from board) a), 1, 0)";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getUserno());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());

			
			int count=pstmt.executeUpdate();
			
			return count==1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return false;
	}
	
	public boolean delete(Long bno, Long userno) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "delete from board"
					+ " where no="+bno
					+ " and user_no="+userno;

			pstmt = conn.prepareStatement(sql); 

			int count = pstmt.executeUpdate(); 

			return (count == 1);

		} catch (SQLException e) {
			System.out.println("error" + e);
			return false;
		} finally {
			try {

				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

}
