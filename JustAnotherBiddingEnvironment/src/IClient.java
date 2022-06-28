import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IClient extends Remote {
    public void receiveAnswer(String message)throws  RemoteException;
    public String receivePassword() throws RemoteException;
    public String getClientName()throws RemoteException;
}
