package com.iiht.capsuleproject.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iiht.capsuleproject.dao.User_Repo;
import com.iiht.capsuleproject.model.User;



public class TestUserService {
	
	
	@InjectMocks
	UserService_impl testservice;
	
	@Mock
	User_Repo testrepo;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
  @Test 
	public void findAllUsersTest() throws ParseException {
		User user1 =new User();
		User user2=new User();
		user1.setUserId(1);
		user1.setFirstName("Kayal");
		user1.setLastName("sakthi");
		user1.setEmployeeId(578446);
		user2.setUserId(2);
		user2.setFirstName("Kayalvizhi");
		user2.setLastName("Sakthivel");
		user2.setEmployeeId(327005872);
		
		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		when(testrepo.findAll()).thenReturn(users);
		// test
		List<User> userList = (List<User>) testservice.findAllUser();
		assertEquals(2, userList.size());
		verify(testrepo, times(1)).findAll();

	}
	@Test
	public void createUserTest() throws ParseException {
		User user =new User();
		user.setUserId(1);
		user.setFirstName("Kayalvizhi");
		user.setLastName("Sakthivel");
		user.setEmployeeId(327005872);
		testservice.addUser(user);
		verify(testrepo, times(1)).save(user);
	}
public void deleteuserTest() throws ParseException {
	User user =new User();
	user.setUserId(1);
	user.setFirstName("Kayal");
	user.setLastName("Sakthi");
	user.setEmployeeId(578446);
	testservice.delete(user);
	verify(testrepo, times(1)).delete(user);
}

	

}
