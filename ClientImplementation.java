import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

public class ClientImplementation extends UnicastRemoteObject
    implements ClientInterface {
  
    public ClientImplementation( ) throws RemoteException {
	super( );
    }

    public String callback( String message ) {
	System.out.println( message );
	String hostname = null;
	try {
	    hostname = InetAddress.getLocalHost( ).getHostName( );
	} catch ( Exception e ) {
	    e.printStackTrace( );
	    hostname = "unknown";
	}
	String returnMessage = "Call back received at " + hostname;
	return returnMessage;
    }      

    public void receiveMessage( String message ) {
	// print out a give message to stdout
	System.out.println( message) ;
    }
}
