package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Topicos {
	private WebDriver driver;
	private Signal horizon;
	private WebDriverWait wdw;
	private long starttime;
	private String ed;
	private String topico;

	public Topicos(WebDriver driver, WebDriverWait wdw, Signal horizon, long starttime, String ed) {
		this.driver = driver;
		this.horizon = horizon;
		this.wdw = wdw; 		
		this.starttime = starttime;
		this.ed = ed;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/so/segurancamaquinas/menuprincipal.html");

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@onclick=\"onItemMenuClick('topicos')\"]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-adicionar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.waitLoad2();

		String cod = String.format("%04d",ThreadLocalRandom.current().nextInt(10000));
		topico =this.horizon.generateString(30);	

		driver.findElement(By.id("i-codigo")).sendKeys(cod);
		driver.findElement(By.id("i-descricao")).sendKeys(topico);
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();

		List<WebElement> lista = driver.findElements(By.xpath("//label [@class='checkbox']"));
		for(int i = 0; i<4;i++) {
			lista.get(i).click();
		}
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();
		driver.findElement(By.id("i-descricao-item")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.id("i-dica-item")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.id("bt-adicionar-item")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-salvar")).click();
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]"))));
		this.driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]")).click();

		this.horizon.waitLoad2();

		System.out.println("Topico " + topico + "					" + horizon.time(starttime));
		return topico;
	}

	public void editar() {
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-pesquisar"))));

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(topico);

		driver.findElement(By.id("bt-pesquisar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ topico +"')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='btnVisualizarClick()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		String cod =String.format("%04d",ThreadLocalRandom.current().nextInt(10000));
		topico =this.horizon.generateString(30);	

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		this.horizon.escolherStatus();
		driver.findElement(By.id("i-codigo")).sendKeys(cod);
		driver.findElement(By.id("i-descricao")).sendKeys(topico);

		driver.findElement(By.id("bt-excluir-item")).click();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='btnSimExcluirItem()']"))));
		this.driver.findElement(By.xpath("//button [@ng-click='btnSimExcluirItem()']")).click();

		this.horizon.waitLoad2();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
			wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));
		}
		catch(Exception e){			
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("i-descricao-item")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("i-dica-item")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("bt-adicionar-item")).click();		

		driver.findElement(By.id("bt-editar-item")).click();

		WebElement tog = driver.findElement(By.id("ddn-status-item-topico"));	
		tog.click();
		List<WebElement> lista = driver.findElements(By.xpath("//li [contains(@class,'item-topico item-status-option') and not(ancestor::div[contains(@style,'display:none')]) and not(ancestor::div[contains(@style,'display: none')])]"));
		lista.get(ThreadLocalRandom.current().nextInt(2)).click();	
		driver.findElement(By.id("i-descricao-item")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("i-dica-item")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("bt-salvar-item")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-salvar")).click();
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]"))));
		this.driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao\"]")).click();

		this.horizon.waitLoad2();

		System.out.println("Topico+ " + topico + "					" + horizon.time(starttime));	
	}

	public void excluir() {
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-pesquisar"))));

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(topico);

		driver.findElement(By.id("bt-pesquisar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ topico +"')]")).click();

		this.driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default pull-right bt-excluir\"]")).click();
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='btnSimExcluir()']"))));
		this.driver.findElement(By.xpath("//button [@ng-click='btnSimExcluir()']")).click();
	}
}