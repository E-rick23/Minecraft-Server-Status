package mineserver;

public class Main {
    public static void main(String[] args) {
        Mine serverStatusChecker = new Mine();
        String serverAddress = ""; // Put the server address here.
        String status = serverStatusChecker.getServerStatus(serverAddress); //Stores the string with the status.
        System.out.println("Server Status: " + status);
    }

}
