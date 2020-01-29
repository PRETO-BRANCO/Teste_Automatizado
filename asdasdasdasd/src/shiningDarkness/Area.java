package shiningDarkness;

import java.util.List;


import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Area {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Actions act;
	private CentroCusto centro;
	private String centroC;
	private Long starttime;
	private String ed;
	private String unidade;
	private String area;
	private String setor;

	public Area(WebDriver driver, WebDriverWait wdw, Signal horizon, Long starttime,String ed, String unidade) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;
		this.starttime = starttime;
		this.ed = ed;
		this.unidade = unidade;
		act = new Actions(driver);
		centro = new CentroCusto(driver,wdw,horizon, starttime, ed);
		centroC = centro.criar();
	}
	
	public String[] criar() {
		
		driver.get("https://desenvolvimento.apollusehs.com.br/apollus/views/cadastro/area/#/");

		this.horizon.waitLoad();	

		driver.findElement(By.id("i-area1-filtro")).sendKeys(unidade);		

		driver.findElement(By.xpath("//td [contains(text(),'" + unidade +"')]")).click();

		this.horizon.waitLoad();

		act.moveToElement(driver.findElement(By.xpath("//a[@class='margin-left tooltip-right tt-menu']"))).click().moveToElement(driver.findElement(By.id("popover-btn-adicionar-1"))).click().perform();

		this.horizon.waitLoad();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("codigo-area-modal"))));

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		area =this.horizon.generateString(50);				

		driver.findElement(By.id("codigo-area-modal")).sendKeys(cod);
		driver.findElement(By.id("descricao-area-modal")).sendKeys(area);
		driver.findElement(By.id("i-nome-fantasia")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("i-inscricao-cnpj-area")).sendKeys(String.valueOf(ThreadLocalRandom.current().nextLong(10000000000000000l, 99999999999999999l)));

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary lb-salvar\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary lb-salvar\"]")).click();

		this.horizon.waitLoad();

		System.out.println("Area "+ area + "			" + horizon.time(starttime));		

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
			wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));
		}
		catch(Exception e){			
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("i-area2-filtro")).sendKeys(area);

		driver.findElement(By.xpath("//td [contains(text(),'" + area +"')]")).click();

		this.horizon.waitLoad();

		act.moveToElement(driver.findElement(By.xpath("//a[@class='margin-left tooltip-top tt-menu']"))).click().moveToElement(driver.findElement(By.id("popover-btn-adicionar-2"))).click().perform();

		this.horizon.waitLoad();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("codigo-area-modal"))));

		cod = String.format("%04d",rand.nextInt(10000));
		setor = this.horizon. generateString(50);		

		driver.findElement(By.id("codigo-area-modal")).sendKeys(cod);		
		driver.findElement(By.id("descricao-area-modal")).sendKeys(setor);

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary lb-salvar\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary lb-salvar\"]")).click();

		this.horizon.waitLoad();

		System.out.println("Setor "+ setor + "		" + horizon.time(starttime));
		String[] ret = {area,setor};

		return ret;
	}

	public void editar() {		
		driver.findElement(By.xpath("//td [contains(text(),'" + unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'" + area +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("i-area3-filtro")).sendKeys(setor);

		driver.findElement(By.xpath("//td [contains(text(),'" + setor +"')]")).click();

		this.horizon.waitLoad();

		act.moveToElement(driver.findElement(By.xpath("//a[@class='tooltip-pq tooltip-top tt-menu']"))).click().moveToElement(driver.findElement(By.id("popover-btn-editar-3"))).click().perform();

		this.horizon.waitLoad();	

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));

		driver.findElement(By.id("codigo-area-modal")).sendKeys(cod);	
		driver.findElement(By.id("descricao-area-modal")).sendKeys(this.horizon.generateString(50));

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary lb-salvar\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary lb-salvar\"]")).click();

		this.horizon.waitLoad();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
			wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));
		}
		catch(Exception e){			
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("i-area2-filtro")).sendKeys(area);

		driver.findElement(By.xpath("//td [contains(text(),'" + area +"')]")).click();

		this.horizon.waitLoad();

		act.moveToElement(driver.findElement(By.xpath("//a[@class='margin-left tooltip-top tt-menu']"))).click().moveToElement(driver.findElement(By.id("popover-btn-editar-2"))).click().perform();

		this.horizon.waitLoad();

		cod = String.format("%04d",rand.nextInt(10000));

		driver.findElement(By.id("codigo-area-modal")).sendKeys(cod);
		driver.findElement(By.id("descricao-area-modal")).sendKeys(this.horizon.generateString(50));		
		driver.findElement(By.id("i-cnae-descricao-area")).sendKeys("a");
		driver.findElement(By.id("bt-buscar-cnae")).click();

		this.horizon.waitLoad();

		List<WebElement> lista = driver.findElements(By.xpath("//li [@class='ui-menu-item']"));
		lista.get(ThreadLocalRandom.current().nextInt(lista.size())).click();
		driver.findElement(By.id("i-cep-area")).sendKeys("89201010");
		driver.findElement(By.id("bt-get-cep")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("i-municipio-area")).sendKeys(this.horizon.generateString(51));
		driver.findElement(By.id("i-endereco-area")).sendKeys(this.horizon.generateString(63));
		driver.findElement(By.id("num-area-modal")).sendKeys(String.format("%05d",rand.nextInt(100000)));
		driver.findElement(By.id("complemento-area-modal")).sendKeys(this.horizon.generateString(60));
		driver.findElement(By.xpath("//textarea [@ng-model='area_modal.recomendacoes']")).sendKeys(this.horizon.generateString(300));
		driver.findElement(By.id("li-dad")).click();

		this.horizon.waitLoad();
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("fone-area-modal"))));

		Select slek = new Select(driver.findElement(By.id("cb-centro-custo-area-model")));
		slek.selectByVisibleText(centroC);
		slek =  new Select(driver.findElement(By.id("cb-grau-risco-area")));
		slek.selectByValue(String.valueOf(ThreadLocalRandom.current().nextInt(1,5)));
		driver.findElement(By.id("fone-area-modal")).sendKeys(String.format("%015d",ThreadLocalRandom.current().nextLong(1000000000000000l)));
		driver.findElement(By.id("i-grupo-cipa-area")).sendKeys(this.horizon.generateString(16));
		driver.findElement(By.id("ck-entidade-area")).click();

		this.horizon.waitLoad();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary lb-salvar\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary lb-salvar\"]")).click();
	}

	public void excluir() {
		driver.get("https://desenvolvimento.apollusehs.com.br/apollus/views/cadastro/area/#/");

		this.horizon.waitLoad();

		driver.findElement(By.id("i-area1-filtro")).clear();
		driver.findElement(By.id("i-area1-filtro")).sendKeys(unidade);	

		driver.findElement(By.xpath("//td [contains(text(),'" + unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("i-area2-filtro")).clear();
		driver.findElement(By.id("i-area2-filtro")).sendKeys(area);	

		driver.findElement(By.xpath("//td [contains(text(),'" + area +"')]")).click();

		this.horizon.waitLoad();

		act.moveToElement(driver.findElement(By.xpath("//a[@class='margin-left tooltip-top tt-menu']"))).click().moveToElement(driver.findElement(By.id("popover-btn-excluir-2"))).click().perform();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary margin-left btn-sim\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary margin-left btn-sim\"]")).click();

		this.horizon.waitLoad();

		driver.get("https://desenvolvimento.apollusehs.com.br/apollus/views/cadastro/geral/centro_custo.html");

		this.horizon.waitLoad2();

		this.centro.excluir();
	}
}