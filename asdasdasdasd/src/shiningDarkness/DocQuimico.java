package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DocQuimico {
	private WebDriver driver;
	private WebDriverWait wdw;
	private Signal horizon;
	private String ed;
	private String unidade;
	private String produto;
	private long starttime;
	private Idioma idioma;
	private String id;
	private Fornecedor fornecedor;
	private String forn;

	public DocQuimico(WebDriver driver, WebDriverWait wdw, Signal horizon, Long starttime, String ed, String unidade) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.ed = ed;
		this.unidade = unidade;
		this.starttime = starttime;
		this.idioma = new Idioma(driver, wdw, horizon, starttime, ed, unidade);
		this.id = idioma.criar();
		this.fornecedor = new Fornecedor(driver, wdw, horizon, starttime, ed, unidade);
		this.forn = fornecedor.criar();
	}

	public void criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/documentacao/segquimica/pesquisa_docs_segquimica.html");

		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-gerenciar")).click();		

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='adicionarDSQ()']"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@ng-click='adicionarDSQ()']")).click();		

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-produto"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();

		driver.findElement(By.xpath("//label [contains(text(),'"+ unidade +"')]")).click();

		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();

		driver.findElement(By.id("ac-produto")).sendKeys("Deceno");
		driver.findElement(By.xpath("//li [contains(text(),'DECENO') and contains(@class,'ui-menu-item')]")).click();

		Select slek = new Select(driver.findElement(By.id("cb-documento")));
		slek.selectByIndex(1);

		slek = new Select(driver.findElement(By.id("cb-tipo")));
		List<WebElement> lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));

		if(driver.findElement(By.id("ac-idioma")).isDisplayed()) {
			driver.findElement(By.id("ac-idioma")).sendKeys(id);
			driver.findElement(By.xpath("//li [contains(text(),'"+ id +"') and contains(@class,'ui-menu-item')]")).click();
		}
		else if(driver.findElement(By.id("ac-fornecedor")).isDisplayed()) {
			driver.findElement(By.id("ac-fornecedor")).sendKeys(forn);
			driver.findElement(By.xpath("//li [contains(text(),'"+ forn +"') and contains(@class,'ui-menu-item')]")).click();
		}

		driver.findElement(By.id("i-validade")).sendKeys("30122030");

		driver.findElement(By.id("ac-area-1-copia")).sendKeys(unidade);
		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"') and contains(@class,'ui-menu-item')]")).click();
		driver.findElement(By.id("ac-responsavel")).sendKeys("Master Epy");
		driver.findElement(By.xpath("//li [contains(text(),'Master Epy') and contains(@class,'ui-menu-item')]")).click();
		driver.findElement(By.id("i-localizacao")).sendKeys(this.horizon.generateString(40));

		driver.findElement(By.xpath("//button [@data-tooltip='Adicionar']")).click();
		
		System.out.println(" Doc Produto " + produto + " " + horizon.time(starttime));
	}
}
