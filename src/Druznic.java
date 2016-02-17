
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
public class Druznic {
	private static final Integer TEMPO = 0;
	private static final Integer NIVEL_UM = 1;
	private static final Integer NIVEL_DOIS = 2;
	private static final Integer T_ONDA = 3;
	private static final Integer T_SAT = 4;
	private static final Integer REF = 5;
	private static final Integer ERRO = 6;
	private static final Integer ERRO_SAT = 7;
	
	private static final String SEP = " ";

	private double i = 0;
	
	private Map<String, Integer> ondas; 
	private Integer onda;
	
	private String ip = "10.13.99.69";
	private String porta = "20081";
	private String leituraUm = "0";
	private String leituraDois = "1";
	private String escrita = "0";

	private int tipo = 0;
	
	private double amp = 15;
	private double amp_sup = 0;
	private double amp_inf = 0;
	
	private double periodo = 0;
	private double periodo_sup = 0;
	private double periodo_inf = 0;
	
	private double offset = 0;
	
	public double getHorario() {
		i = i + 0.1;
		return i;
	}

	private LineChartModel graficoNivel;
	private LineChartModel graficoControle;

	private Map<Integer, LineChartSeries> filas = new HashMap<Integer, LineChartSeries>();

	private Comunicador comunicador;

	public LineChartModel getGraficoNivel() {
		return graficoNivel;
	}

	public Map<String, Integer> getOndas() {
		return ondas;
	}

	public void setOndas(Map<String, Integer> ondas) {
		this.ondas = ondas;
	}

	public Integer getOnda() {
		return onda;
	}

	public void setOnda(Integer onda) {
		this.onda = onda;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public String getLeituraUm() {
		return leituraUm;
	}

	public void setLeituraUm(String leituraUm) {
		this.leituraUm = leituraUm;
	}

	public String getLeituraDois() {
		return leituraDois;
	}

	public void setLeituraDois(String leituraDois) {
		this.leituraDois = leituraDois;
	}

	public String getEscrita() {
		return escrita;
	}

	public void setEscrita(String escrita) {
		this.escrita = escrita;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public double getAmp() {
		return amp;
	}

	public void setAmp(double amp) {
		this.amp = amp;
	}

	public double getAmp_sup() {
		return amp_sup;
	}

	public void setAmp_sup(double amp_sup) {
		this.amp_sup = amp_sup;
	}

	public double getAmp_inf() {
		return amp_inf;
	}

	public void setAmp_inf(double amp_inf) {
		this.amp_inf = amp_inf;
	}

	public double getPeriodo() {
		return periodo;
	}

	public void setPeriodo(double periodo) {
		this.periodo = periodo;
	}

	public double getPeriodo_sup() {
		return periodo_sup;
	}

	public void setPeriodo_sup(double periodo_sup) {
		this.periodo_sup = periodo_sup;
	}

	public double getPeriodo_inf() {
		return periodo_inf;
	}

	public void setPeriodo_inf(double periodo_inf) {
		this.periodo_inf = periodo_inf;
	}

	public double getOffset() {
		return offset;
	}

	public void setOffset(double offset) {
		this.offset = offset;
	}

	public LineChartModel getGraficoControle() {
		return graficoControle;
	}

	@PostConstruct
	public void init() {
		criarLineModels();
		ondas = new HashMap<String, Integer>();
		ondas.put("Degrau", 0);
		ondas.put("Quadrada", 1);
		ondas.put("Senoidal", 2);
		ondas.put("Dente de Serra", 3);
		ondas.put("Aleatória", 4);
	}

	public void limparGrafico() {
		graficoNivel.getSeries().clear();
		graficoControle.getSeries().clear();
	}

	public void criarLineModels() {
		graficoNivel = new LineChartModel();
		graficoNivel.setTitle("Nível");
		graficoNivel.setLegendPosition("e");
		Axis yAxis = graficoNivel.getAxis(AxisType.Y);
		Axis xAxis = graficoNivel.getAxis(AxisType.X);
		xAxis.setMin(0);
		xAxis.setMax(240);
		yAxis.setMin(0);
		yAxis.setMax(30);

		graficoControle = new LineChartModel();
		graficoControle.setTitle("Controle");
		graficoControle.setLegendPosition("e");
		xAxis = graficoControle.getAxis(AxisType.X);
		xAxis.setMin(0);
		xAxis.setMax(240);
	}

	public void conectar(){
		System.out.println("Tentar Conectar");
		comunicador = new Comunicador("localhost", filas);
		if (comunicador.isConectado())
			System.out.println("Conectado");
		else
			System.out.println("Não conectado");
		String config = ip + " "
						+ porta + " "
						+ leituraUm + " " 
						+ leituraDois + " "
						+ escrita;
		comunicador.enviarConfiguracoes(config);
	}
	
	public void configMalhaAberta() {
		System.out.println("Malha Aberta");

		limparGrafico();
		filas.clear();

		filas.put(NIVEL_UM, (new LineChartSeries()));
		filas.get(NIVEL_UM).setLabel("Nível 1");
		filas.get(NIVEL_UM).set(0, 0);
		filas.get(NIVEL_UM).setShowMarker(false);

		filas.put(NIVEL_DOIS, (new LineChartSeries()));
		filas.get(NIVEL_DOIS).setLabel("Nível 2");
		filas.get(NIVEL_DOIS).set(0, 0);
		filas.get(NIVEL_DOIS).setShowMarker(false);

		filas.put(T_ONDA, (new LineChartSeries()));
		filas.get(T_ONDA).setLabel("Sinal Gerado");
		filas.get(T_ONDA).set(0, 0);
		filas.get(T_ONDA).setShowMarker(false);

		filas.put(T_SAT, (new LineChartSeries()));
		filas.get(T_SAT).setLabel("Sinal Saturado");
		filas.get(T_SAT).set(0, 0);
		filas.get(T_SAT).setShowMarker(false);

		graficoNivel.addSeries(filas.get(NIVEL_UM));
		graficoNivel.addSeries(filas.get(NIVEL_DOIS));

		graficoControle.addSeries(filas.get(T_ONDA));
		graficoControle.addSeries(filas.get(T_SAT));
		
		String config = tipo + SEP
						+ amp + SEP
						+ amp_sup + SEP
						+ amp_inf + SEP
						+ periodo + SEP
						+ periodo_sup + SEP
						+ periodo_inf + SEP
						+ offset + SEP + 0;
		
		comunicador.enviarConfiguracoes(config);
	}

	public void configMalhaFechada() {
		System.out.println("Malha Fechada");

		limparGrafico();
		filas.clear();

		filas.put(NIVEL_UM, (new LineChartSeries()));
		filas.get(NIVEL_UM).setLabel("Nível 1");
		filas.get(NIVEL_UM).setShowMarker(false);

		filas.put(NIVEL_DOIS, (new LineChartSeries()));
		filas.get(NIVEL_DOIS).setLabel("Nível 2");
		filas.get(NIVEL_DOIS).setShowMarker(false);

		filas.put(REF, (new LineChartSeries()));
		filas.get(REF).setLabel("Sinal de Referência");
		filas.get(REF).setShowMarker(false);

		filas.put(ERRO, (new LineChartSeries()));
		filas.get(ERRO).setLabel("Erro");
		filas.get(ERRO).setShowMarker(false);

		filas.put(ERRO_SAT, (new LineChartSeries()));
		filas.get(ERRO_SAT).setLabel("Erro Saturado");
		filas.get(ERRO_SAT).setShowMarker(false);

		graficoNivel.addSeries(filas.get(NIVEL_UM));
		graficoNivel.addSeries(filas.get(NIVEL_DOIS));

		graficoControle.addSeries(filas.get(REF));
		graficoControle.addSeries(filas.get(ERRO));
		graficoControle.addSeries(filas.get(ERRO_SAT));
		
		String config = tipo + SEP
				+ amp + SEP
				+ amp_sup + SEP
				+ amp_inf + SEP
				+ periodo + SEP
				+ periodo_sup + SEP
				+ periodo_inf + SEP
				+ offset + SEP + 1;

		comunicador.enviarConfiguracoes(config);
	}
}

