package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dano {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String dam;

	public Dano(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime,String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.starttime = starttime;
		this.ed = ed;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/menu_cadastro.html");

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@href='danos_saude.html']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-adicionar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("descricao"))));

		dam = this.horizon.generateString(20);		

		driver.findElement(By.id("descricao")).sendKeys(dam);
		driver.findElement(By.xpath("//input [@ng-model='danoSaude.catQuimico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='danoSaude.catFisico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='danoSaude.catBiologico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='danoSaude.catErgonomico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='danoSaude.catMecanico']")).click();

		driver.findElement(By.id("bt-salvar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();

		System.out.println("Dano " + dam + "						" + horizon.time(starttime));
		return dam;
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));

		driver.findElement(By.id("iDescricao")).sendKeys(dam);
		Select slek = new Select(driver.findElement(By.id("cb-categoria-filtro")));
		List<WebElement> lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(lista.size()));

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'" + dam +"')]")).click();
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-editar']")).click();

		this.horizon.waitLoad2();
		wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='bg-modal-fake fade in']"))));
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("descricao"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		dam = this.horizon.generateString(20);

		this.horizon.escolherStatus();
		driver.findElement(By.id("descricao")).sendKeys(dam);
		driver.findElement(By.xpath("//input [@ng-model='danoSaude.catFisico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='danoSaude.catBiologico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='danoSaude.catErgonomico']")).click();
		driver.findElement(By.xpath("//input [@ng-model='danoSaude.catMecanico']")).click();

		driver.findElement(By.id("bt-salvar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();

		System.out.println("Dano+ " + dam + "						" + horizon.time(starttime));	
	}

	public void excluir() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		driver.findElement(By.id("iDescricao")).sendKeys(dam);
		Select slek = new Select(driver.findElement(By.id("cb-categoria-filtro")));		
		slek.selectByValue("Q");

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'" + dam +"')]")).click();

		driver.findElement(By.id("bt-excluir")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']")).click();

		this.horizon.waitLoad2();
	}
}