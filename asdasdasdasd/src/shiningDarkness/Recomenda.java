package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Recomenda {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;	
	private Long starttime;
	private String ed;
	private String rec;

	public Recomenda(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime, String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.starttime = starttime;
		this.ed = ed;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/menu_cadastro.html");

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@href='recomendacao.html']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default pull-right bt-adicionar\"]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("descricao"))));

		rec = this.horizon.generateString(50);

		driver.findElement(By.id("descricao")).sendKeys(rec);
		driver.findElement(By.xpath("//input [@ng-model='recomendacao.catQuimico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='recomendacao.catFisico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='recomendacao.catBiologico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='recomendacao.catErgonomico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='recomendacao.catMecanico']")).click();

		driver.findElement(By.id("bt-salvar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]")).click();

		this.horizon.waitLoad2();

		System.out.println("Recomendações " + rec + "	" + horizon.time(starttime));
		return rec;
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));

		driver.findElement(By.id("iDescricao")).sendKeys(rec);
		Select slek = new Select(driver.findElement(By.id("cb-categoria-filtro")));
		List<WebElement> lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(lista.size()));

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ rec +"')]")).click();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-editar']")).click();

		this.horizon.waitLoad2();
		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='bg-modal-fake fade in']"))));
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("descricao"))));

		rec = this.horizon.generateString(50);

		this.horizon.escolherStatus();
		driver.findElement(By.id("descricao")).sendKeys(rec);
		driver.findElement(By.xpath("//input [@ng-model='recomendacao.catFisico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='recomendacao.catBiologico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='recomendacao.catErgonomico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='recomendacao.catMecanico']")).click();

		driver.findElement(By.id("bt-salvar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]")).click();

		this.horizon.waitLoad2();

		System.out.println("Recomendações+ " + rec + "	" + horizon.time(starttime));	
	}

	public void excluir() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));

		driver.findElement(By.id("iDescricao")).sendKeys(rec);
		Select slek = new Select(driver.findElement(By.id("cb-categoria-filtro")));		
		slek.selectByValue("Q");

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ rec +"')]")).click();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']")).click();
	}
}