
package ir.home.controller;

import ir.home.model.TbComment;
import ir.home.utility.HabehException;
import ir.home.webservice.CommentService;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;

public class CommentController {

    public void Create(int userId,int reportuserId, int messageId, String description,int CommenttypeId)
            throws IOException, XmlPullParserException, HabehException {
        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("userId", userId);
        params.put("reportuserId", reportuserId);
        params.put("messageId", messageId);
        params.put("description", description);
        params.put("CommenttypeId", CommenttypeId);

        new CommentService().callMethod("Create", params);
    }

    public List<TbComment> RetrieveComment(int messageId) throws IOException,
            XmlPullParserException, HabehException {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("messageId", messageId);
        SoapObject result = (SoapObject) new CommentService().callMethod(
                "Retrieve", params);
        return TbComment.ToList(result);
    }

}
