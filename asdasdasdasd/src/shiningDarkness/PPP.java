package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PPP {
	private WebDriver driver;
	private WebDriverWait wdw;
	private Signal horizon;
	private String ed;
	private String unidade;

	public PPP(WebDriver driver, WebDriverWait wdw, Signal horizon, Long starttime, String ed, String unidade) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;			
		this.ed = ed;
		this.unidade = unidade;
	}

	public void criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menulaudos-ghe.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("PPP")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@class='btn-small mat-raised-button mat-primary']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("mat-tab-content-0-1"))));

		driver.findElement(By.xpath("//input [@formcontrolname='catalogo']")).sendKeys("Epy");		

		driver.findElement(By.xpath("//span [contains(text(),'Master Epy') and (@class='mat-option-text')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("mat-tab-label-0-1"))));
		this.horizon.waitLoad();

		driver.findElement(By.id("mat-tab-label-0-1")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@placeholder='Início']"))));

		driver.findElement(By.xpath("//input [@placeholder='Início']")).sendKeys("11112011");		
		driver.findElement(By.id("mat-select-3")).click();

		driver.findElement(By.xpath("//mat-option [@value='2']")).click();		
		driver.findElement(By.xpath("//input [@placeholder='Fim']")).sendKeys("08102018");		
		driver.findElement(By.xpath("//input [@placeholder='Unidade']")).sendKeys(String.format("%015d", ThreadLocalRandom.current().nextLong(1000000000000000l)));
		driver.findElement(By.xpath("//input [@placeholder='Unidade']")).sendKeys(String.format("%015d", ThreadLocalRandom.current().nextLong(1000000000000000l)));
		driver.findElement(By.xpath("//input [@placeholder='Área']")).sendKeys(this.horizon.generateString(30));
		driver.findElement(By.xpath("//input [@placeholder='Setor']")).sendKeys(this.horizon.generateString(30));
		driver.findElement(By.id("mat-select-6")).click();

		driver.findElement(By.xpath("//mat-option [@value='2']")).click();	
		driver.findElement(By.xpath("//input [@placeholder='Cargo']")).sendKeys(this.horizon.generateString(30));
		driver.findElement(By.id("mat-select-7")).click();

		driver.findElement(By.xpath("//mat-option [@value='2']")).click();	
		driver.findElement(By.xpath("//input [@placeholder='CBO']")).sendKeys(String.format("%06d", ThreadLocalRandom.current().nextInt(100000)));
		driver.findElement(By.id("mat-select-2")).click();

		List<WebElement>lista = driver.findElements(By.xpath("//mat-option [contains(@id,'mat-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(0,lista.size())).click();

		driver.findElement(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']")).click();

		this.horizon.waitLoad();	
		wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//ng2-toast [@class='ng-star-inserted']"))));

		driver.findElement(By.id("mat-tab-label-0-2")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//textarea [@placeholder='Descrição da atividade']"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@placeholder='Início']")).sendKeys("11112011");
		driver.findElement(By.xpath("//textarea [@placeholder='Descrição da atividade']")).sendKeys(this.horizon.generateString(2400));
		driver.findElement(By.xpath("//input [@placeholder='Fim']")).sendKeys("08102018");

		lista = driver.findElements(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']"));
		lista.get(1).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		this.horizon.waitLoad();	
		wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//ng2-toast [@class='ng-star-inserted']"))));

		driver.findElement(By.id("mat-tab-label-0-3")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@placeholder='Fator de Risco']"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@placeholder='Início']")).sendKeys("11112011");
		driver.findElement(By.id("mat-select-12")).click();

		driver.findElement(By.xpath("//mat-option [@value='2']")).click();		
		driver.findElement(By.xpath("//input [@placeholder='Fim']")).sendKeys("08102018");	
		driver.findElement(By.xpath("//input [@placeholder='Fator de Risco']")).sendKeys(this.horizon.generateString(40));
		driver.findElement(By.xpath("//mat-select [@placeholder='Tipo']")).click();

		lista = driver.findElements(By.xpath("//mat-option [contains(@id,'mat-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(0,lista.size())).click();
		driver.findElement(By.xpath("//mat-select [@placeholder='Unidade Medida']")).click();

		lista = driver.findElements(By.xpath("//mat-option [contains(@id,'mat-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(0,lista.size())).click();
		driver.findElement(By.xpath("//input [@placeholder='Resultado']")).sendKeys(this.horizon.generateString(18));
		driver.findElement(By.id("mat-select-13")).click();

		driver.findElement(By.xpath("//mat-option [@value='2']")).click();	
		driver.findElement(By.xpath("//input [@placeholder='Técnica de Medição']")).sendKeys(this.horizon.generateString(40));
		driver.findElement(By.xpath("//mat-select [@placeholder='EPC Eficaz']")).click();

		lista = driver.findElements(By.xpath("//mat-option [contains(@id,'mat-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(0,lista.size())).click();
		driver.findElement(By.xpath("//mat-select [@placeholder='EPI Eficaz']")).click();

		lista = driver.findElements(By.xpath("//mat-option [contains(@id,'mat-option-')]"));
		int i = ThreadLocalRandom.current().nextInt(0,lista.size());
		lista.get(i).click();
		if(i ==0) {
			driver.findElement(By.id("mat-select-14")).click();

			driver.findElement(By.xpath("//mat-option [@value='2']")).click();	
			driver.findElement(By.xpath("//input [@placeholder='CA']")).sendKeys(this.horizon.generateString(200));	
		}
		driver.findElement(By.xpath("//button [@mattooltip='Adicionar']")).click();

		this.horizon.waitLoad();	

		driver.findElement(By.id("mat-tab-label-0-4")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("mat-tab-label-1-1"))));

		driver.findElement(By.id("mat-tab-label-1-1")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@placeholder='Responsável']"))));

		driver.findElement(By.xpath("//input [@placeholder='Início']")).sendKeys("11112011");	
		driver.findElement(By.xpath("//input [@placeholder='Responsável']")).sendKeys(this.horizon.generateString(40));
		driver.findElement(By.xpath("//input [@placeholder='Fim']")).sendKeys("08102018");	
		driver.findElement(By.xpath("//input [@placeholder='NIT']")).sendKeys(String.format("%011d", ThreadLocalRandom.current().nextLong(100000000000l)));
		driver.findElement(By.xpath("//input [@placeholder='Registro Conselho']")).sendKeys(this.horizon.generateString(20));

		driver.findElement(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']")).click();

		this.horizon.waitLoad();	
		wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//ng2-toast [@class='ng-star-inserted']"))));

		driver.findElement(By.id("mat-tab-label-1-2")).click();

		wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//a [@mattooltip='Editar']"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@placeholder='Início']")).sendKeys("11112011");	
		driver.findElement(By.xpath("//input [@placeholder='Responsável']")).sendKeys(this.horizon.generateString(40));
		driver.findElement(By.xpath("//input [@placeholder='Fim']")).sendKeys("08102018");	
		driver.findElement(By.xpath("//input [@placeholder='NIT']")).sendKeys(String.format("%011d", ThreadLocalRandom.current().nextLong(100000000000l)));
		driver.findElement(By.xpath("//input [@placeholder='Registro Conselho']")).sendKeys(this.horizon.generateString(20));

		driver.findElement(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']")).click();

		this.horizon.waitLoad();
		wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//ng2-toast [@class='ng-star-inserted']"))));

		driver.findElement(By.id("mat-tab-label-1-3")).click();
		driver.findElement(By.id("mat-select-15")).click();

		driver.findElement(By.xpath("//mat-option [@value='2']")).click();	
		driver.findElement(By.xpath("//input [@placeholder='Responsável pela assinatura do PPP']")).sendKeys(this.horizon.generateString(40));
		driver.findElement(By.xpath("//input [@placeholder='NIT']")).sendKeys(String.format("%011d", ThreadLocalRandom.current().nextLong(100000000000l)));
		driver.findElement(By.xpath("//input [@placeholder='Cargo']")).sendKeys(this.horizon.generateString(60));
		driver.findElement(By.xpath("//textarea [@placeholder='Observações (Monitoração Biológica)']")).clear();
		driver.findElement(By.xpath("//textarea [@placeholder='Observações (Monitoração Biológica)']")).sendKeys(this.horizon.generateString(1000));
		driver.findElement(By.xpath("//textarea [@placeholder='Observações (Final do PPP)']")).clear();
		driver.findElement(By.xpath("//textarea [@placeholder='Observações (Final do PPP)']")).sendKeys(this.horizon.generateString(1000));

		driver.findElement(By.xpath("//button [@class='btn-small mat-raised-button mat-primary ng-star-inserted']")).click();

		this.horizon.waitLoad();		
	}

	public void editar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menulaudos-ghe.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("PPP")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@formcontrolname='nome']")).sendKeys("Epy");		

		driver.findElement(By.xpath("//span [contains(text(),'Master Epy') and (@class='mat-option-text')]")).click();

		driver.findElement(By.id("mat-select-1")).click();

		driver.findElement(By.xpath("//span [contains(text(),'" + unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@placeholder='Área']")).sendKeys(Keys.ESCAPE + "AreaTeste");

		driver.findElement(By.xpath("//span [contains(text(),'AreaTeste')]")).click();

		driver.findElement(By.id("mat-select-1")).sendKeys(Keys.ESCAPE);

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@mattooltip='Pesquisar']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//p [contains(text(),'Master Epy')]")).click();

		driver.findElement(By.xpath("//button [@class='btn-list btn-small mat-raised-button mat-primary ng-star-inserted']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("mat-tab-label-0-1"))));
		this.horizon.waitLoad();

		driver.findElement(By.id("mat-tab-label-0-1")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@placeholder='Início']"))));

		driver.findElement(By.xpath("//a [@mattooltip='Deletar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@placeholder='Início']")).sendKeys("11112011");		
		driver.findElement(By.id("mat-select-3")).click();

		driver.findElement(By.xpath("//mat-option [@value='2']")).click();	
		driver.findElement(By.xpath("//input [@placeholder='Fim']")).sendKeys("08102018");		
		driver.findElement(By.xpath("//input [@placeholder='Unidade']")).sendKeys(String.format("%015d", ThreadLocalRandom.current().nextLong(1000000000000000l)));
		driver.findElement(By.xpath("//input [@placeholder='Área']")).sendKeys(this.horizon.generateString(15));
		driver.findElement(By.xpath("//input [@placeholder='Setor']")).sendKeys(this.horizon.generateString(15));
		driver.findElement(By.id("mat-select-6")).click();

		driver.findElement(By.xpath("//mat-option [@value='2']")).click();
		driver.findElement(By.xpath("//input [@placeholder='Cargo']")).sendKeys(this.horizon.generateString(15));
		driver.findElement(By.id("mat-select-7")).click();

		driver.findElement(By.xpath("//mat-option [@value='2']")).click();
		driver.findElement(By.xpath("//input [@placeholder='CBO']")).sendKeys(String.format("%06d", ThreadLocalRandom.current().nextInt(100000)));
		driver.findElement(By.id("mat-select-2")).click();

		List<WebElement> lista = driver.findElements(By.xpath("//mat-option [contains(@id,'mat-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(0,lista.size())).click();

		driver.findElement(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']")).click();

		driver.findElement(By.xpath("//a [@mattooltip='Editar']")).click();

		driver.findElement(By.xpath("//input [@placeholder='Unidade']")).sendKeys(String.format("%015d", ThreadLocalRandom.current().nextLong(1000000000000000l)));
		driver.findElement(By.xpath("//input [@placeholder='Área']")).sendKeys(this.horizon.generateString(15));
		driver.findElement(By.xpath("//input [@placeholder='Setor']")).sendKeys(this.horizon.generateString(15));
		driver.findElement(By.xpath("//input [@placeholder='Cargo']")).sendKeys(this.horizon.generateString(15));

		lista = driver.findElements(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']"));
		lista.get(1).click();

		this.horizon.waitLoad();
		wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//ng2-toast [@class='ng-star-inserted']"))));
		wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//ng2-toast [@class='ng-star-inserted']"))));

		driver.findElement(By.id("mat-tab-label-0-2")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//textarea [@placeholder='Descrição da atividade']"))));
		this.horizon.sleep(500);
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//a [@mattooltip='Deletar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@placeholder='Início']")).sendKeys("11112011");
		driver.findElement(By.xpath("//textarea [@placeholder='Descrição da atividade']")).sendKeys(this.horizon.generateString(1200));
		driver.findElement(By.xpath("//input [@placeholder='Fim']")).sendKeys("08102018");

		lista = driver.findElements(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']"));
		lista.get(1).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		driver.findElement(By.xpath("//a [@mattooltip='Editar']")).click();

		driver.findElement(By.xpath("//textarea [@placeholder='Descrição da atividade']")).sendKeys(this.horizon.generateString(1200));

		lista = driver.findElements(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']"));
		lista.get(1).click();

		this.horizon.waitLoad();		

		driver.findElement(By.id("mat-tab-label-0-3")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@placeholder='Fator de Risco']"))));
		this.horizon.sleep(500);
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//a [@mattooltip='Deletar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@placeholder='Início']")).sendKeys("11112011");
		driver.findElement(By.id("mat-select-12")).click();

		driver.findElement(By.xpath("//mat-option [@value='2']")).click();	
		driver.findElement(By.xpath("//input [@placeholder='Fim']")).sendKeys("08102018");	
		driver.findElement(By.xpath("//input [@placeholder='Fator de Risco']")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.xpath("//mat-select [@placeholder='Tipo']")).click();

		lista = driver.findElements(By.xpath("//mat-option [contains(@id,'mat-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(0,lista.size())).click();
		driver.findElement(By.xpath("//mat-select [@placeholder='Unidade Medida']")).click();

		lista = driver.findElements(By.xpath("//mat-option [contains(@id,'mat-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(0,lista.size())).click();
		driver.findElement(By.xpath("//input [@placeholder='Resultado']")).sendKeys(this.horizon.generateString(9));
		driver.findElement(By.id("mat-select-13")).click();

		driver.findElement(By.xpath("//mat-option [@value='2']")).click();
		driver.findElement(By.xpath("//input [@placeholder='Técnica de Medição']")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.xpath("//mat-select [@placeholder='EPC Eficaz']")).click();

		lista = driver.findElements(By.xpath("//mat-option [contains(@id,'mat-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(0,lista.size())).click();
		driver.findElement(By.xpath("//mat-select [@placeholder='EPI Eficaz']")).click();

		lista = driver.findElements(By.xpath("//mat-option [contains(@id,'mat-option-')]"));
		int i = ThreadLocalRandom.current().nextInt(0,lista.size());
		lista.get(i).click();
		if(i ==0) {
			driver.findElement(By.id("mat-select-14")).click();

			driver.findElement(By.xpath("//mat-option [@value='2']")).click();
			driver.findElement(By.xpath("//input [@placeholder='CA']")).sendKeys(this.horizon.generateString(200));	
		}
		driver.findElement(By.xpath("//button [@mattooltip='Adicionar']")).click();

		driver.findElement(By.xpath("//a [@mattooltip='Editar']")).click();

		driver.findElement(By.xpath("//input [@placeholder='Fator de Risco']")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.xpath("//input [@placeholder='Resultado']")).sendKeys(this.horizon.generateString(9));
		driver.findElement(By.xpath("//input [@placeholder='Técnica de Medição']")).sendKeys(this.horizon.generateString(20));

		lista = driver.findElements(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']"));
		lista.get(1).click();

		this.horizon.waitLoad();
		wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//ng2-toast [@class='ng-star-inserted']"))));

		driver.findElement(By.id("mat-tab-label-0-4")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("mat-tab-label-1-1"))));

		driver.findElement(By.id("mat-tab-label-1-1")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@placeholder='Responsável']"))));
		this.horizon.sleep(500);
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//a [@mattooltip='Deletar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@placeholder='Início']")).sendKeys("11112011");	
		driver.findElement(By.xpath("//input [@placeholder='Responsável']")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.xpath("//input [@placeholder='Fim']")).sendKeys("08102018");	
		driver.findElement(By.xpath("//input [@placeholder='NIT']")).sendKeys(String.format("%011d", ThreadLocalRandom.current().nextLong(100000000000l)));
		driver.findElement(By.xpath("//input [@placeholder='Registro Conselho']")).sendKeys(this.horizon.generateString(10));

		driver.findElement(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//a [@mattooltip='Editar']")).click();

		driver.findElement(By.xpath("//input [@placeholder='Responsável']")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.xpath("//input [@placeholder='Registro Conselho']")).sendKeys(this.horizon.generateString(10));

		lista = driver.findElements(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']"));
		lista.get(1).click();

		this.horizon.waitLoad();
		wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//ng2-toast [@class='ng-star-inserted']"))));
		wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//ng2-toast [@class='ng-star-inserted']"))));
		wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//ng2-toast [@class='ng-star-inserted']"))));

		driver.findElement(By.id("mat-tab-label-1-2")).click();

		this.horizon.sleep(500);
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//a [@mattooltip='Deletar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@placeholder='Início']")).sendKeys("11112011");	
		driver.findElement(By.xpath("//input [@placeholder='Responsável']")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.xpath("//input [@placeholder='Fim']")).sendKeys("08102018");	
		driver.findElement(By.xpath("//input [@placeholder='NIT']")).sendKeys(String.format("%011d", ThreadLocalRandom.current().nextLong(100000000000l)));
		driver.findElement(By.xpath("//input [@placeholder='Registro Conselho']")).sendKeys(this.horizon.generateString(10));

		driver.findElement(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//a [@mattooltip='Editar']")).click();

		driver.findElement(By.xpath("//input [@placeholder='Responsável']")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.xpath("//input [@placeholder='Registro Conselho']")).sendKeys(this.horizon.generateString(10));

		lista = driver.findElements(By.xpath("//button [@class='btn-list background-theme mat-icon-button mat-primary ng-star-inserted']"));
		lista.get(1).click();

		this.horizon.waitLoad();
		wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//ng2-toast [@class='ng-star-inserted']"))));
		wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//ng2-toast [@class='ng-star-inserted']"))));

		driver.findElement(By.id("mat-tab-label-1-3")).click();

		driver.findElement(By.xpath("//textarea [@placeholder='Observações (Monitoração Biológica)']")).clear();
		driver.findElement(By.xpath("//textarea [@placeholder='Observações (Monitoração Biológica)']")).sendKeys(this.horizon.generateString(1000));
		driver.findElement(By.xpath("//textarea [@placeholder='Observações (Final do PPP)']")).clear();
		driver.findElement(By.xpath("//textarea [@placeholder='Observações (Final do PPP)']")).sendKeys(this.horizon.generateString(1000));

		lista = driver.findElements(By.xpath("//button [@class='btn-small mat-raised-button mat-primary ng-star-inserted']"));
		lista.get(1).click();

		this.horizon.waitLoad();
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menulaudos-ghe.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("PPP")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@formcontrolname='nome']")).sendKeys("Epy");		

		driver.findElement(By.xpath("//span [contains(text(),'Master Epy') and (@class='mat-option-text')]")).click();

		driver.findElement(By.id("mat-select-1")).click();

		driver.findElement(By.xpath("//span [contains(text(),'" + unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@placeholder='Área']")).sendKeys(Keys.ESCAPE + "AreaTeste");

		driver.findElement(By.xpath("//span [contains(text(),'AreaTeste')]")).click();

		driver.findElement(By.id("mat-select-1")).sendKeys(Keys.ESCAPE);

		this.horizon.waitLoad();		

		driver.findElement(By.xpath("//button [@mattooltip='Pesquisar']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//p [contains(text(),'Master Epy')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn-small mat-raised-button ng-star-inserted']"))));
		driver.findElement(By.xpath("//button [@class='btn-small mat-raised-button ng-star-inserted']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		this.horizon.waitLoad();
	}
}