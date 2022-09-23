import java.io.*;
import java.rmi.*;
import java.util.*; // for scanner
import java.net.*;  // inetaddr

public class Client {

    public static void main( String args[] ) {
	// verify arguments
	int port = 0;
	try {
	    if ( args.length == 2 ) {
		port = Integer.parseInt( args[1] );
		if ( port < 5001 || port > 65535 )
		    throw new Exception( );
	    }
	    else
		throw new Exception( );
	} catch ( Exception e ) {
	    System.err.println( "usage: java Client serverIp port" );
	    System.exit( -1 );
	}
	String serverIp = args[0];

	try {
	    ServerInterface serverObject =  ( ServerInterface )
		Naming.lookup( "rmi://" + serverIp + ":" + port + "/server" );
	    System.out.println( "connected to server: " + 
				serverObject.greetings( ) );

	    ClientInterface clientObject = new ClientImplementation();

	    serverObject.registerForCallback( clientObject );

	    String hostname = null;
	    try {
		hostname = InetAddress.getLocalHost( ).getHostName( );
	    } catch ( Exception e ) {
		e.printStackTrace( );
		hostname = "unknown";
	    }

	    Scanner keyboard = new Scanner( System.in );

	    // keep
	    //   reading a message until you encounter an EOF
	    //   sending this message to the server.
	    while( keyboard.hasNextLine()) {
		String str = keyboard.nextLine();
		serverObject.broadcast( hostname + ": " + str);
		}
	    Thread.sleep( 30000 );
	    serverObject.unregisterForCallback( clientObject );
	}
	catch ( Exception e ) {
	    e.printStackTrace( );
	    System.exit( -1 );
	}
    }
}
