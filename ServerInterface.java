import java.rmi.*;

public interface ServerInterface extends Remote {
    public String greetings( ) throws RemoteException;

    public void registerForCallback( ClientInterface client )
	throws RemoteException;

    public void unregisterForCallback( ClientInterface client )
	throws RemoteException;

    public void broadcast( String message )
	throws RemoteException;
}

