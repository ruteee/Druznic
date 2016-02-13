
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.sun.org.apache.bcel.internal.generic.LNEG;

import javax.faces.bean.ManagedBean;

import Comunicacao.Comunicador;

@ManagedBean
public class Druznic{
	
	private LineChartModel graficoNivel;
	private LineChartModel graficoControle;
	
	private Map<Integer, LineChartSeries> filas = new HashMap<Integer, LineChartSeries>();
	private Comunicador comunicador;
	
	public ChartSeries nivel_um, nivel_dois, T_ONDA, T_SAT; 
	public ChartSeries vp;
	
	public Druznic (){
		comunicador = new Comunicador("10.10.10.10", filas);
	}

	public LineChartModel getGraficoNivel(){
		return graficoNivel;
	}
	
	public LineChartModel getGraficoControle(){
		return graficoControle;
	}
	
	@PostConstruct
	public void init() {
		criarLineModels();
	}
	
	public LineChartModel gerarGraficoNivel(){
			
			graficoNivel = new LineChartModel();
			
			nivel_um = new LineChartSeries();
			nivel_um = filas.get("NIVEL_UM");
			
			nivel_dois = new LineChartSeries();
			nivel_dois = filas.get("NIVEL_DOIS");
			
			graficoNivel.addSeries(nivel_um);
			graficoNivel.addSeries(nivel_dois);
			
			
			return graficoNivel;
			
	}
	
	public void limparGrafico(){
		graficoNivel.clear();
		graficoControle.clear();
	}
	
	public LineChartModel gerarGraficoControle(){
		
		graficoControle = new LineChartModel();
		
		T_ONDA = new LineChartSeries();
		T_ONDA = filas.get("T_ONDA");
		
		T_SAT = new LineChartSeries();
		T_SAT = filas.get("T_SAT");
		
		graficoControle.addSeries(T_ONDA);
		graficoControle.addSeries(T_SAT);
		
		
		return graficoControle;
		
	}
		
	public void criarLineModels(){
		
		   graficoNivel = gerarGraficoNivel();
	       graficoNivel.setTitle("Gráfico de Nível");
	      // graficoNivel.setLegendPosition("e");
	     //  Axis yAxis = graficoNivel.getAxis(AxisType.Y);
	       //yAxis.setMin(0);
	       //yAxis.setMax(10);
	        
	       graficoControle = gerarGraficoControle();
	       graficoControle.setTitle("Gráfico de Tensão");
	      /* graficoControle.setLegendPosition("e");
	       graficoControle.setShowPointLabels(true);
	       graficoControle.getAxes().put(AxisType.X, new CategoryAxis("segundos"));
	       yAxis = graficoControle.getAxis(AxisType.Y);
	       yAxis.setLabel("Births");
	       yAxis.setMin(0);
	       yAxis.setMax(200);*/
	}

	public void setGraficoNivel(LineChartModel graficoNivel) {
		this.graficoNivel = graficoNivel;
	}	
}
