package ir.home.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

/*User Class second change*/
public class TbComment {
	private int id;
	private int userId;
	private int messageId;
	private Date registerDate;
	private int commenttypeId;
	private String description;

	public TbComment() {

	}

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
	
	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
	
	public int getCommentTypeId() {
		return commenttypeId;
	}

	public void setCommentTypeId(int commenttypeId) {
		this.commenttypeId = commenttypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private static TbComment parseSOAPObject(SoapObject obj) {
		TbComment result = null;
		if (obj != null) {
			result = new TbComment();
			result.setId(Integer.parseInt(obj
					.getPrimitivePropertySafelyAsString("Id")));
			result.setUserId(Integer.parseInt(obj
					.getPrimitivePropertySafelyAsString("UserId")));
			result.setMessageId(Integer.parseInt(obj
					.getPrimitivePropertySafelyAsString("MessageId")));
			result.setCommentTypeId(Integer.parseInt(obj
					.getPrimitivePropertySafelyAsString("CommentTypeId")));
			result.setDescription(obj.getPrimitivePropertySafelyAsString("Description"));
			String date = obj
					.getPrimitivePropertySafelyAsString("RegisterDate");
			try {
				Date d = DateFormat.getInstance().parse(date);
				result.setRegisterDate(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static TbComment ToEntity(SoapObject obj) {
		return parseSOAPObject(obj);
	}

	public static List<TbComment> ToList(SoapObject obj) {
		List<TbComment> result = null;
		if (obj != null) {
			result = new ArrayList<TbComment>();
			int count = obj.getPropertyCount();
			for (int i = 0; i < count; i++) {
				SoapObject userObj = (SoapObject) obj.getProperty(i);
				TbComment newComment = parseSOAPObject(userObj);
				result.add(newComment);
			}
		}

		return result;
	}
}
