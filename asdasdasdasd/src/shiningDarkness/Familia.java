package shiningDarkness;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Familia {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String familia;
	private Actions act;

	public Familia(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime, String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.starttime = starttime;
		this.ed = ed;
		this.act = new Actions(driver);
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/modulos/#/cadastro/equipamentos/familia");

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@class='btn apls-button']")).click();	

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@formcontrolname='descricao']"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		familia = this.horizon.generateString(100);

		driver.findElement(By.xpath("//input [@formcontrolname='descricao']")).sendKeys(familia);
		driver.findElement(By.xpath("//input [@formcontrolname='codigo']")).sendKeys(String.format("%010d", ThreadLocalRandom.current().nextLong(10000000000l)));
		driver.findElement(By.xpath("//apls-select [@formcontrolname='riscos']")).click();

		driver.findElement(By.xpath("//span [contains(text(),'Calor')]")).click();

		driver.findElement(By.xpath("//div [@class='apls-confirm']")).click();

		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar']")).click();

		driver.findElement(By.xpath("//button [@class='btn-salvar btn apls-button']")).click();

		this.horizon.waitLoad();

		System.out.println("Familia " + familia + " " + horizon.time(starttime));
		return familia;
	}

	public void editar() {
		driver.navigate().refresh();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@aplstooltip='Filtrar']"))));
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@formcontrolname='descricaoPT']")).sendKeys(familia);

		driver.findElement(By.xpath("//button [@aplstooltip='Filtrar']")).click();

		act.moveToElement(driver.findElement(By.xpath("//td [contains(text(),'"+ familia +"')]"))).doubleClick().perform();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@formcontrolname='descricao']"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		familia = this.horizon.generateString(100);

		driver.findElement(By.xpath("//input [@formcontrolname='descricao']")).sendKeys(familia);
		driver.findElement(By.xpath("//input [@formcontrolname='codigo']")).sendKeys(String.format("%010d", ThreadLocalRandom.current().nextLong(10000000000l)));	

		act.moveToElement(driver.findElement(By.xpath("//td [@class='col-7 apls-cell cdk-column-descricao ng-star-inserted']"))).perform();
		driver.findElement(By.xpath("//i [@aplstooltip='Excluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='riscos']")).click();

		driver.findElement(By.xpath("//div [contains(@class,'allbutton')]")).click();

		driver.findElement(By.xpath("//div [@class='apls-confirm']")).click();

		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar']")).click();

		driver.findElement(By.xpath("//button [@class='btn-salvar btn apls-button']")).click();

		this.horizon.waitLoad();

		System.out.println("Familia+ " + familia + " " + horizon.time(starttime));
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/modulos/#/cadastro/equipamentos/familia");

		this.horizon.waitLoad();

		driver.navigate().refresh();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@aplstooltip='Filtrar']"))));
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@formcontrolname='descricaoPT']")).sendKeys(familia);

		driver.findElement(By.xpath("//button [@aplstooltip='Filtrar']")).click();

		act.moveToElement(driver.findElement(By.xpath("//td [contains(text(),'"+ familia +"')]"))).perform();
		driver.findElement(By.xpath("//i [@aplstooltip='Excluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();
	}
}