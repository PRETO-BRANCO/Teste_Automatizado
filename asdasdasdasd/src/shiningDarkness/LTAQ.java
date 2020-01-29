package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LTAQ {
	private WebDriver driver;
	private WebDriverWait wdw;
	private Signal horizon;
	private ProfGeral prof;
	private String profs;
	private String ed;
	private String unidade;

	public LTAQ(WebDriver driver, WebDriverWait wdw, Signal horizon, Long starttime, String ed,String unidade) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;
		this.ed = ed;
		this.unidade = unidade;
		prof = new ProfGeral(driver, wdw, horizon, starttime, ed);
		profs = prof.criar();
	}

	public void criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menulaudos-ghe.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("LTAQ")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("apls-select-0"))));
		this.horizon.waitLoad();

		driver.findElement(By.id("apls-select-0")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@class='form-control apls-input']"))));

		driver.findElement(By.xpath("//span [contains(text(),'" + unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("apls-select-1")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@class='form-control apls-input']"))));

		driver.findElement(By.xpath("//span [contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("apls-select-2")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@class='form-control apls-input']"))));

		driver.findElement(By.xpath("//span [contains(text(),'SetorTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("apls-select-3")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@class='form-control apls-input']"))));

		driver.findElement(By.xpath("//span [contains(text(),'GHETeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("apls-select-4")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@placeholder='Digite 3 letras']"))));
		driver.findElement(By.xpath("//input [@placeholder='Digite 3 letras']")).sendKeys(profs);

		driver.findElement(By.xpath("//span [contains(text(),'"+ profs +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@formcontrolname='dataBase']")).sendKeys("27022019");
		driver.findElement(By.xpath("//input [@formcontrolname='art']")).sendKeys(this.horizon.generateString(15));
		driver.findElement(By.xpath("//input [@formcontrolname='avaliacaoCondicoesAmbienteTrabalho']")).sendKeys(this.horizon.generateString(25));
		driver.findElement(By.xpath("//textarea [@formcontrolname='condicoesOperacionais']")).sendKeys(this.horizon.generateString(500));

		driver.findElement(By.id("apls-tab-label-0-1")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//apls-input-group [@label='Descritivo GHEs sem Riscos']"))));

		driver.findElement(By.xpath("//apls-radio-button [@value='2']")).click();

		List<WebElement>lista = driver.findElements(By.xpath("//div [@class='apls-checkbox-inner-container']"));

		for(int i=0; i<ThreadLocalRandom.current().nextInt(1,30);i++) {
			int j = ThreadLocalRandom.current().nextInt(0,lista.size());
			if(j!= 8 && j != 9) lista.get(j).click();
		}

		driver.findElement(By.id("apls-tab-label-0-2")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//apls-checkbox [@formcontrolname='quantitativoRuido']"))));

		lista = driver.findElements(By.xpath("//div [@class='apls-checkbox-inner-container'  and (ancestor::ltaq-metodologia[contains(@class,'ng-star-inserted')])]"));

		for(int i=0; i<ThreadLocalRandom.current().nextInt(1,30);i++) {
			lista.get(ThreadLocalRandom.current().nextInt(0,lista.size())).click();
		}

		driver.findElement(By.id("apls-tab-label-0-3")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@formcontrolname='nome']"))));

		driver.findElement(By.xpath("//input [@formcontrolname='nome']")).sendKeys(this.horizon.generateString(60));
		driver.findElement(By.xpath("//input [@formcontrolname='cargo']")).sendKeys(this.horizon.generateString(60));
		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar'  and (ancestor::ltaq-colaboradores[contains(@class,'ng-star-inserted')])]")).click();		

		driver.findElement(By.xpath("//button [@color='light' and contains(text(),'Gravar Visão')]")).click();		
	}

	public void editar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menulaudos-ghe.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("LTAQ")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("apls-select-0"))));
		this.horizon.waitLoad();

		driver.findElement(By.id("apls-select-5")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@placeholder='Digite 3 letras']"))));
		driver.findElement(By.xpath("//input [@placeholder='Digite 3 letras']")).sendKeys("Yuren");

		driver.findElement(By.xpath("//span [contains(text(),'Yuren')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@formcontrolname='art']")).sendKeys(this.horizon.generateString(15));
		driver.findElement(By.xpath("//input [@formcontrolname='avaliacaoCondicoesAmbienteTrabalho']")).sendKeys(this.horizon.generateString(25));

		driver.findElement(By.id("apls-tab-label-0-1")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//apls-input-group [@label='Descritivo GHEs sem Riscos']"))));

		driver.findElement(By.xpath("//apls-radio-button [@value='1']")).click();

		List<WebElement>lista = driver.findElements(By.xpath("//div [@class='apls-checkbox-inner-container']"));

		for(int i=0; i<ThreadLocalRandom.current().nextInt(1,30);i++) {
			int j = ThreadLocalRandom.current().nextInt(0,lista.size());
			if(j!= 8 && j != 9) lista.get(j).click();
		}

		driver.findElement(By.id("apls-tab-label-0-2")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//apls-checkbox [@formcontrolname='quantitativoRuido']"))));

		lista = driver.findElements(By.xpath("//div [@class='apls-checkbox-inner-container']"));

		lista = driver.findElements(By.xpath("//div [@class='apls-checkbox-inner-container'  and (ancestor::ltaq-metodologia[contains(@class,'ng-star-inserted')])]"));

		for(int i=0; i<ThreadLocalRandom.current().nextInt(1,30);i++) {
			lista.get(ThreadLocalRandom.current().nextInt(0,lista.size())).click();
		}

		driver.findElement(By.id("apls-tab-label-0-3")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@formcontrolname='nome']"))));

		driver.findElement(By.xpath("//span [@aplstooltip='Excluir' and (ancestor::ltaq-colaboradores[contains(@class,'ng-star-inserted')])]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [(@class='btn apls-button') and (@color='primary') and contains(text(),'Excluir')]"))));
		driver.findElement(By.xpath("//button [(@class='btn apls-button') and (@color='primary') and contains(text(),'Excluir')]")).click();		

		driver.findElement(By.xpath("//input [@formcontrolname='nome']")).sendKeys(this.horizon.generateString(60));
		driver.findElement(By.xpath("//input [@formcontrolname='cargo']")).sendKeys(this.horizon.generateString(60));
		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar'  and (ancestor::ltaq-colaboradores[contains(@class,'ng-star-inserted')])]")).click();		

		driver.findElement(By.xpath("//button [@color='light' and contains(text(),'Gravar Visão')]")).click();	
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menulaudos-ghe.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("LTAQ")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("apls-select-0"))));
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@color='light' and contains(text(),'Limpar Visão')]")).click();	

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		this.horizon.waitLoad();

		prof.excluir();
	}
}