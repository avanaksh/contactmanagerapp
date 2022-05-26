package com.iasri.contact.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.iasri.contact.model.Contact;

public class ContactDAOImpl implements ContactDAO {

	private JdbcTemplate jdbcTemplate;

	public ContactDAOImpl(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}

	@Override
	public int save(Contact contact) {
		// TODO Auto-generated method stub
		//String sql="insert into contact(name,email,address,telephone)values(?,?,?,?)";
		//return jdbcTemplate.update(sql,contact.getName(),contact.getEmail(),contact.getAddress(),contact.getPhone());		
		int rows=0;
		if (contact.getId() > 0) {
	        // update
	        String sql = "UPDATE contact SET name=?, email=?, address=?, "
	                    + "telephone=? WHERE contact_id=?";
	       rows= jdbcTemplate.update(sql, contact.getName(), contact.getEmail(),
	                contact.getAddress(), contact.getPhone(), contact.getId());
	    } else {
	        // insert
	        String sql = "INSERT INTO contact (name, email, address, telephone)"
	                    + " VALUES (?, ?, ?, ?)";
	       rows= jdbcTemplate.update(sql, contact.getName(), contact.getEmail(),
	                contact.getAddress(), contact.getPhone());
	    }
		return rows;
	}

	@Override
	public int update(Contact contact) {
		// TODO Auto-generated method stub
		//return 0;
		String sql="update contact set name=?, email=?,address=?,telephone=? where contact_id=?";
		return jdbcTemplate.update(sql,contact.getName(),contact.getEmail(),contact.getAddress(),contact.getPhone(), contact.getId());
	}

	@Override
	public Contact get(Integer contactId) {
		//String sql = "SELECT * FROM Contact WHERE id=" + contactId;
	    //return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Contact.class));
		String sql = "SELECT * FROM Contact WHERE id=" + contactId;
		ResultSetExtractor<Contact> extractor=new ResultSetExtractor<Contact>() {
			@Override
			public Contact extractData(ResultSet rs) throws SQLException, DataAccessException{
				if(rs.next()){
					String name=rs.getString("name");
					String email=rs.getString("email");
					String address=rs.getString("address");
					String phone=rs.getString("telephone");
					return new Contact(contactId,name,email,address,phone);
				}
				return null;				
			}			
		};
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		//return 0;
		String sql="delete from contact where contact_id=?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public List<Contact> list() {
		 //String sql = "SELECT * FROM Contact";	     
		 //return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Contact.class));  
		String sql = "SELECT * FROM Contact";
		RowMapper<Contact> mapper=new RowMapper<Contact>(){
			@Override
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException{
				if(rs.next()){
					Integer id=rs.getInt("contact_id");
					String name=rs.getString("name");
					String email=rs.getString("email");
					String address=rs.getString("address");
					String phone=rs.getString("telephone");
					return new Contact(id,name,email,address,phone);
				}
				return null;				
			}
		};
		return jdbcTemplate.query(sql, mapper);
	}
}
