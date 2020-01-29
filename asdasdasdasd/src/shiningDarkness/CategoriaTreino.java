package shiningDarkness;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoriaTreino {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Actions act;
	private Long starttime;
	private String ed;
	private String cat;

	public CategoriaTreino(WebDriver driver, WebDriverWait wdw, Signal horizon, Long starttime, String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;
		act = new Actions(driver);	
		this.starttime = starttime;
		this.ed = ed;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/modulos/#/ssma/treinamento");

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//h6 [contains(text(),'Categorias')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("btn-novo")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@formcontrolname='descricao']"))));

		cat = this.horizon.generateString(35);

		driver.findElement(By.xpath("//input [@formcontrolname='descricao']")).sendKeys(cat);

		driver.findElement(By.xpath("//button [@color='primary']")).click();

		this.horizon.waitLoad();

		System.out.println("Categoria " + cat + "				" + horizon.time(starttime));
		return cat;
	}

	public void editar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/modulos/#/ssma/treinamento");

		this.horizon.waitLoad();		

		driver.findElement(By.xpath("//h6 [contains(text(),'Categorias')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@formcontrolname='descricao']")).sendKeys(cat);

		driver.findElement(By.xpath("//button [@aplstooltip='Filtrar']")).click();

		this.horizon.waitLoad();

		act.moveToElement(driver.findElement(By.xpath("//td [contains(text(),'"+ cat +"')]"))).doubleClick().perform();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@formcontrolname='descricao']"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		cat =this.horizon.generateString(35);

		driver.findElement(By.xpath("//input [@formcontrolname='descricao']")).sendKeys(cat);

		driver.findElement(By.xpath("//button [@color='primary']")).click();

		this.horizon.waitLoad();

		System.out.println("Categoria+ " + cat + "				" + horizon.time(starttime));
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/modulos/#/ssma/treinamento");

		this.horizon.waitLoad();		

		driver.findElement(By.xpath("//h6 [contains(text(),'Categorias')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@aplstooltip='Limpar']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@formcontrolname='descricao']")).sendKeys(cat);

		driver.findElement(By.xpath("//button [@aplstooltip='Filtrar']")).click();

		this.horizon.waitLoad();
		act.moveToElement(driver.findElement(By.xpath("//tr [@class='apls-row ng-star-inserted']"))).perform();
		driver.findElement(By.xpath("//i [@class='fas fa-trash']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		this.horizon.waitLoad();
	}
}