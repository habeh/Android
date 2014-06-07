package ir.home.webservice;

public class MessageService extends WebService {
	@Override
	protected String getURL() {
		return URL + "MessageService.asmx";
	}
}
