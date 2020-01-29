package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BancoQuimico {
	private WebDriver driver;
	private WebDriverWait wdw;
	private Signal horizon;
	private String ed;
	private String unidade;
	private String produto;
	private long starttime;

	public BancoQuimico(WebDriver driver, WebDriverWait wdw, Signal horizon, Long starttime, String ed, String unidade) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.ed = ed;
		this.unidade = unidade;
		this.starttime = starttime;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/produtoquimico/bancodadosquimicos.html");

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@ng-click='adicionarProduto()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		produto = this.horizon.generateString(25);

		driver.findElement(By.id("i-codigo")).sendKeys(String.format("%06d", ThreadLocalRandom.current().nextInt(1000000)));
		driver.findElement(By.id("i-nome")).sendKeys(produto);
		driver.findElement(By.id("cb-perigoso")).click();
		driver.findElement(By.id("i-onu")).sendKeys(String.format("%06d", ThreadLocalRandom.current().nextInt(1000000)));
		Select slek = new Select(driver.findElement(By.id("cb-tipo")));
		List<WebElement> lista = slek.getOptions();
		int op = ThreadLocalRandom.current().nextInt(1,lista.size());
		slek.selectByIndex(op);
		if(op == 1) {
			driver.findElement(By.id("i-cas")).sendKeys(String.format("%011d", ThreadLocalRandom.current().nextLong(100000000000l)));
			driver.findElement(By.id("bt-add-cas")).click();		
		}
		else {
			driver.findElement(By.id("i-cas")).sendKeys(String.format("%05d", ThreadLocalRandom.current().nextLong(100000)));
		}
		driver.findElement(By.id("ta-sinominos")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("ta-observacao")).sendKeys(this.horizon.generateString(200));

		driver.findElement(By.id("li-passo2")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("t-categoria1"))));		

		for(int i =1; i <8;i++) {
			driver.findElement(By.id("t-categoria"+ i)).click();

			driver.findElement(By.xpath("//a[contains(text(),'"+ unidade +"')]//i[@class='jstree-icon jstree-checkbox']")).click();
		}
		driver.findElement(By.id("i-terceiros")).sendKeys(this.horizon.generateString(40));
		driver.findElement(By.id("btn-terceiros")).click();

		driver.findElement(By.id("li-passo3")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("cb-residuo"))));	

		driver.findElement(By.id("cb-cancerigeno")).click();

		slek = new Select(driver.findElement(By.id("s-cancerigeno")));
		lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));

		slek = new Select(driver.findElement(By.xpath("//select [@ng-model='classeSelecionada']")));
		lista = slek.getOptions();
		op = ThreadLocalRandom.current().nextInt(1,lista.size());
		slek.selectByIndex(op);

		if(op <7) {
			slek = new Select(driver.findElement(By.id("s-grupo")));
			lista = slek.getOptions();
			slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		}

		slek = new Select(driver.findElement(By.id("cb-residuo")));
		lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));

		lista = driver.findElements(By.xpath("//input [@type='checkbox']"));

		for(int i = 2; i<lista.size();i++) {
			lista.get(i).click();
		}

		driver.findElement(By.id("i-controleDemaisListasDescritivo")).sendKeys(this.horizon.generateString(100));

		driver.findElement(By.id("bt-salvar")).click();

		this.horizon.waitLoad2();

		System.out.println("Produto " + produto + "					" + horizon.time(starttime));
		return produto;
	}

	public void editar() {
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@data-tooltip='Pesquisar']"))));

		driver.findElement(By.id("i-nome-filtro")).sendKeys(produto);

		driver.findElement(By.id("ac-unidade-filtro")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"')  and contains(@class,'ui-menu-item')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-area-filtro")).sendKeys("AreaTeste");

		driver.findElement(By.xpath("//li [contains(text(),'AreaTeste')  and contains(@class,'ui-menu-item')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@data-tooltip='Pesquisar']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ produto +"')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='editarProduto()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		produto = this.horizon.generateString(25);

		driver.findElement(By.id("i-codigo")).sendKeys(String.format("%06d", ThreadLocalRandom.current().nextInt(1000000)));
		driver.findElement(By.id("i-nome")).sendKeys(produto);
		driver.findElement(By.id("cb-perigoso")).click();
		Select slek = new Select(driver.findElement(By.id("cb-tipo")));

		if(slek.getFirstSelectedOption().getText().equals("Mistura")) {
			driver.findElement(By.xpath("//a [@data-tooltip='Excluir']")).click();

			driver.findElement(By.id("i-cas")).sendKeys(String.format("%011d", ThreadLocalRandom.current().nextLong(100000000000l)));
			driver.findElement(By.id("bt-add-cas")).click();
			driver.findElement(By.id("i-cas")).sendKeys(String.format("%011d", ThreadLocalRandom.current().nextLong(100000000000l)));
			driver.findElement(By.id("bt-add-cas")).click();
		}
		else {
			driver.findElement(By.id("i-cas")).sendKeys(String.format("%06d", ThreadLocalRandom.current().nextLong(1000000)));
		}

		driver.findElement(By.id("ta-sinominos")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("ta-observacao")).sendKeys(this.horizon.generateString(200));

		driver.findElement(By.id("li-passo2")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("t-categoria1"))));	

		for(int i =1; i <8;i++) {
			driver.findElement(By.id("t-categoria"+ i)).click();

			List<WebElement> lista = driver.findElements(By.xpath("//li [contains(@class,'jstree-node')]//ul[@class='jstree-children']//li[contains(@class,'jstree-node')]//ul[@class='jstree-children']//li[contains(@class,'jstree-node')]//a[contains(@class,'jstree-anchor')]//i[@class='jstree-icon jstree-checkbox']")); 
			for(int j = 0;j<75;j++) {
				lista.get(ThreadLocalRandom.current().nextInt(0,lista.size())).click();
			}
		}

		driver.findElement(By.id("li-passo3")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("cb-residuo"))));	

		driver.findElement(By.id("cb-cancerigeno")).click();

		slek = new Select(driver.findElement(By.xpath("//select [@ng-model='classeSelecionada']")));
		List<WebElement> lista = slek.getOptions();
		int op = ThreadLocalRandom.current().nextInt(1,lista.size());
		slek.selectByIndex(op);

		if(op <7) {
			slek = new Select(driver.findElement(By.id("s-grupo")));
			lista = slek.getOptions();
			slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		}

		slek = new Select(driver.findElement(By.id("cb-residuo")));
		lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));

		lista = driver.findElements(By.xpath("//input [@type='checkbox']"));

		for(int i = 2; i<lista.size();i++) {
			lista.get(i).click();
		}

		driver.findElement(By.id("bt-salvar")).click();

		this.horizon.waitLoad2();

		System.out.println("Produto+ " + produto + "					" + horizon.time(starttime));		
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/produtoquimico/bancodadosquimicos.html");

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@data-tooltip='Pesquisar']"))));
		this.horizon.waitLoad2();

		driver.findElement(By.id("i-nome-filtro")).sendKeys(produto);

		driver.findElement(By.id("ac-unidade-filtro")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"')  and contains(@class,'ui-menu-item')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-area-filtro")).sendKeys("AreaTeste");

		driver.findElement(By.xpath("//li [contains(text(),'AreaTeste')  and contains(@class,'ui-menu-item')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@data-tooltip='Pesquisar']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ produto +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir ng-scope']"))));			
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir ng-scope']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']"))));		
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']")).click();
	}
}