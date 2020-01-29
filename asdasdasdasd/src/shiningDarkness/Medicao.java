package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Medicao {
	private WebDriver driver;
	private Signal horizon;
	private WebDriverWait wdw;
	private EquipMedicao equipMed;
	private String eq1;
	private String eq2;
	private String ed;
	private String unidade;

	public Medicao(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime, String ed, String unidade) {
		this.driver = driver;
		this.horizon = horizon;
		this.wdw = wdw; 			
		this.ed = ed;
		this.unidade = unidade;
		equipMed = new EquipMedicao(driver,wdw,horizon, starttime, ed, unidade);		
		eq1 = equipMed.criar();
		eq2 = equipMed.criar();		
	}

	public void criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menuhigiene.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("Cadastrar Medições")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-area1-cadastro"))));

		driver.findElement(By.id("ac-area1-cadastro")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area2-cadastro")).sendKeys("AreaTeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area3-cadastro")).sendKeys("SetorTeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'SetorTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-ghe-cadastro")).sendKeys("GHETeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'GHETeste')]")).click();		

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-risco-cadastro")).sendKeys("Tetraquis (hidroxemetil) fosfônio (sais)");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'Tetraquis (hidroxemetil) fosfônio (sais)')]")).click();	

		this.horizon.waitLoad();

		driver.findElement(By.id("i-data")).sendKeys("01022019");
		driver.findElement(By.id("i-referencial")).sendKeys(this.horizon.generateString(25));
		driver.findElement(By.id("i-amostra")).sendKeys(this.horizon.generateString(10));

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-equipamento-1")).sendKeys(eq1);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ eq1 +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-equipamento-2")).sendKeys(eq2);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ eq2 +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("i-resultado")).sendKeys(String.format("%01.7f",ThreadLocalRandom.current().nextDouble()));
		driver.findElement(By.id("i-tempo")).sendKeys(String.format("%04d",ThreadLocalRandom.current().nextInt(10000)));
		driver.findElement(By.xpath("//textArea[@ng-model='medicao.observacao']")).sendKeys(this.horizon.generateString(200));

		driver.findElement(By.id("bt-salvar")).click();

		this.horizon.waitLoad();
	}

	public void editar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menuhigiene.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("Pesquisar Medições")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area1-filtro")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area2-filtro")).sendKeys("AreaTeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area3-filtro")).sendKeys("SetorTeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'SetorTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-ghe-filtro")).sendKeys("GHETeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'GHETeste')]")).click();		

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-risco-filtro")).sendKeys("Tetraquis (hidroxemetil) fosfônio (sais)");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'Tetraquis (hidroxemetil) fosfônio (sais)')]")).click();	

		this.horizon.waitLoad();

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'Tetraquis (hidroxemetil) fosfônio (sais)')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='visualizarMedicao()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-referencial"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-referencial")).sendKeys(this.horizon.generateString(25));
		driver.findElement(By.id("i-amostra")).sendKeys(this.horizon.generateString(10));		
		driver.findElement(By.xpath("//textArea[@ng-model='medicao.observacao']")).sendKeys(this.horizon.generateString(200));
		Select slek = new Select(driver.findElement(By.id("cb-tipo")));
		List<WebElement> lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));

		driver.findElement(By.id("bt-salvar")).click();

		this.horizon.waitLoad();
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menuhigiene.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("Pesquisar Medições")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area1-filtro")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area2-filtro")).sendKeys("AreaTeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area3-filtro")).sendKeys("SetorTeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'SetorTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-ghe-filtro")).sendKeys("GHETeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'GHETeste')]")).click();		

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-risco-filtro")).sendKeys("Tetraquis (hidroxemetil) fosfônio (sais)");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'Tetraquis (hidroxemetil) fosfônio (sais)')]")).click();	

		this.horizon.waitLoad();

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'Tetraquis (hidroxemetil) fosfônio (sais)')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default pull-right bt-excluir\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default pull-right bt-excluir\"]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-principal margin-left lb-sim\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-principal margin-left lb-sim\"]")).click();

		this.horizon.waitLoad();

		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/equip_medicao.html#/registros");

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));
		this.horizon.waitLoad(); 		

		driver.findElement(By.id("i-modelo-filtro")).sendKeys(eq1);

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad(); 

		driver.findElement(By.xpath("//td [contains(text(),'" + eq1 +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-show='controleTela.podeExcluir']"))));
		driver.findElement(By.xpath("//button [@ng-show='controleTela.podeExcluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='modalConfirmacaoExclusao()']"))));
		driver.findElement(By.xpath("//button [@ng-click='modalConfirmacaoExclusao()']")).click();

		this.horizon.waitLoad();

		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/equip_medicao.html#/registros");

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));
		this.horizon.waitLoad(); 

		driver.findElement(By.id("i-modelo-filtro")).clear();
		driver.findElement(By.id("i-modelo-filtro")).sendKeys(eq2);

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad(); 

		driver.findElement(By.xpath("//td [contains(text(),'" + eq2 +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-show='controleTela.podeExcluir']"))));
		driver.findElement(By.xpath("//button [@ng-show='controleTela.podeExcluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='modalConfirmacaoExclusao()']"))));
		driver.findElement(By.xpath("//button [@ng-click='modalConfirmacaoExclusao()']")).click();

		this.horizon.waitLoad();
	}
}