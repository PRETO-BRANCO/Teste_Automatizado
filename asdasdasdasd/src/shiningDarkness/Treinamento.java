package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Treinamento {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Actions act;
	private Long starttime;
	private String ed;
	private String treino;
	private String unidade;
	private CategoriaTreino catTreino;
	private String cat;
	private Cargo cargo;
	private String car;

	public Treinamento(WebDriver driver, WebDriverWait wdw, Signal horizon, Long starttime, String ed, String unidade) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;
		act = new Actions(driver);	
		this.starttime = starttime;
		this.ed = ed;
		this.unidade = unidade;
		this.catTreino = new CategoriaTreino(driver, wdw, horizon, starttime, ed);
		this.cat = this.catTreino.criar();
		this.cargo = new Cargo(driver, wdw, horizon, starttime, ed);
		this.car = this.cargo.criar();
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/modulos/#/ssma/treinamento");

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//i [@class='fas fa-user-graduate fa-2x card-title']"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//i [@class='fas fa-user-graduate fa-2x card-title']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("btn-novo")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@formcontrolname='descricao']"))));

		treino = this.horizon.generateString(100);

		driver.findElement(By.xpath("//input [@formcontrolname='descricao']")).sendKeys(treino);
		driver.findElement(By.id("apls-select-8")).click();
		driver.findElement(By.xpath("//span [contains(text(),'"+ unidade +"') and (@class='apls-option-text')]")).click();		
		driver.findElement(By.xpath("//div [@class='apls-confirm']")).click();
		driver.findElement(By.xpath("//apls-select [@placeholder='Digite o código ou o nome']")).click();

		List<WebElement> lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));

		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='modalidade']")).click();

		lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));

		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();

		driver.findElement(By.id("apls-select-11")).click();
		driver.findElement(By.xpath("//span [contains(text(),'"+ cat +"')]")).click();		

		driver.findElement(By.xpath("//input [@formcontrolname='notaAprovacao']")).sendKeys(String.format("%05d", ThreadLocalRandom.current().nextInt(100000)));

		driver.findElement(By.xpath("//apls-select [@formcontrolname='tipoTreinamento']")).click();

		lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));

		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();

		driver.findElement(By.xpath("//input [@formcontrolname='cargaHoraria']")).sendKeys(String.valueOf(ThreadLocalRandom.current().nextInt(10))+String.format("%05d", ThreadLocalRandom.current().nextInt(100000)));

		driver.findElement(By.xpath("//apls-select [@formcontrolname='validade']")).click();

		lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));

		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();

		driver.findElement(By.xpath("//textarea [@formcontrolname='objetivos']")).sendKeys(this.horizon.generateString(1000));

		driver.findElement(By.xpath("//input [@maxlength='250']")).sendKeys(this.horizon.generateString(250));

		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar']")).click();

		driver.findElement(By.id("apls-tab-label-0-1")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@formcontrolname='ghe']"))));

		driver.findElement(By.xpath("//apls-select [@formcontrolname='area1']")).click();
		driver.findElement(By.xpath("//span [contains(text(),'"+ unidade +"') and (@class='apls-option-text')]")).click();		

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='area2']")).click();
		driver.findElement(By.xpath("//span [contains(text(),'AreaTeste') and (@class='apls-option-text')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='ghe']")).click();

		driver.findElement(By.xpath("//span [contains(text(),'GHETeste')]")).click();	

		driver.findElement(By.xpath("//div [@class='apls-confirm']")).click();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='validade' and (ancestor::form[contains(@class,'form-ghe')])]")).click();		

		lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));

		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();

		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar' and (ancestor::form[contains(@class,'form-ghe')])]")).click();		

		driver.findElement(By.id("apls-tab-label-0-2")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@formcontrolname='cargo']"))));

		driver.findElement(By.xpath("//apls-select [@formcontrolname='cargo']")).click();

		driver.findElement(By.xpath("//input [@placeholder='Digite 4 caracteres']")).sendKeys(car);
		driver.findElement(By.xpath("//span [contains(text(),'"+ car +"')]")).click();	

		driver.findElement(By.xpath("//apls-select [@formcontrolname='validade' and (ancestor::treina-cargo[contains(@class,'dv-normalizador')])]")).click();


		lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));

		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();

		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar' and (ancestor::treina-cargo[contains(@class,'dv-normalizador')])]")).click();		

		driver.findElement(By.id("apls-tab-label-0-3")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@formcontrolname='atividadeCritica']"))));

		List<WebElement> lista2 = driver.findElements(By.xpath("//apls-pseudo-checkbox [@class='apls-pseudo-checkbox']"));
		List<WebElement> lista3 = driver.findElements(By.xpath("//apls-select [@disabled='true' and (ancestor::table[contains(@id,'atividadeCriticaTable')])]"));

		for(int i=0;i<lista2.size();i++) {
			lista2.get(i).click();

			lista3.get(i).click();

			lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));

			lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();
		}		

		driver.findElement(By.id("apls-tab-label-0-4")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@formcontrolname='funcionario']"))));

		driver.findElement(By.xpath("//apls-select [@formcontrolname='area1' and (ancestor::treina-funcionario[contains(@class,'dv-normalizador')])]")).click();

		driver.findElement(By.xpath("//span [contains(text(),'"+ unidade +"') and (@class='apls-option-text')]")).click();		

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='area2' and (ancestor::treina-funcionario[contains(@class,'dv-normalizador')])]")).click();

		driver.findElement(By.xpath("//span [contains(text(),'AreaTeste') and (@class='apls-option-text')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='funcionario']")).click();		
		driver.findElement(By.xpath("//input [@placeholder='Digite 3 letras ou a matrícula']")).sendKeys("Epy");
		driver.findElement(By.xpath("//span [contains(text(),'Master Epy') and (@class='apls-option-text')]")).click();
		driver.findElement(By.xpath("//div [@class='apls-confirm']")).click();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='validade' and (ancestor::treina-funcionario[contains(@class,'dv-normalizador')])]")).click();


		lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));

		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();

		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar' and (ancestor::treina-funcionario[contains(@class,'dv-normalizador')])]")).click();		

		driver.findElement(By.xpath("//button [contains(text(),'Salvar')]")).click();

		this.horizon.waitLoad();		

		System.out.println("Treinamento " + treino + " " + horizon.time(starttime));
		return treino;		
	}

	public void editar() {			
		driver.navigate().refresh();		

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@formcontrolname='descricao']")).sendKeys(treino);
		driver.findElement(By.xpath("//apls-select [@formcontrolname='categoria']")).click();
		driver.findElement(By.xpath("//span [contains(text(),'"+ cat +"')]")).click();	
		driver.findElement(By.xpath("//apls-select [@formcontrolname='unidade']")).click();
		driver.findElement(By.xpath("//span [contains(text(),'"+ unidade +"') and (@class='apls-option-text')]")).click();	

		driver.findElement(By.xpath("//button [@aplstooltip='Pesquisar']")).click();

		this.horizon.waitLoad();

		act.moveToElement(driver.findElement(By.xpath("//td [contains(text(),'"+ treino +"')]"))).doubleClick().perform();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@formcontrolname='descricao']"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		treino =this.horizon.generateString(100);

		driver.findElement(By.xpath("//input [@formcontrolname='descricao']")).sendKeys(treino);
		driver.findElement(By.xpath("//input [@formcontrolname='notaAprovacao']")).sendKeys(String.format("%05d", ThreadLocalRandom.current().nextInt(100000)));
		driver.findElement(By.xpath("//textarea [@formcontrolname='objetivos']")).sendKeys(this.horizon.generateString(1000));

		act.moveToElement(driver.findElement(By.xpath("//tr [@class='apls-row ng-star-inserted']"))).perform();
		driver.findElement(By.xpath("//i [@class='fas fa-trash ng-star-inserted']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		driver.findElement(By.xpath("//input [@maxlength='250']")).sendKeys(this.horizon.generateString(250));

		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar']")).click();			

		driver.findElement(By.id("apls-tab-label-0-2")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@formcontrolname='cargo']"))));

		act.moveToElement(driver.findElement(By.xpath("//td [contains(text(),'"+ car +"')]"))).perform();	
		driver.findElement(By.xpath("//i [@aplstooltip='Excluir']")).click();			
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='cargo']")).click();

		driver.findElement(By.xpath("//input [@placeholder='Digite 4 caracteres']")).sendKeys(car);
		driver.findElement(By.xpath("//span [contains(text(),'"+ car +"')]")).click();

		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar'  and (ancestor::treina-cargo[contains(@class,'dv-normalizador')])]")).click();

		driver.findElement(By.xpath("//apls-select [not(contains(@formcontrolname,'a'))  and (ancestor::treina-cargo[contains(@class,'dv-normalizador')])]")).click();

		List<WebElement> lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));

		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();

		driver.findElement(By.id("apls-tab-label-0-4")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@formcontrolname='funcionario']"))));

		act.moveToElement(driver.findElement(By.xpath("//td [contains(text(),'Master Epy')]"))).perform();
		driver.findElement(By.xpath("//i [@aplstooltip='Excluir']")).click();			
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='area1']")).click();
		driver.findElement(By.xpath("//span [contains(text(),'"+ unidade +"') and (@class='apls-option-text')]")).click();		

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='area2']")).click();
		driver.findElement(By.xpath("//span [contains(text(),'AreaTeste') and (@class='apls-option-text')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//apls-select [@formcontrolname='funcionario']")).click();		
		driver.findElement(By.xpath("//input [@placeholder='Digite 3 letras ou a matrícula']")).sendKeys("Epy");
		driver.findElement(By.xpath("//span [contains(text(),'Master Epy')]")).click();
		driver.findElement(By.xpath("//div [@class='apls-confirm']")).click();

		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar'  and (ancestor::treina-funcionario[contains(@class,'dv-normalizador')])]")).click();

		driver.findElement(By.xpath("//apls-select [not(contains(@formcontrolname,'a'))  and (ancestor::treina-funcionario[contains(@class,'dv-normalizador')])]")).click();

		lista = driver.findElements(By.xpath("//apls-option [contains(@id,'apls-option-')]"));

		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();

		driver.findElement(By.xpath("//button [contains(text(),'Salvar')]")).click();

		this.horizon.waitLoad();		

		System.out.println("Treinamento+ " + treino + " " + horizon.time(starttime));
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/modulos/#/ssma/treinamento");		

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//i [@class='fas fa-user-graduate fa-2x card-title']"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.xpath("//i [@class='fas fa-user-graduate fa-2x card-title']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//input [@formcontrolname='descricao']")).sendKeys(treino);
		driver.findElement(By.xpath("//apls-select [@formcontrolname='categoria']")).click();
		driver.findElement(By.xpath("//span [contains(text(),'"+ cat +"')]")).click();	
		driver.findElement(By.xpath("//apls-select [@formcontrolname='unidade']")).click();
		driver.findElement(By.xpath("//span [contains(text(),'"+ unidade +"') and (@class='apls-option-text')]")).click();	

		driver.findElement(By.xpath("//button [@aplstooltip='Pesquisar']")).click();

		this.horizon.waitLoad();

		act.moveToElement(driver.findElement(By.xpath("//td [contains(text(),'"+ treino +"')]"))).perform();
		driver.findElement(By.xpath("//i [@aplstooltip='Excluir']")).click();			
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@tabindex='1']"))));
		driver.findElement(By.xpath("//button [@tabindex='1']")).click();

		this.horizon.waitLoad();

		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/cargo.html");

		this.horizon.waitLoad2();

		cargo.excluir();

		this.horizon.waitLoad2();

		catTreino.excluir();

		this.horizon.waitLoad();		
	}
}