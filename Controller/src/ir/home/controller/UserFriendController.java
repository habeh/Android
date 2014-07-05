package ir.home.controller;

import ir.home.model.TbUserFriend;
import ir.home.utility.HabehException;
import ir.home.webservice.UserMessageService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

public class UserFriendController {

	public void FriendSendRequest(int userId, int friendId) throws IOException,
			XmlPullParserException, HabehException {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("friendId", friendId);
		new UserMessageService().callMethod("FriendSendRequest", params);
	}
	
	
	public void CheckhasFriend(int userId, int friendId) throws IOException,
	XmlPullParserException, HabehException {
HashMap<String, Object> params = new HashMap<String, Object>();
params.put("userId", userId);
params.put("friendId", friendId);
new UserMessageService().callMethod("CheckhasFriend", params);
}

	public void FriendAcceptRequest(int id) throws IOException,
			XmlPullParserException, HabehException {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		new UserMessageService().callMethod("FriendAcceptRequest", params);
	}

	public List<TbUserFriend> FriendList(int userId) throws IOException,
			XmlPullParserException, HabehException {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		SoapObject result = (SoapObject) new UserMessageService().callMethod(
				"FriendList", params);
		return TbUserFriend.ToList(result);
	}

}
