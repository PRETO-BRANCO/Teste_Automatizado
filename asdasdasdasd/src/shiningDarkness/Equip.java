package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Equip {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;	
	private String ed;	
	private String unidade;
	private Classe classe;
	private String clas;
	private String epi;
	private long starttime;

	public Equip(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime, String ed, String unidade) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;			
		this.ed = ed;
		this.unidade = unidade;
		this.starttime = starttime;		
		this.classe = new Classe(driver, wdw, horizon, starttime, ed);
		this.clas = classe.criar();		
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/equipamento/epis.html");

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-adicionar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		epi = String.format("%010d", ThreadLocalRandom.current().nextLong(10000000000l));

		driver.findElement(By.id("codigo")).sendKeys(epi);
		driver.findElement(By.id("codigoCA")).sendKeys(this.horizon.generateString2(8));
		driver.findElement(By.id("validadeCA")).sendKeys("31122030");
		driver.findElement(By.xpath("//input [@data-ng-model='epi.validadeAposEntrega']")).sendKeys(String.format("%04d", ThreadLocalRandom.current().nextInt(10000)));
		driver.findElement(By.id("acUnidade")).sendKeys("unidade");
		driver.findElement(By.xpath("//li [contains(text(),'Unidade') and contains(@class,'ui-menu-item')]")).click();
		driver.findElement(By.id("acClasse")).sendKeys(clas);
		driver.findElement(By.xpath("//li [contains(text(),'"+ clas +"') and contains(@class,'ui-menu-item')]")).click();

		this.horizon.waitLoad2();

		if(driver.findElement(By.id("lb-fator-protecao")).isDisplayed()) {
			driver.findElement(By.xpath("//input [@ng-model='epi.fatorProtecao']")).sendKeys(String.format("%04d", ThreadLocalRandom.current().nextInt(10000)));
		}
		if(driver.findElement(By.id("lb-fator-reducao")).isDisplayed()) {
			driver.findElement(By.xpath("//input [@ng-model='epi.fatorReducao']")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(100)));
		}

		List<WebElement> lista = driver.findElements(By.xpath("//textarea [contains(@id,'desc')]"));
		for(int i = 0; i<lista.size();i++) {
			lista.get(i).sendKeys(this.horizon.generateString(100));
		}

		driver.findElement(By.id("li-catalogos")).click();

		lista = driver.findElements(By.xpath("//select [contains(@id,'cb-')]"));

		for(int i = 0; i < lista.size(); i++) {
			Select slek = new Select(lista.get(i));
			List<WebElement> op = slek.getOptions();
			slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,op.size()));
		}		

		driver.findElement(By.id("li-areas")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("acArea"))));

		driver.findElement(By.id("acArea")).sendKeys(unidade);
		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"') and contains(@class,'ui-menu-item')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='adicionarArea()']")).click();

		driver.findElement(By.id("li-fornecedor")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-fornecedor"))));

		driver.findElement(By.id("ac-fornecedor")).sendKeys("Almoxarifado");	
		driver.findElement(By.xpath("//li [contains(text(),'Almoxarifado') and contains(@class,'ui-menu-item')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='adicionarFornecedor()']")).click();

		driver.findElement(By.id("bt-salvar")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();

		System.out.println("Equipamento " + epi + "							" + horizon.time(starttime));
		return epi;
	}

	public void editar() {
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));

		driver.findElement(By.id("iCodigo")).sendKeys(epi);

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ epi +"')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='editarEpi()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("codigo"))));
		this.horizon.sleep(500);
		this.horizon.waitLoad2();	

		driver.findElement(By.id("codigo")).sendKeys(String.format("%010d", ThreadLocalRandom.current().nextLong(10000000000l)));

		List<WebElement> lista = driver.findElements(By.xpath("//textarea [contains(@id,'desc')]"));
		for(int i = 0; i<lista.size();i++) {
			lista.get(i).sendKeys(this.horizon.generateString(100));
		}

		driver.findElement(By.id("li-catalogos")).click();

		lista = driver.findElements(By.xpath("//select [contains(@id,'cb-')]"));
		for(int i = 0; i< lista.size();i++) {		
			Select slek = new Select(lista.get(i));
			List<WebElement> op = slek.getOptions();
			slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,op.size()));	
		}

		driver.findElement(By.id("li-fornecedor")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-fornecedor"))));

		//driver.findElement(By.xpath("//i [@ng-click='excluirFornecedor(forn)']")).click();

		driver.findElement(By.id("ac-fornecedor")).sendKeys("Estoque");	
		driver.findElement(By.xpath("//li [contains(text(),'Estoque') and contains(@class,'ui-menu-item')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='adicionarFornecedor()']")).click();

		driver.findElement(By.id("bt-salvar")).click();	
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();

		System.out.println("Equipamento+ " + epi + "							" + horizon.time(starttime));		
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/equipamento/epis.html");

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));
		this.horizon.waitLoad2();	

		driver.findElement(By.id("iCodigo")).sendKeys(epi);

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ epi +"')]")).click();	

		driver.findElement(By.xpath("//button [@ng-click='excluirEpi()']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='modalConfirmacaoExclusao()']"))));
		driver.findElement(By.xpath("//button [@ng-click='modalConfirmacaoExclusao()']")).click();

		this.horizon.waitLoad2();

		classe.excluir();

		this.horizon.waitLoad2();
	}
}