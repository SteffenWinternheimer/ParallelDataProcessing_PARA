import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Scanner;

public class JABEServer extends UnicastRemoteObject implements JABEInterface {


    static Scanner input = new Scanner(System.in);
    static JABEServer JABEServer;
    static HashMap<String, String> credentials;

    protected JABEServer() throws RemoteException {
        super();
    }


    protected JABEServer(String[] args) throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try{
            JABEServer = new JABEServer(args);
            Registry reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            reg.rebind("Server", JABEServer);
            System.out.println("Server online");
            credentials = new HashMap<>();
            JABEServer.actions();
        }catch (RemoteException e){

        }
        catch (InterruptedException e){

        }
    }

    @Override
    public void checkUser(IClient client) throws RemoteException {
        String userName = client.getClientName();
        if(credentials.get(userName) == null){
            client.receiveAnswer("Username not found in database! Type a password to register:");
            String password = client.receivePassword();
            credentials.put(userName,password);
            client.receiveAnswer("Successfully registered!");
        }
        else {
            client.receiveAnswer("Username found! Please type your password to proceed:");
            String password = client.receivePassword();
            while(!credentials.get(userName).equals(password)){
                client.receiveAnswer("Wrong Password! Please try again!");
                password = client.receivePassword();
            }
            client.receiveAnswer("Login successfully!");
        }

    }





    void actions() throws RemoteException, InterruptedException {
//        System.out.println("What do you want to do?");
        int index = input.nextInt();
        actions();
    }


    @Override
    public void login(IClient client, String name) throws RemoteException {
        System.out.println("Client " + name + " connected.");
    }
}
