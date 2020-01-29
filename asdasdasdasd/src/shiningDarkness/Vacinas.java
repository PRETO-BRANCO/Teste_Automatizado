package shiningDarkness;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Vacinas {	
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String vacina;

	public Vacinas(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime,String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;
		this.starttime = starttime;
		this.ed = ed;
	}

	public void criar() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/si/cadastros/#/");

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//li [@ui-sref='/vacinas']")).click();

		this.horizon.goNovo();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-vacina"))));

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		vacina = this.horizon.generateString(30);			

		this.horizon.escolherStatus();
		this.driver.findElement(By.id("i-codigo-vacina")).sendKeys(cod);
		this.driver.findElement(By.id("i-descricao-vacina")).sendKeys(vacina);

		this.driver.findElement(By.id("i-primeira")).click();
		Select val = new Select(driver.findElement(By.id("cb-primeira-validade")));
		Select not = new Select(driver.findElement(By.id("cb-primeira-notificacao")));		

		val.selectByValue(String.valueOf(rand.nextInt(11)+1));		
		not.selectByValue(String.valueOf((int) (7.5 * Math.pow(2,(rand.nextInt(4))))));

		this.driver.findElement(By.id("i-segunda")).click();
		val = new Select(driver.findElement(By.id("cb-segunda-validade")));
		not = new Select(driver.findElement(By.id("cb-segunda-notificacao")));

		val.selectByValue(String.valueOf(rand.nextInt(11)+1));		
		not.selectByValue(String.valueOf((int) (7.5 * Math.pow(2,(rand.nextInt(4))))));

		this.driver.findElement(By.id("i-terceira")).click();
		val = new Select(driver.findElement(By.id("cb-terceira-validade")));
		not = new Select(driver.findElement(By.id("cb-terceira-notificacao")));

		val.selectByValue(String.valueOf(rand.nextInt(11)+1));		
		not.selectByValue(String.valueOf((int) (7.5 * Math.pow(2,(rand.nextInt(4))))));

		this.driver.findElement(By.id("i-reforco")).click();
		val = new Select(driver.findElement(By.id("cb-reforco-validade")));
		not = new Select(driver.findElement(By.id("cb-reforco-notificacao")));

		val.selectByValue(String.valueOf(rand.nextInt(11)+1));		
		not.selectByValue(String.valueOf((int) (7.5 * Math.pow(2,(rand.nextInt(4))))));

		this.horizon.salvar();

		System.out.println("Vacinas " + vacina + "					" + horizon.time(starttime));
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(vacina);

		Select slek = new Select(driver.findElement(By.id("cb-dose-filtro")));
		slek.selectByValue("PRIMEIRA");

		this.horizon.selectFirst();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-vacina"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		vacina = this.horizon.generateString(30);

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-vacina"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		this.horizon.escolherStatus();
		this.driver.findElement(By.id("i-codigo-vacina")).sendKeys(cod);
		this.driver.findElement(By.id("i-descricao-vacina")).sendKeys(vacina);
		this.driver.findElement(By.id("i-unica")).click();

		this.horizon.salvar();

		System.out.println("Vacinas+ " + vacina + "					" + horizon.time(starttime));
	}

	public void excluir() {		
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(vacina);

		Select slek = new Select(driver.findElement(By.id("cb-dose-filtro")));
		slek.selectByValue("UNICA");

		this.horizon.excluir();
	}
}