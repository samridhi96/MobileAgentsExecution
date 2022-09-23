import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class ServerImplementation extends UnicastRemoteObject
    implements ServerInterface {

    private Vector<ClientInterface> clientList;
    public ServerImplementation( ) throws RemoteException {
	super( );
	clientList = new Vector<ClientInterface>( );
    }

    public String greetings( ) throws RemoteException {
	return( "hello" );
    }

    public synchronized void registerForCallback( ClientInterface client )
	throws RemoteException {

	if ( !clientList.contains( client ) ) {
	    clientList.addElement( client );
	    System.out.println( "Registered: " + client );
	    callbacks( );
	}
    }  

    public synchronized void broadcast( String message ) 
	throws RemoteException {
	// broadcast a given message to all clients.
	for ( int i = 0; i < clientList.size(); i++) {
		ClientInterface client = clientList.elementAt(i);
		client.receiveMessage( message );
	}
    }

    public synchronized void unregisterForCallback( ClientInterface client )
	throws RemoteException {
	if ( clientList.removeElement( client ) ) {
	    System.out.println( "Unregistered: " + client );
	} else {
	    System.out.println( "Didnot unregister: " + client );
	}
    } 

    private void callbacks( ) throws RemoteException {

	System.out.println( "Callbacks......." );
	for ( int i = 0; i < clientList.size( ); i++ ){
	    ClientInterface client = clientList.elementAt( i );
	    System.out.
		println( client.callback( "Number of registered clients="
					  +  clientList.size() ) );
	}
    }

}
