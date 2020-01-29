package shiningDarkness;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PCMSO {
	private WebDriver driver;
	private Signal horizon;
	private Actions act;
	private Profissionais prof;
	private GrupoRisco grupo;
	private WebDriverWait wdw;
	private String ed;
	private String unidade;
	private String grup;

	public PCMSO(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime,String ed,String unidade) {
		this.driver = driver;
		this.horizon = horizon;
		this.wdw = wdw;
		act = new Actions(driver);	
		this.ed = ed;
		this.unidade = unidade;
		prof = new Profissionais(driver,wdw,horizon, ed);
		grupo = new GrupoRisco(driver,wdw,horizon, starttime, ed);
		this.horizon.waitLoad();
		prof.criar();
		this.horizon.waitLoad();
		grup = grupo.criar();
	}

	public void criar(){
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/modulo/#/pcmso/cadastro");

		this.horizon.waitLoad();	

		driver.findElement(By.xpath("//input [@placeholder='Unidade']")).sendKeys(unidade);
		driver.findElement(By.xpath("//span [contains(@class,'mat-option-text') and contains(text(),'" + unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@placeholder='Área']")).sendKeys("AreaTeste");
		driver.findElement(By.xpath("//span [contains(@class,'mat-option-text') and contains(text(),'AreaTeste')]")).click();
		driver.findElement(By.xpath("//input [@placeholder='Início']")).sendKeys("01012019");
		driver.findElement(By.xpath("//input [@placeholder='Médico Coordenador']")).sendKeys("Epy");
		driver.findElement(By.xpath("//span [contains(text(),'Master Epy')]")).click();
		driver.findElement(By.xpath("//input [@placeholder='Fim']")).sendKeys("01022019");
		driver.findElement(By.xpath("//input [@placeholder='Médico Examinador']")).sendKeys("Epy");
		driver.findElement(By.xpath("//span [contains(@class,'mat-option-text') and contains(text(),'Master Epy')]")).click();		
		driver.findElement(By.xpath("//button [@mattooltip='Adicionar']")).click();

		List<WebElement> lista = driver.findElements(By.xpath("//div [@class='mat-checkbox-inner-container']"));		
		for(int i = 0; i<lista.size();i++) {
			lista.get(i).click();
		}

		wdw.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("mat-input-4"))));

		driver.findElement(By.id("mat-input-4")).sendKeys("01012019");

		act.click().perform();

		driver.findElement(By.id("mat-input-5")).sendKeys("01022019");

		driver.findElement(By.id("mat-tab-label-0-1")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("mat-input-6"))));
		this.horizon.waitLoad();

		driver.findElement(By.id("mat-input-6")).sendKeys("NR");
		lista = driver.findElements(By.xpath("//mat-option [contains(@id,'mat-option-')]"));
		lista.get(0).click();
		lista = driver.findElements(By.xpath("//button [@mattooltip='Adicionar']"));
		lista.get(0).click();
		driver.findElement(By.id("mat-input-7")).sendKeys(grup);
		driver.findElement(By.xpath("//span [contains(@class,'mat-option-text') and contains(text(),'" + grup +"')]")).click();
		lista = driver.findElements(By.xpath("//button [@mattooltip='Adicionar']"));
		lista.get(1).click();		

		driver.findElement(By.id("mat-tab-label-0-2")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//textarea [@placeholder='Descrição']"))));
		driver.findElement(By.xpath("//textarea [@placeholder='Descrição']")).sendKeys(this.horizon.generateString(250));
		driver.findElement(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']")).click();

		driver.findElement(By.id("mat-tab-label-0-3")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//textarea [@placeholder='Descrição da Ação']"))));
		driver.findElement(By.xpath("//textarea [@placeholder='Descrição da Ação']")).sendKeys(this.horizon.generateString(250));
		driver.findElement(By.id("mat-select-1")).click();

		lista = driver.findElements(By.xpath("//mat-pseudo-checkbox [@class='mat-option-pseudo-checkbox mat-pseudo-checkbox ng-star-inserted']"));		
		for(int i = 0; i<lista.size();i++) {
			lista.get(i).click();
		}	

		act.sendKeys(Keys.ESCAPE).perform();

		driver.findElement(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']")).click();
		driver.findElement(By.xpath("//button [@class='btn-small mat-raised-button mat-primary ng-star-inserted']")).click();
		this.horizon.waitLoad();
	}

	public void editar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/modulo/#/pcmso");

		this.horizon.waitLoad();		

		driver.findElement(By.xpath("//mat-select [@aria-label='Unidade']")).click();
		act.moveToElement(driver.findElement(By.xpath("//span [contains(text(),'" + unidade +"')]"))).click().sendKeys(Keys.ESCAPE).perform();
		driver.findElement(By.xpath("//button [@mattooltip='Pesquisar']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//p [contains(text(),'" + unidade +"')]")).click();

		driver.findElement(By.xpath("//button [@class='btn-list btn-small mat-raised-button mat-primary ng-star-inserted']")).click();

		this.horizon.waitLoad();

		driver.navigate().refresh();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@placeholder='Médico Examinador']"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();


		driver.findElement(By.xpath("//a [@mattooltip='Deletar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();	

		this.horizon.waitLoad();
		wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//p [@class='only-text']"))));

		List<WebElement> lista = driver.findElements(By.xpath("//div [@class='mat-checkbox-inner-container']"));		
		for(int i = 1; i<lista.size();i++) {
			lista.get(i).click();
		}

		driver.findElement(By.id("mat-tab-label-0-1")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("mat-input-6"))));
		this.horizon.waitLoad();		

		driver.findElement(By.id("mat-input-6")).sendKeys("nr");
		lista = driver.findElements(By.xpath("//mat-option [contains(@id,'mat-option-')]"));
		lista.get(1).click();
		lista = driver.findElements(By.xpath("//button [@mattooltip='Adicionar']"));
		lista.get(0).click();
		driver.findElement(By.id("mat-input-7")).sendKeys("dia");
		lista = driver.findElements(By.xpath("//mat-option [contains(@id,'mat-option-')]"));
		lista.get(0).click();
		lista = driver.findElements(By.xpath("//button [@mattooltip='Adicionar']"));
		lista.get(1).click();
		lista = driver.findElements(By.xpath("//a [@mattooltip='Deletar']"));
		lista.get(0).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();	

		this.horizon.waitLoad();
		wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//p [@class='only-text']"))));

		lista.get(2).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();			

		driver.findElement(By.id("mat-tab-label-0-2")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//textarea [@placeholder='Descrição']"))));
		driver.findElement(By.xpath("//textarea [@placeholder='Descrição']")).sendKeys(this.horizon.generateString(250));
		driver.findElement(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']")).click();
		lista = driver.findElements(By.xpath("//a [@mattooltip='Deletar']"));
		lista.get(0).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();	
		lista = driver.findElements(By.xpath("//a [@mattooltip='Editar']"));
		lista.get(0).click();
		driver.findElement(By.xpath("//textarea [@placeholder='Descrição']")).sendKeys(this.horizon.generateString(250));
		lista = driver.findElements(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']"));
		lista.get(1).click();

		driver.findElement(By.id("mat-tab-label-0-3")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//textarea [@placeholder='Descrição da Ação']"))));
		driver.findElement(By.xpath("//textarea [@placeholder='Descrição da Ação']")).sendKeys(this.horizon.generateString(250));
		driver.findElement(By.id("mat-select-1")).click();		
		lista = driver.findElements(By.xpath("//mat-pseudo-checkbox [@class='mat-option-pseudo-checkbox mat-pseudo-checkbox ng-star-inserted']"));		
		for(int i = 4; i<lista.size()-4;i++) {
			lista.get(i).click();
		}
		act.sendKeys(Keys.ESCAPE).perform();
		driver.findElement(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']")).click();
		lista = driver.findElements(By.xpath("//a [@mattooltip='Deletar']"));
		lista.get(0).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();	
		lista = driver.findElements(By.xpath("//a [@mattooltip='Editar']"));
		lista.get(0).click();
		driver.findElement(By.xpath("//textarea [@placeholder='Descrição da Ação']")).sendKeys(this.horizon.generateString(250));
		lista = driver.findElements(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']"));
		lista.get(1).click();		

		driver.findElement(By.xpath("//button [@class='btn-small mat-raised-button mat-primary ng-star-inserted']")).click();
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/modulo/#/pcmso");

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//mat-select [@aria-label='Unidade']")).click();
		act.moveToElement(driver.findElement(By.xpath("//span [contains(text(),'" + unidade +"')]"))).click().sendKeys(Keys.ESCAPE).perform();
		driver.findElement(By.xpath("//button [@mattooltip='Pesquisar']")).click();

		this.horizon.waitLoad();	

		driver.findElement(By.xpath("//p [contains(text(),'" + unidade +"')]")).click();

		driver.findElement(By.xpath("//button [@class='btn-small mat-raised-button ng-star-inserted']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();	

		this.horizon.waitLoad();

		prof.excluir();

		this.horizon.waitLoad();

		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/si/cadastros/#/gruporisco");

		this.horizon.waitLoad();

		grupo.excluir();

		this.horizon.waitLoad();
	}
}