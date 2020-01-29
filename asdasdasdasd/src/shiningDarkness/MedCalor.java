package shiningDarkness;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MedCalor {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Actions act;
	private EquipMedicao equipMed;
	private String eq;
	private String ed;
	private String unidade;

	public MedCalor(WebDriver driver, WebDriverWait wdw, Signal horizon, long starttime, String ed,String unidade) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;
		this.act = new Actions(driver);
		this.ed = ed;
		this.unidade = unidade;
		equipMed = new EquipMedicao(driver,wdw,horizon, starttime, ed, unidade);
		eq = equipMed.criar2();
	}

	public void criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menuhigiene.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("Cadastrar Medições de Calor")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-area1-cadastro"))));

		driver.findElement(By.id("ac-area1-cadastro")).sendKeys(unidade);		
		driver.findElement(By.xpath("//li [contains(text(),'" + unidade +"') and contains(@class,'ui-menu-item')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area2-cadastro")).sendKeys("AreaTeste");

		driver.findElement(By.xpath("//li [contains(text(),'AreaTeste') and contains(@class,'ui-menu-item')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area3-cadastro")).sendKeys("SetorTeste");

		driver.findElement(By.xpath("//li [contains(text(),'SetorTeste') and contains(@class,'ui-menu-item')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-ghe-cadastro")).sendKeys("GHETeste");

		driver.findElement(By.xpath("//li [contains(text(),'GHETeste') and contains(@class,'ui-menu-item')]")).click();

		driver.findElement(By.id("ac-equipamento")).sendKeys(eq);

		driver.findElement(By.xpath("//li [contains(text(),'" + eq +"') and contains(@class,'ui-menu-item')]")).click();

		driver.findElement(By.id("i-referencial")).sendKeys(this.horizon.generateString(50));
		driver.findElement(By.id("i-amostra")).sendKeys(this.horizon.generateString(20));

		this.horizon.waitLoad();

		Select slek = new Select(driver.findElement(By.id("cb-tipo-registro")));
		slek.selectByIndex(2);
		driver.findElement(By.id("i-ibutg-final")).sendKeys(String.format("%02f",ThreadLocalRandom.current().nextDouble()*100));
		driver.findElement(By.id("i-limite-legal-final")).sendKeys(String.format("%02f",ThreadLocalRandom.current().nextDouble()*100));
		driver.findElement(By.id("i-limite-tecnico-final")).sendKeys(String.format("%02f",ThreadLocalRandom.current().nextDouble()*100));	
		slek = new Select(driver.findElement(By.id("cb-regime-trabalho")));
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,3));
		driver.findElement(By.id("i-data")).sendKeys("01022019");

		driver.findElement(By.id("bt-salvar")).click();

		this.horizon.waitLoad();
	}

	public void editar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menuhigiene.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("Pesquisar Medições de Calor")).click();

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

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'GHETeste')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='visualizarMedicao()']")).click();

		this.horizon.waitLoad();
		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='div-loader-apollus']"))));

		Select slek = new Select(driver.findElement(By.id("cb-tipo-registro")));
		slek.selectByIndex(1);

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='onClickBtnSimModalConfirmarTipoRegistro()']"))));
		driver.findElement(By.xpath("//button [@onclick='onClickBtnSimModalConfirmarTipoRegistro()']")).click();

		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));

		slek = new Select(driver.findElement(By.id("cb-regime-trabalho")));
		slek.selectByIndex(1);
		slek = new Select(driver.findElement(By.id("cb-regime-hora")));
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,5));
		slek = new Select(driver.findElement(By.id("cb-tipo-atividade")));
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,4));
		driver.findElement(By.id("chk-carga-lt")).click();
		driver.findElement(By.id("i-tbn-lt")).sendKeys(String.format("%02.2f", ThreadLocalRandom.current().nextDouble(0.2,0.5)*100));
		driver.findElement(By.id("i-tg-lt")).sendKeys(String.format("%02.2f", ThreadLocalRandom.current().nextDouble(0.2,0.5)*100));
		driver.findElement(By.id("i-tbs-lt")).sendKeys(String.format("%02.2f", ThreadLocalRandom.current().nextDouble(0.2,0.5)*100));
		driver.findElement(By.id("i-metabolismo-lt")).sendKeys(String.format("%03d", ThreadLocalRandom.current().nextInt(500)));		

		driver.findElement(By.id("bt-salvar")).click();

		this.horizon.waitLoad();

		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menuhigiene.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("Pesquisar Medições de Calor")).click();

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

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'GHETeste')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='visualizarMedicao()']")).click();

		this.horizon.waitLoad();
		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='div-loader-apollus']"))));

		slek = new Select(driver.findElement(By.id("cb-regime-trabalho")));
		slek.selectByIndex(2);

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarSimAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarSimAlertaCadastro()']")).click();

		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));

		driver.findElement(By.id("i-local-item")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("chk-carga-item")).click();
		driver.findElement(By.id("i-minutos-item")).sendKeys("29");
		driver.findElement(By.id("i-segundos-item")).sendKeys("60");
		driver.findElement(By.id("i-metabolismo-item")).sendKeys(String.format("%03d", ThreadLocalRandom.current().nextInt(500)));
		driver.findElement(By.id("i-tbn-item")).sendKeys(String.format("%02.2f", ThreadLocalRandom.current().nextDouble(0.25,0.5)*100));
		driver.findElement(By.id("i-tg-item")).sendKeys(String.format("%02.2f", ThreadLocalRandom.current().nextDouble(0.25,0.5)*100));
		driver.findElement(By.id("i-tbs-item")).sendKeys(String.format("%02.2f", ThreadLocalRandom.current().nextDouble(0.25,0.5)*100));
		driver.findElement(By.id("tt-adicionar")).click();
		driver.findElement(By.id("i-local-item")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("i-minutos-item")).sendKeys("30");
		driver.findElement(By.id("i-segundos-item")).sendKeys("00");
		driver.findElement(By.id("i-metabolismo-item")).sendKeys(String.format("%03d", ThreadLocalRandom.current().nextInt(250)));
		driver.findElement(By.id("i-tbn-item")).sendKeys(String.format("%02.2f", ThreadLocalRandom.current().nextDouble(0.1,0.3)*100));
		driver.findElement(By.id("i-tg-item")).sendKeys(String.format("%02.2f", ThreadLocalRandom.current().nextDouble(0.1,0.3)*100));
		driver.findElement(By.id("tt-adicionar")).click();

		driver.findElement(By.xpath("//a [@data-tooltip='Excluir']")).click();		

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='onClickBtnSimModalConfirmarExcluir()']"))));
		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSimModalConfirmarExcluir()']")).click();

		this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));

		driver.findElement(By.xpath("//a [@data-tooltip='Editar']")).click();		

		driver.findElement(By.id("i-metabolismo-item")).clear();
		driver.findElement(By.id("i-metabolismo-item")).sendKeys(String.format("%03d", ThreadLocalRandom.current().nextInt(250)));
		driver.findElement(By.id("i-tbn-item")).clear();
		driver.findElement(By.id("i-tbn-item")).sendKeys(String.format("%02.2f", ThreadLocalRandom.current().nextDouble(0.1,0.3)*100));
		driver.findElement(By.id("i-tg-item")).clear();
		driver.findElement(By.id("i-tg-item")).sendKeys(String.format("%02.2f", ThreadLocalRandom.current().nextDouble(0.1,0.3)*100));
		act.moveToElement(driver.findElement(By.id("tt-salvar"))).clickAndHold().moveToElement(driver.findElement(By.id("tt-salvar"))).release().perform();
		driver.findElement(By.id("i-local-item")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("chk-carga-item")).click();
		driver.findElement(By.id("i-minutos-item")).sendKeys("29");
		driver.findElement(By.id("i-segundos-item")).sendKeys("60");
		driver.findElement(By.id("i-metabolismo-item")).sendKeys(String.format("%03d", ThreadLocalRandom.current().nextInt(500)));
		driver.findElement(By.id("i-tbn-item")).sendKeys(String.format("%02.2f", ThreadLocalRandom.current().nextDouble(0.25,0.5)*100));
		driver.findElement(By.id("i-tg-item")).sendKeys(String.format("%02.2f", ThreadLocalRandom.current().nextDouble(0.25,0.5)*100));
		driver.findElement(By.id("i-tbs-item")).sendKeys(String.format("%02.2f", ThreadLocalRandom.current().nextDouble(0.25,0.5)*100));
		driver.findElement(By.id("tt-adicionar")).click();

		driver.findElement(By.id("bt-salvar")).click();

		this.horizon.waitLoad();
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menuhigiene.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("Pesquisar Medições de Calor")).click();

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

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'GHETeste')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default pull-right bt-excluir\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default pull-right bt-excluir\"]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-principal margin-left lb-sim\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-principal margin-left lb-sim\"]")).click();

		this.horizon.waitLoad(); 

		equipMed.excluir();
	}
}