package ir.home.controller;

import java.io.IOException;
import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

import ir.home.model.TbUser;
import ir.home.webservice.UserService;

public class UserController {
	
	public void register(String userName,String email,String password) throws IOException, XmlPullParserException{		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		params.put("email", email);
		params.put("password", password);
		params.put("userName", userName);
		
		new UserService().callMethod("Register", params);
	}
	
    public void sendForgiveInformation(String email) throws IOException, XmlPullParserException
    {
		HashMap<String,Object> params = new HashMap<String,Object>();		
		params.put("email", email);		
		new UserService().callMethod("SendForgiveInformation", params);
    }
    
	public TbUser login(String userName,String password) throws IOException, XmlPullParserException{
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("userName", userName);
		params.put("password", password);
		SoapObject result = new UserService().callMethod("Login", params);
		return new TbUser(result);
	}
	
    public TbUser getProfile(int userId) throws IOException, XmlPullParserException
    {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		SoapObject result = new UserService().callMethod("GetProfile", params);
		return new TbUser(result);
    }
    

}
