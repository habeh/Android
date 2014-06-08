package ir.home.webservice;

public class UserService extends WebService {

	@Override
	protected String getURL() {
		return URL + "UserService.asmx";
	}
}
