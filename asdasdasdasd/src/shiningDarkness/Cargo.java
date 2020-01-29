package shiningDarkness;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cargo {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String cargo;

	public Cargo(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime, String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.starttime = starttime;
		this.ed = ed;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/menu_cadastro.html");

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@href='cargo.html']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-adicionar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("descricao"))));

		cargo =this.horizon.generateString(50);

		driver.findElement(By.id("descricao")).sendKeys(cargo);
		driver.findElement(By.xpath("//input [@data-ng-model='funcao.potencialmentePericulosa']")).click();
		Select slek = new Select(driver.findElement(By.id("cb-referencia")));
		slek.selectByValue(String.valueOf(ThreadLocalRandom.current().nextInt(1,4)));
		driver.findElement(By.xpath("//textarea [@data-ng-model='funcao.atividades']")).sendKeys(this.horizon.generateString(200));

		driver.findElement(By.id("bt-salvar")).click();	

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]"))));
		this.driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]")).click();

		this.horizon.waitLoad2();

		System.out.println("Cargo " + cargo + "		" + horizon.time(starttime));
		return cargo;
	}

	public void editar() {		
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));		
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		driver.findElement(By.id("iDescricao")).sendKeys(cargo);
		driver.findElement(By.id("iPericulosa")).click();

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'" + cargo +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-editar']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-editar']")).click();

		this.horizon.waitLoad2();
		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='bg-modal-fake fade in']"))));

		cargo =this.horizon.generateString(50);

		this.horizon.escolherStatus();
		driver.findElement(By.id("descricao")).sendKeys(cargo);
		driver.findElement(By.xpath("//input [@data-ng-model='funcao.potencialmentePericulosa']")).click();		
		driver.findElement(By.xpath("//textarea [@data-ng-model='funcao.atividades']")).sendKeys(this.horizon.generateString(200));

		driver.findElement(By.id("bt-salvar")).click();	

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]"))));
		this.driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]")).click();

		this.horizon.waitLoad2();

		System.out.println("Cargo+ " + cargo + "		" + horizon.time(starttime));	
	}

	public void excluir() {			
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));		
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		driver.findElement(By.id("iDescricao")).sendKeys(cargo);
		driver.findElement(By.id("iPericulosa")).click();

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'" + cargo +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']")).click();
	}
}