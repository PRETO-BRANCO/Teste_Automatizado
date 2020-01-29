package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Estoque {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String unidade;
	private String estoque;

	public Estoque(WebDriver driver, WebDriverWait wdw,Signal horizon,Long starttime,String ed,String unidade) {		
		this.driver = driver;
		this.wdw = wdw;		
		this.horizon = horizon;
		this.starttime = starttime;
		this.ed = ed;
		this.unidade = unidade;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/ma/residuos/principal/");

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//a [@onclick=\"onClickItemMenu('C')\"]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("lb-cadastro-estoque")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary pull-right bt-novo']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo-cadastro"))));

		estoque = this.horizon.generateString(20);

		driver.findElement(By.id("i-codigo-cadastro")).sendKeys(String.format("%04d", ThreadLocalRandom.current().nextInt(10000)));
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();
		driver.findElement(By.xpath("//label [contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();
		driver.findElement(By.id("i-descricao-cadastro")).sendKeys(estoque);

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSalvar()']")).click();		
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default margin-left btn-nao']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default margin-left btn-nao']")).click();

		this.horizon.waitLoad();

		System.out.println("Estoque " + estoque + "						" + horizon.time(starttime));	
		return estoque;
	}

	public void editar() {
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));

		driver.findElement(By.id("ac-area1-filtro")).sendKeys(unidade);
		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"') and contains(@class,'ui-menu-item')]")).click();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(estoque);

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'"+ estoque +"')]")).click();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary pull-right bt-visualizar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo-cadastro"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		estoque = this.horizon.generateString(20);

		this.horizon.escolherStatus();
		driver.findElement(By.id("i-codigo-cadastro")).sendKeys(String.format("%04d", ThreadLocalRandom.current().nextInt(10000)));
		driver.findElement(By.id("i-descricao-cadastro")).sendKeys(estoque);
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();
		List<WebElement> lista = driver.findElements(By.xpath("//input [@type='checkbox' and not(@disabled='')]"));		
		for(int i = 0;i<lista.size();i++) {
			lista.get(i).click();
		}
		driver.findElement(By.xpath("//label [contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSalvar()']")).click();		
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default margin-left btn-nao']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default margin-left btn-nao']")).click();

		this.horizon.waitLoad();

		System.out.println("Estoque+ " + estoque + "						" + horizon.time(starttime));	
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/ma/residuos/principal/");

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//a [@onclick=\"onClickItemMenu('C')\"]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("lb-cadastro-estoque")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));
		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area1-filtro")).sendKeys(unidade);
		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"') and contains(@class,'ui-menu-item')]")).click();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(estoque);

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'"+ estoque +"')]")).click();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary lb-sim margin-left']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary lb-sim margin-left']")).click();
	}
}