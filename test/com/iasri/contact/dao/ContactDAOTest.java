package com.iasri.contact.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.iasri.contact.model.Contact;

public class ContactDAOTest {
	private DriverManagerDataSource dataSource;
	private ContactDAO dao;
	
	@Before
	public void BestBeforeEach(){
		//dataSource=new DriverManagerDataSource();
		//dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		//dataSource.setUrl("jdbc:mysql://localhost:3306/contactdb");//?autoReconnect=true&useSSL=false;
		//dataSource.setUsername("root");
		//dataSource.setPassword("12345678");
	}
	
	@Test
	public void testSave() {
		//fail("Not yet implemented");
		dao=new ContactDAOImpl(dataSource);		
		Contact contact=new Contact("Bill Gates","bill@microsoft.com","Redmon WA","180023456789");		
		int result=dao.save(contact);
		System.out.println("test result : "+result);
		assertTrue(result>0);
	}

	@Test
	public void testUpdate() {
		//fail("Not yet implemented");
		Contact contact=new Contact(2,"Bill Gates","bill@microsoft.com","Redmon WA","180023456789");
		int result=dao.update(contact);
		assertTrue(result>0);
	}

	@Test
	public void testGet() {
		//fail("Not yet implemented");
		Integer id=1;
		Contact contact=dao.get(id);	
		if(contact!=null){
			System.out.println(contact);
		}
		assertNotNull(contact);
	}

	@Test
	public void testDelete() {
		//fail("Not yet implemented");
		Integer id=2;
		int result=dao.delete(id);
		assertTrue(result>0);
	}

	@Test
	public void testList() {
		//fail("Not yet implemented");
		List<Contact> listContacts=dao.list();
		for(Contact aContact:listContacts){
			System.out.println(aContact);
		}
		assertTrue(listContacts.isEmpty());
	}

}
