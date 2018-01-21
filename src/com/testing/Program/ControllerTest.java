package com.testing.Program;

import junit.framework.TestCase;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runners.JUnit4;

import com.shenkar.hibernate.controller.*;
import com.shenkar.hibernate.model.HibernateToDoListDAO;

public class ControllerTest<Junit> extends TestCase {

	ToDoProgramController todo = new ToDoProgramController();
	
	@Test
	public void testSingelton()
	{
		if (todo == null)
		{
			fail("server couldn't be created!");

		}
				
	}
	
	@Test
	public void testVerification()
	{
		
		//expected con to be true
		boolean con;
		con = todo.verification("check", "check2");
		assertEquals(true, con);
		
	}
	
	@Test
	public void testvalidtionCheck()
	{
		//expected con to be false
		boolean con = todo.validtionCheck("123");
		assertEquals(false,con);
		
	}
}
