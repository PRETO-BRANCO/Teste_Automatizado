package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Maquina {
	private WebDriver driver;
	private Signal horizon;
	private WebDriverWait wdw;
	private Area area;
	private String[] areas;
	private Long starttime;
	private String ed;
	private String unidade;
	private String maquina;

	public Maquina(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime,String ed, String unidade) {
		this.driver = driver;
		this.horizon = horizon;
		this.wdw = wdw; 	
		this.ed = ed;
		this.unidade = unidade;
		area = new Area(driver,wdw,horizon, starttime, ed, unidade);
		areas = area.criar();
		this.starttime = starttime;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/so/segurancamaquinas/menuprincipal.html");

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@href='pesquisamaquina.html']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-adicionar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.waitLoad2();

		maquina = String.format("%020.0f", ThreadLocalRandom.current().nextDouble(100000000000000000000.0f));

		driver.findElement(By.id("i-codigo")).sendKeys(maquina);
		driver.findElement(By.id("ac-area1")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-area2")).sendKeys(areas[0]);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ areas[0]+"')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-area3")).sendKeys(areas[1]);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ areas[1]+"')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("i-localizacao")).sendKeys(this.horizon.generateString(50));
		driver.findElement(By.id("i-n-oper")).sendKeys(String.format("%020.0f", ThreadLocalRandom.current().nextDouble(100000000000000000000.0f)));
		driver.findElement(By.id("i-nr-turnos")).sendKeys(String.format("%020.0f", ThreadLocalRandom.current().nextDouble(100000000000000000000.0f)));
		driver.findElement(By.id("i-interacao-equipamento")).sendKeys(this.horizon.generateString(125));
		driver.findElement(By.id("i-manual-oper")).sendKeys(this.horizon.generateString(30));
		driver.findElement(By.id("i-planta-baixa")).sendKeys(this.horizon.generateString(30));
		driver.findElement(By.id("ac-resp-reg")).sendKeys("Epy");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'Master Epy')]")).click();		

		this.horizon.waitLoad2();

		driver.findElement(By.id("lb-passo-2")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-modelo"))));
		this.horizon.waitLoad2();

		driver.findElement(By.id("i-modelo")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.id("i-fabricante")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.id("i-numero-serie")).sendKeys(String.format("%020.0f", ThreadLocalRandom.current().nextDouble(100000000000000000000.0f)));
		Select slek = new Select(driver.findElement(By.id("cb-abastecimento")));
		List<WebElement> lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		slek = new Select(driver.findElement(By.id("cb-descarga")));
		lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		driver.findElement(By.id("i-data-fabricacao")).sendKeys(String.format("%04d", ThreadLocalRandom.current().nextInt(2020)));
		driver.findElement(By.id("i-capacidade")).sendKeys(this.horizon.generateString(20));
		slek = new Select(driver.findElement(By.id("cb-tipo-maquina")));
		lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		driver.findElement(By.id("i-produto")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.id("i-equipamentos-aux")).sendKeys(this.horizon.generateString(125));		

		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-salvar")).click();

		this.horizon.waitLoad2();

		System.out.println("Maquina " + maquina + "						" + horizon.time(starttime));
		return maquina;
	}

	public void editar() {		
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a [@onclick=\"onItemMenuClick('dados')\"]"))));
		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//a [@onclick=\"onItemMenuClick('dados')\"]")).click();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));		
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		maquina = String.format("%020.0f", ThreadLocalRandom.current().nextDouble(100000000000000000000.0f));

		this.horizon.escolherStatus();
		driver.findElement(By.id("i-codigo")).sendKeys(maquina);
		driver.findElement(By.id("i-localizacao")).sendKeys(this.horizon.generateString(50));
		driver.findElement(By.id("i-n-oper")).sendKeys(String.format("%020.0f", ThreadLocalRandom.current().nextDouble(100000000000000000000.0f)));
		driver.findElement(By.id("i-nr-turnos")).sendKeys(String.format("%020.0f", ThreadLocalRandom.current().nextDouble(100000000000000000000.0f)));
		driver.findElement(By.id("i-interacao-equipamento")).sendKeys(this.horizon.generateString(125));
		driver.findElement(By.id("i-manual-oper")).sendKeys(this.horizon.generateString(30));
		driver.findElement(By.id("i-planta-baixa")).sendKeys(this.horizon.generateString(30));
		driver.findElement(By.id("lb-passo-2")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-modelo"))));
		this.horizon.waitLoad2();

		driver.findElement(By.id("i-modelo")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.id("i-fabricante")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.id("i-numero-serie")).sendKeys(String.format("%020.0f", ThreadLocalRandom.current().nextDouble(100000000000000000000.0f)));
		driver.findElement(By.id("i-capacidade")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.id("i-produto")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.id("i-equipamentos-aux")).sendKeys(this.horizon.generateString(125));		

		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-salvar")).click();

		this.horizon.waitLoad2();

		System.out.println("Maquina+ " + maquina + "						" + horizon.time(starttime));
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/so/segurancamaquinas/pesquisamaquina.html");

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-area1"))));
		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-area1")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-area2")).sendKeys(areas[0]);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ areas[0]+"')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-area3")).sendKeys(areas[1]);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ areas[1]+"')]")).click();

		driver.findElement(By.id("i-codigo")).sendKeys(maquina);

		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-pesquisar")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ maquina +"')]")).click();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']")).click();
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-principal btn-sim margin-left']")).click();

		this.horizon.waitLoad2();

		area.excluir();
	}
}