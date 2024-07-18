package mineserver;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Mine serverStatusChecker = new Mine();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Digite o endereço do servidor: ");
        String serverAddress = keyboard.nextLine(); // Put the server address here.
        String status = serverStatusChecker.getServerStatus(serverAddress); //Stores the string with the status.
        System.out.println("Server Status: " + status);
    }

}
