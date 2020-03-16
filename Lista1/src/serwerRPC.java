
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.xmlrpc.WebServer;


public class serwerRPC {
	public Integer echo(int x, int y)
	{
		System.out.println("Print");
		return new Integer(x+y);
	}
	public int execAsy(int x)
	{
		System.out.println("... wywo³ano asy - odliczam");
		try
		{
			Thread.sleep(x);
		}
		catch(InterruptedException exception)
		{
			exception.printStackTrace();
			Thread.currentThread().interrupt();
		}
		System.out.println("... asy - koniec odliczania");
		return 123;
	}
	
	public Integer multiplyAsync(int x, int y)
	{
		return new Integer( x*y);
	}
	
	public String shows()
	{
		
		String ans="Dostepne funkcje:";
		String l1= "\n 1. echo: dwa argumenty int, zwraca sume intow";
		String l2= "\n2. execAsy: jeden argument int, asynchroniczna funkcja, usypia watek na czas argumentu w ms";
		String l3= "\n3. multiplyAsync, dwa argumenty int, mnozy dwie liczby przez siebie";
		String l4= "\n4. intCalculate, 3 argumenty, dwa razy int oraz string. ciag operacji plus,minus,mnozenie,dzielenie ";
		String l5= "\n5. myHello, 2 argumenty string, imie i lokalizacja jezykowa.";
		String l6= "\n6. maxPrimeAsync, 2 argumenty int, opoznienie oraz wartosc do sprawdzenia ";	
		String finale = ans+l1+l2+l3+l4+l5+l6;
		return  new String(finale);
	}
	
	public String intCalculate (int a, int b, String x)
	{
		int z;
		String answer=new String();
		for (int i=0;i<x.length();i++)
		{
			switch (x.charAt(i)) {
				case '+':
				{
					 z=a+b;
					answer+=(a +"+"+b+"= " + z);
				}
				break;
				case '-':
				{
					z=a-b;
					answer+=(a +"-"+b+"= " + z);
				}
					break;
				case '*':
				{
					z=a*b;
					answer+=(a +"*"+b+"= " + z);
				}
				break;
				case '/':
				{
					z=a/b;
					answer+=(a +"/"+b+"= " + z);
				}
				break;
				case '%':
				{
					z=a%b;
					answer+=(a +"%"+b+"= " + z);
				}
				break;
				default :
				{
					answer+=("Nie obslugiwana operacja");
				}
				break;			
			}
			answer+=("\n");
		}
		String ans= new String(answer);
		
		return ans;
	}
	
	public String myHello(String name, String lang)
	{
		LocalDate localDate=LocalDate.now();
		Locale lclang=new Locale(lang);
		String dateinLang=localDate.format(DateTimeFormatter.ofPattern("EEEE, dd, MMMM, yyyy",lclang));
		String line1=("Witaj "+name+" dzisiaj jest: \n" );
		String ans=line1+dateinLang;
		
		char[] ans2=ans.toCharArray();
		for (int i=0;i<ans2.length;i++)
		{
			if(ans2[i]>127)
			{
				ans2[i]='?';
			}
		}
		
		ans=String.valueOf(ans2);
		return ans;
	}
	
	
	public int maxPrimeAsync(int delay, int value )
	{
		boolean []sieve=new boolean[value+10]; 
        sieve[0] = sieve[1] = false; 
        try {
        	Thread.sleep(delay);
        for(int i=0;i<value+10;i++) 
            sieve[i]=true; 
          
        for (int i = 2; i <= value; i++) { 
            if (sieve[i]) { 
      
                for ( int j = i * i; j <= value; j += i) { 
                    sieve[j] = false; 
                } 
            } 
        } 
            
        while (true) { 
            
            if (checkSpecialPrime(sieve, value)) { 
              
               return value; 
                
            } 
           
            else
                value--; 
        	} 
        }
        catch(InterruptedException exception)
		{
			exception.printStackTrace();
			Thread.currentThread().interrupt();
		}
		return value;
	}
	static boolean checkSpecialPrime(boolean [] sieve, int num) 
    { 
      
        while (num!=0) { 
            
            if (!sieve[num]) { 
                return false; 
            } 
      
            num /= 10; 
        } 
      
        return true; 
    } 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			try {
				System.out.println("Startuje serwer XML-RPC...");
				int port = 10001;
				WebServer server = new WebServer(port);
				server.addHandler("MojSerwer",new serwerRPC());
				server.start();
				System.out.println("Serwer wystartowal pomyslnie.");
				System.out.println("Nasluchuje na porcie: "+ port);
				System.out.println("Aby zatrzymaæ serwer naciœnij ctrl+c");
			}
			catch(Exception exception)
			{
				System.err.println("Serwer XML-RPC: "+exception);
			}
	}

}
