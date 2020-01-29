package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Perigos {
	private WebDriver driver;
	private Signal horizon;
	private WebDriverWait wdw;
	private long starttime;
	private String ed;
	private String perigo;
	private String fonte;
	private String dano;

	public Perigos(WebDriver driver, WebDriverWait wdw, Signal horizon, long starttime, String ed) {
		this.driver = driver;
		this.horizon = horizon;
		this.wdw = wdw; 		
		this.starttime = starttime;
		this.ed = ed;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/so/segurancamaquinas/menuprincipal.html");

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@onclick=\"onItemMenuClick('perigos')\"]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-adicionar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.waitLoad2();

		String cod = String.format("%04d",ThreadLocalRandom.current().nextInt(10000));
		perigo =this.horizon.generateString(20);
		fonte = this.horizon.generateString(100);
		dano = this.horizon.generateString(100);

		driver.findElement(By.id("i-codigo")).sendKeys(cod);
		driver.findElement(By.id("i-descricao")).sendKeys(perigo);
		driver.findElement(By.id("i-descricao-fonte")).sendKeys(fonte);
		driver.findElement(By.id("bt-adicionar-item")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("lb-danos")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-dano"))));
		this.horizon.waitLoad2();

		driver.findElement(By.id("i-descricao-dano")).sendKeys(dano);
		driver.findElement(By.id("bt-adicionar-dano")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-salvar")).click();
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]"))));
		this.driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]")).click();

		this.horizon.waitLoad2();

		System.out.println("Perigo " + perigo + "						" + horizon.time(starttime));
		return perigo;
	}

	public void editar() {
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-pesquisar"))));

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(perigo);

		driver.findElement(By.id("bt-pesquisar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ perigo +"')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='btnVisualizarClick()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		String cod =String.format("%04d",ThreadLocalRandom.current().nextInt(10000));
		perigo =this.horizon.generateString(20);		

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		this.horizon.escolherStatus();
		driver.findElement(By.id("i-codigo")).sendKeys(cod);
		driver.findElement(By.id("i-descricao")).sendKeys(perigo);

		driver.findElement(By.id("bt-excluir-fonte")).click();
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='btnSimExcluirFonte()']"))));
		this.driver.findElement(By.xpath("//button [@ng-click='btnSimExcluirFonte()']")).click();

		this.horizon.waitLoad2();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
			wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));
		}
		catch(Exception e){			
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("i-descricao-fonte")).sendKeys(this.horizon.generateString(50));
		driver.findElement(By.id("bt-adicionar-item")).click();

		driver.findElement(By.id("bt-editar-fonte")).click();

		WebElement tog = driver.findElement(By.id("ddn-status-item-fonte"));		
		tog.click();
		List<WebElement> lista = driver.findElements(By.xpath("//li [contains(@class,'item-fonte item-status-option') and not(ancestor::div[contains(@style,'display:none')]) and not(ancestor::div[contains(@style,'display: none')])]"));	
		lista.get(ThreadLocalRandom.current().nextInt(2)).click();
		driver.findElement(By.id("i-descricao-fonte")).sendKeys(this.horizon.generateString(50));
		driver.findElement(By.id("bt-salvar-item")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("lb-danos")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-dano"))));
		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-excluir-dano")).click();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='btnSimExcluirDano()']"))));
		this.driver.findElement(By.xpath("//button [@ng-click='btnSimExcluirDano()']")).click();

		this.horizon.waitLoad2();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
			wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));
		}
		catch(Exception e){			
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("i-descricao-dano")).sendKeys(this.horizon.generateString(50));
		driver.findElement(By.id("bt-adicionar-dano")).click();

		driver.findElement(By.id("bt-editar-dano")).click();

		tog = driver.findElement(By.id("ddn-status-item-dano"));		
		tog.click();
		lista = driver.findElements(By.xpath("//li [contains(@class,'item-dano item-status-option') and not(ancestor::div[contains(@style,'display:none')]) and not(ancestor::div[contains(@style,'display: none')])]"));	
		lista.get(ThreadLocalRandom.current().nextInt(2)).click();
		driver.findElement(By.id("i-descricao-dano")).sendKeys(this.horizon.generateString(50));
		driver.findElement(By.id("bt-salvar-dano")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-salvar")).click();
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]"))));
		this.driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]")).click();

		this.horizon.waitLoad2();

		System.out.println("Perigo+ " + perigo + "						" + horizon.time(starttime));	
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/so/segurancamaquinas/perigos.html");

		this.horizon.waitLoad2();		
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-pesquisar"))));

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(perigo);

		driver.findElement(By.id("bt-pesquisar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ perigo +"')]")).click();

		this.driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default pull-right bt-excluir\"]")).click();
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='btnSimExcluir()']"))));
		this.driver.findElement(By.xpath("//button [@ng-click='btnSimExcluir()']")).click();
	}

	public String getFonte() {
		return fonte;
	}

	public String getDano() {
		return dano;
	}
}