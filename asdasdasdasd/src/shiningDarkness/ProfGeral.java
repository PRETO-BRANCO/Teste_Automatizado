package shiningDarkness;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfGeral {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private long starttime;
	private String ed;
	private String profGeral;

	public ProfGeral(WebDriver driver, WebDriverWait wdw, Signal horizon, long starttime, String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.starttime = starttime;
		this.ed = ed;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/menu_cadastro.html");

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@href='profissional.html']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-adicionar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-nome"))));
		this.horizon.waitLoad2();

		profGeral = this.horizon.generateString2(9)+ " " + this.horizon.generateString2(10);

		driver.findElement(By.id("i-nome")).sendKeys(profGeral);
		driver.findElement(By.id("i-cargo")).sendKeys(this.horizon.generateString(15));
		driver.findElement(By.id("i-registro-conselho")).sendKeys(this.horizon.generateString(10));
		driver.findElement(By.id("i-nit")).sendKeys(String.format("%011d", ThreadLocalRandom.current().nextLong(100000000000l)));
		driver.findElement(By.id("i-cpf")).sendKeys("58451999131");
		driver.findElement(By.id("ck-rt")).click();

		driver.findElement(By.id("bt-salvar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();

		System.out.println("Profissional " + profGeral + "					" + horizon.time(starttime));	
		return profGeral;
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));

		driver.findElement(By.id("i-nome-filtro")).sendKeys(profGeral);
		driver.findElement(By.id("i-cpf-filtro")).sendKeys("58451999131");

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ profGeral +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-editar']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-editar']")).click();		

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-nome"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		profGeral =this.horizon.generateString2(20);

		driver.findElement(By.id("i-nome")).sendKeys(profGeral);
		driver.findElement(By.id("i-cargo")).sendKeys(this.horizon.generateString(15));
		driver.findElement(By.id("i-registro-conselho")).sendKeys(this.horizon.generateString(10));
		driver.findElement(By.id("ck-rt")).click();

		driver.findElement(By.id("bt-salvar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();

		System.out.println("Profissional+ " + profGeral + "					" + horizon.time(starttime));
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/profissional.html");

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));
		this.horizon.waitLoad2();

		driver.findElement(By.id("i-nome-filtro")).sendKeys(profGeral);
		driver.findElement(By.id("i-cpf-filtro")).sendKeys("58451999131");

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ profGeral +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-show='controleTela.podeExcluir']"))));
		driver.findElement(By.xpath("//button [@ng-show='controleTela.podeExcluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']")).click();
	}
}