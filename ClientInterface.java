import java.rmi.*;

public interface ClientInterface extends Remote{
    public String callback( String message ) throws RemoteException;
    public void receiveMessage( String message ) throws RemoteException;
}

