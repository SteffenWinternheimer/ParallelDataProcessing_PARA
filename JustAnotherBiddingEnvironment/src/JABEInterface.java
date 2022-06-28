import java.rmi.Remote;
import java.rmi.RemoteException;

public interface JABEInterface extends Remote {
    public void login(IClient client, String name)throws RemoteException;
    public void checkUser(IClient client) throws RemoteException;
}
