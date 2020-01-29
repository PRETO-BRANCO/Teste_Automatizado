package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tecnica {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String tec;

	public Tecnica(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime, String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.starttime = starttime;
		this.ed = ed;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/menu_cadastro.html");

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@href='tecnica_medicao.html']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default pull-right bt-adicionar\"]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("descricao"))));

		tec = this.horizon.generateString(15);		

		driver.findElement(By.id("descricao")).sendKeys(tec);
		driver.findElement(By.id("ck-1")).click();
		driver.findElement(By.id("ck-2")).click();
		driver.findElement(By.id("ck-3")).click();
		driver.findElement(By.id("ck-4")).click();
		driver.findElement(By.id("ck-5")).click();

		driver.findElement(By.id("bt-salvar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]")).click();		

		this.horizon.waitLoad2();

		System.out.println("Tecnica " + tec + "							" + horizon.time(starttime));
		return tec;
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));

		driver.findElement(By.id("iDescricao")).sendKeys(tec);
		Select slek = new Select(driver.findElement(By.id("cb-categoria-filtro")));
		List<WebElement> lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(lista.size()));

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ tec +"')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='editarTecnicaMedicao()']")).click();

		this.horizon.waitLoad2();
		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='bg-modal-fake fade in']"))));

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("descricao"))));

		tec =this.horizon.generateString(15);		

		this.horizon.escolherStatus();
		driver.findElement(By.id("descricao")).sendKeys(tec);
		driver.findElement(By.id("ck-2")).click();
		driver.findElement(By.id("ck-3")).click();
		driver.findElement(By.id("ck-4")).click();
		driver.findElement(By.id("ck-5")).click();

		driver.findElement(By.id("bt-salvar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]")).click();

		this.horizon.waitLoad2();

		System.out.println("Tecnica+ " + tec + "						" + horizon.time(starttime));	
	}

	public void excluir() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));

		driver.findElement(By.id("iDescricao")).sendKeys(tec);
		Select slek = new Select(driver.findElement(By.id("cb-categoria-filtro")));		
		slek.selectByValue("F");

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ tec +"')]")).click();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']")).click();
	}
}