package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Transportador {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String unidade;
	private String transporte;

	public Transportador(WebDriver driver, WebDriverWait wdw,Signal horizon,Long starttime,String ed,String unidade) {		
		this.driver = driver;
		this.wdw = wdw;		
		this.horizon = horizon;
		this.starttime = starttime;
		this.ed = ed;
		this.unidade = unidade;
	}

	public String criar(){
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/ma/residuos/principal/");

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//a [@onclick=\"onClickItemMenu('C')\"]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("lb-cadastro-transportador")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary pull-right bt-novo']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));

		transporte = this.horizon.generateString(20);

		driver.findElement(By.id("i-codigo")).sendKeys(String.format("%04d", ThreadLocalRandom.current().nextInt(10000)));		
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();		
		driver.findElement(By.xpath("//label [contains(text(),'"+ unidade +"')]")).click();		
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();		
		driver.findElement(By.id("i-cnpj")).sendKeys(String.format("%014d", ThreadLocalRandom.current().nextLong(100000000000000l)));
		driver.findElement(By.id("i-nome")).sendKeys(transporte);
		driver.findElement(By.id("i-contato")).sendKeys(this.horizon.generateString(30));
		driver.findElement(By.id("i-email")).sendKeys(this.horizon.generateString2(50) + "@email.com");
		driver.findElement(By.id("i-logradouro")).sendKeys(this.horizon.generateString(50));
		driver.findElement(By.id("i-numero")).sendKeys(String.format("%04d", ThreadLocalRandom.current().nextInt(10000)));	
		driver.findElement(By.id("i-telefone")).sendKeys(String.format("%011d", ThreadLocalRandom.current().nextLong(100000000000l)));
		driver.findElement(By.id("i-complemento")).sendKeys(this.horizon.generateString(50));
		driver.findElement(By.id("i-bairro")).sendKeys(this.horizon.generateString(50));
		driver.findElement(By.id("i-cidade")).sendKeys(this.horizon.generateString(50));
		Select slek = new Select(driver.findElement(By.id("cb-uf")));
		List <WebElement> lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(lista.size()));
		driver.findElement(By.id("chk-destinatario")).click();
		driver.findElement(By.id("chk-classe1-destinatario")).click();
		driver.findElement(By.id("chk-classe2-destinatario")).click();
		driver.findElement(By.id("chk-transportador")).click();
		driver.findElement(By.id("chk-classe1-transportador")).click();
		driver.findElement(By.id("chk-classe2-transportador")).click();

		driver.findElement(By.id("li-passo-2")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-tipo-transportador"))));

		driver.findElement(By.id("i-tipo-transportador")).sendKeys(this.horizon.generateString(40));

		driver.findElement(By.id("bt-adicionar")).click();

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSalvar()']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default margin-left btn-nao']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default margin-left btn-nao']")).click();

		this.horizon.waitLoad();

		System.out.println("Transporte " + transporte + "						" + horizon.time(starttime));	
		return transporte;
	}

	public void editar() {
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));

		driver.findElement(By.id("ac-area1-filtro")).sendKeys(unidade);
		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"') and contains(@class,'ui-menu-item')]")).click();

		driver.findElement(By.id("i-nome-filtro")).sendKeys(transporte);

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'"+ transporte +"')]")).click();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary pull-right bt-visualizar']")).click();  

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		transporte = this.horizon.generateString(20);

		this.horizon.escolherStatus();
		driver.findElement(By.id("i-codigo")).sendKeys(String.format("%04d", ThreadLocalRandom.current().nextInt(10000)));		
		driver.findElement(By.id("i-nome")).sendKeys(transporte);
		driver.findElement(By.id("i-contato")).sendKeys(this.horizon.generateString(30));
		driver.findElement(By.id("i-logradouro")).sendKeys(this.horizon.generateString(50));
		driver.findElement(By.id("i-numero")).sendKeys(String.format("%04d", ThreadLocalRandom.current().nextInt(10000)));
		driver.findElement(By.id("i-complemento")).sendKeys(this.horizon.generateString(50));
		driver.findElement(By.id("i-bairro")).sendKeys(this.horizon.generateString(50));
		driver.findElement(By.id("i-cidade")).sendKeys(this.horizon.generateString(50));
		Select slek = new Select(driver.findElement(By.id("cb-uf")));
		List <WebElement> lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(lista.size()));
		driver.findElement(By.id("chk-destinatario")).click();
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();
		lista = driver.findElements(By.xpath("//label[@class='checkbox']//input [@type='checkbox' and not(@disabled='')]"));		
		for(int i = 0;i<lista.size();i++) {
			lista.get(i).click();
		}
		driver.findElement(By.xpath("//label [contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();

		driver.findElement(By.id("li-passo-2")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-tipo-transportador"))));

		driver.findElement(By.xpath("//a [@data-tooltip='Excluir']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@data-ng-click='onClickBtnSimExcluirTipoTransporte()']"))));
		driver.findElement(By.xpath("//button [@data-ng-click='onClickBtnSimExcluirTipoTransporte()']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("i-tipo-transportador")).sendKeys(this.horizon.generateString(20));

		driver.findElement(By.id("bt-adicionar")).click();

		driver.findElement(By.xpath("//a [@data-tooltip='Editar']")).click();

		driver.findElement(By.id("i-tipo-transportador")).sendKeys(this.horizon.generateString(20));

		driver.findElement(By.id("bt-salvar")).click();

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSalvar()']")).click();

		this.horizon.waitLoad();

		if(driver.findElement(By.xpath("//button [@data-ng-click='onClickBtnSimAlterarStatus()']")).isDisplayed()) {
			driver.findElement(By.xpath("//button [@data-ng-click='onClickBtnSimAlterarStatus()']")).click();
		}		
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default margin-left btn-nao']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default margin-left btn-nao']")).click();

		this.horizon.waitLoad();

		System.out.println("Transporte+ " + transporte + "					" + horizon.time(starttime));	
	}

	public void excluir(){
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/ma/residuos/principal/");

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//a [@onclick=\"onClickItemMenu('C')\"]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("lb-cadastro-transportador")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));
		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area1-filtro")).sendKeys(unidade);
		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"') and contains(@class,'ui-menu-item')]")).click();

		driver.findElement(By.id("i-nome-filtro")).sendKeys(transporte);

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'"+ transporte +"')]")).click();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default lb-sim margin-left']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default lb-sim margin-left']")).click();
	}
}