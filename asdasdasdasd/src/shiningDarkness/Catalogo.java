package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Catalogo {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String catalogo;

	public Catalogo(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime,String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.starttime = starttime;
		this.ed = ed;
	}

	public void criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/menu_cadastro.html");

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@href='catalogo.html']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("descricao"))));

		catalogo =this.horizon.generateString(20);
		int cod = ThreadLocalRandom.current().nextInt(10000);
		driver.findElement(By.id("descricao")).sendKeys(catalogo);
		driver.findElement(By.id("codigo")).sendKeys(String.format("%04d",cod));		
		WebElement tog = driver.findElement(By.id("ddn-status-sub-cadastro"));		
		tog.click();
		List<WebElement> lista = driver.findElements(By.xpath("//li [contains(@class,'sub-cadastro item-status-option') and not(ancestor::div[contains(@style,'display:none')]) and not(ancestor::div[contains(@style,'display: none')])]"));
		lista.get(ThreadLocalRandom.current().nextInt(2)).click();
		driver.findElement(By.id("descricaoVC")).sendKeys(this.horizon.generateString(40));
		driver.findElement(By.id("codigoVC")).sendKeys(String.format("%08d",ThreadLocalRandom.current().nextInt(100000000)));
		driver.findElement(By.id("bt-novo")).click();

		driver.findElement(By.id("bt-salvar")).click();	

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		this.driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();

		System.out.println("Catalogo " + catalogo + "						" + horizon.time(starttime));
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));

		driver.findElement(By.id("iDescricao")).sendKeys(catalogo);

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'" + catalogo +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-editar']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-editar']")).click();

		this.horizon.waitLoad2();
		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='bg-modal-fake fade in']"))));

		catalogo =this.horizon.generateString(20);
		int cod = ThreadLocalRandom.current().nextInt(10000);

		driver.findElement(By.id("ddn-status-cadastro")).click();
		List<WebElement> lista = driver.findElements(By.xpath("//li [contains(@class,'cadastro item-status-option') and not(contains(@class,'sub-')) and not(ancestor::div[contains(@style,'display:none')]) and not(ancestor::div[contains(@style,'display: none')])]"));
		lista.get(ThreadLocalRandom.current().nextInt(2)).click();
		driver.findElement(By.id("descricao")).sendKeys(catalogo);
		driver.findElement(By.id("codigo")).sendKeys(String.format("%04d",cod));
		WebElement tog = driver.findElement(By.id("ddn-status-sub-cadastro"));	
		tog.click();
		lista = driver.findElements(By.xpath("//li [contains(@class,'sub-cadastro item-status-option') and not(ancestor::div[contains(@style,'display:none')]) and not(ancestor::div[contains(@style,'display: none')])]"));
		lista.get(ThreadLocalRandom.current().nextInt(2)).click();
		driver.findElement(By.id("descricaoVC")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.id("codigoVC")).sendKeys(String.format("%04d",ThreadLocalRandom.current().nextInt(10000)));

		driver.findElement(By.id("bt-novo")).click();

		driver.findElement(By.xpath("//a [@data-tooltip='Excluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal margin-left btn-sim']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal margin-left btn-sim']")).click();

		this.horizon.waitLoad2();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
			wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));
		}
		catch(Exception e){			
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//a [@data-tooltip='Editar']")).click();

		tog = driver.findElement(By.id("ddn-status-sub-cadastro"));	
		tog.click();
		lista = driver.findElements(By.xpath("//li [contains(@class,'sub-cadastro item-status-option') and not(ancestor::div[contains(@style,'display:none')]) and not(ancestor::div[contains(@style,'display: none')])]"));
		lista.get(ThreadLocalRandom.current().nextInt(2)).click();
		driver.findElement(By.id("descricaoVC")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.id("codigoVC")).sendKeys(String.format("%04d",ThreadLocalRandom.current().nextInt(10000)));

		driver.findElement(By.id("lb_salvar_edicao")).click();		

		driver.findElement(By.id("bt-salvar")).click();	

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		this.driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();

		System.out.println("Catalogo+ " + catalogo + "						" + horizon.time(starttime));
	}

	public void excluir() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		driver.findElement(By.id("iDescricao")).sendKeys(catalogo);

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'" + catalogo +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-show='controleTela.podeExcluir']"))));
		driver.findElement(By.xpath("//button [@ng-show='controleTela.podeExcluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']")).click();
	}
}