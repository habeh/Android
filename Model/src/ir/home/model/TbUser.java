package ir.home.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.ksoap2.serialization.SoapObject;

/*User Class*/
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
	private int statusId;

	public TbUser() {

	}

	public TbUser(SoapObject obj) {
		parseSOAPObject(obj);
	}

	private void parseSOAPObject(SoapObject obj) {
		if (obj != null) {
			this.setId(Integer.parseInt(obj
					.getPrimitivePropertySafelyAsString("Id")));
			this.setUserName(obj.getPrimitivePropertySafelyAsString("UserName"));
			this.setPassword(obj.getPrimitivePropertySafelyAsString("Password"));
			this.setFirstName(obj.getPrimitivePropertySafelyAsString("FirstName"));
			this.setLastName(obj.getPrimitivePropertySafelyAsString("LastName"));
			this.setEmail(obj.getPrimitivePropertySafelyAsString("Email"));
			this.setPhoneNo(obj.getPrimitivePropertySafelyAsString("PhoneNo"));
			this.setLocation(obj.getPrimitivePropertySafelyAsString("Location"));
			this.setPicture(obj.getPrimitivePropertySafelyAsString("Picture"));
			
			String date=obj.getPrimitivePropertySafelyAsString("RegisterDate");
			try {
				Date d =DateFormat.getInstance().parse(date);
				this.setRegisterDate(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}			
			
			this.setStatusId(Integer.parseInt(obj.getPrimitivePropertySafelyAsString("StatusId")));
		}
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

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
}
