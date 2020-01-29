package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RelatorioResiduo {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;	
	private String ed;
	private String unidade;	
	private Estoque estoque;
	private String est;
	private Situacao situacao;
	private String sit;
	private Caracteristicas caracteristicas;
	private String caracter;
	private Destino destino;
	private String dest;
	private Transportador transporte;
	private String trans;


	public RelatorioResiduo(WebDriver driver, WebDriverWait wdw,Signal horizon,Long starttime,String ed,String unidade) {		
		this.driver = driver;
		this.wdw = wdw;		
		this.horizon = horizon;		
		this.ed = ed;
		this.unidade = unidade;	
		this.estoque = new Estoque(driver, wdw, horizon, starttime, ed, unidade);
		this.est = estoque.criar();
		this.situacao = new Situacao(driver, wdw, horizon, starttime, ed, unidade);
		this.sit = this.situacao.criar();
		this.caracteristicas = new Caracteristicas(driver, wdw, horizon, starttime, ed, unidade);
		this.caracter = this.caracteristicas.criar();
		this.destino = new Destino(driver, wdw, horizon, starttime, ed, unidade);
		this.dest = this.destino.criar();
		this.transporte = new Transportador(driver, wdw, horizon, starttime, ed, unidade);
		this.trans = transporte.criar();
	}

	public void criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/ma/residuos/principal/");

		this.horizon.waitLoad();

		driver.findElement(By.id("lb-relatorios")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-area1"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area1")).sendKeys(unidade);
		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area2")).clear();
		driver.findElement(By.id("ac-area2")).sendKeys("AreaTeste");
		driver.findElement(By.xpath("//li [contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad();		
		driver.findElement(By.id("ac-area3")).clear();
		driver.findElement(By.id("ac-area3")).sendKeys("SetorTeste");
		driver.findElement(By.xpath("//li [contains(text(),'SetorTeste')]")).click();

		this.horizon.waitLoad();

		Select slek = new Select(driver.findElement(By.id("cb-estoque")));
		slek.selectByVisibleText(est);

		slek = new Select(driver.findElement(By.id("cb-situacao")));
		slek.selectByVisibleText(sit);

		slek = new Select(driver.findElement(By.id("cb-caracteristica")));
		slek.selectByVisibleText(caracter);

		slek = new Select(driver.findElement(By.id("cb-tipo-destino")));
		slek.selectByVisibleText(dest);

		slek = new Select(driver.findElement(By.id("cb-classe")));
		List<WebElement> lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(lista.size()));

		driver.findElement(By.id("bt-gravar-visao")).click();

		this.horizon.waitLoad();
	}

	public void editar() {
		driver.navigate().refresh();

		this.horizon.waitLoad();

		driver.findElement(By.id("bt-limpar-visao")).click();

		this.horizon.waitLoad();		

		driver.findElement(By.xpath("//input [@value='T']")).click();

		wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("cb-situacao"))));

		driver.findElement(By.id("ac-area1")).sendKeys(unidade);
		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area2")).clear();
		driver.findElement(By.id("ac-area2")).sendKeys("AreaTeste");
		driver.findElement(By.xpath("//li [contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad();		

		Select slek = new Select(driver.findElement(By.id("cb-tipo-destino")));
		slek.selectByVisibleText(dest);

		driver.findElement(By.id("bt-gravar-visao")).click();

		this.horizon.waitLoad();

		driver.navigate().refresh();

		this.horizon.waitLoad();

		driver.findElement(By.id("bt-limpar-visao")).click();

		this.horizon.waitLoad();		

		driver.findElement(By.xpath("//input [@value='D']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("cb-cdf"))));

		driver.findElement(By.id("ac-area1")).sendKeys(unidade);
		driver.findElement(By.xpath("//li [contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();

		slek = new Select(driver.findElement(By.id("cb-estoque")));
		slek.selectByVisibleText(est);

		slek = new Select(driver.findElement(By.id("cb-transporte")));
		slek.selectByVisibleText(trans);

		slek = new Select(driver.findElement(By.id("cb-destino")));
		slek.selectByVisibleText(trans);

		slek = new Select(driver.findElement(By.id("cb-caracteristica")));
		slek.selectByVisibleText(caracter);

		slek = new Select(driver.findElement(By.id("cb-cdf")));
		List<WebElement> lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(lista.size()));

		slek = new Select(driver.findElement(By.id("cb-mtr")));
		lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(lista.size()));

		driver.findElement(By.id("bt-gravar-visao")).click();

		this.horizon.waitLoad();
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/ma/residuos/principal/");

		this.horizon.waitLoad();

		driver.findElement(By.id("lb-relatorios")).click();

		wdw.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("bt-limpar-visao"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("bt-limpar-visao")).click();

		this.horizon.waitLoad();	

		estoque.excluir();

		this.horizon.waitLoad();

		situacao.excluir();

		this.horizon.waitLoad();

		caracteristicas.excluir();

		this.horizon.waitLoad();

		destino.excluir();

		this.horizon.waitLoad();

		transporte.excluir();
	}
}