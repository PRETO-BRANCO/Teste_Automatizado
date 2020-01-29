package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Apreciacao {
	private WebDriver driver;
	private Signal horizon;
	private WebDriverWait wdw;	
	private ProfGeral profGeral;
	private Perigos perigos;
	private String ed;
	private String unidade;
	private String perigo;
	private String prof;

	public Apreciacao(WebDriver driver, WebDriverWait wdw, Signal horizon,long starttime,String ed, String unidade) {
		this.driver = driver;
		this.horizon = horizon;
		this.wdw = wdw; 		
		this.ed = ed;
		this.unidade = unidade;
		this.profGeral = new ProfGeral(driver,wdw,horizon, starttime, ed);
		prof = profGeral.criar();
		this.perigos = new Perigos(driver,wdw,horizon, starttime, ed);
		perigo = perigos.criar();
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

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a [@onclick=\"onItemMenuClick('apreciacao')\"]"))));
		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@onclick=\"onItemMenuClick('apreciacao')\"]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-responsavel-analise"))));
		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-responsavel-analise")).clear();
		driver.findElement(By.id("ac-responsavel-analise")).sendKeys(prof);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ prof +"')]")).click();	

		this.horizon.waitLoad2();

		driver.findElement(By.id("i-equipe")).sendKeys(this.horizon.generateString(40));
		driver.findElement(By.xpath("//button [@class='btn btn-principal ng-scope']")).click();
		driver.findElement(By.id("i-art")).sendKeys(this.horizon.generateString(14));
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default legislacao']")).click();

		List<WebElement> lista = driver.findElements(By.xpath("//label [@class='checkbox']"));
		for(int i = 0; i<lista.size();i++) {
			lista.get(i).click();
		}
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default legislacao']")).click();
		Select slek = new Select(driver.findElement(By.id("cb-fases-utilizacao")));
		lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		driver.findElement(By.id("ta-sistemas-existentes")).sendKeys(this.horizon.generateString(399));
		driver.findElement(By.id("ta-medidas-controle")).sendKeys(this.horizon.generateString(399));
		driver.findElement(By.id("ta-uso-correto-in")).sendKeys(this.horizon.generateString(399));
		driver.findElement(By.id("ta-limites-maquina")).sendKeys(this.horizon.generateString(399));
		driver.findElement(By.id("tab-checklist")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("lb-dica"))));
		this.horizon.waitLoad2();

		lista = driver.findElements(By.xpath("//input [@value='S']"));
		List<WebElement> lista2 = driver.findElements(By.xpath("//input [@value='N']"));
		List<WebElement> lista3 = driver.findElements(By.xpath("//input [@value='NA']"));

		for(int i = 1;i<=lista.size();i++) {
			if(i%3 == 0) {
				lista3.get(i-1).click();
			} 
			else if(i%2 == 0) {
				lista2.get(i-1).click();

				wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ta-item-justificativa"))));
				this.horizon.waitLoad2();

				driver.findElement(By.id("ta-item-justificativa")).clear();
				driver.findElement(By.id("ta-item-justificativa")).sendKeys(this.horizon.generateString(400));
				driver.findElement(By.id("bt-salvar-justificativa")).click();

				this.horizon.waitLoad2();
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				try{
					wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));
				}
				catch(Exception e){			
				}
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			}
			else {
				lista.get(i-1).click();
			}
		}
		driver.findElement(By.id("tab-identificacao")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ta-descricao-perigo"))));
		this.horizon.waitLoad2();

		driver.findElement(By.id("ta-descricao-perigo")).sendKeys(this.horizon.generateString(300));
		slek = new Select(driver.findElement(By.id("cb-categoria-perigo")));
		slek.selectByVisibleText(perigo);

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default fonte-perigo']")).click();

		driver.findElement(By.xpath("//label [@title='"+ perigos.getFonte() +"']")).click();

		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default fonte-perigo']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default danos-perigo']")).click();

		driver.findElement(By.xpath("//label [@title='"+ perigos.getDano() +"']")).click();

		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default danos-perigo']")).click();

		driver.findElement(By.id("i-requisito-legal")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//button [@ng-click='onClickBtnAdicionarPerigo()']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@data-tooltip='Avaliação de Riscos']")).click();

		this.horizon.waitLoad2();

		lista = driver.findElements(By.xpath("//select [@class='form-control input-sm']"));
		for(int i=0; i<lista.size();i++) {
			slek = new Select(lista.get(i));
			List<WebElement> op = slek.getOptions();
			try{
				if(op.size()>1)	slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,op.size()));
			}catch(Exception e) {				
			}
		}
		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSalvarAvaliacao()']")).click();

		this.horizon.waitLoad2();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
			wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));
		}
		catch(Exception e){			
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(By.id("bt-salvar")).click();		

		this.horizon.waitLoad2();
	}

	public void visualizar() {
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

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a [@onclick=\"onItemMenuClick('dashboard')\"]"))));
		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@onclick=\"onItemMenuClick('dashboard')\"]")).click();

		this.horizon.sleep(3000);
	}

	public void statusQuo() {
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

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a [@onclick=\"onItemMenuClick('apreciacao')\"]"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@onclick=\"onItemMenuClick('apreciacao')\"]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-responsavel-analise"))));
		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-responsavel-analise")).clear();
		driver.findElement(By.id("ac-responsavel-analise")).sendKeys("jana");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'JANA ASASA')]")).click();

		this.horizon.waitLoad2();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a [@data-tooltip='Excluir']"))));
		driver.findElement(By.xpath("//a [@data-tooltip='Excluir']")).click();
		driver.findElement(By.id("i-art")).clear();
		driver.findElement(By.id("i-art")).sendKeys("0");
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default legislacao']")).click();

		List<WebElement> lista = driver.findElements(By.xpath("//label [@class='checkbox']"));
		for(int i = 0; i<lista.size();i++) {
			lista.get(i).click();
		}
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default legislacao']")).click();
		Select slek = new Select(driver.findElement(By.id("cb-fases-utilizacao")));
		lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		driver.findElement(By.id("ta-sistemas-existentes")).clear();
		driver.findElement(By.id("ta-sistemas-existentes")).sendKeys(this.horizon.generateString(1));
		driver.findElement(By.id("ta-medidas-controle")).clear();
		driver.findElement(By.id("ta-medidas-controle")).sendKeys(this.horizon.generateString(1));
		driver.findElement(By.id("ta-uso-correto-in")).clear();
		driver.findElement(By.id("ta-uso-correto-in")).sendKeys(this.horizon.generateString(1));
		driver.findElement(By.id("ta-limites-maquina")).clear();
		driver.findElement(By.id("ta-limites-maquina")).sendKeys(this.horizon.generateString(1));
		driver.findElement(By.id("tab-checklist")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("lb-dica"))));
		this.horizon.waitLoad2();

		lista = driver.findElements(By.xpath("//input [@value='NA']"));
		for(int i = 1;i<=lista.size();i++) {
			lista.get(i-1).click();			
		}

		driver.findElement(By.id("tab-identificacao")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ta-descricao-perigo"))));
		this.horizon.waitLoad2();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a [@class='btn btn-xs btn-table tooltip-pq tooltip-right tooltip-btn-excluir ng-scope']"))));
		driver.findElement(By.xpath("//a [@class='btn btn-xs btn-table tooltip-pq tooltip-right tooltip-btn-excluir ng-scope']")).click();
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='onClickBtnConfirmarExclusaoPerigo()']"))));
		driver.findElement(By.xpath("//button [@ng-click='onClickBtnConfirmarExclusaoPerigo()']")).click();

		this.horizon.waitLoad2();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
			wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));
		}
		catch(Exception e){			
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("bt-salvar")).click();

		this.horizon.waitLoad2();

		this.profGeral.excluir();

		this.horizon.waitLoad2();

		this.perigos.excluir();
	}
}