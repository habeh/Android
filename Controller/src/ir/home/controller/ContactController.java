package ir.home.controller;

import ir.home.model.TbMessage;
import ir.home.webservice.ContactService;
import ir.home.webservice.MessageService;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

public class ContactController {

	public void Create( int userId, String description)
			throws IOException, XmlPullParserException {
		HashMap<String, Object> params = new HashMap<String, Object>();

		
		params.put("userId", userId);
		params.put("description", description);

		new ContactService().callMethod("Create", params);
	}

}
