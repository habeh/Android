package ir.home.model;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

/*User Class second change*/
public class TbCategory {
	private int id;
	private String title;

	public TbCategory() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private static TbCategory parseSOAPObject(SoapObject obj) {
		TbCategory result = null;
		if (obj != null) {
			result = new TbCategory();
			result.setId(Integer.parseInt(obj
					.getPrimitivePropertySafelyAsString("Id")));
			result.setTitle(obj.getPrimitivePropertySafelyAsString("Title"));
		}
		return result;
	}

	public static TbCategory ToEntity(SoapObject obj) {
		return parseSOAPObject(obj);
	}

	public static List<TbCategory> ToList(SoapObject obj) {
		List<TbCategory> result = null;
		if (obj != null) {
			result = new ArrayList<TbCategory>();
			int count = obj.getPropertyCount();
			for (int i = 0; i < count; i++) {
				SoapObject userObj = (SoapObject) obj.getProperty(i);
				TbCategory newUser = parseSOAPObject(userObj);
				result.add(newUser);
			}
		}

		return result;
	}
}
