package ir.home.webservice;

public class MessageService extends WebService {
	@Override
	protected String getURL() {
		return NAMESPACE + "MessageService.asmx";
	}
}
