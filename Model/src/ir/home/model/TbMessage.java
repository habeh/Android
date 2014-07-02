
package ir.home.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

public class TbMessage {
    private int id;
    private int categoryId;
    private String categoryTitle;
    private int userId;
    private String userName;
    private String description;
    private Date registerDate;
    private Date sendDate;
    private int share;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getSendDateAsString() {
        if (sendDate != null)
            return DateFormat.getInstance().format(sendDate);
        return null;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    private static TbMessage parseSOAPObject(SoapObject obj) {
        TbMessage result = null;
        if (obj != null) {
            result = new TbMessage();
            result.setId(Integer.parseInt(obj
                    .getPrimitivePropertySafelyAsString("Id")));
            result.setCategoryId(Integer.parseInt(obj
                    .getPrimitivePropertySafelyAsString("CategoryId")));
            result.setUserId(Integer.parseInt(obj
                    .getPrimitivePropertySafelyAsString("UserId")));
            result.setDescription(obj
                    .getPrimitivePropertySafelyAsString("Description"));
            result.setCategoryTitle(obj
                    .getPrimitivePropertySafelyAsString("CategoryTitle"));
            String regdate = obj
                    .getPrimitivePropertySafelyAsString("RegisterDate");
            String senddate = obj
                    .getPrimitivePropertySafelyAsString("SendDate");
            result.setShare(Integer.parseInt(obj
                    .getPrimitivePropertySafelyAsString("Share")));
            try {
                Date regDate = DateFormat.getInstance().parse(regdate);
                result.setRegisterDate(regDate);

                if (!senddate.isEmpty()) {
                    Date sendDate = DateFormat.getInstance().parse(senddate);
                    result.setSendDate(sendDate);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    public static TbMessage ToEntity(SoapObject obj) {
        return parseSOAPObject(obj);
    }

    public static List<TbMessage> ToList(SoapObject obj) {
        List<TbMessage> result = null;
        if (obj != null) {
            result = new ArrayList<TbMessage>();
            int count = obj.getPropertyCount();
            for (int i = 0; i < count; i++) {
                SoapObject messageObj = (SoapObject) obj.getProperty(i);
                TbMessage newmessage = parseSOAPObject(messageObj);
                result.add(newmessage);
            }
        }

        return result;
    }
}
