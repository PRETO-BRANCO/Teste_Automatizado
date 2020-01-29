package shiningDarkness;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Acoes {
	private WebDriver driver;
	private Signal horizon;
	private WebDriverWait wdw;
	private long starttime;	
	private String ed;
	private String unidade;
	private String acao;

	public Acoes(WebDriver driver, WebDriverWait wdw, Signal horizon, long starttime, String ed, String unidade) {
		this.driver = driver;
		this.horizon = horizon;
		this.wdw = wdw; 				
		this.starttime = starttime;
		this.ed = ed;
		this.unidade = unidade;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/so/segurancamaquinas/pesquisamaquina.html");

		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-area1")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ unidade +"')]")).click();	

		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-pesquisar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'MaquinaTeste')]")).click();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-visualizar']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-visualizar']")).click();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a [@onclick=\"onItemMenuClick('acoes')\"]"))));
		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@onclick=\"onItemMenuClick('acoes')\"]")).click();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ta-acao"))));
		this.horizon.waitLoad2();

		acao = this.horizon.generateString(400);

		driver.findElement(By.id("ta-acao")).sendKeys(acao);
		driver.findElement(By.id("ac-responsavel")).sendKeys("Epy");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'Master Epy')]")).click();

		driver.findElement(By.id("i-data-criacao")).sendKeys("01022019");
		driver.findElement(By.id("i-data-prazo")).sendKeys("31122030");
		driver.findElement(By.id("i-custo-estimado")).sendKeys(String.format("%09d", ThreadLocalRandom.current().nextInt(1000000000)));

		driver.findElement(By.id("bt-adicionar-acao")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-salvar")).click();

		this.horizon.waitLoad2();

		System.out.println("Ação " + acao + "			" + horizon.time(starttime));	
		return acao;
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a [@onclick=\"onItemMenuClick('acoes')\"]"))));
		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@onclick=\"onItemMenuClick('acoes')\"]")).click();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ta-acao"))));
		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@data-tooltip='Excluir']")).click();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='btnSimExcluirAcao()']"))));
		driver.findElement(By.xpath("//button [@ng-click='btnSimExcluirAcao()']")).click();		

		this.horizon.waitLoad2();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
			wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));
		}
		catch(Exception e){			
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		acao = this.horizon.generateString(200);

		driver.findElement(By.id("ta-acao")).sendKeys(acao);
		driver.findElement(By.id("ac-responsavel")).sendKeys("Epy");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'Master Epy')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("i-data-criacao")).sendKeys("01022019");
		driver.findElement(By.id("i-data-prazo")).sendKeys("31122030");
		driver.findElement(By.id("i-custo-estimado")).sendKeys(String.format("%09d", ThreadLocalRandom.current().nextInt(1000000000)));

		driver.findElement(By.id("bt-adicionar-acao")).click();

		this.horizon.waitLoad2();



		driver.findElement(By.xpath("//a [@data-tooltip='Editar']")).click();

		driver.findElement(By.id("ta-acao")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.id("bt-salvar-acao")).click();

		driver.findElement(By.id("bt-salvar")).click();

		this.horizon.waitLoad2();

		System.out.println("Ação+ " + acao + "			" + horizon.time(starttime));	
	}

	public void excluir() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a [@onclick=\"onItemMenuClick('acoes')\"]"))));
		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-menu")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@href='gestaoacao.html']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-area1-filtro")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-pesquisar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ acao +"')]")).click();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']")).click();
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='btnSimExcluir()']"))));
		driver.findElement(By.xpath("//button [@ng-click='btnSimExcluir()']")).click();
	}
}