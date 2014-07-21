
package ir.home.controller;

import ir.home.model.TbCategory;
import ir.home.model.TbMessage;
import ir.home.utility.HabehException;
import ir.home.utility.MethodResult;
import ir.home.webservice.MessageService;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;


public class MessageController {

    public void InsertMessage(int categoryid, int userId, String description)
            throws IOException, XmlPullParserException, HabehException {
        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("categoryId", categoryid);
        params.put("userId", userId);
        params.put("description", description);

        new MessageService().callMethod("InsertMessage", params);
    }

    public int CountNewMessage(String date) throws IOException,
            XmlPullParserException, HabehException {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("LastReadMessage", date);
        Object result = new MessageService().callMethod("CountNewMessage",
                params);
        return Integer.parseInt(result.toString());
    }

    public List<TbMessage> ShowOnlineMessage(String date) throws IOException,
            XmlPullParserException, HabehException {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("lastUpdateMessage", date);
        SoapObject result = (SoapObject) new MessageService().callMethod(
                "ReadMessage", params);
        return TbMessage.ToList(result);
    }


    public int CountLike(int messageid) throws IOException,
            XmlPullParserException, HabehException {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("messageid", messageid);
        Object result = new MessageService().callMethod("CountLike", params);
        return Integer.parseInt(result.toString());
    }

    public void LikeMessage(int userid, int messageid) throws IOException,
            XmlPullParserException, HabehException {
        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("userId", userid);
        params.put("messageId", messageid);

        new MessageService().callMethod("LikeMessage", params);
    }

    public List<TbCategory> retrieveCategoryList() throws IOException, XmlPullParserException,
            HabehException {
        SoapObject result = (SoapObject) new MessageService().callMethod(
                "RetrieveCategoryList", null);
        return TbCategory.ToList(result);
    }

    public List<TbCategory> retrieveCategoryUsedList() throws IOException, XmlPullParserException,
            HabehException {
        SoapObject result = (SoapObject) new MessageService().callMethod(
                "RetrieveCategoryUsedList", null);
        return TbCategory.ToList(result);
    }

    public List<TbMessage> ReadUserMessage(int userId) throws IOException, XmlPullParserException,
            HabehException {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        SoapObject result = (SoapObject) new MessageService().callMethod("ReadUserMessage", params);
        return TbMessage.ToList(result);
    }

}
