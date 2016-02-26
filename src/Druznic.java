
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

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
	
	private boolean canal0;
	private boolean canal1;
	private boolean canal2;
	private boolean canal3;
	private boolean canal4;
	private boolean canal5;
	private boolean canal6;
	private boolean canal7;
	
	private Double tempo;
	
	private String nivelUm;
	private String nivelDois;
	
	private Map<String, String> ondas; 
	private String onda;
	
	private String ip = "10.13.99.69";
	private String porta = "20081";
	private String leituraUm = "0";
	private String leituraDois = "1";
	private String escrita = "0";

	private boolean tempoReal;
	
	private double amp;
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
	
	private Map<String, String> canais;
	
	private Comunicador comunicador;

	public String getNivelUm() {
		nivelUm = "Nível 1: ";
		if (filas.get(NIVEL_UM) != null )
			nivelUm += filas.get(NIVEL_UM).getData().get(tempo).toString();
		return nivelUm;
	}

	public void setNivelUm(String nivelUm) {
		this.nivelUm = nivelUm;
	}

	public String getNivelDois() {
		if (filas.get(NIVEL_DOIS) != null)
			nivelDois = "Nível 2: " + filas.get(NIVEL_DOIS).getData().get(tempo).toString();
		else
			nivelDois = "Nível 2: ";
		return nivelDois;
	}

	public void setNivelDois(String nivelDois) {
		this.nivelDois = nivelDois;
	}

	public Map<String, String> getCanais() {
		canais = new HashMap<String, String>();
		if (canal0)
			canais.put("Canal 0", "0");
		if (canal1)
			canais.put("Canal 1", "1");
		if (canal2)
			canais.put("Canal 2", "2");
		if (canal3)
			canais.put("Canal 3", "3");
		if (canal4)
			canais.put("Canal 4", "4");
		if (canal5)
			canais.put("Canal 5", "5");
		if (canal6)
			canais.put("Canal 6", "6");
		if (canal7)
			canais.put("Canal 7", "7");
		return canais;
	}

	public void setCanais(Map<String, String> canais) {
		this.canais = canais;
	}

	public boolean isCanal0() {
		return canal0;
	}

	public void setCanal0(boolean canal0) {
		this.canal0 = canal0;
	}

	public boolean isCanal1() {
		return canal1;
	}

	public void setCanal1(boolean canal1) {
		this.canal1 = canal1;
	}

	public boolean isCanal2() {
		return canal2;
	}

	public void setCanal2(boolean canal2) {
		this.canal2 = canal2;
	}

	public boolean isCanal3() {
		return canal3;
	}

	public void setCanal3(boolean canal3) {
		this.canal3 = canal3;
	}

	public boolean isCanal4() {
		return canal4;
	}

	public void setCanal4(boolean canal4) {
		this.canal4 = canal4;
	}

	public boolean isCanal5() {
		return canal5;
	}

	public void setCanal5(boolean canal5) {
		this.canal5 = canal5;
	}

	public boolean isCanal6() {
		return canal6;
	}

	public void setCanal6(boolean canal6) {
		this.canal6 = canal6;
	}

	public boolean isCanal7() {
		return canal7;
	}

	public void setCanal7(boolean canal7) {
		this.canal7 = canal7;
	}

	public String getConectado() {
		if (comunicador !=  null)
			return comunicador.isConectado() ? "Conectado" : "Desconectado";
		else 
			return "Desconectado";
	}
	
	public void setConectado(String conectado) {
		System.out.println(conectado);
	}

	public boolean isTempoReal() {
		return tempoReal;
	}

	public void setTempoReal(boolean tempoReal) {
		this.tempoReal = tempoReal;
	}
	
	public LineChartModel getGraficoNivel() {
		return graficoNivel;
	}

	public Map<String, String> getOndas() {
		return ondas;
	}

	public void setOndas(Map<String, String> ondas) {
		this.ondas = ondas;
	}

	public String getOnda() {
		return onda;
	}

	public void setOnda(String onda) {
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
		ondas = new HashMap<String, String>();
		ondas.put("Degrau", "0");
		ondas.put("Quadrada", "2");
		ondas.put("Senoidal", "1");
		ondas.put("Dente de Serra", "3");
		ondas.put("Aleatória", "4");
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
		xAxis.setMax(120);
		yAxis.setMin(0);
		yAxis.setMax(30);

		graficoControle = new LineChartModel();
		graficoControle.setTitle("Controle");
		graficoControle.setLegendPosition("e");
		xAxis = graficoControle.getAxis(AxisType.X);
		xAxis.setMin(0);
		xAxis.setMax(120);
		yAxis = graficoControle.getAxis(AxisType.Y);
		//yAxis.setMin(0);
	}

	public void conectar(){
		System.out.println("Tentar Conectar");
		comunicador = new Comunicador("localhost", filas, tempo);
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
	
	public void desligarBomba() {
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
		
		String config = 0 + SEP
						+ 0 + SEP
						+ 0 + SEP
						+ 0 + SEP
						+ 0 + SEP
						+ 0 + SEP
						+ 0 + SEP
						+ 0 + SEP + 0;
		
		System.out.println(config);
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
		
		String config = onda + SEP
						+ amp + SEP
						+ amp + SEP
						+ amp_inf + SEP
						+ periodo + SEP
						+ periodo + SEP
						+ periodo_inf + SEP
						+ offset + SEP + 0;
		
		System.out.println(config);
		comunicador.enviarConfiguracoes(config);
	}

	public void configMalhaFechada() {
		System.out.println("Malha Fechada, " + TEMPO);

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
		graficoNivel.addSeries(filas.get(REF));
		
		graficoControle.addSeries(filas.get(ERRO));
		graficoControle.addSeries(filas.get(ERRO_SAT));
		
		String config = this.onda + SEP
				+ this.amp + SEP
				+ this.amp + SEP
				+ this.amp_inf + SEP
				+ this.periodo + SEP
				+ this.periodo + SEP
				+ this.periodo_inf + SEP
				+ this.offset + SEP + 1;
		System.out.println(config);
		comunicador.enviarConfiguracoes(config);
	}
}
