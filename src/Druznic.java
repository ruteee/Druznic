
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
	
	public ChartSeries nivel_um; 
	public ChartSeries vp;

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
			nivel_um.set("1", 2);
			nivel_um.set("4", 4);
			nivel_um.set("3", 8);
			nivel_um.set("5", 10);
			
			graficoNivel.addSeries(nivel_um);
			
			return graficoNivel;
			
	}
	
	public LineChartModel gerarGraficoControle(){
		
		graficoControle = new LineChartModel();
		vp = new LineChartSeries();
		vp.set("1", -2);
		vp.set("5", -4);
		vp.set("13", -8);
		vp.set("17", -10);
		
		graficoControle.addSeries(vp);
		
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
