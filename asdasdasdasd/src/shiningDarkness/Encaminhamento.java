package shiningDarkness;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Encaminhamento {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String encaminhamento;

	public Encaminhamento(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime, String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;
		this.starttime = starttime;
		this.ed = ed;
	}

	public void criar() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/si/cadastros/#/");

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//li [@ui-sref='/encaminhamento']")).click();

		this.horizon.goNovo();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao"))));

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		encaminhamento =this.horizon.generateString(20);		

		this.horizon.escolherStatus();	
		this.driver.findElement(By.id("i-codigo")).sendKeys(cod);
		this.driver.findElement(By.id("i-descricao")).sendKeys(encaminhamento);		

		this.horizon.salvar();

		System.out.println("Encaminhamento " + encaminhamento + "					" + horizon.time(starttime));
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(encaminhamento);

		Select slek = new Select(driver.findElement(By.id("cb-padrao-filtro")));
		slek.selectByValue("N");

		this.horizon.selectFirst();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		encaminhamento = this.horizon.generateString(20);

		this.horizon.escolherStatus();
		this.driver.findElement(By.id("i-codigo")).sendKeys(cod);
		this.driver.findElement(By.id("i-descricao")).sendKeys(encaminhamento);		

		this.horizon.salvar();

		System.out.println("Encaminhamento+ " + encaminhamento + "					" + horizon.time(starttime));
	}

	public void excluir() {	
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(encaminhamento);

		Select slek = new Select(driver.findElement(By.id("cb-padrao-filtro")));
		slek.selectByValue("N");

		this.horizon.excluir();
	}
}