package ir.home.test.controller;

import static org.junit.Assert.*;
import java.io.IOException;
import ir.home.controller.UserController;

import org.junit.Before;
import org.junit.Test;
import org.xmlpull.v1.XmlPullParserException;

public class UserControllerTest {

    
    
	@Test
	public void testRegister() {
		try {
			new UserController().register("karim", "karim@yahoo.com", "123");
		} catch (IOException e) {
			fail(e.toString());
		} catch (XmlPullParserException e) {
			fail(e.toString());
		}
	}

	public void testSendForgiveInformation() {
		fail("Not Impelemented");
	}

	public void testLogin() {
		fail("Not Impelemented");
	}

	public void testGetProfile() {
		fail("Not Impelemented");
	}
}