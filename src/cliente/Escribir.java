package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Escribir {
	private ObjectInputStream input;
	
	public Escribir(ObjectInputStream input){
		this.input = input;
	}
	
	public void run(){
		String mensajeIn = "";
		while (true) {
            try {
                mensajeIn = (String) input.readObject();
                System.out.println(mensajeIn);
            } catch (ClassNotFoundException e) {
                System.err.println("Object of an unknown type was recieved");
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
		}
	}
}
