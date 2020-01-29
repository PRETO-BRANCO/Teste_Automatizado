package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LTCAT {
	private WebDriver driver;
	private WebDriverWait wdw;
	private Signal horizon;
	private ProfGeral prof;
	private String profs;
	private String ed;
	private String unidade;

	public LTCAT(WebDriver driver, WebDriverWait wdw, Signal horizon, Long starttime, String ed,String unidade) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;
		this.ed = ed;
		this.unidade = unidade;
		prof = new ProfGeral(driver, wdw, horizon, starttime, ed);
		profs = prof.criar();
	}

	public void criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menulaudos-ghe.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("LTCAT")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-nome-responsavel"))));
		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area1")).clear();
		driver.findElement(By.id("ac-area1")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(text(),'" + unidade +"') and contains(@class,'ui-menu-item')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area2")).clear();
		driver.findElement(By.id("ac-area2")).sendKeys("AreaTeste");

		driver.findElement(By.xpath("//li [contains(text(),'AreaTeste') and contains(@class,'ui-menu-item')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area3")).clear();
		driver.findElement(By.id("ac-area3")).sendKeys("SetorTeste");

		driver.findElement(By.xpath("//li [contains(text(),'SetorTeste') and contains(@class,'ui-menu-item')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-ghe")).clear();
		driver.findElement(By.id("ac-ghe")).sendKeys("g");

		driver.findElement(By.xpath("//li [contains(text(),'GHETeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-nome-responsavel")).sendKeys(profs);
		driver.findElement(By.xpath("//button [@ng-click=\"buscarProfissionais('responsavel')\"]")).click();

		driver.findElement(By.xpath("//li [contains(text(),'"+ profs +"') and contains(@class,'ui-menu-item')]")).click();

		driver.findElement(By.id("i-data-database")).sendKeys("26022019");
		driver.findElement(By.id("i-art")).sendKeys(this.horizon.generateString(15));
		driver.findElement(By.id("i-avaliacao-condicoes")).sendKeys(this.horizon.generateString(25));
		driver.findElement(By.id("ta-consideracoes")).sendKeys(this.horizon.generateString(200));

		driver.findElement(By.id("tab-configuracoes")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("lb_codigo"))));

		List<WebElement> lista = driver.findElements(By.xpath("//input [(@type='checkbox') and (ancestor::div[contains(@id,'div-configuracoes')])]"));		
		for (int i = 0; i <  ThreadLocalRandom.current().nextInt(1,30); i++) {
			lista.get(ThreadLocalRandom.current().nextInt(0,7)).click();
		}

		if (driver.findElement(By.id("ta-texto-padrao")).isEnabled()) {
			driver.findElement(By.id("ta-texto-padrao")).clear();
			driver.findElement(By.id("ta-texto-padrao")).sendKeys(this.horizon.generateString(400));
		}

		driver.findElement(By.id("tab-metodologia")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("lb-calor"))));

		lista = driver.findElements(By.xpath("//input [(@type='checkbox') and (@class='metodologia')]"));		
		for (int i = 0; i <  ThreadLocalRandom.current().nextInt(1,30); i++) {
			lista.get(ThreadLocalRandom.current().nextInt(0,lista.size())).click();
		}

		driver.findElement(By.id("tab-colaboradores")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-colaborador"))));

		driver.findElement(By.id("i-colaborador")).sendKeys(this.horizon.generateString(60));
		driver.findElement(By.id("i-funcao")).sendKeys(this.horizon.generateString(60));

		driver.findElement(By.xpath("//button [@ng-click='adicionarColaborador()']")).click();

		driver.findElement(By.id("btnGravarVisao")).click();

		this.horizon.waitLoad();
	}

	public void editar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menulaudos-ghe.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("LTCAT")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-nome-responsavel"))));
		this.horizon.waitLoad();

		driver.findElement(By.id("ac-nome-equipe")).sendKeys("Yuren");
		driver.findElement(By.xpath("//button [@ng-click=\"buscarProfissionais('equipe')\"]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//li [contains(text(),'Yuren') and contains(@class,'ui-menu-item')]")).click();
		driver.findElement(By.xpath("//button [@ng-click='adicionarEquipe()']")).click();

		driver.findElement(By.id("i-art")).sendKeys(this.horizon.generateString(15));
		driver.findElement(By.id("i-avaliacao-condicoes")).sendKeys(this.horizon.generateString(25));
		driver.findElement(By.id("ta-consideracoes")).sendKeys(this.horizon.generateString(200));

		driver.findElement(By.id("tab-configuracoes")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("lb_codigo"))));

		List<WebElement> lista = driver.findElements(By.xpath("//input [(@type='checkbox') and (ancestor::div[contains(@id,'div-configuracoes')])]"));		
		for (int i = 0; i <  ThreadLocalRandom.current().nextInt(1,30); i++) {
			lista.get(ThreadLocalRandom.current().nextInt(0,7)).click();
		}

		if (driver.findElement(By.id("ta-texto-padrao")).isEnabled()) {
			driver.findElement(By.id("ta-texto-padrao")).clear();
			driver.findElement(By.id("ta-texto-padrao")).sendKeys(this.horizon.generateString(400));
		}

		driver.findElement(By.id("tab-metodologia")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("lb-calor"))));

		lista = driver.findElements(By.xpath("//input [(@type='checkbox') and (@class='metodologia')]"));		
		for (int i = 0; i <  ThreadLocalRandom.current().nextInt(1,30); i++) {
			lista.get(ThreadLocalRandom.current().nextInt(0,lista.size())).click();
		}

		driver.findElement(By.id("tab-colaboradores")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-colaborador"))));

		driver.findElement(By.xpath("//a [@ng-click='excluirColaborador(c)']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='modalConfirmacaoExcluirItem()']"))));
		driver.findElement(By.xpath("//button [@ng-click='modalConfirmacaoExcluirItem()']")).click();

		driver.findElement(By.id("i-colaborador")).sendKeys(this.horizon.generateString(60));
		driver.findElement(By.id("i-funcao")).sendKeys(this.horizon.generateString(60));

		driver.findElement(By.xpath("//button [@ng-click='adicionarColaborador()']")).click();

		driver.findElement(By.id("btnGravarVisao")).click();

		this.horizon.waitLoad();
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menulaudos-ghe.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("LTCAT")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-nome-responsavel"))));
		this.horizon.waitLoad();	

		driver.findElement(By.id("btnLimparVisao")).click();

		this.horizon.waitLoad();

		prof.excluir();
	}
}