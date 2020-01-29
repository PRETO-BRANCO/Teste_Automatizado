package shiningDarkness;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LaudoAnaliseErgo {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;	
	private String ed;
	private Actions act;
	private String unidade;	
	private ProfGeral profGeral;
	private String prof;
	private EquipMedicao equipMed;
	private String equip;
	private AnaliseErgo analise;

	public LaudoAnaliseErgo(WebDriver driver, WebDriverWait wdw,Signal horizon,String ed,String unidade,Long starttime) {		
		this.driver = driver;
		this.wdw = wdw;		
		this.horizon = horizon;		
		this.ed = ed;
		this.unidade = unidade;
		this.act = new Actions(driver);	
		profGeral = new ProfGeral(driver, wdw, horizon, starttime, ed);
		prof = profGeral.criar();
		equipMed = new EquipMedicao(driver, wdw, horizon, starttime, ed, unidade);
		equip = equipMed.criar();
		analise = new AnaliseErgo(driver, wdw, horizon, ed, unidade, starttime);
		analise.criar();
		analise.editar();
	}

	public void criar() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/analise/");	

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//a [@ui-sref='laudo']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-area1"))));
		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area1")).sendKeys(unidade);
		act.moveToElement(driver.findElement(By.xpath("//li [contains(text(),'" + unidade +"')]"))).click().perform();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area2")).sendKeys("AreaTeste");
		act.moveToElement(driver.findElement(By.xpath("//li [contains(text(),'AreaTeste')]"))).click().perform();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area3")).sendKeys("SetorTeste");
		act.moveToElement(driver.findElement(By.xpath("//li [contains(text(),'SetorTeste')]"))).click().perform();

		this.horizon.waitLoad();

		driver.findElement(By.id("bt-pesquisar")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("chk-seleciona-todos")).click();

		driver.findElement(By.id("li-referencias")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-art"))));		

		driver.findElement(By.id("ac-nome-responsavel")).sendKeys(prof);
		driver.findElement(By.xpath("//button [@ng-click=\"buscarProfissionais('responsavel')\"]")).click();
		act.moveToElement(driver.findElement(By.xpath("//li [contains(text(),'"+ prof +"')]"))).click().perform();

		driver.findElement(By.id("i-art")).sendKeys(this.horizon.generateString(15));
		driver.findElement(By.id("i-avaliacao-condicoes")).sendKeys(this.horizon.generateString(25));

		driver.findElement(By.id("li-configuracoes")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("lb-temperatura-velocidade"))));

		List<WebElement> lista = driver.findElements(By.xpath("//input [@type='checkbox'and not(ancestor::div[contains(@style,'display:none')]) and not(ancestor::div[contains(@style,'display: none')])]"));		
		for(int i = 2;i<lista.size();i++) {
			lista.get(i).click();
		}

		driver.findElement(By.id("li-colaboradores")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-colaborador"))));

		driver.findElement(By.id("i-colaborador")).sendKeys(this.horizon.generateString(60));
		driver.findElement(By.id("i-cargo")).sendKeys(this.horizon.generateString(60));

		driver.findElement(By.xpath("//button [@class='btn btn-primary tooltip-top tt-adicionar']")).click();

		driver.findElement(By.id("li-equipmed")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-equipamento-medicao"))));		

		driver.findElement(By.id("ac-equipamento-medicao")).sendKeys(equip);
		act.moveToElement(driver.findElement(By.xpath("//li [contains(text(),'" + equip +"')]"))).click().perform();

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnAdicionarEquipamento()']")).click();

		driver.findElement(By.id("bt-gravar-visao")).click();

		this.horizon.waitLoad();
	}

	public void editar() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/analise/");	

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//a [@ui-sref='laudo']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-area1"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("li-referencias")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-art"))));

		driver.findElement(By.id("ac-nome-equipe")).sendKeys("Yuren");
		driver.findElement(By.xpath("//button [@ng-click=\"buscarProfissionais('equipe')\"]")).click();
		act.moveToElement(driver.findElement(By.xpath("//li [contains(text(),'Yuren')]"))).click().perform();
		driver.findElement(By.xpath("//button [@ng-click='onClickBtnAdicionarEquipe()']")).click();

		driver.findElement(By.id("i-art")).sendKeys(this.horizon.generateString(15));
		driver.findElement(By.id("i-avaliacao-condicoes")).sendKeys(this.horizon.generateString(25));

		driver.findElement(By.id("li-configuracoes")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("lb-temperatura-velocidade"))));

		List<WebElement> lista = driver.findElements(By.xpath("//input [@type='checkbox'and not(ancestor::div[contains(@style,'display:none')]) and not(ancestor::div[contains(@style,'display: none')])]"));		
		for(int i = 2;i<lista.size();i++) {
			lista.get(i).click();
		}

		driver.findElement(By.id("li-colaboradores")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-colaborador"))));

		driver.findElement(By.xpath("//button [@class='btn btn-xs btn-primary tooltip-right tt-excluir']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-excluir-sim-laudo"))));
		driver.findElement(By.id("bt-excluir-sim-laudo")).click();

		wdw.until(ExpectedConditions.elementToBeClickable(	driver.findElement(By.xpath("//button [@class='btn btn-primary tooltip-top tt-adicionar']"))));

		driver.findElement(By.id("i-colaborador")).sendKeys(this.horizon.generateString(60));
		driver.findElement(By.id("i-cargo")).sendKeys(this.horizon.generateString(60));

		driver.findElement(By.xpath("//button [@class='btn btn-primary tooltip-top tt-adicionar']")).click();		

		driver.findElement(By.id("li-equipmed")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-equipamento-medicao"))));

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnExcluirEquipamento(equipamento)']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-excluir-sim-laudo"))));
		driver.findElement(By.id("bt-excluir-sim-laudo")).click();		

		Select slek = new Select(driver.findElement(By.id("cb-tipo-equipamento")));
		slek.selectByIndex(1);

		driver.findElement(By.id("i-equipamento-medicao")).sendKeys(this.horizon.generateString(50));

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnAdicionarEquipamento()']")).click();

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnEditarEquipamento(equipamento)']")).click();

		driver.findElement(By.id("i-equipamento-medicao")).sendKeys(this.horizon.generateString(50));

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnEditarEquipamento(equipamento)']")).click();

		driver.findElement(By.id("bt-gravar-visao")).click();

		this.horizon.waitLoad();
	}

	public void excluir() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/analise/");	

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//a [@ui-sref='laudo']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-area1"))));
		this.horizon.waitLoad();

		driver.findElement(By.id("bt-limpar-visao")).click();

		this.horizon.waitLoad();

		profGeral.excluir();

		this.horizon.waitLoad2();

		equipMed.excluir();

		this.horizon.waitLoad();

		analise.excluir();
	}
}
