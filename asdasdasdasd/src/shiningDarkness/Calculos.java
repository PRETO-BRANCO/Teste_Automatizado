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

public class Calculos {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private MedCalor medC;
	private Medicao med;
	private String ed;
	private String unidade;

	public Calculos(WebDriver driver, WebDriverWait wdw, Signal horizon, long starttime, String ed,String unidade) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;
		this.ed = ed;
		this.unidade = unidade;
		medC = new MedCalor(driver, wdw, horizon, starttime, ed, unidade);
		medC.criar();
		medC.criar();
		medC.criar();
		med = new Medicao(driver, wdw, horizon, starttime, ed, unidade);
		med.criar();
		med.criar();
		med.criar();
	}

	public void calculoCalor() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menuhigiene.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("Cadastrar Cálculos Estatísticos")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-area1-filtro-2"))));
		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='bg-modal-fake fade in']"))));

		driver.findElement(By.id("ac-area1-filtro-2")).clear();
		driver.findElement(By.id("ac-area1-filtro-2")).sendKeys(unidade);	

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-area2-filtro-2")).clear();
		driver.findElement(By.id("ac-area2-filtro-2")).sendKeys("AreaTeste");	

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-ghe-filtro-2")).sendKeys("GHETeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'GHETeste')]")).click();	

		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [contains(@class,'bg-modal-fake fade in')]"))));

		driver.findElement(By.id("ac-risco-filtro-2")).sendKeys("calor");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'Calor')]")).click();

		this.horizon.waitLoad2();
		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [contains(@class,'bg-modal-fake fade in')]"))));

		driver.findElement(By.xpath("//button [@ng-click='buscarMedicoes()']")).click();

		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [contains(@class,'bg-modal-fake fade in')]"))));

		driver.findElement(By.id("bt-calcular")).click();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-gravar"))));

		List<WebElement> lista = driver.findElements(By.xpath("//input [contains(@type,'radio') and not(contains(@disabled,'disabled'))]"));		
		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();		
		driver.findElement(By.id("i-data-inicial")).sendKeys("01022019");
		driver.findElement(By.id("i-data-final")).sendKeys("28022019");

		driver.findElement(By.id("bt-gravar")).click();		

		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [contains(@class,'bg-modal-fake fade in')]"))));

		driver.findElement(By.id("bt-voltar")).click();
	}

	public void excluirCalor() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menuhigiene.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("Pesquisar Cálculos Estatísticos")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-area1-filtro-1"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-area1-filtro-1")).clear();
		driver.findElement(By.id("ac-area1-filtro-1")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-area2-filtro-1")).clear();
		driver.findElement(By.id("ac-area2-filtro-1")).sendKeys("AreaTeste");	

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-ghe-filtro-1")).sendKeys("GHETeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'GHETeste')]")).click();	

		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [contains(@class,'bg-modal-fake fade in')]"))));

		driver.findElement(By.id("ac-risco-filtro-1")).sendKeys("calor");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'Calor')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='buscarCalculos()']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'Calor')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default pull-right lb-excluir\"]"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right lb-excluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-principal btn-sim margin-left\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-principal btn-sim margin-left\"]")).click();

		this.horizon.waitLoad2();

		medC.excluir();

		this.horizon.waitLoad();

		medC.excluir();

		this.horizon.waitLoad();

		medC.excluir();

		this.horizon.waitLoad();
	}

	public void calcularMedida() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menuhigiene.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("Cadastrar Cálculos Estatísticos")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-area1-filtro-2"))));
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='bg-modal-fake fade in']"))));
		}
		catch(Exception e){			
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.id("ac-area1-filtro-2")).clear();
		driver.findElement(By.id("ac-area1-filtro-2")).sendKeys(unidade);	

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-area2-filtro-2")).clear();
		driver.findElement(By.id("ac-area2-filtro-2")).sendKeys("AreaTeste");	

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-ghe-filtro-2")).sendKeys("GHETeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'GHETeste')]")).click();	

		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [contains(@class,'bg-modal-fake fade in')]"))));

		driver.findElement(By.id("ac-risco-filtro-2")).sendKeys("Tetraquis (hidroxemetil) fosfônio (sais)");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'Tetraquis (hidroxemetil) fosfônio (sais)')]")).click();	

		this.horizon.waitLoad2();
		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [contains(@class,'bg-modal-fake fade in')]"))));

		Select slek = new Select(driver.findElement(By.id("cb-tipo-filtro-2")));
		slek.selectByIndex(1);

		driver.findElement(By.xpath("//button [@ng-click='buscarMedicoes()']")).click();

		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [contains(@class,'bg-modal-fake fade in')]"))));

		driver.findElement(By.id("bt-calcular")).click();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-gravar"))));

		List<WebElement> lista = driver.findElements(By.xpath("//input [contains(@type,'radio') and not(contains(@disabled,'disabled'))]"));		
		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();		
		driver.findElement(By.id("i-data-inicial")).sendKeys("01022019");
		driver.findElement(By.id("i-data-final")).sendKeys("28022019");

		driver.findElement(By.id("bt-gravar")).click();	
	}

	public void excluirMedida() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menuhigiene.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("Pesquisar Cálculos Estatísticos")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-area1-filtro-1"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-area1-filtro-1")).clear();
		driver.findElement(By.id("ac-area1-filtro-1")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-area2-filtro-1")).clear();
		driver.findElement(By.id("ac-area2-filtro-1")).sendKeys("AreaTeste");	

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.id("ac-ghe-filtro-1")).sendKeys("GHETeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'GHETeste')]")).click();	

		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [contains(@class,'bg-modal-fake fade in')]"))));

		driver.findElement(By.id("ac-risco-filtro-1")).sendKeys("Tetraquis (hidroxemetil) fosfônio (sais)");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'Tetraquis (hidroxemetil) fosfônio (sais)')]")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@ng-click='buscarCalculos()']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'Tetraquis (hidroxemetil) fosfônio (sais)')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default pull-right lb-excluir\"]"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right lb-excluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-principal btn-sim margin-left\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-principal btn-sim margin-left\"]")).click();

		this.horizon.waitLoad2();

		med.excluir();

		this.horizon.waitLoad();

		med.excluir();

		this.horizon.waitLoad();

		med.excluir();

		this.horizon.waitLoad();
	}
}