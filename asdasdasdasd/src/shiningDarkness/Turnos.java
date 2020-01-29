package shiningDarkness;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Turnos {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String turno;
	private String unidade;

	public Turnos(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime, String ed, String unidade) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.starttime = starttime;
		this.ed = ed;
		this.unidade = unidade;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/menu_cadastro.html");

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@href='turnos.html']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@ng-click='adicionarTurno()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("descricao"))));

		turno = this.horizon.generateString(30);

		driver.findElement(By.id("codigo")).sendKeys(String.format("%06d", ThreadLocalRandom.current().nextInt(1000000)));
		driver.findElement(By.id("descricao")).sendKeys(turno);
		driver.findElement(By.id("cb-area-1-cadastro")).sendKeys(unidade);		

		driver.findElement(By.xpath("//li [contains(text(),'" + unidade +"')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='adicionarArea(this)']")).click();

		driver.findElement(By.id("bt-salvar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();

		System.out.println("Turno " + turno + "					" + horizon.time(starttime));
		return turno;
	}

	public void editar() {
		driver.navigate().refresh();

		this.horizon.waitLoad2();		

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));

		driver.findElement(By.id("iDescricao")).sendKeys(turno);
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();		
		driver.findElement(By.xpath("//label [contains(@title,'"+ unidade +"')]")).click();
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'" + turno +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-editar']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal pull-right bt-editar']")).click();

		this.horizon.waitLoad2();
		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='bg-modal-fake fade in']"))));

		turno = this.horizon.generateString(30);

		this.horizon.escolherStatus();
		driver.findElement(By.id("codigo")).sendKeys(String.format("%06d", ThreadLocalRandom.current().nextInt(1000000)));
		driver.findElement(By.id("descricao")).sendKeys(turno);

		driver.findElement(By.xpath("//a [@ng-click='excluirAreaUnidade(item)']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='modalConfirmacaoExcluirItem()']"))));
		driver.findElement(By.xpath("//button [@ng-click='modalConfirmacaoExcluirItem()']")).click();

		driver.findElement(By.id("cb-area-1-cadastro")).sendKeys(unidade);		

		driver.findElement(By.xpath("//li [contains(text(),'" + unidade +"')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='adicionarArea(this)']")).click();

		driver.findElement(By.id("bt-salvar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();

		System.out.println("Turno+ " + turno + "					" + horizon.time(starttime));
	}

	public void excluir() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));

		driver.findElement(By.id("iDescricao")).sendKeys(turno);
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();		
		driver.findElement(By.xpath("//label [contains(@title,'"+ unidade +"')]")).click();
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'" + turno +"')]")).click();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']")).click();
	}
}