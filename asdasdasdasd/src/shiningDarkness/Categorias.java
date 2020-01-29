package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Categorias {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private Actions act;
	private String cat;

	public Categorias(WebDriver driver, WebDriverWait wdw,Signal horizon,Long starttime,String ed) {		
		this.driver = driver;
		this.wdw = wdw;		
		this.horizon = horizon;
		this.starttime = starttime;
		this.ed = ed;
		this.act = new Actions(driver);
	}

	public String criar() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/analise/#/");	

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//a [@ui-sref='categorias']")).click();

		this.horizon.waitLoad();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='onClickBtnAdicionar()']"))));

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnAdicionar()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao"))));

		cat = this.horizon.generateString(40);			

		driver.findElement(By.id("i-codigo")).sendKeys("0" + String.format("%02d", ThreadLocalRandom.current().nextInt(100)));
		driver.findElement(By.id("i-descricao")).sendKeys(cat);

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary pull-right lb-salvar']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));		
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad();

		System.out.println("Categoria " + cat + "			" + horizon.time(starttime));
		return cat;
	}

	public void editar() {
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-pesquisar"))));

		driver.findElement(By.id("bt-pesquisar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'" + cat +"')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnVisualizar()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		cat = this.horizon.generateString(40);			

		WebElement tog = driver.findElement(By.id("ddn-status-cadastro"));	
		List<WebElement> lista = driver.findElements(By.xpath("//li [contains(@class,'item-status-option')]"));
		tog.click();
		act.moveToElement(lista.get(ThreadLocalRandom.current().nextInt(lista.size()-2,lista.size()))).click().perform();
		driver.findElement(By.id("i-codigo")).sendKeys(String.format("%03d", ThreadLocalRandom.current().nextInt(1000)));
		driver.findElement(By.id("i-descricao")).sendKeys(cat);

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary pull-right lb-salvar']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));		
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad();

		System.out.println("Categoria+ " + cat + "			" + horizon.time(starttime));
	}

	public void excluir() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/analise/");	

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//a [@ui-sref='categorias']")).click();

		this.horizon.waitLoad();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-pesquisar"))));

		driver.findElement(By.id("bt-pesquisar")).click();

		this.horizon.waitLoad();
		this.horizon.sleep(350);

		List<WebElement> lista = driver.findElements(By.xpath("//tr [contains(@class,'ng-scope')]"));
		act.moveToElement(lista.get(0)).click().perform();

		this.driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']")).click();
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@data-ng-click='onClickBtnSimExcluir()']"))));
		this.driver.findElement(By.xpath("//button [@data-ng-click='onClickBtnSimExcluir()']")).click();
	}
}