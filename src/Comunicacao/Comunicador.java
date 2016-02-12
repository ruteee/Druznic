package Comunicacao;

import java.net.Socket;

public class Comunicador {
	private Receptor receptor;
	private Emissor emissor;
	
	private boolean conectado;

	private String ip;

	public Comunicador(String IP) {
		this.ip = IP;
		try {
			// Connect to the server at the given address on port 8080
			if (this.ip == null || this.ip.length() == 0)
				this.ip = "localhost";
			Socket sock = new Socket(this.ip, 54321);
			sock.setTcpNoDelay(true);
			this.receptor = new Receptor(sock);
			receptor.start();
			this.emissor = new Emissor(sock);
			this.conectado = true;
			// this.viewer = viewer;
		} catch (Exception e) {
			this.conectado = false;
			e.printStackTrace();
		}
	}

	public boolean isConectado() {
		return conectado;
	}

	public boolean esperando() {
		return receptor.isFlag();
	}
	
	public void enviarConfiguracoes(String config) {
		emissor.Enviar(config);
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
