import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class JABEClient extends UnicastRemoteObject implements IClient {

    static JABEInterface JABEInterface;
    static Scanner input;
    String name;

    JABEClient(String name) throws RemoteException, MalformedURLException, NotBoundException, InterruptedException {
        this.name = name;
        connect();
    }

    public static void main(String[] args) {
        try{
            new JABEClient(args[0]);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Wrong input! Please do the following: java Client <ClientName>");
        }
        catch (IllegalArgumentException e){
            System.out.println("Wrong input! Please do the following: java Client <ClientName>");
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e){

        }
    }


    void connect() throws RemoteException, MalformedURLException, NotBoundException {
        JABEInterface = new JABEServer();
        JABEInterface = (JABEInterface) Naming.lookup("//localhost:1099/Server");
        System.out.println("user connected.");
        JABEInterface.login(this, name);
        input = new Scanner(System.in);
        JABEInterface.checkUser(this);
        selection();
    }

    void selection() throws RemoteException {
        System.out.println("Welcome to Just Another Bidding Environment (JABE). What do you want to do?");
        System.out.println("[0] Make an offer.");
        System.out.println("[1] List all items or auctions of an user.");
        System.out.println("[2] Make a bid.");
        System.out.println("[3] Change to observer mode (observe bids).");
        System.out.println("[4] Disconnected.");
        int value = input.nextInt();
        if(value == 0){
            System.out.println("Make an offer:");
        }
        else if(value == 1){
            System.out.println("List all items:");
        }
        else if(value == 2){
            System.out.println("Make a bid:");
        }
        else if(value == 3){
            System.out.println("Change to observer mode:");
        }
        else if(value == 4){
            System.out.println("Thank you for visiting us! See you soon!");
        }
        else{
            System.out.println("Wrong input! Please check your input again!");
        }
        selection();
    }


    @Override
    public void receiveAnswer(String message) throws RemoteException {
        System.out.println(message);
    }

    @Override
    public String getClientName(){
        return name;
    }


    public String receivePassword() throws RemoteException{
        System.out.println("Type password:");
        String password = input.next();
        return password;
    }
}
