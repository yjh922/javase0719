package org.sp.app0719.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//오직 Member2 테이블에 대한 CRUD를 처리한는 DAO 객체
public class Member2DAO {
	String url="jdbc:mysql://localhost:3306/javase?characterEncoding=utf8";
	String user="root";
	String password="1234";
	
	//레코드 1건 등록
	public int insert(Member2 member2) {
		Connection con=null;//접속 성공 후 그 정보를 가진 객체 따라서 접속을 해제할때 이 객체를 이용할 수 있다.
		PreparedStatement pstmt=null;//쿼리 수행 객체
		int result=0;
		
		try {
			//드라이버 로드
			Class.forName("com.mysql.jdbc.Driver");
			
			//접속
			con=DriverManager.getConnection(url, user, password);
			if(con==null) {
				System.out.println("접속 실패");
			}else {
				System.out.println("접속 성공");
				
				String sql="insert into member2(id, pass, name, email)";
				sql+=" values('"+member2.getId()+"','"+member2.getPass()+"','"+member2.getName()+"','"+member2.getEmail()+"')";
				
				//System.out.println(sql);
				pstmt=con.prepareStatement(sql);
				result=pstmt.executeUpdate();
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//이 메서드를 호출한 자가 그 결과를 알 수 있도록 반환하자.
		return result;
	}
	
	//모든 레코드 가져오기
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member2> list=new ArrayList<Member2>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection(url, user, password);
			
			String sql="select * from member2";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();//쿼리 실행 및 표 반환
			
			//rs는 곧 닫히므로 rs를 대체할 DTO와List를 이용하여 데이터르 옮겨담자.
			while(rs.next()) {//레코드가 존재하는 만큼
				Member2 dto=new Member2();
				
				dto.setMember2_idx(rs.getInt("member2_idx"));
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				
				list.add(dto);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return list;
		
	}
	
	//한 사람에 대한 정보 가져오기
	public Member2 login(Member2 member2) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member2 dto=null;
		
		try {
			//드라이버 로드
			Class.forName("com.mysql.jdbc.Driver");
			
			//접속
			con=DriverManager.getConnection(url, user, password);
			if(con==null) {
				System.out.println("접속 실패");
			}else {
				
				String sql=" select * from member2";
				sql+=" where id='"+member2.getId()+"' and pass='"+member2.getPass()+"'";
				//System.out.println(sql);
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();//쿼리실행 및 표 반환
				
				//로그인 성공시 레코드는 언제나 1건이므로 rs의 next()메서드 호출시 true가 봔환될지 false가 반환될지에 따라 로그인 성공, 실패 여부를 따져본다.
				if(rs.next()) {
					//성공
					dto=new Member2();
					//텅빈 DTO에 로그인 성공한 회원의 정보를 옮겨심자.
					dto.setMember2_idx(rs.getInt("member2_idx"));
					dto.setId(rs.getString("id"));
					dto.setPass(rs.getString("pass"));
					dto.setName(rs.getString("name"));
					dto.setEmail(rs.getString("email"));
				
				}
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return dto;
	}
	
	//1건 수정
	public void update() {
		
	}
	
	//1건 삭제
	public void delete() {
		
	}
}
