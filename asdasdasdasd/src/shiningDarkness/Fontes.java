package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Fontes {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private long starttime;
	private String ed;
	private String fonte;

	public Fontes(WebDriver driver, WebDriverWait wdw, Signal horizon, long starttime,String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.starttime = starttime;
		this.ed = ed;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/menu_cadastro.html");

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@href='fonte_geradora.html']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("descricao"))));

		fonte =this.horizon.generateString(50);

		driver.findElement(By.id("descricao")).sendKeys(fonte);
		driver.findElement(By.xpath("//input [@ng-model='fonteGeradora.catQuimico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='fonteGeradora.catFisico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='fonteGeradora.catBiologico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='fonteGeradora.catErgonomico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='fonteGeradora.catMecanico']")).click();

		driver.findElement(By.id("bt-salvar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();

		System.out.println("Fontes " + fonte + "		" + horizon.time(starttime));
		return fonte;
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));

		driver.findElement(By.id("iDescricao")).sendKeys(fonte);
		Select slek = new Select(driver.findElement(By.id("cb-categoria-filtro")));
		List<WebElement> lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(lista.size()));

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'" + fonte +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-editar']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-editar']")).click();

		this.horizon.waitLoad2();
		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='bg-modal-fake fade in']"))));

		fonte = this.horizon.generateString(50);

		this.horizon.escolherStatus();
		driver.findElement(By.id("descricao")).sendKeys(fonte);
		driver.findElement(By.xpath("//input [@ng-model='fonteGeradora.catQuimico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='fonteGeradora.catFisico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='fonteGeradora.catBiologico']")).click();		
		driver.findElement(By.xpath("//input [@ng-model='fonteGeradora.catMecanico']")).click();

		driver.findElement(By.id("bt-salvar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();

		System.out.println("Fontes+ " + fonte + "		" + horizon.time(starttime));	
	}

	public void excluir() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));

		driver.findElement(By.id("iDescricao")).sendKeys(fonte);
		Select slek = new Select(driver.findElement(By.id("cb-categoria-filtro")));		
		slek.selectByValue("EP");

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'" + fonte +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-show='controleTela.podeExcluir']"))));
		driver.findElement(By.xpath("//button [@ng-show='controleTela.podeExcluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']")).click();
	}
}