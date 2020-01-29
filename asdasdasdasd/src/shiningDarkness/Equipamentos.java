package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Equipamentos {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String epi;
	private String unidade;
	private Actions act;
	private Familia familia;
	private String fam;

	public Equipamentos(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime, String ed, String unidade) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.starttime = starttime;
		this.ed = ed;
		this.unidade = unidade;
		this.act = new Actions(driver);		
		this.familia = new Familia(driver, wdw, horizon, starttime, ed);
		fam = familia.criar();
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/modulos/#/cadastro/equipamentos/epi");

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn-novo btn apls-button ng-star-inserted']"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@class='btn-novo btn apls-button ng-star-inserted']")).click();	

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//textarea [@formcontrolname='descricao']"))));
		this.horizon.sleep(500);
		this.horizon.waitLoad();

		epi = this.horizon.generateString(500);

		driver.findElement(By.xpath("//textarea [@formcontrolname='descricao']")).sendKeys(epi);		
		driver.findElement(By.id("cod")).sendKeys(String.format("%021d", ThreadLocalRandom.current().nextLong()));
		driver.findElement(By.id("cod")).sendKeys(String.format("%020d", ThreadLocalRandom.current().nextLong()));			

		driver.findElement(By.xpath("//apls-select [@formcontrolname='unidadeMedida']")).click();
		List<WebElement> lista = driver.findElements(By.xpath("//apls-option [@tabindex='0']"));
		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='categoria']")).click();
		int n = ThreadLocalRandom.current().nextInt(1,11);
		driver.findElement(By.xpath("//apls-option [@chave='"+ String.valueOf(n) +"']")).click();
		driver.findElement(By.id("text")).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(1,10)) + String.format("%07d", ThreadLocalRandom.current().nextInt(10000000)));
		driver.findElement(By.xpath("//input [@formcontrolname='validadeCA']")).sendKeys("31012030");
		driver.findElement(By.xpath("//input [@formcontrolname='fpa']")).sendKeys(this.horizon.generateString(25));

		if (n == 7) {
			driver.findElement(By.xpath("//input [@formcontrolname='atenuacaoDBA']")).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(1,10)) + String.format("%09d", ThreadLocalRandom.current().nextInt(1000000000)));
		}

		driver.findElement(By.xpath("//apls-select [@formcontrolname='familia']")).click();
		driver.findElement(By.xpath("//span [contains(text(),'" + fam +"')]")).click();		

		driver.findElement(By.id("apls-tab-label-0-1")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//apls-select [@formcontrolname='area1']"))));
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='area1']")).click();

		driver.findElement(By.xpath("//span [contains(text(),'" + unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='area2']")).click();

		driver.findElement(By.xpath("//span [contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='ghe']")).click();

		driver.findElement(By.xpath("//span [contains(text(),'GHETeste')]")).click();

		act.moveToElement(driver.findElement(By.xpath("//apls-checkbox [@formcontrolname='solicitarDevolucao']"))).doubleClick().perform();			

		driver.findElement(By.xpath("//input [@formcontrolname='quantidadeMaxima']")).sendKeys(String.format("%04d", ThreadLocalRandom.current().nextInt(10000)));		

		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("apls-tab-label-0-2")).click();

		wdw.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//apls-select [@formcontrolname='funcionario']"))));
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='area1' and (ancestor::form[contains(@class,'form-funcionario')])]")).click();		

		driver.findElement(By.xpath("//span [contains(text(),'" + unidade +"') and (@class='apls-option-text')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='area2' and (ancestor::form[contains(@class,'form-funcionario')])]")).click();		

		driver.findElement(By.xpath("//span [contains(text(),'AreaTeste') and (@class='apls-option-text')]")).click();

		driver.findElement(By.xpath("//input [@formcontrolname='dataInicio']")).sendKeys("01032019");
		driver.findElement(By.xpath("//input [@formcontrolname='dataFim']")).sendKeys("01012030");

		driver.findElement(By.xpath("//apls-select [@formcontrolname='funcionario']")).click();
		driver.findElement(By.xpath("//input [@placeholder='Digite 3 letras ou a matrícula']")).sendKeys("Epy");

		driver.findElement(By.xpath("//span [contains(text(),'Master Epy')]")).click();

		driver.findElement(By.xpath("//input [@formcontrolname='justificativa']")).sendKeys(this.horizon.generateString(500));

		driver.findElement(By.xpath("//apls-checkbox [@formcontrolname='solicitarDevolucao' and (ancestor::form[contains(@class,'form-funcionario')])]")).click();		

		driver.findElement(By.xpath("//input [@formcontrolname='quantidadeMaxima' and (ancestor::form[contains(@class,'form-funcionario')])]")).sendKeys(String.format("%04d", ThreadLocalRandom.current().nextInt(10000)));		

		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar' and (ancestor::form[contains(@class,'form-funcionario')])]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("apls-tab-label-0-3")).click();

		wdw.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//apls-select [@formcontrolname='cargo']"))));
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='cargo']")).click();
		driver.findElement(By.xpath("//input [@placeholder='Digite 4 caracteres']")).sendKeys("zzzz");

		driver.findElement(By.xpath("//span [contains(text(),'zzzz')]")).click();

		driver.findElement(By.xpath("//apls-checkbox [@formcontrolname='solicitarDevolucao' and (ancestor::form[contains(@class,'form-cargo')])]")).click();

		driver.findElement(By.xpath("//input [@formcontrolname='quantidadeMaxima' and (ancestor::form[contains(@class,'form-cargo')])]")).sendKeys(String.format("%04d", ThreadLocalRandom.current().nextInt(10000)));

		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar' and (ancestor::form[contains(@class,'form-cargo')])]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("apls-tab-label-0-4")).click();

		wdw.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//apls-select [@formcontrolname='atividadeCritica']"))));
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='atividadeCritica']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span [contains(text(),'Hipertensão')]"))));
		this.horizon.waitLoad();

		lista = driver.findElements(By.xpath("//apls-pseudo-checkbox [@class='apls-option-pseudo-checkbox apls-pseudo-checkbox ng-star-inserted']"));

		for(int i = 0; i <ThreadLocalRandom.current().nextInt(lista.size(),lista.size()+50);i++) {
			lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();
		}

		driver.findElement(By.xpath("//div [@class='apls-confirm']")).click();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='area1' and (ancestor::form[contains(@class,'form-atividade')])]")).click();

		driver.findElement(By.xpath("//span [contains(text(),'" + unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//div [@class='apls-confirm']")).click();

		driver.findElement(By.xpath("//apls-checkbox [@formcontrolname='solicitarDevolucao' and (ancestor::form[contains(@class,'form-atividade')])]")).click();

		driver.findElement(By.xpath("//input [@formcontrolname='quantidadeMaxima' and (ancestor::form[contains(@class,'form-atividade')])]")).sendKeys(String.format("%04d", ThreadLocalRandom.current().nextInt(10000)));

		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar' and (ancestor::form[contains(@class,'form-atividade')])]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("apls-tab-label-0-5")).click();

		wdw.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input [@formcontrolname='descricao']"))));
		this.horizon.waitLoad();

		lista = driver.findElements(By.xpath("//apls-pseudo-checkbox [@class='apls-pseudo-checkbox']"));

		for(int i = 0; i <ThreadLocalRandom.current().nextInt(lista.size(),lista.size()+50);i++) {
			lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();
		}

		driver.findElement(By.xpath("//button [@class='btn-salvar btn apls-button']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [contains(text(),'Voltar')]")).click();

		System.out.println("Equipamento " + epi + " " + horizon.time(starttime));
		return epi;
	}

	public void editar() {
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn-filtro btn apls-button']"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@formcontrolname='descricao']")).sendKeys(epi);	
		driver.findElement(By.xpath("//apls-select [@formcontrolname='familia']")).click();
		driver.findElement(By.xpath("//span [contains(text(),'" + fam +"')]")).click();	

		driver.findElement(By.xpath("//button [@class='btn-filtro btn apls-button']")).click();

		this.horizon.waitLoad();

		act.moveToElement(driver.findElement(By.xpath("//td [contains(text(),'"+ epi +"')]"))).doubleClick().perform();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//textarea [@formcontrolname='descricao']"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		epi =this.horizon.generateString(499);

		driver.findElement(By.xpath("//textarea [@formcontrolname='descricao']")).sendKeys(epi);

		driver.findElement(By.xpath("//apls-select [@formcontrolname='categoria']")).click();
		int n = ThreadLocalRandom.current().nextInt(1,11);
		driver.findElement(By.xpath("//apls-option [@chave='"+ String.valueOf(n) +"']")).click();		
		driver.findElement(By.xpath("//input [@formcontrolname='fpa']")).sendKeys(this.horizon.generateString(25));

		if (n == 7) {
			driver.findElement(By.xpath("//input [@formcontrolname='atenuacaoDBA']")).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(1,10)) + String.format("%09d", ThreadLocalRandom.current().nextInt(1000000000)));
		}

		List<WebElement> lista = driver.findElements(By.xpath("//div [@class='apls-checkbox-inner-container']"));
		for(int i = 0; i<lista.size();i++) {
			lista.get(i).click();
		}

		driver.findElement(By.id("apls-tab-label-1-1")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//apls-select [@formcontrolname='area1']"))));
		this.horizon.waitLoad();

		act.moveToElement(driver.findElement(By.xpath("//span [contains(text(),'GHETeste')]"))).perform();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//i [@aplstooltip='Excluir']"))));
		driver.findElement(By.xpath("//i [@aplstooltip='Excluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='area1']")).click();

		driver.findElement(By.xpath("//span [contains(text(),'" + unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='area2']")).click();

		driver.findElement(By.xpath("//span [contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='ghe']")).click();

		driver.findElement(By.xpath("//span [contains(text(),'GHETeste')]")).click();

		act.moveToElement(driver.findElement(By.xpath("//apls-checkbox [@formcontrolname='solicitarDevolucao']"))).doubleClick().perform();	
		driver.findElement(By.xpath("//input [@formcontrolname='quantidadeMaxima']")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(100)));

		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar']")).click();

		this.horizon.waitLoad();

		act.moveToElement(driver.findElement(By.xpath("//span [contains(text(),'GHETeste')]"))).perform();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//i [@aplstooltip='Excluir']"))));
		driver.findElement(By.xpath("//i [@aplstooltip='Editar']")).click();

		driver.findElement(By.xpath("//input [@formcontrolname='quantidadeMaxima']")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(100)));

		driver.findElement(By.xpath("//button [@aplstooltip='Salvar']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("apls-tab-label-1-2")).click();

		wdw.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//apls-select [@formcontrolname='funcionario']"))));
		this.horizon.waitLoad();

		act.moveToElement(driver.findElement(By.xpath("//td [contains(text(),'Master Epy')]"))).perform();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//i [@aplstooltip='Excluir']"))));
		driver.findElement(By.xpath("//i [@aplstooltip='Excluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='area1' and (ancestor::form[contains(@class,'form-funcionario')])]")).click();

		driver.findElement(By.xpath("//span [contains(text(),'" + unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='area2' and (ancestor::form[contains(@class,'form-funcionario')])]")).click();		

		driver.findElement(By.xpath("//span [contains(text(),'AreaTeste')]")).click();

		driver.findElement(By.xpath("//input [@formcontrolname='dataInicio']")).sendKeys("01032019");
		driver.findElement(By.xpath("//input [@formcontrolname='dataFim']")).sendKeys("01012030");

		driver.findElement(By.xpath("//apls-select [@formcontrolname='funcionario']")).click();
		driver.findElement(By.xpath("//input [@placeholder='Digite 3 letras ou a matrícula']")).sendKeys("Epy");

		driver.findElement(By.xpath("//span [contains(text(),'Master Epy')]")).click();

		driver.findElement(By.xpath("//input [@formcontrolname='justificativa']")).sendKeys(this.horizon.generateString(250));

		driver.findElement(By.xpath("//apls-checkbox [@formcontrolname='solicitarDevolucao' and (ancestor::form[contains(@class,'form-funcionario')])]")).click();		

		driver.findElement(By.xpath("//input [@formcontrolname='quantidadeMaxima' and (ancestor::form[contains(@class,'form-funcionario')])]")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(100)));

		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar' and (ancestor::form[contains(@class,'form-funcionario')])]")).click();

		this.horizon.waitLoad();

		act.moveToElement(driver.findElement(By.xpath("//td [contains(text(),'Master Epy')]"))).perform();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//i [@aplstooltip='Excluir']"))));
		driver.findElement(By.xpath("//i [@aplstooltip='Editar']")).click();

		driver.findElement(By.xpath("//input [@formcontrolname='justificativa']")).sendKeys(this.horizon.generateString(250));
		driver.findElement(By.xpath("//input [@formcontrolname='quantidadeMaxima' and (ancestor::form[contains(@class,'form-funcionario')])]")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(100)));				

		driver.findElement(By.xpath("//button [@aplstooltip='Salvar']")).click();

		driver.findElement(By.id("apls-tab-label-1-3")).click();

		wdw.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//apls-select [@formcontrolname='cargo']"))));
		this.horizon.waitLoad();

		act.moveToElement(driver.findElement(By.xpath("//td [contains(text(),'zzzz')]"))).perform();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//i [@aplstooltip='Excluir']"))));
		driver.findElement(By.xpath("//i [@aplstooltip='Excluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='cargo']")).click();
		driver.findElement(By.xpath("//input [@placeholder='Digite 4 caracteres']")).sendKeys("zzzz");

		driver.findElement(By.xpath("//span [contains(text(),'zzzz')]")).click();

		driver.findElement(By.xpath("//apls-checkbox [@formcontrolname='solicitarDevolucao' and (ancestor::form[contains(@class,'form-cargo')])]")).click();

		driver.findElement(By.xpath("//input [@formcontrolname='quantidadeMaxima' and (ancestor::form[contains(@class,'form-cargo')])]")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(100)));

		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar' and (ancestor::form[contains(@class,'form-cargo')])]")).click();

		this.horizon.waitLoad();

		act.moveToElement(driver.findElement(By.xpath("//td [contains(text(),'zzzz')]"))).perform();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//i [@aplstooltip='Excluir']"))));
		driver.findElement(By.xpath("//i [@aplstooltip='Editar']")).click();

		driver.findElement(By.xpath("//input [@formcontrolname='quantidadeMaxima' and (ancestor::form[contains(@class,'form-cargo')])]")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(100)));		

		driver.findElement(By.xpath("//button [@aplstooltip='Salvar']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("apls-tab-label-1-5")).click();

		wdw.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input [@formcontrolname='descricao']"))));
		this.horizon.waitLoad();

		lista = driver.findElements(By.xpath("//apls-pseudo-checkbox [contains(@class,'apls-pseudo-checkbox')]"));

		for(int i = 0; i <ThreadLocalRandom.current().nextInt(lista.size(),lista.size()+50);i++) {
			lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();
		}

		driver.findElement(By.xpath("//button [@class='btn-salvar btn apls-button']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [contains(text(),'Voltar')]")).click();

		System.out.println("Equipamento+ " + epi + " " + horizon.time(starttime));
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/modulos/#/cadastro/equipamentos/epi");

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn-filtro btn apls-button']"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@formcontrolname='descricao']")).sendKeys(epi);	
		driver.findElement(By.xpath("//apls-select [@formcontrolname='familia']")).click();
		driver.findElement(By.xpath("//span [contains(text(),'" + fam +"')]")).click();	

		driver.findElement(By.xpath("//button [@class='btn-filtro btn apls-button']")).click();

		this.horizon.waitLoad();

		act.moveToElement(driver.findElement(By.xpath("//td [contains(text(),'"+ epi +"')]"))).perform();
		driver.findElement(By.xpath("//i [@aplstooltip='Excluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		this.horizon.waitLoad();		

		familia.excluir();		
	}
}