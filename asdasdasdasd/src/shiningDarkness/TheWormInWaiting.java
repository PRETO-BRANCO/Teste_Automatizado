package shiningDarkness;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TheWormInWaiting {
	static long starttime = System.currentTimeMillis();	
	static WebDriver driver;
	static WebDriverWait wdw;
	static Actions act;
	static int x;
	static String endereco;
	static String unidade;
	static String login;
	static String senha;

	public static void main(String[] args) {	
		System.setProperty("webdriver.gecko.driver", "D:\\geckodriver.exe");
		endereco = "electrolux.qa";
		unidade = "70-ARGENTINA";
		login = "ADM1";
		senha = "Desenv2019@@";
		x = 0;
		File ffPath = new File("C:\\Program Files\\Firefox Nightly\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(ffPath);
		FirefoxProfile ffProfile = new FirefoxProfile();
		FirefoxOptions ffOptions = new FirefoxOptions();
		ffOptions.setProfile(ffProfile);
		ffOptions.setBinary(ffBinary);
		ffOptions.setCapability(FirefoxOptions.FIREFOX_OPTIONS,ffOptions);
		driver  = new FirefoxDriver(ffOptions);
		wdw = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Signal horizonSignal = new Signal(driver,wdw);
		EnterTheWyvernNest nest = new EnterTheWyvernNest(driver,wdw,endereco, login, senha);

		nest.login();

		horizonSignal.waitLoad();
		int i = 0;
		while(i<1){
			System.out.println("\nWhat was, shall be. What shall be, was.\n");
			
			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				PontoDistribuicao ponto = new PontoDistribuicao(driver,wdw,horizonSignal,starttime,endereco,unidade);				
				ponto.criar();				
			}catch(Exception e) {
				e.printStackTrace();
			}

			/*try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Alergia alergia = new Alergia(driver,wdw,horizonSignal,starttime,endereco);				
				alergia.criar();
				alergia.editar();
				alergia.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Encaminhamento encaminhamento = new Encaminhamento(driver,wdw,horizonSignal,starttime, endereco);				
				encaminhamento.criar();
				encaminhamento.editar();				
				encaminhamento.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Vacinas vacinas = new Vacinas(driver,wdw,horizonSignal,starttime, endereco);				
				vacinas.criar();
				vacinas.editar();
				vacinas.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Centro centro = new Centro(driver,wdw,horizonSignal,starttime, endereco);				
				centro.criar();
				centro.editar();
				centro.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Diagnostico diag = new Diagnostico(driver,wdw,horizonSignal,starttime, endereco);				
				diag.criar();
				diag.editar();
				diag.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Sinais sinais = new Sinais(driver,wdw,horizonSignal,starttime, endereco);				
				sinais.criar();
				sinais.editar();
				sinais.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				GrupoRisco grupo = new GrupoRisco(driver,wdw,horizonSignal,starttime, endereco);				
				grupo.criar();
				grupo.editar();
				grupo.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Agendamento agenda = new Agendamento(driver,wdw,horizonSignal,starttime, endereco);				
				agenda.criar();
				agenda.editar();
				agenda.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Tipo tipo = new Tipo(driver,wdw,horizonSignal,starttime, endereco);				
				tipo.criar();
				tipo.editar();
				tipo.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Questoes quest = new Questoes(driver,wdw,horizonSignal,starttime, endereco);				
				quest.criar0();
				quest.criar1();
				quest.criar2();
				quest.editar();				
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();		

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Questionario questionario = new Questionario(driver,wdw,horizonSignal, starttime, endereco);				
				questionario.criar();
				questionario.editar();
				questionario.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Profissionais prof = new Profissionais(driver,wdw,horizonSignal, endereco);				
				prof.criar();
				prof.editar();
				prof.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				driver.navigate().refresh();
				PCMSO pcmso = new PCMSO(driver,wdw,horizonSignal, starttime, endereco, unidade);				
				pcmso.criar();
				pcmso.editar();
				pcmso.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Area area = new Area(driver,wdw,horizonSignal, starttime, endereco, unidade);				
				area.criar();
				horizonSignal.waitLoad();
				area.editar();	
				horizonSignal.waitLoad();
				area.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				CentroCusto centroC = new CentroCusto(driver,wdw,horizonSignal, starttime, endereco);				
				centroC.criar();
				centroC.editar();
				centroC.excluir();								
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				ProfGeral profGeral = new ProfGeral(driver,wdw,horizonSignal, starttime, endereco);				
				profGeral.criar();			
				profGeral.editar();
				profGeral.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Cargo cargo = new Cargo(driver,wdw,horizonSignal, starttime, endereco);				
				cargo.criar();
				cargo.editar();	
				cargo.excluir();								
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Catalogo catalogo = new Catalogo(driver,wdw,horizonSignal, starttime, endereco);				
				catalogo.criar();
				catalogo.editar();	
				catalogo.excluir();								
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Dano dano = new Dano(driver,wdw,horizonSignal, starttime, endereco);				
				dano.criar();
				dano.editar();	
				dano.excluir();								
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				EPIs epi = new EPIs(driver,wdw,horizonSignal, starttime, endereco);				
				epi.criar();
				epi.editar();
				epi.excluir();								
			}catch(Exception e) {
				e.printStackTrace();
			}	

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Fontes fontes = new Fontes(driver,wdw,horizonSignal, starttime, endereco);				
				fontes.criar();
				fontes.editar();	
				fontes.excluir();								
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				EPCs epc = new EPCs(driver,wdw,horizonSignal, starttime, endereco);				
				epc.criar();
				epc.editar();	
				epc.excluir();								
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Processo processo = new Processo(driver,wdw,horizonSignal, starttime, endereco);				
				processo.criar();
				processo.editar();
				processo.excluir();								
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Recomenda recomenda = new Recomenda(driver,wdw,horizonSignal, starttime, endereco);				
				recomenda.criar();
				recomenda.editar();	
				recomenda.excluir();								
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Tecnica tec = new Tecnica(driver,wdw,horizonSignal, starttime, endereco);				
				tec.criar();
				tec.editar();	
				tec.excluir();								
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				EquipMedicao equipMed = new EquipMedicao(driver,wdw,horizonSignal, starttime, endereco, unidade);				
				equipMed.criar();
				equipMed.editar();
				equipMed.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Turnos turno = new Turnos(driver, wdw, horizonSignal, starttime, endereco, unidade);
				turno.criar();
				turno.editar();
				turno.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Risco risco = new Risco(driver,wdw,horizonSignal, starttime, endereco);
				risco.criar();
				horizonSignal.waitLoad();
				risco.editar();		
				risco.excluir();								
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				GHE ghe = new GHE(driver,wdw,horizonSignal, starttime, endereco, unidade);					
				horizonSignal.waitLoad2();
				ghe.criar();
				horizonSignal.waitLoad();
				ghe.editar();	
				ghe.excluir();							
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();			

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Topicos top = new Topicos(driver,wdw,horizonSignal, starttime, endereco);				
				top.criar();
				top.editar();
				top.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Perigos perigo = new Perigos(driver,wdw,horizonSignal, starttime, endereco);				
				perigo.criar();
				perigo.editar();
				perigo.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Maquina maquina = new Maquina(driver,wdw,horizonSignal, starttime, endereco, unidade);				
				maquina.criar();
				horizonSignal.waitLoad2();
				maquina.editar();	
				maquina.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();			

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Apreciacao aprecia = new Apreciacao(driver,wdw,horizonSignal, starttime, endereco, unidade);				
				aprecia.criar();
				aprecia.visualizar();
				aprecia.statusQuo();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Acoes acao = new Acoes(driver,wdw,horizonSignal, starttime, endereco, unidade);				
				acao.criar();				
				acao.editar();
				acao.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}			

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Conclusao conclusao = new Conclusao(driver,wdw,horizonSignal, endereco, unidade);				
				conclusao.criar();
				conclusao.statusQuo();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Medicao medicao = new Medicao(driver,wdw,horizonSignal, starttime, endereco, unidade);				
				medicao.criar();
				medicao.editar();
				medicao.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}			

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				MedCalor medCalor = new MedCalor(driver,wdw,horizonSignal, starttime, endereco, unidade);				
				medCalor.criar();
				medCalor.editar();
				medCalor.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Calculos calculo = new Calculos(driver,wdw,horizonSignal, starttime, endereco, unidade);				
				calculo.calculoCalor();
				calculo.excluirCalor();
				calculo.calcularMedida();
				calculo.excluirMedida();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				PPRA ppra = new PPRA(driver,wdw,horizonSignal, starttime, endereco, unidade);				
				ppra.criar();
				ppra.editar();
				ppra.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				PPP ppp = new PPP(driver,wdw,horizonSignal, starttime, endereco, unidade);				
				ppp.criar();
				ppp.editar();
				ppp.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				LTCAT ltcat = new LTCAT(driver,wdw,horizonSignal, starttime, endereco, unidade);
				ltcat.criar();
				ltcat.editar();
				ltcat.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				LTAQ ltaq = new LTAQ(driver,wdw,horizonSignal, starttime, endereco, unidade);
				ltaq.criar();
				ltaq.editar();
				ltaq.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Insalubridade insal = new Insalubridade(driver, wdw, horizonSignal, starttime, endereco, unidade);
				insal.criar();
				insal.editar();
				insal.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Categorias cat = new Categorias(driver, wdw, horizonSignal, starttime, endereco);
				cat.criar();
				cat.editar();
				cat.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				QuestArg qa = new QuestArg(driver, wdw, horizonSignal, starttime, endereco);
				qa.criar();
				qa.editar();
				qa.excluir();
				qa.excat();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				AnaliseErgo analise = new AnaliseErgo(driver, wdw, horizonSignal, endereco, unidade, starttime);
				analise.criar();
				analise.editar();
				analise.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				LaudoAnaliseErgo laudo = new LaudoAnaliseErgo(driver, wdw, horizonSignal, endereco, unidade, starttime);
				laudo.criar();
				laudo.editar();
				laudo.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Familia familia = new Familia(driver, wdw, horizonSignal, starttime, endereco);
				familia.criar();
				familia.editar();
				familia.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Equipamentos equip = new Equipamentos(driver, wdw, horizonSignal, starttime, endereco, unidade);
				equip.criar();
				equip.editar();
				equip.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				BancoQuimico banco = new BancoQuimico(driver, wdw, horizonSignal, starttime, endereco, unidade);
				banco.criar();
				banco.editar();
				banco.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Classe classe = new Classe(driver, wdw, horizonSignal, starttime, endereco);
				classe.criar();
				classe.editar();
				classe.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Equip equip = new Equip(driver, wdw, horizonSignal, starttime, endereco, unidade);
				equip.criar();
				equip.editar();
				equip.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad2();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				CategoriaTreino catTreino = new CategoriaTreino(driver, wdw, horizonSignal, starttime, endereco);
				catTreino.criar();
				catTreino.editar();
				catTreino.excluir();
			}catch(Exception e) {
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Treinamento treino = new Treinamento(driver, wdw, horizonSignal, starttime, endereco, unidade);
				treino.criar();
				treino.editar();
				treino.excluir();
			}catch(Exception e) {			
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Destino destino = new Destino(driver, wdw, horizonSignal, starttime, endereco, unidade);
				destino.criar();
				destino.editar();
				destino.excluir();
			}catch(Exception e) {			
				e.printStackTrace();
			}	

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Caracteristicas karak = new Caracteristicas(driver, wdw, horizonSignal, starttime, endereco, unidade);
				karak.criar();
				karak.editar();
				karak.excluir();
			}catch(Exception e) {			
				e.printStackTrace();
			}	

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Residuos residuos = new Residuos(driver, wdw, horizonSignal, starttime, endereco, unidade);
				residuos.criar();
				residuos.editar();
				residuos.excluir();
			}catch(Exception e) {			
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Transportador transporte = new Transportador(driver, wdw, horizonSignal, starttime, endereco, unidade);
				transporte.criar();
				transporte.editar();
				transporte.excluir();
			}catch(Exception e) {			
				e.printStackTrace();
			}	

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Estoque estoque = new Estoque(driver, wdw, horizonSignal, starttime, endereco, unidade);
				estoque.criar();
				estoque.editar();
				estoque.excluir();
			}catch(Exception e) {			
				e.printStackTrace();
			}	

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Acondicionamento acondicionamento = new Acondicionamento(driver, wdw, horizonSignal, starttime, endereco, unidade);
				acondicionamento.criar();
				acondicionamento.editar();
				acondicionamento.excluir();
			}catch(Exception e) {			
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Situacao situacao = new Situacao(driver, wdw, horizonSignal, starttime, endereco, unidade);
				situacao.criar();
				situacao.editar();
				situacao.excluir();
			}catch(Exception e) {			
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Geracao geracao = new Geracao(driver, wdw, horizonSignal, starttime, endereco, unidade );
				geracao.criar();
				geracao.editar();
				geracao.excluir();
			}catch(Exception e) {			
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Destinacao destinacao = new Destinacao(driver, wdw, horizonSignal, starttime, endereco, unidade);
				destinacao.criar();
				destinacao.editar();
				destinacao.excluir();
			}catch(Exception e) {			
				e.printStackTrace();
			}

			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				RelatorioResiduo relatorio = new RelatorioResiduo(driver, wdw, horizonSignal, starttime, endereco, unidade);
				relatorio.criar();
				relatorio.editar();
				relatorio.excluir();
			}catch(Exception e) {			
				e.printStackTrace();
			}
			
			horizonSignal.waitLoad();
			System.out.println();

			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Idioma idioma = new Idioma(driver, wdw, horizonSignal, starttime, endereco, unidade);
				idioma.criar();
				idioma.editar();
				idioma.excluir();
			}catch(Exception e) {			
				e.printStackTrace();
			}
			
			horizonSignal.waitLoad2();
			System.out.println();
			
			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				Fornecedor fornecedor = new Fornecedor(driver, wdw, horizonSignal, starttime, endereco, unidade);
				fornecedor.criar();
				fornecedor.editar();
				fornecedor.excluir();
			}catch(Exception e) {			
				e.printStackTrace();
			}
			
			horizonSignal.waitLoad2();
			System.out.println();
			
			try {
				System.out.println("Teste  "+ ++x + "								" + horizonSignal.time(starttime));
				DocQuimico docQ = new DocQuimico(driver, wdw, horizonSignal, starttime, endereco, unidade);
				docQ.criar();				
			}catch(Exception e) {			
				e.printStackTrace();
			}
			*/
			i++;			
			System.out.println();
			System.out.println("The Worm comes								" + horizonSignal.time(starttime));
			System.out.println();
		}		
	}
}