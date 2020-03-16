package klientRPC;
import java.util.Vector;
import java.util.Scanner;


import org.apache.xmlrpc.XmlRpcClient;

public class klientRPC {
	
	public static void main(String[] args) {
		String func="";
		String arg="";
		try {
			System.out.println("Podaj ip lub dns serwera");
			Scanner scan = new Scanner(System.in);
			String ip =scan.nextLine();
			System.out.println("Podaj port serwera");
			String port = scan.nextLine();
			
			XmlRpcClient srv = new XmlRpcClient("http://"+ip+":"+port);
			
			AC cb = new AC();			
			Object results = srv.execute("MojSerwer.shows",new Vector<Object>(0));
			
			while(true) {
			System.out.println(results);
			System.out.println("Podaj nazwê funkcji:");
			func=scan.nextLine();
			System.out.println("Podaj argumenty po przecinku:");
			arg=scan.nextLine();
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
			
			}
			
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
