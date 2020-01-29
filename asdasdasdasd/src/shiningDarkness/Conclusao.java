package shiningDarkness;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Conclusao {
	private WebDriver driver;
	private Signal horizon;
	private WebDriverWait wdw;	
	private String ed;
	private String unidade;

	public  Conclusao(WebDriver driver, WebDriverWait wdw, Signal horizon, String ed, String unidade) {
		this.driver = driver;
		this.horizon = horizon;
		this.wdw = wdw; 			
		this.ed = ed;
		this.unidade = unidade;
	}

	public void criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/so/segurancamaquinas/pesquisamaquina.html");

		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-area1")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-pesquisar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'MaquinaTeste')]")).click();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-visualizar']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-visualizar']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@onclick=\"onItemMenuClick('conclusoes')\"]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("inlineRadio2"))));
		this.horizon.waitLoad2();

		driver.findElement(By.id("inlineRadio2")).click();		
		driver.findElement(By.id("i-data-adequacao")).sendKeys("dez2030" + Keys.ENTER);

		driver.findElement(By.id("ta-consideracao")).sendKeys(this.horizon.generateString(1999));
		driver.findElement(By.id("ta-conclusao")).sendKeys(this.horizon.generateString(1999));

		driver.findElement(By.id("bt-salvar")).click();

		this.horizon.waitLoad2();
	}

	public void statusQuo() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a [@onclick=\"onItemMenuClick('conclusoes')\"]"))));
		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@onclick=\"onItemMenuClick('conclusoes')\"]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("inlineRadio2"))));
		this.horizon.waitLoad2();

		driver.findElement(By.id("inlineRadio1")).click();	
		driver.findElement(By.id("ta-consideracao")).clear();
		driver.findElement(By.id("ta-consideracao")).sendKeys(this.horizon.generateString(1));
		driver.findElement(By.id("ta-conclusao")).clear();
		driver.findElement(By.id("ta-conclusao")).sendKeys(this.horizon.generateString(1));

		driver.findElement(By.id("bt-salvar")).click();		
	}
}