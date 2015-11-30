package com.justcode.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PersonDAO {
	private Connection cn = ConnectTools.getDBConn();
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public PersonDAO() {
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Person> personList() { // 分页
		ArrayList<Person> al = new ArrayList<Person>();
		try {
			String sql = "select * from t_user order by dateCreated desc limit 1";
			ps = cn.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				Person person = new Person();
				person.setId(rs.getString("id"));
				person.setUserName(rs.getString("userName"));
				person.setEmail(rs.getString("email"));
				al.add(person);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return al;
	}

}
