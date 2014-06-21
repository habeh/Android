package ir.home.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

import sun.rmi.runtime.Log;

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
		return TbUser.ToEntity(result);
	}
	
    public TbUser getProfile(int userid) throws IOException, XmlPullParserException
    {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("userid", userid);
		SoapObject result = new UserService().callMethod("GetProfile", params);
		return TbUser.ToEntity(result);
    }
        
    public List<TbUser> Search(String usersearch) throws IOException, XmlPullParserException
    {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("searchText", usersearch);
		SoapObject result = new UserService().callMethod("Search", params);
		return TbUser.ToList(result);
    }
    
    
    public void  SaveProfile(String username,String firstName,String lastName,String email,String Status,String password) throws IOException, XmlPullParserException{		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		params.put("username", username);
		params.put("firstname", firstName);
		params.put("lastname", lastName);
		params.put("email", email);
		params.put("status", Status);
		params.put("password", password);
	
		
		new UserService().callMethod("SaveProfile", params);
	}
    
    
}
