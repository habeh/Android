package ir.home.test.controller;

import static org.junit.Assert.*;
import ir.home.controller.MessageController;
import java.io.IOException;

import org.junit.Test;
import org.xmlpull.v1.XmlPullParserException;

public class MessageControllerTest {

	@Test
	public final void testCountNewMessage() {
		try {
			int count = new MessageController().CountNewMessage("2014-01-01");
			assertTrue(count > 0);
		} catch (IOException e) {
			fail(e.toString());
		} catch (XmlPullParserException e) {
			fail(e.toString());
		}
	}

}
