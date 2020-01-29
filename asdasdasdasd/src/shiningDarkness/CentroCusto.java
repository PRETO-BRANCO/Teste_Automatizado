package shiningDarkness;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CentroCusto {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String centroC;

	public CentroCusto(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime,String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.starttime = starttime;
		this.ed = ed;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/menu_cadastro.html");

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@href='centro_custo.html']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-adicionar']")).click();

		this.horizon.waitLoad2();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao"))));

		centroC =this.horizon.generateString(20);
		long cod = ThreadLocalRandom.current().nextLong(10000000000l);

		driver.findElement(By.id("i-descricao")).sendKeys(centroC);
		driver.findElement(By.id("i-codigo")).sendKeys(String.format("%010d",cod));	

		driver.findElement(By.id("bt-salvar")).click();	

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]"))));
		this.driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]")).click();

		this.horizon.waitLoad2();

		System.out.println("CentroCusto " + centroC + "					" + horizon.time(starttime));
		return centroC;
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-principal tooltip-pq tooltip-left']"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(centroC);

		driver.findElement(By.xpath("//button [@class='btn btn-principal tooltip-pq tooltip-left']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'" + centroC +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-editar']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-editar']")).click();

		this.horizon.waitLoad2();
		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='bg-modal-fake fade in']"))));

		centroC =this.horizon.generateString(20);
		long cod = ThreadLocalRandom.current().nextLong(10000000000l);		

		this.horizon.escolherStatus();
		driver.findElement(By.id("i-descricao")).sendKeys(centroC);
		driver.findElement(By.id("i-codigo")).sendKeys(String.format("%010d",cod));	

		driver.findElement(By.id("bt-salvar")).click();	

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]"))));
		this.driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]")).click();

		this.horizon.waitLoad2();

		System.out.println("CentroCusto+ " + centroC + "					" + horizon.time(starttime));
	}

	public void excluir() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-principal tooltip-pq tooltip-left']"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(centroC);

		driver.findElement(By.xpath("//button [@class='btn btn-principal tooltip-pq tooltip-left']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'" + centroC +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']")).click();
	}
}