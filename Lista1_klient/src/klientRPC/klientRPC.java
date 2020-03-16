package klientRPC;
import java.util.Vector;
import java.util.Scanner;


import org.apache.xmlrpc.XmlRpcClient;

public class klientRPC {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String func="";
		String arg="";
		try {
			System.out.println("Podaj ip serwera");
			Scanner scan = new Scanner(System.in);
			String ip =scan.nextLine();
			System.out.println("Podaj port serwera");
			String port = scan.nextLine();
			
			XmlRpcClient srv = new XmlRpcClient(ip+":"+port);
			//XmlRpcClient srv = new XmlRpcClient("http://localhost:10001");
			
			AC cb = new AC();			
			Object results = srv.execute("MojSerwer.shows",new Vector<Object>(0));
			System.out.println(results);
			System.out.println("Podaj nazwê funkcji:");
			func=scan.nextLine();
			System.out.println("Podaj argumenty po przecinku:");
			arg=scan.next();
			String[] args0 =arg.split(",");
			Vector<Object> params = new Vector<Object>();
			for (int i=0;i<args0.length;i++)
			{
				if(tryParseInt(args0[i])) {
					
				params.addElement(Integer.parseInt(args0[i]));
				}
				else
					params.addElement(args0[i]);
			}
			invoke(srv,"MojSerwer."+func,params,cb);
			
			
			/*
			XmlRpcClient srv = new XmlRpcClient("http://localhost:10001");
			Vector<Integer> params = new Vector<Integer>();
			params.addElement(new Integer(13));
			params.addElement(new Integer(21));
			Object results = srv.execute("MojSerwer.echo",params);
			int wynik=((Integer) results).intValue();
			System.out.println(wynik);
			AC cb = new AC();
			Vector <Integer> params2 = new Vector<Integer>(0);
			params2.addElement(new Integer(3000));
			srv.executeAsync("MojSerwer.execAsy", params2, cb);
			System.out.println("Wywolano asynchronicznie");
			params2.addElement(new Integer(30));
			srv.executeAsync("MojSerwer.addAsync", params2, cb);
			*/
		}
		catch(Exception exception)
		{
			System.out.println("KLient xml-rpc: "+exception);
		}
	}
public static void invoke(XmlRpcClient srv, String metod, Vector<Object> arg, AC cb)
{
	try {
	if (metod.contains("Asy"))
	{
		srv.executeAsync(metod,arg,cb);
	}
	else
	{
		Object result = srv.execute(metod, arg);
		System.out.println(result);
	}
	}
	catch (Exception e)
	{
		System.out.println(e);
	}
}
static boolean  tryParseInt(String value) {
	try {
		Integer.parseInt(value);
		return true;
	}
	catch (NumberFormatException e)
	{
		return false;
	}
	
}
}
