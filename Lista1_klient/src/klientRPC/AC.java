package klientRPC;

import java.net.URL;

import org.apache.xmlrpc.AsyncCallback;

public class AC implements AsyncCallback {

	@Override
	public void handleError(Exception arg0, URL arg1, String arg2) {
		System.out.println("Wyjatek: "+ arg0);
		
	}

	@Override
	public void handleResult(Object arg0, URL arg1, String arg2) {
		System.out.println("Rezultat:"+arg0 );
		
	}

}
