package shiningDarkness;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Centro {	
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String centro;

	public Centro(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime, String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;
		this.starttime = starttime;
		this.ed = ed;
	}

	public void criar() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/si/cadastros/#/");

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//li [@ui-sref='/centromedico']")).click();

		this.horizon.goNovo();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao"))));

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		centro =  this.horizon.generateString(50);		

		this.horizon.escolherStatus();
		this.driver.findElement(By.id("i-codigo")).sendKeys(cod);
		this.driver.findElement(By.id("i-descricao")).sendKeys(centro);		
		this.driver.findElement(By.id("i-cnes")).sendKeys(String.format("%05d",rand.nextInt(100000)));
		this.driver.findElement(By.id("i-cep")).sendKeys("957"+String.format("%05d",rand.nextInt(100000)));
		this.driver.findElement(By.id("i-endereco")).sendKeys(this.horizon.generateString(50));
		this.driver.findElement(By.id("i-cidade")).sendKeys(this.horizon.generateString(50));
		Select uf = new Select(driver.findElement(By.id("cb-uf")));
		List<WebElement> lista = uf.getOptions();
		uf.selectByIndex(rand.nextInt(lista.size()-1)+1);
		this.driver.findElement(By.id("i-forma-contato")).sendKeys(this.horizon.generateString(50));
		this.driver.findElement(By.id("i-email")).sendKeys(this.horizon.generateString2(50) + "@gmail.com");

		this.horizon.salvar();

		System.out.println("Centro " + centro + "		" + horizon.time(starttime));
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(centro);

		this.horizon.selectFirst();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		centro = this.horizon.generateString(50);			

		this.horizon.escolherStatus();
		this.driver.findElement(By.id("i-codigo")).sendKeys(cod);
		this.driver.findElement(By.id("i-descricao")).sendKeys(centro);		
		this.driver.findElement(By.id("i-cnes")).sendKeys(String.format("%05d",rand.nextInt(100000)));		
		this.driver.findElement(By.id("i-endereco")).sendKeys(this.horizon.generateString(50));		
		this.driver.findElement(By.id("i-forma-contato")).sendKeys(this.horizon.generateString(50));			

		this.horizon.salvar();

		System.out.println("Centro+ " + centro + "		" + horizon.time(starttime));
	}

	public void excluir() {	
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(centro);

		this.horizon.excluir();
	}
}