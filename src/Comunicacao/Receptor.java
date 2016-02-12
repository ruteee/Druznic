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
				String xml = reader.readLine();
				if (xml == null) {
					// Connection lost
					return;
				}

				System.out.println("XML: " + xml + "\n\n");
				this.flag = true;
				// Hand off to the UI
				String lista[] = xml.split("\t");
				for (int i = 1; i < lista.length / 2; i++) {
					filas.get(Integer.parseInt(lista[2 * i])).set(Double.parseDouble(lista[1]), Double.parseDouble(lista[(2*i) + 1]));
				}
				/*
				 * if ( xml.indexOf("HostnameResponse") != -1 )
				 * viewer.onHostnameResponse(xml); else if (
				 * xml.indexOf("MemoryResponse") != -1 )
				 * viewer.onMemoryResponse(xml); else if (
				 * xml.indexOf("RandomNumberResponse") != -1 )
				 * viewer.onRandomNumberResponse(xml);
				 */
			}
		} catch (StreamCorruptedException sce) {
			// skip over the bad bytes
			try {
				if (instream != null)
					instream.skip(instream.available());
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
