package shiningDarkness;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Processo {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private long starttime;
	private String ed;
	private String processo;

	public Processo(WebDriver driver, WebDriverWait wdw, Signal horizon,long starttime, String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.starttime = starttime;
		this.ed = ed;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/menu_cadastro.html");

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@href='processo.html']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-adicionar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao"))));

		processo =this.horizon.generateString(20);
		int cod = ThreadLocalRandom.current().nextInt(100);

		driver.findElement(By.id("i-descricao")).sendKeys(processo);
		driver.findElement(By.id("i-codigo")).sendKeys(String.format("%02d",cod));	

		driver.findElement(By.id("bt-salvar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();

		System.out.println("Processo " + processo + "						" + horizon.time(starttime));
		return processo;
	}

	public void editar() {
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-principal tooltip-pq tooltip-left']"))));

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(processo);

		driver.findElement(By.xpath("//button [@class='btn btn-principal tooltip-pq tooltip-left']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ processo +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-editar']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-editar']")).click();

		this.horizon.waitLoad2();
		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='bg-modal-fake fade in']"))));

		processo =this.horizon.generateString(20);
		int cod = ThreadLocalRandom.current().nextInt(100);

		this.horizon.escolherStatus();
		driver.findElement(By.id("i-descricao")).sendKeys(processo);
		driver.findElement(By.id("i-codigo")).sendKeys(String.format("%02d",cod));	

		driver.findElement(By.id("bt-salvar")).click();	

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();

		System.out.println("Processo+ " + processo + "						" + horizon.time(starttime));	
	}

	public void excluir() {
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-principal tooltip-pq tooltip-left']"))));

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(processo);

		driver.findElement(By.xpath("//button [@class='btn btn-principal tooltip-pq tooltip-left']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ processo +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']")).click();
	}
}