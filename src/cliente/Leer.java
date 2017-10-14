package cliente;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Leer extends Thread {
	
	private Scanner sc;
	private ObjectOutputStream output;
	
	public Leer(ObjectOutputStream output){
		this.sc = new Scanner(System.in);
		this.output = output;
	}

    public void run() {
        String outputMessage = "";
        do {
            outputMessage = sc.nextLine();
            mandarMensaje("Client: " + outputMessage);
        } while (!outputMessage.equals("QUIT"));
    }
    
    public void mandarMensaje(String s) {
        try {
            output.writeObject(s);
            output.flush();
            System.out.println("Yo: " + s);
        } catch (IOException e) {
            System.err.println("Error escribiendo el mensaje");
        }
    }
}
