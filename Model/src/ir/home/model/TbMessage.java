package ir.home.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ksoap2.serialization.SoapObject;


public class TbMessage {
	private int id;
	private int CategoryId;
	private int UserId;
	private int CountLike;
	private String Description;
	private Date RegisterDate;
	private Date SendDate;
	private int NewMessageCount;
	private String Title;
	
	

	public TbMessage() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return CategoryId;
	}

	public void setCategoryId(int CategoryId) {
		this.CategoryId = CategoryId;
	}

	public int getCountLike() {
		return CountLike;
	}

	public void setCountLike(int CountLike) {
		this.CountLike = CountLike;
	}
	
	public int getUserId() {
		return UserId;
	}

	public void setUserId(int UserId) {
		this.UserId = UserId;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	
	public Date getRegisterDate() {
		return RegisterDate;
	}

	public void setRegisterDate(Date RegisterDate) {
		this.RegisterDate = RegisterDate;
		
	}

	
	public Date getSendDate() {
		return RegisterDate;
	}

	public void setSendDate(Date SendDate) {
		this.SendDate = SendDate;
	}
	
	public int getnewMessageCount() {
		return NewMessageCount;
	}

	public void setNewMessageCount(int NewMessageCount) {
		this.NewMessageCount = NewMessageCount;
	}
	
	public String getTitle() {
		return Title;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

	private static TbMessage parseSOAPObject(SoapObject obj) {
		TbMessage result = null;
		if (obj != null) {
			result = new TbMessage();
			result.setNewMessageCount(Integer.parseInt(obj
					.getPrimitivePropertySafelyAsString("Count")));
			result.setCountLike(Integer.parseInt(obj
					.getPrimitivePropertySafelyAsString("CountLike")));
			result.setId(Integer.parseInt(obj
					.getPrimitivePropertySafelyAsString("Id")));
			result.setCategoryId(Integer.parseInt(obj
					.getPrimitivePropertySafelyAsString("CategoryId")));
			result.setUserId(Integer.parseInt(obj
					.getPrimitivePropertySafelyAsString("UserId")));
			result.setDescription(obj
					.getPrimitivePropertySafelyAsString("Description"));
			result.setTitle(obj
					.getPrimitivePropertySafelyAsString("Title"));
			
			String regdate = obj.getPrimitivePropertySafelyAsString("RegisterDate");
			String senddate = obj.getPrimitivePropertySafelyAsString("SendDate");
			try {
				Date d = DateFormat.getInstance().parse(regdate);
				Date d1=DateFormat.getInstance().parse(senddate);
				result.setRegisterDate(d);
				result.setSendDate(d1);
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
        	result=new ArrayList<TbMessage>();
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
