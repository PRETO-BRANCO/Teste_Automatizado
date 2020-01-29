package shiningDarkness;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Sinais {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String sinais;

	public Sinais(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime,String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;
		this.starttime = starttime;
		this.ed = ed;
	}

	public void criar() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/si/cadastros/#/");

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//li [@ui-sref='/sinaisvitais']")).click();

		this.horizon.goNovo();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-parametro"))));

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		sinais = this.horizon.generateString(50);	

		this.horizon.escolherStatus();
		this.driver.findElement(By.id("i-codigo")).sendKeys(cod);
		this.driver.findElement(By.id("i-parametro")).sendKeys(sinais);
		this.driver.findElement(By.id("i-unidade-medida")).sendKeys(this.horizon.generateString(5));
		this.driver.findElement(By.id("ck-normal-alterado")).click();
		this.driver.findElement(By.id("ck-repetir")).click();
		this.driver.findElement(By.id("ck-obrigatorio")).click();
		this.driver.findElement(By.id("ck-desc")).click();

		this.horizon.salvar();

		System.out.println("Sinais " + sinais + "		" + horizon.time(starttime));
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-parametro-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-parametro-filtro")).sendKeys(sinais);
		Select slek = new Select(driver.findElement(By.id("cb-obrigatorio-filtro")));
		slek.selectByValue("S");
		slek = new Select(driver.findElement(By.id("cb-repetir-filtro")));
		slek.selectByValue("S");
		slek = new Select(driver.findElement(By.id("cb-normal-filtro")));
		slek.selectByValue("S");

		this.horizon.selectFirst();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-parametro"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		sinais =this.horizon.generateString(50);		

		this.horizon.escolherStatus();
		this.driver.findElement(By.id("i-codigo")).sendKeys(cod);
		this.driver.findElement(By.id("i-parametro")).sendKeys(sinais);
		this.driver.findElement(By.id("i-unidade-medida")).sendKeys(this.horizon.generateString(5));
		this.driver.findElement(By.id("ck-normal-alterado")).click();
		this.driver.findElement(By.id("ck-repetir")).click();
		this.driver.findElement(By.id("ck-obrigatorio")).click();
		this.driver.findElement(By.id("ck-num")).click();

		this.horizon.salvar();

		System.out.println("Sinais+ " + sinais + "		" + horizon.time(starttime));
	}

	public void excluir() {	
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-parametro-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-parametro-filtro")).sendKeys(sinais);
		Select slek = new Select(driver.findElement(By.id("cb-obrigatorio-filtro")));
		slek.selectByValue("N");
		slek = new Select(driver.findElement(By.id("cb-repetir-filtro")));
		slek.selectByValue("N");
		slek = new Select(driver.findElement(By.id("cb-normal-filtro")));
		slek.selectByValue("N");

		this.horizon.excluir();
	}
}