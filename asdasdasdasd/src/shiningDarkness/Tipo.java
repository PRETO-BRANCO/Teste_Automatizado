package shiningDarkness;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tipo {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String tipo;

	public Tipo(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime, String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;
		this.starttime = starttime;
		this.ed = ed;
	}

	public void criar() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/si/cadastros/#/");

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//li [@ui-sref='/tiporegistro']")).click();

		this.horizon.goNovo();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao"))));

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		tipo = this.horizon.generateString(20);

		this.horizon.escolherStatus();
		driver.findElement(By.id("i-codigo")).sendKeys(cod);
		driver.findElement(By.id("i-descricao")).sendKeys(tipo);		

		this.horizon.salvar();

		System.out.println("Tipo " + tipo + "						" + horizon.time(starttime));
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(tipo);

		Select slek = new Select(driver.findElement(By.id("cb-padrao-filtro")));
		slek.selectByValue("N");

		this.horizon.selectFirst();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao"))));		
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		tipo =this.horizon.generateString(20);

		this.horizon.escolherStatus();
		driver.findElement(By.id("i-codigo")).sendKeys(cod);
		driver.findElement(By.id("i-descricao")).sendKeys(tipo);		

		this.horizon.salvar();

		System.out.println("Tipo+ " + tipo + "						" + horizon.time(starttime));
	}

	public void excluir() {		
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(tipo);

		Select slek = new Select(driver.findElement(By.id("cb-padrao-filtro")));
		slek.selectByValue("N");

		this.horizon.excluir();
	}
}