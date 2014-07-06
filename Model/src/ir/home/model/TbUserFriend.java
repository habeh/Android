package ir.home.model;

import java.util.ArrayList;
import java.util.List;
import org.ksoap2.serialization.SoapObject;
public class TbUserFriend{

    private int id;
    private int userId;
    private int friendId;
    private String accept;
    private String userName;
    private String frienduserName;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getFriendId() {
        return friendId;
    }
    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }
    
    public String getAccept() {
        return accept;
    }
    public void setAccept(String accept) {
        this.accept = accept;
    }
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
        
    public String getFriendUserName() {
        return frienduserName;
    }
    public void setFriendUserName(String frienduserName) {
        this.frienduserName = frienduserName;
    }
    
    
    
    private static TbUserFriend parseSOAPObject(SoapObject obj) {
        TbUserFriend result = null;
        if (obj != null) {
            result = new TbUserFriend();
            result.setId(Integer.parseInt(obj
                    .getPrimitivePropertySafelyAsString("Id")));
            result.setUserId(Integer.parseInt(obj
                    .getPrimitivePropertySafelyAsString("UserId")));
            result.setFriendId(Integer.parseInt(obj
                    .getPrimitivePropertySafelyAsString("FriendId")));
            result.setAccept(obj
                    .getPrimitivePropertySafelyAsString("Accept"));
            result.setUserName(obj
                    .getPrimitivePropertySafelyAsString("UserName"));
            result.setFriendUserName(obj
                    .getPrimitivePropertySafelyAsString("FriendUserName"));
        }
        return result;
    }

    public static TbUserFriend ToEntity(SoapObject obj) {
        return parseSOAPObject(obj);
    }

    public static List<TbUserFriend> ToList(SoapObject obj) {
        List<TbUserFriend> result = null;
        if (obj != null) {
            result = new ArrayList<TbUserFriend>();
            int count = obj.getPropertyCount();
            for (int i = 0; i < count; i++) {
                SoapObject userfriendObj = (SoapObject) obj.getProperty(i);
                TbUserFriend friendlist = parseSOAPObject(userfriendObj);
                result.add(friendlist);
            }
        }

        return result;
    }
}
