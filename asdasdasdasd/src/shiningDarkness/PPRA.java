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

public class PPRA {
	private WebDriver driver;
	private WebDriverWait wdw;
	private Signal horizon;
	private ProfGeral prof;
	private String profs;
	private String ed;
	private String unidade;

	public PPRA(WebDriver driver, WebDriverWait wdw, Signal horizon, Long starttime, String ed,String unidade) {
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

		driver.findElement(By.linkText("PPRA")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary pull-right bt-novo']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-nome-responsavel"))));

		driver.findElement(By.id("ac-area1-cadastro")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ unidade +"')]")).click();		

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area2-cadastro")).clear();
		driver.findElement(By.id("ac-area2-cadastro")).sendKeys("AreaTeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area3-cadastro")).clear();
		driver.findElement(By.id("ac-area3-cadastro")).sendKeys("SetorTeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'SetorTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-ghe-cadastro")).clear();
		driver.findElement(By.id("ac-ghe-cadastro")).sendKeys("GHETeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'GHETeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-nome-responsavel")).sendKeys(profs);
		driver.findElement(By.xpath("//button [@data-ng-click=\"buscarProfissionais('responsavel')\"]")).click();

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ profs +"')]")).click();

		driver.findElement(By.id("i-data-vigenciainicial")).sendKeys("01022019");
		driver.findElement(By.id("i-data-vigenciafinal")).sendKeys("28022019");
		driver.findElement(By.id("i-data-database")).sendKeys("22022019");
		driver.findElement(By.xpath("//input [@data-ng-model='ppra.art']")).sendKeys(this.horizon.generateString(15));
		driver.findElement(By.id("ta-observacao-referencia")).sendKeys(this.horizon.generateString(200));

		driver.findElement(By.id("tab-configuracoes")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("lb-codigo-ghe"))));

		driver.findElement(By.id("rb-ordenar-codigo-ghe")).click();
		List<WebElement> lista = driver.findElements(By.xpath("//input [@type='checkbox' and (ancestor::div[contains(@id,'div-tab-2')])]"));

		for (int i = 0; i < ThreadLocalRandom.current().nextInt(1,30); i++) {
			int j = ThreadLocalRandom.current().nextInt(0,lista.size()-1);
			if(j!= 9 && j != 10) lista.get(j).click();
		}		

		if (driver.findElement(By.id("ta-texto-padrao-risco-ghe")).isEnabled()) {
			driver.findElement(By.id("ta-texto-padrao-risco-ghe")).clear();
			driver.findElement(By.id("ta-texto-padrao-risco-ghe")).sendKeys(this.horizon.generateString(400));
		}

		driver.findElement(By.id("tab-metodologia")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("lb-umidade"))));

		lista = driver.findElements(By.xpath("//input [@type='checkbox' and (contains(@class,'metodologia '))]"));

		for (int i = 0; i < ThreadLocalRandom.current().nextInt(1,30); i++) {
			lista.get(ThreadLocalRandom.current().nextInt(0,lista.size()-1)).click();
		}

		driver.findElement(By.id("tab-plano-acao")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a [@data-tooltip='Adicionar Ações']"))));

		driver.findElement(By.xpath("//a [@data-tooltip='Adicionar Ações']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-topico-acao"))));

		driver.findElement(By.id("i-topico-acao")).sendKeys(this.horizon.generateString(180));
		driver.findElement(By.id("rb-descritivo")).click();
		driver.findElement(By.id("i-acao")).sendKeys(this.horizon.generateString(2000));
		driver.findElement(By.id("i-evidencia-acao")).sendKeys(this.horizon.generateString(1200));
		driver.findElement(By.id("i-responsavel-acao")).sendKeys(this.horizon.generateString(40));
		driver.findElement(By.id("i-data-previsto")).sendKeys("28022019");
		Select slek = new Select(driver.findElement(By.id("cb-implantacao")));
		int i = ThreadLocalRandom.current().nextInt(0, 6);
		if (i == 5) {
			driver.findElement(By.id("i-data-realizado")).sendKeys("22022019");
		} else {
			slek.selectByValue(String.valueOf(i * 20));
		}
		driver.findElement(By.id("i-comentario-acao")).sendKeys(this.horizon.generateString(200));

		driver.findElement(By.xpath("//button [@data-ng-click='onClickBtnSalvarAcao()']")).click();

		this.horizon.waitLoad();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
			wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));
		}
		catch(Exception e){			
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("tab-avaliacao")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ta-texto-padrao-avaliacao-global"))));

		driver.findElement(By.id("ta-texto-padrao-avaliacao-global")).clear();
		driver.findElement(By.id("ta-texto-padrao-avaliacao-global")).sendKeys(this.horizon.generateString(2500));
		driver.findElement(By.id("ta-texto-padrao-avaliacao-global")).sendKeys(this.horizon.generateString(2500));

		driver.findElement(By.id("bt-salvar-ppra")).click();
	}

	public void editar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menulaudos-ghe.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("PPRA")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area1-filtro")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();		

		driver.findElement(By.id("ac-area2-filtro")).clear();
		driver.findElement(By.id("ac-area2-filtro")).sendKeys("AreaTeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area3-filtro")).clear();
		driver.findElement(By.id("ac-area3-filtro")).sendKeys("SetorTeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'SetorTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-ghe-filtro")).clear();
		driver.findElement(By.id("ac-ghe-filtro")).sendKeys("GHETeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'GHETeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@data-tooltip='Pesquisar']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'"+ profs +"')]")).click();

		driver.findElement(By.xpath("//button [@data-ng-click='onClickVisualizar()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-nome-responsavel"))));
		this.horizon.sleep(500);
		this.horizon.waitLoad();		

		driver.findElement(By.xpath("//input [@data-ng-model='ppra.art']")).sendKeys(this.horizon.generateString(15));
		driver.findElement(By.id("ta-observacao-referencia")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.id("ac-nome-equipe")).sendKeys("Yuren");
		driver.findElement(By.xpath("//button [@data-ng-click=\"buscarProfissionais('equipe')\"]")).click();

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'Yuren')]")).click();

		driver.findElement(By.xpath("//button [@data-ng-click='adicionarEquipe()']")).click();

		driver.findElement(By.id("tab-configuracoes")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("lb-codigo-ghe"))));

		driver.findElement(By.id("rb-ordenar-codigo-ghe")).click();
		List<WebElement> lista = driver.findElements(By.xpath("//input [@type='checkbox' and (ancestor::div[contains(@id,'div-tab-2')])]"));

		for (int i = 0; i < ThreadLocalRandom.current().nextInt(1,30); i++) {
			int j = ThreadLocalRandom.current().nextInt(0,lista.size()-1);
			if(j!= 9 && j != 10) lista.get(j).click();
		}

		driver.findElement(By.id("tab-metodologia")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("lb-umidade"))));

		lista = driver.findElements(By.xpath("//input [@type='checkbox' and (contains(@class,'metodologia '))]"));

		for (int i = 0; i < ThreadLocalRandom.current().nextInt(1,30); i++) {
			lista.get(ThreadLocalRandom.current().nextInt(0,lista.size())).click();
		}

		driver.findElement(By.id("tab-plano-acao")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a [@data-tooltip='Adicionar Ações']"))));

		driver.findElement(By.xpath("//a [@data-tooltip='Adicionar Ações']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-topico-acao"))));

		driver.findElement(By.id("i-topico-acao")).sendKeys(this.horizon.generateString(90));
		driver.findElement(By.id("rb-descritivo")).click();
		driver.findElement(By.id("i-acao")).sendKeys(this.horizon.generateString(1000));
		driver.findElement(By.id("i-evidencia-acao")).sendKeys(this.horizon.generateString(600));
		driver.findElement(By.id("i-responsavel-acao")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.id("i-data-previsto")).sendKeys("28022019");
		Select slek = new Select(driver.findElement(By.id("cb-implantacao")));
		int i = ThreadLocalRandom.current().nextInt(0, 6);
		if (i == 5) {
			driver.findElement(By.id("i-data-realizado")).sendKeys("22022019");
		} else {
			slek.selectByValue(String.valueOf(i * 20));
		}
		driver.findElement(By.id("i-comentario-acao")).sendKeys(this.horizon.generateString(100));

		driver.findElement(By.xpath("//button [@data-ng-click='onClickBtnSalvarAcao()']")).click();

		this.horizon.waitLoad();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
			wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));
		}
		catch(Exception e){			
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//a [@data-ng-click='onExcluirItemAcao(p)']")).click();		

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary margin-left btn-sim\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary margin-left btn-sim\"]")).click();

		this.horizon.waitLoad();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
			wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));
		}
		catch(Exception e){			
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//a [@data-ng-click='onEditarItemAcao(p, $index)']")).click();


		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-topico-acao"))));

		driver.findElement(By.id("i-topico-acao")).sendKeys(this.horizon.generateString(90));
		driver.findElement(By.id("i-acao")).sendKeys(this.horizon.generateString(1000));
		driver.findElement(By.id("i-evidencia-acao")).sendKeys(this.horizon.generateString(600));
		driver.findElement(By.id("i-responsavel-acao")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.id("i-comentario-acao")).sendKeys(this.horizon.generateString(100));

		driver.findElement(By.xpath("//button [@data-ng-click='onClickBtnSalvarAcao()']")).click();

		this.horizon.waitLoad();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
			wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='modal-backdrop fade']"))));
		}
		catch(Exception e){			
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [@class='toast-message']"))));

		driver.findElement(By.id("tab-avaliacao")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ta-texto-padrao-avaliacao-global"))));

		driver.findElement(By.id("ta-texto-padrao-avaliacao-global")).sendKeys(this.horizon.generateString(2500));
		driver.findElement(By.id("ta-texto-padrao-avaliacao-global")).sendKeys(this.horizon.generateString(2500));

		driver.findElement(By.id("bt-deifinir-texto-padrao")).click();

		driver.findElement(By.id("bt-salvar-ppra")).click();
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/menu/menulaudos-ghe.html");

		this.horizon.waitLoad2();

		driver.findElement(By.linkText("PPRA")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area1-filtro")).sendKeys(unidade);

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'"+ unidade +"')]")).click();

		this.horizon.waitLoad();		

		driver.findElement(By.id("ac-area2-filtro")).clear();
		driver.findElement(By.id("ac-area2-filtro")).sendKeys("AreaTeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'AreaTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-area3-filtro")).clear();
		driver.findElement(By.id("ac-area3-filtro")).sendKeys("SetorTeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'SetorTeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("ac-ghe-filtro")).clear();
		driver.findElement(By.id("ac-ghe-filtro")).sendKeys("GHETeste");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'GHETeste')]")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//button [@data-tooltip='Pesquisar']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'"+ profs +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@data-ng-click='onClickExcluirItem()']"))));
		driver.findElement(By.xpath("//button [@data-ng-click='onClickExcluirItem()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary margin-left btn-sim\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary margin-left btn-sim\"]")).click();

		this.horizon.waitLoad();

		prof.excluir();		
	}
}