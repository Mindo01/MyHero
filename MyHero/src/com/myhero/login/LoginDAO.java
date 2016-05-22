package com.myhero.login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LoginDAO {
	private static User makeUser(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getString("id"));
		user.setPassword(resultSet.getString("password"));
		user.setAccesDay(resultSet.getString("accessDay"));
		user.setUserName(resultSet.getString("userName"));
		user.setUser_id(resultSet.getString("user_id"));
		user.setLevel(resultSet.getInt("level"));
		user.setFull(resultSet.getInt("full"));
		user.setClean(resultSet.getInt("clean"));
		user.setStamina(resultSet.getInt("stamina"));
		user.setName(resultSet.getString("name"));
		user.setType(resultSet.getInt("type"));
		return user;
	}

	// 로그인 DAO
	public String validate(String id, String pw) throws Exception {
		/* 로그인 테스트용 - 그냥 통과시키기 */
		if (id.equals("admin") && pw.equals("admin"))
			return null;
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = null;
		try {
			conn = DB.getConnetion();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM User where id=?";// user테이블의 id를 검사한다.
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) { // 아이디가 있는 경우
				if (pw.equals(rs.getString("password"))) {// 비밀번호 일치하는 경우
					// 아이디에 맞게 최근접속일 업데이트 해준다.
					sql = "update user set accessDay=? where id=?";
					ps = conn.prepareStatement(sql);
					Date now = new Date();// 현재시간 가져옴.
					SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String current = form.format(now);
					ps.setString(1, current);
					ps.setString(2, id);
					ps.executeUpdate();
					result = null;
				} else {
					// 비밀번호 틀린경우
					result = "비밀번호가 틀렸습니다.";
				}
			} else {// 없는 아이디일 경우
				result = "아이디가 없습니다.";
			}

		} finally {
			if (stmt != null)
				stmt.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
			if (rs != null)
				rs.close();
		}
		return result;
	}

	// User DAO
	public User selectById(String id) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DB.getConnetion();
			// user 테이블과 캐릭터정보 테이블의 아이디가 같을 경우
			String sql = "SELECT * FROM user join characterinfo on id = user_id where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			while (rs.next())
				user = makeUser(rs);
			return user;
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}
	}

	// CharacterInfoDAO
	public boolean character(String id, String name, int type) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		try {
			conn = DB.getConnetion();
			stmt = conn.createStatement();
			// 캐릭터 테이블에 캐릭터 정보를 넣어준다.
			String sql = "insert into characterinfo values(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);// id
			ps.setInt(2, 1);// 레벨
			ps.setInt(3, 100);// full
			ps.setInt(4, 100);// clean
			ps.setInt(5, 100);// stamina
			ps.setString(6, name);// name
			ps.setInt(7, type);// 캐릭터type
			int a = ps.executeUpdate();
			if (a == 1) {
				return true;
			} else {
				return false;
			}
		} finally {
			if (stmt != null)
				stmt.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}
	}

	// 회원가입
	public String register(String id, String password, String userName) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		String result = null;
		try {
			conn = DB.getConnetion();
			stmt = conn.createStatement();
			if (duplicate(id)) {// 아이디 중복일 경우
				result = "ID중복입니다.";
			} else {// 아이디 중복 아닐 경우
				String sql = "insert into user values(?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
				ps.setString(2, password);
				Date now = new Date();
				SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String current = form.format(now);
				ps.setString(3, current);
				ps.setString(4, userName);
				int a = ps.executeUpdate();
				result = null;
			}

		} finally {
			if (stmt != null)
				stmt.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}
		return result;
	}

	// 아이디 중복일 경우
	public boolean duplicate(String id) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		try {
			conn = DB.getConnetion();
			stmt = conn.createStatement();
			// user테이블의 id를 검색
			String sql = "select * from user where id =?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {// 아이디 중복일 경우
				return true;
			} else {
				return false;
			}
		} finally {
			if (stmt != null)
				stmt.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}
	}
}