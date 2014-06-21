package ir.home.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.xml.crypto.Data;

import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

import sun.rmi.runtime.Log;

import ir.home.model.TbMessage;
import ir.home.model.TbUser;
import ir.home.webservice.MessageService;
import ir.home.webservice.UserService;

public class MessageController {
	
	public void InsertMessage(int categoryid,int userId,String description) throws IOException, XmlPullParserException{		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		params.put("categoryId", categoryid);
		params.put("userId", userId);
		params.put("description", description);
		
		new MessageService().callMethod("InsertMessage", params);
	}
	
	
	
	
	
	public TbMessage NewMessageCount(String date) throws IOException, XmlPullParserException
    {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("LastReadMessage", date);
		SoapObject result = new MessageService().callMethod("CountNewMessage", params);
		return TbMessage.ToEntity(result);
    }
	
	public List<TbMessage> ShowOnlineMessage(String date) throws IOException, XmlPullParserException
    {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("lastUpdateMessage", date);
		SoapObject result = new MessageService().callMethod("ReadMessage", params);
		return TbMessage.ToList(result);
    }
	
	
	public TbMessage LikeCount(int messageid) throws IOException, XmlPullParserException
    {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("messageid", messageid);
		SoapObject result = new MessageService().callMethod("CountLike", params);
		return TbMessage.ToEntity(result);
    }
	
	
	public void Like(int userid,int messageid ) throws IOException, XmlPullParserException{		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		params.put("userId", userid);
		params.put("messageId", messageid);
		
		new MessageService().callMethod("LikeMessage", params);
	}
	
}
