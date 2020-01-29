package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EPIs {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String epi;

	public EPIs(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime, String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.starttime = starttime;
		this.ed = ed;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/menu_cadastro.html");

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@href='equip_geral.html']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("descricao"))));

		epi =this.horizon.generateString(50);		

		driver.findElement(By.id("descricao")).sendKeys(epi);		
		driver.findElement(By.id("i-ca")).sendKeys(String.format("%08d",ThreadLocalRandom.current().nextInt(100000000)));
		driver.findElement(By.id("descricao")).click();		
		driver.findElement(By.id("i-validade-ca")).sendKeys("31122030");
		Select slek = new Select(driver.findElement(By.id("cb-categoria")));
		List<WebElement> lista = slek.getOptions();	
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		slek = new Select(driver.findElement(By.id("cb-classe")));
		lista = slek.getOptions();	
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));

		driver.findElement(By.id("bt-salvar")).click();	

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();

		System.out.println("EPIs " + epi + "			" + horizon.time(starttime));
		return epi;
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(	driver.findElement(By.xpath("//button [@class='btn btn-principal tooltip-pq tooltip-left']"))));

		driver.findElement(By.id("iDescricao")).sendKeys(epi);

		driver.findElement(By.xpath("//button [@class='btn btn-principal tooltip-pq tooltip-left']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'" + epi +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-editar']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-editar']")).click();

		this.horizon.waitLoad2();
		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='bg-modal-fake fade in']"))));

		epi =this.horizon.generateString(50);		

		this.horizon.escolherStatus();
		driver.findElement(By.id("descricao")).sendKeys(epi);				
		Select slek = new Select(driver.findElement(By.id("cb-categoria")));
		List<WebElement> lista = slek.getOptions();	
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		slek = new Select(driver.findElement(By.id("cb-classe")));
		lista = slek.getOptions();	
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));

		driver.findElement(By.id("bt-salvar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();

		System.out.println("EPIs+ " + epi + "		" + horizon.time(starttime));		
	}

	public void excluir() {
		this.wdw.until(ExpectedConditions.visibilityOf(	driver.findElement(By.xpath("//button [@class='btn btn-principal tooltip-pq tooltip-left']"))));

		driver.findElement(By.id("iDescricao")).sendKeys(epi);

		driver.findElement(By.xpath("//button [@class='btn btn-principal tooltip-pq tooltip-left']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'" + epi +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='excluirEquipamento()']"))));
		driver.findElement(By.xpath("//button [@ng-click='excluirEquipamento()']")).click();	

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']")).click();
	}
}