package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    ServerSocket serverSocket;
    Socket clientSocket;
    ObjectOutputStream output;
    ObjectInputStream input;
    String serverAddress = "127.0.0.1";

    public void run() {
        connect2Server();
        getIOStreams();
        processConnection();
        closeConnection();
    }

    public void connect2Server() {
        System.out.println("Trying to connect to the server");
        try {
            clientSocket = new Socket(serverAddress, 6000);
        } catch (UnknownHostException e) {
            System.err.println("Server unavailable");
            System.exit(1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void getIOStreams() {
        try {
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            output.flush();
            input = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("Client established I/O Stream");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processConnection() {
        sendData("Connection established with the client");
        Leer leer = new Leer(output);
        leer.run();
        Escribir escribir = new Escribir(input);
        escribir.run();
    }

    public void sendData(String s) {
        try {
            output.writeObject(s);
            output.flush();
            System.out.println("Client: " + s);
        } catch (IOException e) {
            System.err.println("Error writting the message");
        }
    }

    public void closeConnection() {
        try {
            output.close();
            input.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
 
/*import java.io.*;
import java.net.*;
 
public class Cliente {
 
    private static String HOST = "localhost";
    private static int PUERTO = 2017;
 
    public static void main(String args[]) {
         
        Socket socket;
        DataOutputStream mensaje;
         
        try {
            //Creamos nuestro socket
            socket = new Socket(HOST, PUERTO);
     
            mensaje = new DataOutputStream(socket.getOutputStream());
 
            //Enviamos un mensaje
            mensaje.writeUTF("Hola soy un cliente!!"); //Esto va a ser lo escrito por consola, hacer bucles y demas
 
            //Cerramos la conexión
            socket.close();
 
        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
         
    }
}*/