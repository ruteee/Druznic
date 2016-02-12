package Comunicacao;

import java.io.BufferedOutputStream;
import java.net.Socket;

public class Emissor {
	private Socket sock;

	private BufferedOutputStream os;

	public Emissor(Socket sock) {
		try {
			this.sock = sock;
			this.sock.setTcpNoDelay(true);
			this.os = new BufferedOutputStream(sock.getOutputStream());
		} catch (Exception e) {
			System.out.println("bad");
			e.printStackTrace();
		}
	}

	public void Enviar(String mensagem) {
		try {
			os.write(mensagem.getBytes());
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
