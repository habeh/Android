
package ir.home.controller;

import ir.home.model.TbUser;
import ir.home.utility.HabehException;
import ir.home.webservice.UserService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

public class UserController {

    public void register(String userName, String email, String password)
            throws IOException, XmlPullParserException, HabehException {
        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("email", email);
        params.put("password", password);
        params.put("userName", userName);

        new UserService().callMethod("Register", params);
    }

    public void sendForgiveInformation(String email) throws IOException,
            XmlPullParserException, HabehException {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("email", email);
        new UserService().callMethod("SendForgiveInformation", params);
    }

    public TbUser login(String userName, String password) throws IOException,
            XmlPullParserException, HabehException {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("userName", userName);
        params.put("password", password);
        SoapObject result = (SoapObject) new UserService().callMethod("Login",
                params);
        return TbUser.ToEntity(result);
    }

    public TbUser getProfile(int userid) throws IOException,
            XmlPullParserException, HabehException {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("userid", userid);
        SoapObject result = (SoapObject) new UserService().callMethod(
                "GetProfile", params);
        return TbUser.ToEntity(result);
    }

    public List<TbUser> Search(String usersearch) throws IOException,
            XmlPullParserException, HabehException {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("searchText", usersearch);
        SoapObject result = (SoapObject) new UserService().callMethod("Search",
                params);
        return TbUser.ToList(result);
    }

    public void SaveProfile(String username, String firstName, String lastName,
            String email, String Status,String picture) throws IOException,
            XmlPullParserException, HabehException {
        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("username", username);
        params.put("firstname", firstName);
        params.put("lastname", lastName);
        params.put("email", email);
        params.put("status", Status);
        params.put("picture", picture);

        new UserService().callMethod("SaveProfile", params);
    }

    public void ChangePassword(String userName, String oldPass, String newPass)
            throws IOException, XmlPullParserException, HabehException {
        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("userName", userName);
        params.put("oldPass", oldPass);
        params.put("newPass", newPass);

        new UserService().callMethod("ChangePassword", params);
    }

}
