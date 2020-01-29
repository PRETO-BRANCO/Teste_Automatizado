package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AnaliseErgo {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;	
	private String ed;
	private Actions act;
	private String unidade;
	private JavascriptExecutor js;
	private QuestArg quest;
	private String quest1;
	private String quest2;
	private String quest3;
	private Cargo cargo;
	private String car;	

	public AnaliseErgo(WebDriver driver, WebDriverWait wdw,Signal horizon,String ed,String unidade,Long starttime) {		
		this.driver = driver;
		this.wdw = wdw;		
		this.horizon = horizon;		
		this.ed = ed;
		this.unidade = unidade;
		this.act = new Actions(driver);	
		js = (JavascriptExecutor) driver;
		quest = new QuestArg(driver, wdw, horizon, starttime, ed);
		quest.criar();
		quest.criar();
		quest.criar();
		quest1 = quest.getCat();
		quest = new QuestArg(driver, wdw, horizon, starttime, ed);
		quest.criar();
		quest.criar();
		quest.criar();
		quest2 = quest.getCat();
		quest = new QuestArg(driver, wdw, horizon, starttime, ed);
		quest.criar();
		quest.criar();
		quest.criar();
		quest3 = quest.getCat();
		cargo = new Cargo(driver, wdw, horizon, starttime, ed);
		car = cargo.criar();
	}

	public void criar() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/analise/");	

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//a [@ui-sref='analise']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary pull-right bt-novo']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-area1-cadastro"))));

		driver.findElement(By.id("ac-area1-cadastro")).sendKeys(unidade);

		List<WebElement> lista =  driver.findElements(By.xpath("//li [contains(@class,'ui-menu-item')]"));
		lista.get(0).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area2-cadastro")).sendKeys("a");

		this.horizon.sleep(350);

		lista =  driver.findElements(By.xpath("//li [contains(@class,'ui-menu-item')]"));	
		lista.get(1).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area3-cadastro")).sendKeys("s");

		this.horizon.sleep(350);

		lista =  driver.findElements(By.xpath("//li [contains(@class,'ui-menu-item')]"));	
		lista.get(2).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-cargo")).sendKeys(car);

		this.horizon.waitLoad();
		this.horizon.sleep(350);

		act.moveToElement(driver.findElement(By.xpath("//li [contains(text(),'" + car +"')]"))).click().perform();

		this.horizon.waitLoad();

		driver.findElement(By.id("i-ghe")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.id("i-funcao")).clear();
		driver.findElement(By.id("i-funcao")).sendKeys(this.horizon.generateString(25));
		driver.findElement(By.id("i-jornada-revezamento")).clear();
		driver.findElement(By.id("i-jornada-revezamento")).sendKeys(this.horizon.generateString(30));
		driver.findElement(By.id("i-situacao-posto")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("ta-atividades")).sendKeys(this.horizon.generateString(5000));

		driver.findElement(By.id("li-checklist")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary lb-salvar']"))));

		lista = driver.findElements(By.xpath("//input [@ng-model='categoria.itemSelecionado']"));

		for(int i=0; i<lista.size(); i++) {
			act.moveToElement(lista.get(i)).click().perform();
		}

		act.moveToElement(driver.findElement(By.xpath("//span [contains(text(),'" + quest1 +"')]"))).click().perform();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary lb-salvar']")).click();

		this.horizon.waitLoad();

		lista = driver.findElements(By.xpath("//select [contains(@id,'cb-variavel-')]"));

		for(int i = 0;i<lista.size();i++) {
			Select slek = new Select(lista.get(i));
			List<WebElement> op = slek.getOptions();
			slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,op.size()));
			if(i==2) {
				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			}
		}

		js.executeScript("window.scrollTo(0, 0)");
		driver.findElement(By.id("bt-adicionar-categoria")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary lb-salvar']"))));
		this.horizon.sleep(350);

		lista = driver.findElements(By.xpath("//input [@ng-model='categoria.itemSelecionado']"));

		for(int i=0; i<lista.size(); i++) {
			act.moveToElement(lista.get(i)).click().perform();
		}

		act.moveToElement(driver.findElement(By.xpath("//span [contains(text(),'" + quest2 +"')]"))).click().perform();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary lb-salvar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span [contains(text(),'" + quest2 +"')]"))));
		this.horizon.sleep(350);

		driver.findElement(By.xpath("//li [@class='ng-scope']")).click();

		this.horizon.waitLoad();
		this.horizon.sleep(350);

		lista = driver.findElements(By.xpath("//select [contains(@id,'cb-variavel-')]"));		
		for(int i = 0;i<lista.size();i++) {
			Select slek = new Select(lista.get(i));
			List<WebElement> op = slek.getOptions();
			slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,op.size()));
			if(i==2) {
				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			}
		}

		driver.findElement(By.id("li-recomendacoes")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li [@ng-click='onSelecionaRecomendacao(categoriaQuestao)']"))));

		lista = driver.findElements(By.xpath("//li [@class='ng-scope']"));
		lista.get(1).click();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary pull-right lb-rascunho']")).click();

		this.horizon.waitLoad();
	}

	public void editar() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/analise/");	

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//a [@ui-sref='analise']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area1-filtro")).sendKeys(unidade);

		List<WebElement> lista =  driver.findElements(By.xpath("//li [contains(@class,'ui-menu-item')]"));
		lista.get(0).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@class='btn btn-primary tooltip-top bt-pesquisar']")).click();

		this.horizon.waitLoad();
		this.horizon.sleep(350);

		lista = driver.findElements(By.xpath("//tr [contains(@class,'ng-scope')]"));
		act.moveToElement(lista.get(0)).doubleClick().perform();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-area1-cadastro"))));
		this.horizon.sleep(500);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-ghe")).sendKeys(this.horizon.generateString(20));		
		driver.findElement(By.id("i-funcao")).sendKeys(this.horizon.generateString(25));	
		driver.findElement(By.id("i-jornada-revezamento")).sendKeys(this.horizon.generateString(30));
		driver.findElement(By.id("i-situacao-posto")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("ta-atividades")).sendKeys(this.horizon.generateString(5000));

		driver.findElement(By.id("li-checklist")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li [@class='ng-scope']"))));

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnExcluirCategoria()']")).click();
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-excluir-sim-analise"))));
		this.driver.findElement(By.id("bt-excluir-sim-analise")).click();	

		this.horizon.waitLoad();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
			wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));
		}
		catch(Exception e){			
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		js.executeScript("window.scrollTo(0, 0)");
		driver.findElement(By.id("bt-adicionar-categoria")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary lb-salvar']"))));
		this.horizon.sleep(350);

		lista = driver.findElements(By.xpath("//input [@ng-model='categoria.itemSelecionado']"));

		for(int i=0; i<lista.size(); i++) {
			act.moveToElement(lista.get(i)).click().perform();
		}

		act.moveToElement(driver.findElement(By.xpath("//span [contains(text(),'" + quest3 +"')]"))).click().perform();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary lb-salvar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span [contains(text(),'" + quest3 +"')]"))));
		this.horizon.sleep(350);

		driver.findElement(By.xpath("//li [@class='ng-scope']")).click();

		this.horizon.waitLoad();
		this.horizon.sleep(350);

		lista = driver.findElements(By.xpath("//select [contains(@id,'cb-variavel-')]"));		
		for(int i = 0;i<lista.size();i++) {
			Select slek = new Select(lista.get(i));
			List<WebElement> op = slek.getOptions();
			slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,op.size()));
			if(i==2) {
				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			}
		}

		driver.findElement(By.id("li-recomendacoes")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li [@ng-click='onSelecionaRecomendacao(categoriaQuestao)']"))));

		lista = driver.findElements(By.xpath("//li [@class='ng-scope']"));
		lista.get(1).click();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary pull-right lb-salvar']")).click();

		this.horizon.waitLoad();
	}

	public void excluir() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/analise/");	

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//a [@ui-sref='analise']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area1-filtro")).sendKeys(unidade);

		List<WebElement> lista =  driver.findElements(By.xpath("//li [contains(@class,'ui-menu-item')]"));
		lista.get(0).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@class='btn btn-primary tooltip-top bt-pesquisar']")).click();

		this.horizon.waitLoad();
		this.horizon.sleep(350);

		lista = driver.findElements(By.xpath("//tr [contains(@class,'ng-scope')]"));
		act.moveToElement(lista.get(0)).click().perform();

		this.driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']")).click();
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-excluir-sim-analise"))));
		this.driver.findElement(By.id("bt-excluir-sim-analise")).click();		

		this.horizon.waitLoad();

		for(int i = 0;i<9;i++) {
			quest.excluir();

			this.horizon.waitLoad();			
		}
		for(int i = 0;i<3;i++) {
			quest.excat();

			this.horizon.waitLoad();			
		}

		driver.get("https://desenvolvimento.apollusehs.com.br/apollus/views/cadastro/geral/cargo.html");

		this.horizon.waitLoad2();

		cargo.excluir();
	}
}
