
package com.basic.contrloller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

@Repository

public class PenDao {
	@Autowired
	JdbcTemplate temp;

	public List<Pen> getAll() {
		System.out.println("reached get Method");
		List<Pen> li = new ArrayList<Pen>();
		temp.query("select id,name,category from pen", new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				li.add(new Pen(rs.getInt("id"), rs.getString("name"), rs.getString("category")));
			}
		});
		return li;
	}

	public Pen getIndiviualItem(int itemId) {
		String query = "select * from pen where id=?";
		Pen p = temp.queryForObject(query, new BeanPropertyRowMapper<>(Pen.class));
		return p;
	}

	public int addItem(String name, String category) {
		String quary = "insert into pen(name,category)values(?,?)";
		return temp.update(quary, name, category);

	}

	public int deleteItem(int id) {
		String query = "delete from pen where id=?";
		return temp.update(query, id);
	}

	public int updateItem(int id, String name, String category) {
		String quary = "update pen set name=?,category=? where id=?";
		return temp.update(quary, name, category, id);
	}

}
