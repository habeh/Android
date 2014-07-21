
package ir.home.controller.json;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import ir.home.model.TbMessage;
import ir.home.utility.HabehException;
import ir.home.webservice.json.MessageService;

public class MessageController {

    public int CountNewMessage(String date) throws IOException,
            XmlPullParserException, HabehException, JSONException {
        
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("LastReadMessage", date);
        String jsonResult = new MessageService().callMethod("CountNewMessage",
                params);
        org.json.JSONObject jobj = new JSONObject(jsonResult);

        if (jobj.getBoolean("HasError"))
        {
            throw new HabehException(jobj.getString("Message"));
        }
        else
        {
            return jobj.getInt("Data");
        }
    }

    public List<TbMessage> ShowOnlineMessage(String date) throws IOException,
            XmlPullParserException, JSONException, HabehException {

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("lastUpdateMessage", date);
        String jsonResult = new MessageService().callMethod("ReadMessage", params);

        org.json.JSONObject jobj = new JSONObject(jsonResult);

        if (jobj.getBoolean("HasError"))
        {
            throw new HabehException(jobj.getString("Message"));
        }
        else
        {
            JSONArray datas = jobj.getJSONArray("Data");
            return TbMessage.ToList(datas);
        }
    }

}
