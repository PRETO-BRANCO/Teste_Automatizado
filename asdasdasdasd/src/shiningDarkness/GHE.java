package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GHE {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Area area;
	private Risco risco;
	private String[] areas;
	private String ris;
	private Cargo cargo;
	private String car;
	private EPCs epc;
	private String ep;
	private EPIs epis;
	private String epi;
	private Fontes fontes;
	private String fonte;
	private long starttime;
	private String ed;
	private String unidade;
	private String ghe;

	public GHE(WebDriver driver, WebDriverWait wdw, Signal horizon, Long starttime,String ed, String unidade) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.starttime = starttime;
		this.ed = ed;
		this.unidade = unidade;
		area = new Area(driver,wdw,horizon, starttime, ed, unidade);
		risco = new Risco(driver,wdw,horizon, starttime, ed);
		cargo = new Cargo(driver,wdw,horizon, starttime, ed);
		epc = new EPCs(driver,wdw,horizon, starttime, ed);
		epis = new EPIs(driver,wdw,horizon, starttime, ed);
		fontes = new Fontes(driver,wdw,horizon, starttime, ed);
		areas = area.criar();
		ris = risco.criar();

		this.horizon.waitLoad();

		car = cargo.criar();

		this.horizon.waitLoad2();

		ep = epc.criar();

		this.horizon.waitLoad2();

		epi = epis.criar();

		this.horizon.waitLoad2();

		fonte = fontes.criar();
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/ghe/ghe.html");

		this.horizon.waitLoad();

		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary pull-right bt-adicionar\"]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-area1-cadastro"))));
		this.horizon.waitLoad();		

		driver.findElement(By.id("ac-area1-cadastro")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area2-cadastro")).sendKeys(areas[0]);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ areas[0] +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area3-cadastro")).sendKeys(areas[1]);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ areas[1] +"')]")).click();

		ghe = this.horizon.generateString(30);

		driver.findElement(By.id("i-codigo")).sendKeys(String.format("%015d",ThreadLocalRandom.current().nextLong(1000000000000000l)));		
		driver.findElement(By.id("i-descricao")).sendKeys(ghe);
		driver.findElement(By.id("ta-descricao-ambiente")).sendKeys(this.horizon.generateString(500));
		driver.findElement(By.id("i-data-cadastro-inicio")).sendKeys("01012019");
		driver.findElement(By.id("i-jornada-diaria")).sendKeys("88");
		driver.findElement(By.id("i-jornada-semanal")).sendKeys("4400");
		driver.findElement(By.id("i-expostos")).sendKeys(String.format("%03d",ThreadLocalRandom.current().nextInt(1000)));
		driver.findElement(By.id("i-revezamento")).sendKeys(this.horizon.generateString(13));
		driver.findElement(By.id("chk-estabelecimento-terceiro")).click();
		driver.findElement(By.id("i-cnpj-terceiro")).sendKeys(String.format("%014d",ThreadLocalRandom.current().nextLong(100000000000000l)));
		driver.findElement(By.id("ac-cargo")).sendKeys(car);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ car +"')]")).click();
		driver.findElement(By.id("btn-adicionar-cargo")).click();
		driver.findElement(By.id("btn-observacoes")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ta-observacao-registro")).sendKeys(this.horizon.generateString(400));
		driver.findElement(By.xpath("//button [@ng-click='onClickBtnAdicionarRegistroObservacao()']")).click();
		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSalvarModalRegistro()']")).click();

		this.horizon.waitLoad();
		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));

		driver.findElement(By.id("li-passo-principal-2")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ta-atividade-descricao"))));

		driver.findElement(By.id("ta-atividade-descricao")).sendKeys(this.horizon.generateString(2000));
		driver.findElement(By.id("i-data-atividade-inicio")).sendKeys("01012019");

		Select freq = new Select(driver.findElement(By.id("cb-atividade-frequencia")));
		List<WebElement> lista = freq.getOptions();
		freq.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));		

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary lb-adicionar campo tooltip-top tt-adicionar\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary lb-adicionar campo tooltip-top tt-adicionar\"]")).click();

		driver.findElement(By.id("li-passo-principal-3")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-risco"))));

		driver.findElement(By.id("ac-risco")).sendKeys(ris);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ ris +"')]")).click();

		freq = new Select(driver.findElement(By.id("cb-probabilidade")));
		freq.selectByValue(String.valueOf(ThreadLocalRandom.current().nextInt(1,5)));
		freq = new Select(driver.findElement(By.id("cb-severidade")));
		freq.selectByValue(String.valueOf(ThreadLocalRandom.current().nextInt(1,5)));
		freq = new Select(driver.findElement(By.id("cb-risco-via-absorcao")));
		lista = freq.getOptions();
		freq.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));	

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-primary lb-adicionar campo tooltip-top tt-adicionar\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-primary lb-adicionar campo tooltip-top tt-adicionar\"]")).click();

		driver.findElement(By.id("li-passo-principal-5")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-risco-insalubridade"))));

		driver.findElement(By.id("ac-risco-insalubridade")).sendKeys(ris);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'- "+ ris +" -') and not(ancestor::div[contains(@style,'display:none')]) and not(ancestor::div[contains(@style,'display: none')])]")).click();

		freq= new Select(driver.findElement(By.id("cb-grau-insalubridade")));
		freq.selectByValue("10");
		driver.findElement(By.id("i-data-insalubridade-fim")).sendKeys("01022019");
		driver.findElement(By.xpath("//button [@ng-click='onClickBtnAdicionarInsalubridade()']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("tab-periculosidade")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-risco-periculosidade"))));

		driver.findElement(By.id("ac-risco-periculosidade")).sendKeys("ele");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'Eletricidade')]")).click();
		driver.findElement(By.id("i-data-periculosidade-fim")).sendKeys("01022019");
		driver.findElement(By.xpath("//button [@ng-click='onClickBtnAdicionarPericulosidade()']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("tab-aposentadoria")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-risco-aposentadoria"))));

		driver.findElement(By.id("ac-risco-aposentadoria")).sendKeys(ris);

		driver.findElement(By.xpath("//ul[not(contains(@style,'display: none'))]//li [contains(@class,'ui-menu-item') and contains(text(),'- "+ ris +" -')]")).click();

		freq= new Select(driver.findElement(By.id("cb-codigo-fae")));
		freq.selectByIndex(ThreadLocalRandom.current().nextInt(1,4));
		driver.findElement(By.id("i-data-aposentadoria-fim")).sendKeys("01022019");
		driver.findElement(By.xpath("//button [@ng-click='onClickBtnAdicionarAposentadoria()']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("li-passo-principal-4")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default risco-epc']"))));

		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default risco-epc']")).click();

		driver.findElement(By.xpath("//label [contains(@title,'"+ris+"')]")).click();

		this.horizon.waitLoad();

		freq= new Select(driver.findElement(By.id("cb-medidas-controle")));
		freq.selectByVisibleText(ep);
		driver.findElement(By.id("i-data-epc-fim")).sendKeys("01022019");
		driver.findElement(By.xpath("//button [@ng-click='onClickBtnAdicionarControle()']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("tab-epis")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default risco-epi']"))));

		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default risco-epi']")).click();

		driver.findElement(By.xpath("//label [contains(@title,'"+ris+"') and not(ancestor::div[contains(@style,'display:none')]) and not(ancestor::div[contains(@style,'display: none')])]")).click();

		driver.findElement(By.id("ac-epis")).sendKeys(epi);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ epi +"')]")).click();
		driver.findElement(By.id("i-data-epi-fim")).sendKeys("01022019");
		driver.findElement(By.id("bt-questionario-esocial")).click();

		this.horizon.waitLoad();

		lista = driver.findElements(By.xpath("//input [@value='true']"));
		List<WebElement> lista2 = driver.findElements(By.xpath("//input [@value='false']"));
		for(int i = 1; i<7;i++) {
			if((i%2) == 0) {
				lista.get(i).click();
			}
			else {
				lista2.get(i).click();
			}
		}		
		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSalvarQuestionario()']")).click();		

		this.horizon.waitLoad();
		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnAdicionarEPI()']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("tab-fontes")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default risco-fonte']"))));

		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default risco-fonte']")).click();

		driver.findElement(By.xpath("//label [contains(@title,'"+ris+"') and not(ancestor::div[contains(@style,'display:none')]) and not(ancestor::div[contains(@style,'display: none')])]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-fonte-geradora")).sendKeys(fonte);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ fonte +"')]")).click();
		driver.findElement(By.id("i-data-fonte-fim")).sendKeys("01022019");
		driver.findElement(By.xpath("//button [@ng-click='onClickBtnAdicionarFonteGeradora()']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("tab-recomendacoes")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default risco-recomendacoes']"))));

		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default risco-recomendacoes']")).click();

		driver.findElement(By.xpath("//label [contains(@title,'"+ris+"') and not(ancestor::div[contains(@style,'display:none')]) and not(ancestor::div[contains(@style,'display: none')])]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-recomendacoes")).sendKeys(risco.getRecomendacao());

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ risco.getRecomendacao() +"')]")).click();
		driver.findElement(By.id("i-data-recomendacao-fim")).sendKeys("01022019");
		driver.findElement(By.xpath("//button [@ng-click='onClickBtnAdicionarRecomendacoes()']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("tab-relevancia")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("chk-ppra"))));

		driver.findElement(By.xpath("//a [@ng-click='onClickBtnEditarRelevancia(r)' and not(ancestor::div[contains(@style,'display:none')]) and not(ancestor::div[contains(@style,'display: none')])]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("chk-ppra")).click();
		driver.findElement(By.id("chk-ltcat")).click();
		driver.findElement(By.id("chk-ppp")).click();
		driver.findElement(By.id("chk-aprho")).click();
		driver.findElement(By.id("chk-esocial")).click();
		driver.findElement(By.id("chk-pcmso")).click();
		driver.findElement(By.xpath("//button [@class='btn btn-primary tooltip-top tt-salvar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-salvar"))));
		driver.findElement(By.id("bt-salvar")).click();

		this.horizon.waitLoad();

		System.out.println("GHE " + ghe + " 					" + horizon.time(starttime));
		return ghe;
	}

	public void editar() {
		driver.navigate().refresh();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area1-filtro")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();	

		driver.findElement(By.id("ac-area2-filtro")).sendKeys(areas[0]);

		this.horizon.sleep(350);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ areas[0] +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'"+ ghe +"')]")).click();

		driver.findElement(By.xpath("//button [@data-ng-click='onClickBtnVisualizar()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-area1-cadastro"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		ghe = this.horizon.generateString(30);

		driver.findElement(By.id("i-codigo")).sendKeys(String.format("%015d",ThreadLocalRandom.current().nextLong(1000000000000000l)));		
		driver.findElement(By.id("i-descricao")).sendKeys(ghe);
		driver.findElement(By.id("ta-descricao-ambiente")).sendKeys(this.horizon.generateString(499));
		driver.findElement(By.id("i-data-cadastro-fim")).sendKeys("01022019");
		driver.findElement(By.id("i-jornada-diaria")).sendKeys("88");

		this.horizon.waitLoad();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-salvar"))));
		driver.findElement(By.id("bt-salvar")).click();

		this.horizon.waitLoad();

		System.out.println("GHE+ " + ghe + "					" + horizon.time(starttime));	
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/ghe/ghe.html");

		this.horizon.waitLoad();

		driver.navigate().refresh();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area1-filtro")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();	

		driver.findElement(By.id("ac-area2-filtro")).sendKeys(areas[0]);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ areas[0] +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'"+ ghe +"')]")).click();		

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default pull-right bt-excluir\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default pull-right bt-excluir\"]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-principal margin-left btn-sim\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-principal margin-left btn-sim\"]")).click();

		this.horizon.waitLoad();

		this.area.excluir();

		this.horizon.waitLoad2();

		this.risco.excluir();

		this.horizon.waitLoad2();

		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/cargo.html");

		this.horizon.waitLoad2();

		this.cargo.excluir();

		this.horizon.waitLoad2();

		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/equip_geral.html");

		this.horizon.waitLoad2();

		this.epis.excluir();

		this.horizon.waitLoad2();

		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/fonte_geradora.html");

		this.horizon.waitLoad2();

		this.fontes.excluir();

		this.horizon.waitLoad2();

		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/medidas_controle.html");

		this.horizon.waitLoad2();

		this.epc.excluir();

		this.horizon.waitLoad2();
	}

	public String[] getAreas() {
		return areas;
	}

	public String getRis() {
		return ris;
	}
}