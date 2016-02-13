
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
	private static final Integer TEMPO = 0;
	private static final Integer NIVEL_UM = 1;
	private static final Integer NIVEL_DOIS = 2;
	private static final Integer T_ONDA = 3;
	private static final Integer T_SAT = 4;
	private static final Integer REF = 5;
	private static final Integer ERRO = 6;
	private static final Integer ERRO_SAT = 7;
	
	private int i = 0;

	public int getHorario() {
		return i++;
	}
	
	private LineChartModel graficoNivel;
	private LineChartModel graficoControle;
	
	private Map<Integer, LineChartSeries> filas = new HashMap<Integer, LineChartSeries>();
	
	@SuppressWarnings("unused")
	private Comunicador comunicador;
	
	public Druznic() {
		System.out.println("Tentar Conectar");
		comunicador = new Comunicador("localhost", filas);
		System.out.println("Conectado");
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
			
//			nivel_um = new LineChartSeries();
//			nivel_um = filas.get("NIVEL_UM");
//			
//			nivel_dois = new LineChartSeries();
//			nivel_dois = filas.get("NIVEL_DOIS");
//			
//			graficoNivel.addSeries(nivel_um);
//			graficoNivel.addSeries(nivel_dois);

			
			return graficoNivel;
			
	}
	
	public LineChartModel gerarGraficoControle(){
		
		graficoControle = new LineChartModel();
		
//		T_ONDA = new LineChartSeries();
//		T_ONDA = filas.get("T_ONDA");
//		
//		T_SAT = new LineChartSeries();
//		T_SAT = filas.get("T_SAT");
//		
//		graficoControle.addSeries(T_ONDA);
//		graficoControle.addSeries(T_SAT);
//		
		
		return graficoControle;
		
	}

	public void limparGrafico(){
		graficoNivel.clear();
		graficoControle.clear();
	}
	
	public void criarLineModels(){	
		   graficoNivel = gerarGraficoNivel();
	       graficoNivel.setTitle("Gráfico de Nível");
	       //graficoNivel.setLegendPosition("e");
	       //Axis yAxis = graficoNivel.getAxis(AxisType.Y);
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
	
	public void configMalhaAberta(){
		System.out.println("Malha Aberta");
		
		limparGrafico();
		filas.clear();
		
		filas.put(NIVEL_UM, (new LineChartSeries()));
		filas.put(NIVEL_DOIS, (new LineChartSeries()));
		filas.put(T_ONDA, (new LineChartSeries()));
		filas.put(T_SAT, (new LineChartSeries()));
		
		graficoNivel.addSeries(filas.get(NIVEL_UM));
		graficoNivel.addSeries(filas.get(NIVEL_DOIS));
		
		graficoControle.addSeries(filas.get(T_ONDA));
		graficoControle.addSeries(filas.get(T_SAT));
	}
	
	public void configMalhaFechada(){
		System.out.println("Malha Fechada");
		
		limparGrafico();
		filas.clear();
		
		filas.put(NIVEL_UM, (new LineChartSeries()));
		filas.put(NIVEL_DOIS, (new LineChartSeries()));
		filas.put(REF, (new LineChartSeries()));
		filas.put(ERRO, (new LineChartSeries()));
		filas.put(ERRO_SAT, (new LineChartSeries()));
		
		graficoNivel.addSeries(filas.get(NIVEL_UM));
		graficoNivel.addSeries(filas.get(NIVEL_DOIS));
		
		graficoControle.addSeries(filas.get(REF));
		graficoControle.addSeries(filas.get(ERRO));
		graficoControle.addSeries(filas.get(ERRO_SAT));
	}
}
