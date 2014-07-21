package ir.home.webservice.json;

public class MessageService extends WebService {
    @Override
    protected String getURL() {
        return URL + "JsonMessageService.asmx";
    }
}