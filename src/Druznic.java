
import java.util.HashMap;
import java.util.Map;

import javax.annotation.ManagedBean;

import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import Comunicacao.Comunicador;

@ManagedBean
public class Druznic {
	private LineChartModel graficoNivel;
	private LineChartModel graficoControle;
	private Map<Integer, LineChartSeries> filas = new HashMap<Integer, LineChartSeries>();
	private Comunicador comunicador;
	
	public LineChartModel getGraficoNivel() {
		return graficoNivel;
	}
	public LineChartModel getGraficoControle() {
		return graficoControle;
	}
	
}
