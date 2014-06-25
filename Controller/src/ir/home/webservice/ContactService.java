package ir.home.webservice;

public class ContactService extends WebService {
	@Override
	protected String getURL() {
		return URL + "ContactService.asmx";
	}
}