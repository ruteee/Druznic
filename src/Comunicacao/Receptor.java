package Comunicacao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.util.Map;

import org.primefaces.model.chart.LineChartSeries;

public class Receptor extends Thread {
	private Socket sock = null;
	private boolean esperando = true;
	private Map<Integer, LineChartSeries> filas;

	private boolean flag = false;
	
	public Receptor(Socket sock, Map<Integer, LineChartSeries> filas) {
		this.sock = sock;
		this.setName("ThreadRecepção");
		this.filas = filas;
	}

	@Override
	public void run() {
		InputStream instream = null;

		try {
			instream = this.sock.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
			
			while (this.esperando) {
				System.out.println("Esperando na thread");
				instream.skip(instream.available());
				String xml = reader.readLine();
				if (xml == null) {
					// Connection lost
					System.out.println("Conexão perdida");
					return;
				}
				System.out.println("XML:" + xml);
				
				this.flag = true;
				
				String lista[] = xml.split("[|]");
				for (String string : lista) {
					System.out.println(string);
				}
				for (int i = 1; i < lista.length/2; i++) {
					filas.get(Integer.parseInt(lista[2 * i])).set(Double.parseDouble(lista[1]), Double.parseDouble(lista[(2*i) + 1]));
				}
				
			}
		} catch (StreamCorruptedException sce) {
			// skip over the bad bytes
			try {
				if (instream != null)
					instream.skip(instream.available());
				System.out.println("ex");
			} catch (Exception e1) {
				this.esperando = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.esperando = false;
		}
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
