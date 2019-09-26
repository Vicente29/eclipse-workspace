package Ficheros_de_Objetos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;

public class MyObjectOutputStreamVicente extends ObjectOutputStream{

	@Override
	protected void writeStreamHeader() throws IOException {
		// TODO Auto-generated method stub
		
	}

	public MyObjectOutputStreamVicente(OutputStream out) throws IOException {
		super(out);
		// TODO Auto-generated constructor stub
	}

	

	

	
	
}
