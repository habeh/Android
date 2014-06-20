package ir.home.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

/*User Class second change*/
public class TbUser {
	private int id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNo;
	private String location;
	private String picture;
	private Date registerDate;
	private String status;

	public TbUser() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private static TbUser parseSOAPObject(SoapObject obj) {
		TbUser result = null;
		if (obj != null) {
			result = new TbUser();
			result.setId(Integer.parseInt(obj
					.getPrimitivePropertySafelyAsString("Id")));
			result.setUserName(obj
					.getPrimitivePropertySafelyAsString("UserName"));
			result.setPassword(obj
					.getPrimitivePropertySafelyAsString("Password"));
			result.setFirstName(obj
					.getPrimitivePropertySafelyAsString("FirstName"));
			result.setLastName(obj
					.getPrimitivePropertySafelyAsString("LastName"));
			result.setEmail(obj.getPrimitivePropertySafelyAsString("Email"));
			result.setPhoneNo(obj.getPrimitivePropertySafelyAsString("PhoneNo"));
			result.setLocation(obj
					.getPrimitivePropertySafelyAsString("Location"));
			result.setPicture(obj.getPrimitivePropertySafelyAsString("Picture"));

			String date = obj
					.getPrimitivePropertySafelyAsString("RegisterDate");
			try {
				Date d = DateFormat.getInstance().parse(date);
				result.setRegisterDate(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			result.setStatus(obj.getPrimitivePropertySafelyAsString("Status"));
			
		}
		return result;
	}

	public static TbUser ToEntity(SoapObject obj) {
		return parseSOAPObject(obj);
	}

	public static List<TbUser> ToList(SoapObject obj) {        	
		List<TbUser> result = null;
        if (obj != null) {
        	result=new ArrayList<TbUser>();
            int count = obj.getPropertyCount();
            for (int i = 0; i < count; i++) {
                SoapObject userObj = (SoapObject) obj.getProperty(i);
                TbUser newUser = parseSOAPObject(userObj);
                result.add(newUser);
            }
        }

        return result;
	}
}
