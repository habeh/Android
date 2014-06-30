package ir.home.webservice;

public class CommentService extends WebService {
	@Override
	protected String getURL() {
		return URL + "CommentService.asmx";
	}
}