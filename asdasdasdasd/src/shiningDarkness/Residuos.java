package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Residuos {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String unidade;
	private String residuo;
	private Destino destino;
	private String dest;
	private Caracteristicas caracteristicas;
	private String car;

	public Residuos(WebDriver driver, WebDriverWait wdw,Signal horizon,Long starttime,String ed,String unidade) {		
		this.driver = driver;
		this.wdw = wdw;		
		this.horizon = horizon;
		this.starttime = starttime;
		this.ed = ed;
		this.unidade = unidade;
		this.destino = new Destino(driver, wdw, horizon, starttime, ed, unidade);
		this.dest = this.destino.criar();
		this.caracteristicas = new Caracteristicas(driver, wdw, horizon, starttime, ed, unidade);
		this.car = this.caracteristicas.criar();
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/ma/residuos/principal/");

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//a [@onclick=\"onClickItemMenu('C')\"]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("lb-cadastro-residuos")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary pull-right bt-novo']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo-cadastro"))));

		residuo = this.horizon.generateString(150);

		driver.findElement(By.id("i-codigo-cadastro")).sendKeys(String.format("%04d", ThreadLocalRandom.current().nextInt(10000)));
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();
		driver.findElement(By.xpath("//label [contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();
		Select slek = new Select(driver.findElement(By.id("cb-classe")));	//*[@id="cb-primeira-validade"]	
		slek.selectByIndex(1);	
		slek = new Select(driver.findElement(By.id("cb-unidade-medida")));
		List<WebElement> lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));	
		driver.findElement(By.id("chk-unidade-secundaria")).click();
		slek = new Select(driver.findElement(By.id("cb-unidade-secundaria")));
		lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		driver.findElement(By.id("i-residuo")).sendKeys(residuo);
		driver.findElement(By.id("i-codigo-ibama")).sendKeys(String.format("%03d", ThreadLocalRandom.current().nextInt(1000)));
		driver.findElement(By.id("i-custo")).sendKeys(String.format("%09d", ThreadLocalRandom.current().nextInt(1000000000)));
		slek = new Select(driver.findElement(By.id("cb-tipo-destino")));
		slek.selectByVisibleText(dest);
		slek = new Select(driver.findElement(By.id("cb-caracteristica")));
		slek.selectByVisibleText(car);		

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSalvar()']")).click();		
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default margin-left btn-nao']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default margin-left btn-nao']")).click();

		this.horizon.waitLoad();

		System.out.println("Residuo " + residuo + "						" + horizon.time(starttime));	
		return residuo;
	}

	public void editar() {
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));

		driver.findElement(By.id("ac-area1-filtro")).sendKeys(unidade);
		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"') and contains(@class,'ui-menu-item')]")).click();

		driver.findElement(By.id("i-residuo-filtro")).sendKeys(residuo);

		Select slek = new Select(driver.findElement(By.id("cb-classe-filtro")));
		slek.selectByIndex(1);
		slek = new Select(driver.findElement(By.id("cb-tipodestino-filtro")));
		slek.selectBy 	(dest);

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'"+ residuo +"')]")).click();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary pull-right bt-visualizar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo-cadastro"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		residuo = this.horizon.generateString(150);

		this.horizon.escolherStatus();
		driver.findElement(By.id("i-codigo-cadastro")).sendKeys(String.format("%04d", ThreadLocalRandom.current().nextInt(10000)));
		slek = new Select(driver.findElement(By.id("cb-classe")));		
		slek.selectByIndex(2);
		slek = new Select(driver.findElement(By.id("cb-unidade-medida")));
		List<WebElement> lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		driver.findElement(By.id("chk-unidade-secundaria")).click();
		driver.findElement(By.id("i-residuo")).sendKeys(residuo);
		driver.findElement(By.id("i-codigo-ibama")).sendKeys(String.format("%03d", ThreadLocalRandom.current().nextInt(1000)));
		driver.findElement(By.id("chk-desconsiderar-indicador")).click();

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSalvar()']")).click();		
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default margin-left btn-nao']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default margin-left btn-nao']")).click();

		this.horizon.waitLoad();

		System.out.println("Residuo+ " + residuo + "						" + horizon.time(starttime));
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/ma/residuos/principal/");

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//a [@onclick=\"onClickItemMenu('C')\"]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("lb-cadastro-residuos")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));
		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area1-filtro")).sendKeys(unidade);
		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"') and contains(@class,'ui-menu-item')]")).click();

		driver.findElement(By.id("i-residuo-filtro")).sendKeys(residuo);

		Select slek = new Select(driver.findElement(By.id("cb-classe-filtro")));
		slek.selectByIndex(2);
		slek = new Select(driver.findElement(By.id("cb-tipodestino-filtro")));
		slek.selectByVisibleText(dest);

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'"+ residuo +"')]")).click();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default lb-sim margin-left']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default lb-sim margin-left']")).click();

		this.horizon.waitLoad();

		destino.excluir();

		this.horizon.waitLoad();

		caracteristicas.excluir();
	}

	public String getDestino() {
		return dest;
	}
}