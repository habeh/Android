package ir.home.webservice;

public class UserMessageService extends WebService {

	@Override
	protected String getURL() {
		return URL + "UserMessageService.asmx";
	}
}
