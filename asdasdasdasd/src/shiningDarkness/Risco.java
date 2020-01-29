package shiningDarkness;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Risco {
	private WebDriver driver;
	private Signal horizon;
	private Actions act;
	private Dano dano;
	private Recomenda rec;
	private Tecnica tec;
	private String recomenda;
	private String dam;
	private String tecnica;
	private Long starttime;
	private String ed;
	private String risco;
	private WebDriverWait wdw;

	public Risco(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime, String ed) {
		this.driver = driver;
		this.horizon = horizon;
		this.act = new Actions(driver);
		this.ed = ed;
		this.starttime = starttime;
		this.wdw = wdw;
		dano = new Dano(driver, wdw, horizon, starttime, ed);
		this.dam = this.dano.criar();		
		this.horizon.waitLoad2();
		rec = new Recomenda(driver, wdw, horizon, starttime, ed);
		this.recomenda = this.rec.criar();
		this.horizon.waitLoad2();
		tec = new Tecnica(driver, wdw, horizon, starttime, ed);
		this.tecnica = this.tec.criar();
		this.horizon.waitLoad2();		
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/modulos/#/cadastro/risco");

		this.horizon.waitLoad();

		driver.findElement(By.id("btn-novo")).click();

		Random rand = new Random();
		risco = this.horizon.generateString(75);

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@formcontrolname='descricao']")).sendKeys(risco);
		driver.findElement(By.xpath("//apls-select [@formcontrolname='categoria']")).click();		
		driver.findElement(By.xpath("//apls-option [@value='Q']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [contains(text(),'Configurações')]")).click();

		this.horizon.waitLoad();

		List<WebElement> lista = driver.findElements(By.xpath("//apls-radio-button [@value='N']"));
		List<WebElement> lista2 = driver.findElements(By.xpath("//apls-radio-button [@value='S']"));
		List<WebElement> lista3 = driver.findElements(By.xpath("//apls-radio-button [@value='C']"));
		List<WebElement> lista4 = driver.findElements(By.xpath("//input [contains(@formcontrolname,'fc')]"));

		for(int i = 1;i<lista.size();i++) {
			if((i%3)==0) {
				lista.get(i).click();
			}
			else if((i%2)==0){
				lista2.get(i).click();
			}
			else {
				lista3.get(i).click();
				lista4.get(i-1).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(10)) + String.format("%010d", ThreadLocalRandom.current().nextLong(10000000000l)));
			}
		}

		driver.findElement(By.xpath("//button [contains(text(),'Salvar') and @class='btn-small btn apls-button']")).click();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='unidadeMedida']")).click();

		lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();
		driver.findElement(By.xpath("//input [@formcontrolname='cas']")).sendKeys(String.format("%011d",ThreadLocalRandom.current().nextLong(100000000000l)));
		driver.findElement(By.xpath("//input [@formcontrolname='esocial']")).sendKeys(String.format("%010d",ThreadLocalRandom.current().nextLong(10000000000l)));
		driver.findElement(By.xpath("//apls-select [@formcontrolname='tipoAvaliacao']")).click();

		lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();
		driver.findElement(By.id("apls-select-6")).click();

		driver.findElement(By.xpath("//span [contains(text(),'"+ tecnica +"')]")).click();

		lista = driver.findElements(By.xpath("//div [@class='apls-checkbox-inner-container']"));		
		for(int i=0;i<lista.size();i++) {
			lista.get(i).click();
		}
		driver.findElement(By.xpath("//apls-select [@formcontrolname='categoriaCancerigeno']")).click();

		lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();
		driver.findElement(By.xpath("//apls-select [@formcontrolname='severidadePPRA']")).click();

		lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();
		driver.findElement(By.id("apls-select-9")).click();

		driver.findElement(By.xpath("//span [contains(text(),'"+ dam +"')]")).click();
		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar']")).click();

		driver.findElement(By.id("apls-tab-label-1-1")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span [contains(text(),'Dispositivos Mecânicos')]"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		lista = driver.findElements(By.xpath("//div [@class='apls-checkbox-inner-container' and not(ancestor::div[contains(@style,'display:none')]) and not(ancestor::div[contains(@style,'display: none')])]"));		
		for(int i=6;i<lista.size();i++) {
			lista.get(i).click();
		}		

		driver.findElement(By.id("apls-tab-label-1-2")).click();

		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("apls-select-10")).click();

		driver.findElement(By.xpath("//span [contains(text(),'"+ recomenda +"')]")).click();
		lista = driver.findElements(By.xpath("//button [@aplstooltip='Adicionar']"));
		lista.get(1).click();

		driver.findElement(By.id("apls-tab-label-1-3")).click();

		this.horizon.sleep(350);	
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//div [contains(text(),'Insalubridade Quantitativa')]")).click();
		driver.findElement(By.xpath("//apls-select [@formcontrolname='anexoNR15Quantitativa']")).click();

		lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();
		driver.findElement(By.xpath("//apls-select [@formcontrolname='grau']")).click();

		lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();		

		driver.findElement(By.id("apls-tab-label-1-4")).click();

		this.horizon.sleep(350);	
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//div [contains(text(),'Anexo 4 - Decreto 3048/99 Quantitativo')]")).click();
		driver.findElement(By.xpath("//input [@formcontrolname='agenteNocivo']")).sendKeys(this.horizon.generateString(30));

		driver.findElement(By.id("apls-tab-label-1-5")).click();

		this.horizon.sleep(350);	
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//textarea [@formcontrolname='regraTrabalhista1']")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//textarea [@formcontrolname='regraTrabalhista2']")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//textarea [@formcontrolname='regraTrabalhista3']")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//textarea [@formcontrolname='regraTrabalhista4']")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//textarea [@formcontrolname='regraTrabalhista5']")).sendKeys(this.horizon.generateString(200));

		driver.findElement(By.id("apls-tab-label-1-6")).click();

		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//textarea [@formcontrolname='regraPrevidenciaria1']")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//textarea [@formcontrolname='regraPrevidenciaria2']")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//textarea [@formcontrolname='regraPrevidenciaria3']")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//textarea [@formcontrolname='regraPrevidenciaria4']")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//textarea [@formcontrolname='regraPrevidenciaria5']")).sendKeys(this.horizon.generateString(200));		

		driver.findElement(By.id("apls-tab-label-0-1")).click();

		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='criterio']")).click();

		driver.findElement(By.xpath("//span [contains(text(),'Legal')]")).click();
		driver.findElement(By.xpath("//apls-select [@formcontrolname='tipo']")).click();

		driver.findElement(By.xpath("//span [contains(text(),'TLV/TWA (Média Ponderada)')]")).click();
		driver.findElement(By.xpath("//input [@formcontrolname='lt']")).sendKeys(String.format("%09d",ThreadLocalRandom.current().nextInt(100000000,1000000000)));
		driver.findElement(By.xpath("//apls-select [@formcontrolname='fonte']")).click();

		lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();		
		driver.findElement(By.xpath("//input [@formcontrolname='na']")).sendKeys(String.format("%09d",rand.nextInt(100000000)));
		driver.findElement(By.xpath("//apls-select [@formcontrolname='jornadaSemanal']")).click();

		lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();	
		driver.findElement(By.xpath("//input [@formcontrolname='dataInicio']")).sendKeys("01012019");
		driver.findElement(By.xpath("//button [contains(@class,'btn-icon btn-add-linha btn apls-button')]")).click();

		driver.findElement(By.xpath("//button [contains(text(),'Salvar')]")).click();

		System.out.println("Risco " + risco + " " + horizon.time(starttime));
		return risco;
	}

	public void editar() {		
		driver.navigate().refresh();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@formcontrolname='descricao']")).sendKeys(risco);
		driver.findElement(By.xpath("//apls-select [@formcontrolname='categoria']")).click();		
		driver.findElement(By.xpath("//apls-option [@value='Q']")).click();

		driver.findElement(By.xpath("//button [@aplstooltip='Filtrar']")).click();

		this.horizon.waitLoad();

		act.moveToElement(driver.findElement(By.xpath("//td [contains(text(),'"+ risco +"')]"))).doubleClick().perform();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [contains(text(),'Configurações')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-radio-button [@value='N']")).click();

		driver.findElement(By.xpath("//button [contains(text(),'Salvar') and @class='btn-small btn apls-button']")).click();

		risco =this.horizon.generateString(75);

		driver.findElement(By.xpath("//input [@formcontrolname='descricao']")).sendKeys(risco);
		driver.findElement(By.xpath("//apls-select [@formcontrolname='unidadeMedida']")).click();

		List<WebElement> lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();
		lista = driver.findElements(By.xpath("//div [@class='apls-checkbox-inner-container']"));		
		for(int i=0;i<lista.size();i++) {
			lista.get(i).click();
		}

		driver.findElement(By.id("apls-tab-label-1-1")).click();

		lista = driver.findElements(By.xpath("//div [@class='apls-checkbox-inner-container']"));		
		for(int i=7;i<lista.size();i++) {
			lista.get(i).click();
		}

		driver.findElement(By.id("apls-tab-label-1-3")).click();

		this.horizon.sleep(250);
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//div [contains(text(),'Insalubridade Qualitativa')]")).click();
		driver.findElement(By.xpath("//apls-select [@formcontrolname='anexoNR15Qualitativa']")).click();

		lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();

		lista = driver.findElements(By.xpath("//div [@class='apls-checkbox-inner-container']"));		
		for(int i=lista.size()-3;i<lista.size();i++) {
			lista.get(i).click();
		}

		driver.findElement(By.id("apls-tab-label-1-4")).click();

		this.horizon.sleep(250);	
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//div [contains(text(),'Anexo 4 - Decreto 3048/99 Qualitativo')]")).click();
		driver.findElement(By.xpath("//input [@formcontrolname='agenteNocivo']")).sendKeys(this.horizon.generateString(30));

		driver.findElement(By.id("apls-tab-label-1-5")).click();

		this.horizon.sleep(250);
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//textarea [@formcontrolname='regraTrabalhista1']")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//textarea [@formcontrolname='regraTrabalhista2']")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//textarea [@formcontrolname='regraTrabalhista3']")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//textarea [@formcontrolname='regraTrabalhista4']")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//textarea [@formcontrolname='regraTrabalhista5']")).sendKeys(this.horizon.generateString(200));

		driver.findElement(By.id("apls-tab-label-1-6")).click();

		this.horizon.sleep(250);
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//textarea [@formcontrolname='regraPrevidenciaria1']")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//textarea [@formcontrolname='regraPrevidenciaria2']")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//textarea [@formcontrolname='regraPrevidenciaria3']")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//textarea [@formcontrolname='regraPrevidenciaria4']")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//textarea [@formcontrolname='regraPrevidenciaria5']")).sendKeys(this.horizon.generateString(200));

		driver.findElement(By.id("apls-tab-label-0-1")).click();

		this.horizon.sleep(250);
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='criterio']")).click();

		driver.findElement(By.xpath("//span [contains(text(),'Técnico')]")).click();
		driver.findElement(By.xpath("//apls-select [@formcontrolname='tipo']")).click();

		lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(1,3)).click();
		driver.findElement(By.xpath("//input [@formcontrolname='lt']")).sendKeys(String.format("%09d",ThreadLocalRandom.current().nextInt(1000000000)));
		driver.findElement(By.xpath("//apls-select [@formcontrolname='fonte']")).click();

		lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));
		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();			
		driver.findElement(By.xpath("//input [@formcontrolname='dataInicio']")).sendKeys("01022019");
		driver.findElement(By.xpath("//button [contains(@class,'btn-icon btn-add-linha btn apls-button')]")).click();

		driver.findElement(By.xpath("//button [contains(text(),'Salvar')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [contains(text(),'Voltar')]")).click();

		this.horizon.waitLoad();

		System.out.println("Risco+ " + risco + " " + horizon.time(starttime));
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/modulos/#/cadastro/risco");

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@formcontrolname='descricao']")).sendKeys(risco);
		driver.findElement(By.xpath("//apls-select [@formcontrolname='categoria']")).click();		
		driver.findElement(By.xpath("//apls-option [@value='Q']")).click();

		driver.findElement(By.xpath("//button [@aplstooltip='Filtrar']")).click();	

		this.horizon.waitLoad();

		act.moveToElement(driver.findElement(By.xpath("//tr [@class='apls-row ng-star-inserted']"))).perform();		
		driver.findElement(By.xpath("//i [@aplstooltip='Excluir']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		this.horizon.waitLoad();

		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/danos_saude.html");

		this.horizon.waitLoad2();

		dano.excluir();

		this.horizon.waitLoad2();

		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/recomendacao.html");

		this.horizon.waitLoad2();

		rec.excluir();

		this.horizon.waitLoad2();

		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/tecnica_medicao.html");

		this.horizon.waitLoad2();

		tec.excluir();
	}

	public String getRecomendacao() {
		return this.recomenda;
	}
}