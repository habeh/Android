
package ir.home.webservice;

import ir.home.utility.HabehException;

import java.io.IOException;
import java.util.HashMap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class WebService {
    protected final String NAMESPACE = "http://tempuri.org/";
   // protected final String URL = "http://www.fpkaroon.com/";
    protected final String URL = "http://10.0.2.2:6694/";

    protected String getURL() {
        return URL + "Service.asmx";
    }

    public Object callMethod(String methodName,
            HashMap<String, Object> params) throws IOException,
            XmlPullParserException, HabehException {

        SoapObject request = new SoapObject(NAMESPACE, methodName);

        if (params != null && params.size() > 0)
            for (String key : params.keySet()) {
                request.addProperty(key, params.get(key));
            }

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(getURL());

        androidHttpTransport.call(NAMESPACE + methodName, envelope);

        Object response = envelope.getResponse();
        if(response instanceof SoapObject){
            SoapObject obj= (SoapObject) response;
            
            Boolean hasError =Boolean.valueOf(obj.getPrimitivePropertySafelyAsString("HasError"));
            if(hasError){
                String msg  =obj.getPrimitivePropertySafelyAsString("Message");
                throw new HabehException(msg);
            }
            else
            {
                return obj.getPropertySafely("Data");   
            }            
        }
        else
        {
            throw new HabehException("web service method must return MethodResult");
        }
    }
}
