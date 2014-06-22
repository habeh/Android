package ir.home.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.xml.crypto.Data;

import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

import ir.home.model.TbMessage;
import ir.home.model.TbUser;
import ir.home.webservice.MessageService;
import ir.home.webservice.UserService;

public class MessageController {

	public void InsertMessage(int categoryid, int userId, String description)
			throws IOException, XmlPullParserException {
		HashMap<String, Object> params = new HashMap<String, Object>();

		params.put("categoryId", categoryid);
		params.put("userId", userId);
		params.put("description", description);

		new MessageService().callMethod("InsertMessage", params);
	}

	public int CountNewMessage(String date) throws IOException,
			XmlPullParserException {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("LastReadMessage", date);
		Object result = new MessageService().callMethod("CountNewMessage",
				params);
		return Integer.parseInt(result.toString());
	}

	public List<TbMessage> ShowOnlineMessage(String date) throws IOException,
			XmlPullParserException {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("lastUpdateMessage", date);
		SoapObject result = (SoapObject)new MessageService().callMethod("ReadMessage",
				params);
		return TbMessage.ToList(result);
	}

	public int CountLike(int messageid) throws IOException,
			XmlPullParserException {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("messageid", messageid);
		Object result = new MessageService()
				.callMethod("CountLike", params);
		return Integer.parseInt(result.toString());
	}

	public void LikeMessage(int userid, int messageid) throws IOException,
			XmlPullParserException {
		HashMap<String, Object> params = new HashMap<String, Object>();

		params.put("userId", userid);
		params.put("messageId", messageid);

		new MessageService().callMethod("LikeMessage", params);
	}

}