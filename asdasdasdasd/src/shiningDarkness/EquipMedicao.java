package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EquipMedicao {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String unidade;
	private String equip;

	public EquipMedicao(WebDriver driver, WebDriverWait wdw, Signal horizon, Long starttime, String ed, String unidade) {
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

		driver.findElement(By.xpath("//a [@href='equip_medicao.html']")).click();

		this.horizon.waitLoad(); 

		driver.findElement(By.xpath("//button [@ng-click='adicionarEquipamentoMedicao()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-marca"))));

		String cod = String.format("%010d", ThreadLocalRandom.current().nextLong(10000000000l));
		equip = this.horizon.generateString(15);

		driver.findElement(By.id("codigo")).sendKeys(cod);
		driver.findElement(By.id("i-data-aquisicao")).sendKeys("01022019");
		driver.findElement(By.xpath("//input [@ng-model='equipamentoMedicao.numeroSerie']")).sendKeys(this.horizon.generateString2(15));
		Select slek = new Select(driver.findElement(By.id("cb-categoria")));
		slek.selectByValue("Q");
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();

		this.horizon.waitLoad(); 

		driver.findElement(By.xpath("//label [@title='"+ unidade +"']")).click();
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();
		slek = new Select(driver.findElement(By.id("cb-tipo")));
		List<WebElement> lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		driver.findElement(By.id("i-marca")).sendKeys(this.horizon.generateString(15));
		driver.findElement(By.id("i-modelo")).sendKeys(equip);
		driver.findElement(By.id("ac-responsavel")).sendKeys("Epy");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'Master Epy')]")).click();		

		this.horizon.waitLoad(); 

		driver.findElement(By.id("tab-registro")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-data-envio"))));

		driver.findElement(By.id("i-data-envio")).sendKeys("01012019");
		driver.findElement(By.id("i-data-retorno")).sendKeys("01022019");
		driver.findElement(By.id("i-data-validade")).sendKeys("28022019");
		slek = new Select(driver.findElement(By.id("cb-situacao")));
		lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		driver.findElement(By.id("i-n-certificado")).sendKeys(String.format("%012d", ThreadLocalRandom.current().nextLong(1000000000000l)));
		driver.findElement(By.id("i-responsavel")).sendKeys(this.horizon.generateString(40));
		driver.findElement(By.id("ta-observacao")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//button [@ng-click='adicionarRegistro()']")).click();

		this.horizon.waitLoad(); 

		driver.findElement(By.id("bt-salvar")).click();		

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad(); 

		System.out.println("Equipamento " + equip + "						" + horizon.time(starttime));	
		return equip;
	}

	public String criar2() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/menu_cadastro.html");

		this.horizon.waitLoad2(); 

		driver.findElement(By.xpath("//a [@href='equip_medicao.html']")).click();

		this.horizon.waitLoad(); 

		driver.findElement(By.xpath("//button [@ng-click='adicionarEquipamentoMedicao()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-marca"))));

		String cod = String.format("%010d", ThreadLocalRandom.current().nextLong(10000000000l));
		equip = this.horizon.generateString(15);

		driver.findElement(By.id("codigo")).sendKeys(cod);
		driver.findElement(By.id("i-data-aquisicao")).sendKeys("01022019");
		driver.findElement(By.xpath("//input [@ng-model='equipamentoMedicao.numeroSerie']")).sendKeys(this.horizon.generateString2(15));
		Select slek = new Select(driver.findElement(By.id("cb-categoria")));
		slek.selectByValue("F");
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();

		this.horizon.waitLoad(); 

		driver.findElement(By.xpath("//label [@title='"+ unidade +"']")).click();
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();
		slek = new Select(driver.findElement(By.id("cb-tipo")));
		List<WebElement> lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		driver.findElement(By.id("i-marca")).sendKeys(this.horizon.generateString(15));
		driver.findElement(By.id("i-modelo")).sendKeys(equip);
		driver.findElement(By.id("ac-responsavel")).sendKeys("Epy");

		driver.findElement(By.xpath("//li [contains(@class,'ui-menu-item') and contains(text(),'Master Epy')]")).click();	

		this.horizon.waitLoad(); 

		driver.findElement(By.id("tab-registro")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-data-envio"))));

		driver.findElement(By.id("i-data-envio")).sendKeys("01012019");
		driver.findElement(By.id("i-data-retorno")).sendKeys("01022019");
		driver.findElement(By.id("i-data-validade")).sendKeys("28022019");
		slek = new Select(driver.findElement(By.id("cb-situacao")));
		lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		driver.findElement(By.id("i-n-certificado")).sendKeys(String.format("%012d", ThreadLocalRandom.current().nextLong(1000000000000l)));
		driver.findElement(By.id("i-responsavel")).sendKeys(this.horizon.generateString(40));
		driver.findElement(By.id("ta-observacao")).sendKeys(this.horizon.generateString(200));
		driver.findElement(By.xpath("//button [@ng-click='adicionarRegistro()']")).click();

		this.horizon.waitLoad(); 

		driver.findElement(By.id("bt-salvar")).click();		

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad(); 

		System.out.println("Equipamento " + equip + "						" + horizon.time(starttime));	
		return equip;
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));

		driver.findElement(By.id("i-modelo-filtro")).sendKeys(equip);

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad(); 

		driver.findElement(By.xpath("//td [contains(text(),'" + equip +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary pull-right bt-editar']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-primary pull-right bt-editar']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad(); 

		equip = this.horizon.generateString(15);		

		driver.findElement(By.xpath("//input [@ng-model='equipamentoMedicao.numeroSerie']")).sendKeys(this.horizon.generateString2(15));
		Select slek = new Select(driver.findElement(By.id("cb-categoria")));
		List<WebElement> lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();

		lista = driver.findElements(By.xpath("//a [@tabindex='0']"));
		for(int i = 0;i<lista.size();i++) {
			lista.get(i).click();
		}		
		driver.findElement(By.xpath("//button [@class='multiselect dropdown-toggle btn btn-default']")).click();
		slek = new Select(driver.findElement(By.id("cb-tipo")));
		lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		driver.findElement(By.id("i-marca")).sendKeys(this.horizon.generateString(15));
		driver.findElement(By.id("i-modelo")).sendKeys(equip);

		this.horizon.waitLoad(); 

		driver.findElement(By.id("tab-registro")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-data-envio"))));

		driver.findElement(By.xpath("//a [@ng-click='excluirRegistro(reg)']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default margin-left btn-sim']"))));
		driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default margin-left btn-sim']")).click();

		this.horizon.waitLoad(); 

		driver.findElement(By.id("i-data-envio")).sendKeys("01012019");
		driver.findElement(By.id("i-data-retorno")).sendKeys("01022019");
		driver.findElement(By.id("i-data-validade")).sendKeys("28022019");
		slek = new Select(driver.findElement(By.id("cb-situacao")));
		lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		driver.findElement(By.id("i-n-certificado")).sendKeys(String.format("%06d", ThreadLocalRandom.current().nextInt(1000000)));
		driver.findElement(By.id("i-responsavel")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.id("ta-observacao")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.xpath("//button [@ng-click='adicionarRegistro()']")).click();		
		driver.findElement(By.xpath("//a [@ng-click='editarRegistro(reg)']")).click();		

		this.horizon.waitLoad(); 

		slek = new Select(driver.findElement(By.id("cb-situacao")));
		lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));
		driver.findElement(By.id("i-n-certificado")).sendKeys(String.format("%06d", ThreadLocalRandom.current().nextInt(1000000)));
		driver.findElement(By.id("i-responsavel")).sendKeys(this.horizon.generateString(20));
		driver.findElement(By.id("ta-observacao")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.xpath("//button [@ng-click='salvarEdicaoRegistro()']")).click();	

		this.horizon.waitLoad(); 

		driver.findElement(By.id("bt-salvar")).click();	
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad(); 

		System.out.println("Equipamento+ " + equip + "						" + horizon.time(starttime));	
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/geral/equip_medicao.html#/registros");

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-filtrar"))));
		this.horizon.waitLoad(); 		

		driver.findElement(By.id("i-modelo-filtro")).sendKeys(equip);

		driver.findElement(By.id("bt-filtrar")).click();

		this.horizon.waitLoad(); 

		driver.findElement(By.xpath("//td [contains(text(),'" + equip +"')]")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-show='controleTela.podeExcluir']"))));
		driver.findElement(By.xpath("//button [@ng-show='controleTela.podeExcluir']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='modalConfirmacaoExclusao()']"))));
		driver.findElement(By.xpath("//button [@ng-click='modalConfirmacaoExclusao()']")).click();
	}
}