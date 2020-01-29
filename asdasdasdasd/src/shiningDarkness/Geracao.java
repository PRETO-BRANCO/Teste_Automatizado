package shiningDarkness;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Geracao {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;	
	private String ed;
	private String unidade;	
	private Acondicionamento acondicionamento;
	private String acondi;
	private Situacao situacao;
	private String sit;

	public Geracao(WebDriver driver, WebDriverWait wdw,Signal horizon,Long starttime,String ed,String unidade) {		
		this.driver = driver;
		this.wdw = wdw;		
		this.horizon = horizon;		
		this.ed = ed;
		this.unidade = unidade;		
		this.acondicionamento = new Acondicionamento(driver, wdw, horizon, starttime, ed, unidade);
		this.acondi = this.acondicionamento.criar();
		this.situacao = new Situacao(driver, wdw, horizon, starttime, ed, unidade);
		this.sit = situacao.criar();
	}

	public void criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/ma/residuos/principal/");

		this.horizon.waitLoad();

		driver.findElement(By.id("lb-geracao-residuos")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-area1"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area1")).sendKeys(unidade);
		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area2")).sendKeys("AreaTeste");
		driver.findElement(By.xpath("//li [contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area3")).sendKeys("SetorTeste");
		driver.findElement(By.xpath("//li [contains(text(),'SetorTeste')]")).click();

		driver.findElement(By.id("ac-residuo")).sendKeys("ResiduoTeste");
		driver.findElement(By.xpath("//li [contains(text(),'ResiduoTeste')]")).click();

		driver.findElement(By.id("i-quantidade")).sendKeys( String.format("%06d", ThreadLocalRandom.current().nextInt(1000000)));
		driver.findElement(By.id("i-data-geracao")).sendKeys("22032019");

		Select slek = new Select(driver.findElement(By.id("cb-tipo-destino")));
		slek.selectByVisibleText("DestinoTeste");

		driver.findElement(By.id("i-comentario")).sendKeys(this.horizon.generateString(200));

		slek = new Select(driver.findElement(By.id("cb-estoque")));
		slek.selectByVisibleText("EstoqueTeste");

		slek = new Select(driver.findElement(By.id("cb-acondicionamento")));
		slek.selectByVisibleText(acondi);

		slek = new Select(driver.findElement(By.id("cb-situacao")));
		slek.selectByVisibleText(sit);

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSalvar()']")).click();		
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default margin-left btn-nao']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default margin-left btn-nao']")).click();

		this.horizon.waitLoad();
	}

	public void editar() {
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));
		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area1")).sendKeys(unidade);
		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area2")).sendKeys("AreaTeste");
		driver.findElement(By.xpath("//li [contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area3")).sendKeys("SetorTeste");
		driver.findElement(By.xpath("//li [contains(text(),'SetorTeste')]")).click();

		driver.findElement(By.id("ac-residuo")).sendKeys("ResiduoTeste");
		driver.findElement(By.xpath("//li [contains(text(),'ResiduoTeste')]")).click();	

		Select slek = new Select(driver.findElement(By.id("cb-tipo-destino")));
		slek.selectByVisibleText("DestinoTeste");

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'ResiduoTeste')]")).click();			

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary pull-right bt-visualizar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-quantidade"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-quantidade"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-quantidade")).sendKeys(String.format("%06d", ThreadLocalRandom.current().nextInt(1000000)));
		driver.findElement(By.id("i-comentario")).sendKeys(this.horizon.generateString(200));

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSalvar()']")).click();		
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default margin-left btn-nao']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default margin-left btn-nao']")).click();

		this.horizon.waitLoad();
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/ma/residuos/principal/");

		this.horizon.waitLoad();

		driver.findElement(By.id("lb-pesquisar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));
		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area1")).sendKeys(unidade);
		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area2")).sendKeys("AreaTeste");
		driver.findElement(By.xpath("//li [contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area3")).sendKeys("SetorTeste");
		driver.findElement(By.xpath("//li [contains(text(),'SetorTeste')]")).click();

		driver.findElement(By.id("ac-residuo")).sendKeys("ResiduoTeste");
		driver.findElement(By.xpath("//li [contains(text(),'ResiduoTeste')]")).click();	

		Select slek = new Select(driver.findElement(By.id("cb-tipo-destino")));
		slek.selectByVisibleText("DestinoTeste");

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'ResiduoTeste')]")).click();		

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default lb-sim margin-left']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default lb-sim margin-left']")).click();

		this.horizon.waitLoad();

		situacao.excluir();

		this.horizon.waitLoad();

		acondicionamento.excluir();
	}
}