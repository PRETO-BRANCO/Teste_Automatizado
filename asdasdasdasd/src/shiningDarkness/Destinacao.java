package shiningDarkness;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Destinacao {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;	
	private String ed;
	private String unidade;	
	private Acondicionamento acondicionamento;
	private String acondi;	
	private Transportador transporte;
	private String trans;
	private Situacao situacao;
	private String situa;

	public Destinacao(WebDriver driver, WebDriverWait wdw,Signal horizon,Long starttime,String ed,String unidade) {		
		this.driver = driver;
		this.wdw = wdw;		
		this.horizon = horizon;		
		this.ed = ed;
		this.unidade = unidade;		
		this.acondicionamento = new Acondicionamento(driver, wdw, horizon, starttime, ed, unidade);
		this.acondi = this.acondicionamento.criar();
		this.transporte = new Transportador(driver, wdw, horizon, starttime, ed, unidade);
		this.trans = this.transporte.criar();		
		this.situacao = new Situacao(driver, wdw, horizon, starttime, ed, unidade);
		this.situa = situacao.criar();
	}

	public void criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/ma/residuos/principal/");

		this.horizon.waitLoad();

		driver.findElement(By.id("lb-destinacao-residuos")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-area1"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area1")).sendKeys(unidade);
		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-residuo")).sendKeys("ResiduoTeste");
		driver.findElement(By.xpath("//li [contains(text(),'ResiduoTeste')]")).click();

		this.horizon.waitLoad();

		Select slek = new Select(driver.findElement(By.id("cb-origem")));
		slek.selectByIndex(1);

		slek = new Select(driver.findElement(By.id("cb-acondicionamento")));
		slek.selectByVisibleText(acondi);

		slek = new Select(driver.findElement(By.id("cb-destino")));
		slek.selectByVisibleText(trans);

		slek = new Select(driver.findElement(By.id("cb-transporte")));
		slek.selectByVisibleText(trans);

		slek = new Select(driver.findElement(By.id("cb-tipo-transporte")));
		slek.selectByIndex(1);

		driver.findElement(By.id("i-custo-quantidade")).sendKeys( String.format("%05d", ThreadLocalRandom.current().nextInt(100000)));
		driver.findElement(By.id("i-custo-quantidade2")).sendKeys( String.format("%05d", ThreadLocalRandom.current().nextInt(100000)));
		driver.findElement(By.id("i-placa-veiculo")).sendKeys(this.horizon.generateString2(5));
		driver.findElement(By.id("i-numero-nf")).sendKeys( String.format("%07d", ThreadLocalRandom.current().nextInt(10000000)));
		driver.findElement(By.id("i-numero-cdf")).sendKeys( String.format("%07d", ThreadLocalRandom.current().nextInt(10000000)));
		driver.findElement(By.id("i-numero-mtr")).sendKeys( String.format("%07d", ThreadLocalRandom.current().nextInt(10000000)));
		driver.findElement(By.id("i-data-mtr")).sendKeys("22032019");
		driver.findElement(By.id("ta-comentario")).sendKeys(this.horizon.generateString(200));

		driver.findElement(By.id("li-passo-2")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-area2"))));

		driver.findElement(By.id("ac-area2")).sendKeys("AreaTeste");
		driver.findElement(By.xpath("//li [contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area3")).sendKeys("SetorTeste");
		driver.findElement(By.xpath("//li [contains(text(),'SetorTeste')]")).click();

		slek = new Select(driver.findElement(By.id("cb-tipo-destino")));
		slek.selectByVisibleText("DestinoTeste");

		slek = new Select(driver.findElement(By.id("cb-situacao")));
		slek.selectByVisibleText(situa);

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSalvar(true)']")).click();		
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@data-ng-click='onClickBtnNaoModalIncluirDestinacao()']"))));
		driver.findElement(By.xpath("//button [@data-ng-click='onClickBtnNaoModalIncluirDestinacao()']")).click();

		this.horizon.waitLoad();
	}

	public void editar() {
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));

		driver.findElement(By.id("rb-destinacao")).click();

		driver.findElement(By.id("ac-area1")).sendKeys(unidade);
		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area2")).sendKeys("AreaTeste");
		driver.findElement(By.xpath("//li [contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area3")).sendKeys("SetorTeste");
		driver.findElement(By.xpath("//li [contains(text(),'SetorTeste')]")).click();

		Select slek = new Select(driver.findElement(By.id("cb-tipo-destino")));
		slek.selectByVisibleText("DestinoTeste");

		driver.findElement(By.id("ac-residuo")).sendKeys("ResiduoTeste");
		driver.findElement(By.xpath("//li [contains(text(),'ResiduoTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'ResiduoTeste')]")).click();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary pull-right bt-visualizar']")).click();

		wdw.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("i-placa-veiculo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-placa-veiculo")).sendKeys(String.format("%05d", ThreadLocalRandom.current().nextInt(100000)));
		driver.findElement(By.id("i-custo-quantidade")).sendKeys( String.format("%06d", ThreadLocalRandom.current().nextInt(1000000)));
		driver.findElement(By.id("i-custo-quantidade2")).sendKeys( String.format("%06d", ThreadLocalRandom.current().nextInt(1000000)));
		driver.findElement(By.id("i-numero-nf")).sendKeys( String.format("%08d", ThreadLocalRandom.current().nextInt(100000000)));
		driver.findElement(By.id("i-numero-cdf")).sendKeys( String.format("%08d", ThreadLocalRandom.current().nextInt(100000000)));
		driver.findElement(By.id("i-numero-mtr")).sendKeys( String.format("%08d", ThreadLocalRandom.current().nextInt(100000000)));
		driver.findElement(By.id("ta-comentario")).sendKeys(this.horizon.generateString(200));

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSalvar(true)']")).click();		
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@data-ng-click='onClickBtnNaoModalIncluirDestinacao()']"))));
		driver.findElement(By.xpath("//button [@data-ng-click='onClickBtnNaoModalIncluirDestinacao()']")).click();

		this.horizon.waitLoad();
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/ma/residuos/principal/");

		this.horizon.waitLoad();

		driver.findElement(By.id("lb-pesquisar")).click();		

		wdw.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("bt-filtrar"))));

		driver.findElement(By.id("ac-area1")).sendKeys(unidade);
		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area2")).sendKeys("AreaTeste");
		driver.findElement(By.xpath("//li [contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area3")).sendKeys("SetorTeste");
		driver.findElement(By.xpath("//li [contains(text(),'SetorTeste')]")).click();

		Select slek = new Select(driver.findElement(By.id("cb-tipo-destino")));
		slek.selectByVisibleText("DestinoTeste");

		driver.findElement(By.id("ac-residuo")).sendKeys("ResiduoTeste");
		driver.findElement(By.xpath("//li [contains(text(),'ResiduoTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'ResiduoTeste')]")).click();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@data-ng-click='onClickBtnSimExcluir()']"))));
		driver.findElement(By.xpath("//button [@data-ng-click='onClickBtnSimExcluir()']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("rb-destinacao")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'ResiduoTeste')]")).click();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@data-ng-click='onClickBtnSimExcluir()']"))));
		driver.findElement(By.xpath("//button [@data-ng-click='onClickBtnSimExcluir()']")).click();

		this.horizon.waitLoad();

		acondicionamento.excluir();

		this.horizon.waitLoad();

		transporte.excluir();

		this.horizon.waitLoad();

		situacao.excluir();

		this.horizon.waitLoad();
	}
}