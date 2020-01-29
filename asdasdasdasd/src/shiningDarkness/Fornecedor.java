package shiningDarkness;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Fornecedor {
	private WebDriver driver;
	private WebDriverWait wdw;
	private Signal horizon;
	private String ed;
	private long starttime;
	private String fornecedor;

	public Fornecedor(WebDriver driver, WebDriverWait wdw, Signal horizon, Long starttime, String ed, String unidade) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.ed = ed;
		this.starttime = starttime;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/documentacao/segquimica/pesquisa_docs_segquimica.html");

		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-gerenciar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='adicionarDSQ()']"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-configuracoes")).click();

		driver.findElement(By.xpath("//a [@href='fornecedordsq.html']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@ng-click='adicionarFornecedor()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		fornecedor = this.horizon.generateString(20);

		driver.findElement(By.id("i-codigo")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(100)));
		driver.findElement(By.id("i-descricao")).sendKeys(fornecedor);

		driver.findElement(By.id("bt-salvar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();

		System.out.println("Fornecedor " + fornecedor + "						" + horizon.time(starttime));
		return fornecedor;
	}

	public void editar() {
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@data-tooltip='Pesquisar']"))));

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(fornecedor);

		driver.findElement(By.xpath("//button [@data-tooltip='Pesquisar']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ fornecedor +"')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='editarFornecedor()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		fornecedor = this.horizon.generateString(20);

		this.horizon.escolherStatus();
		driver.findElement(By.id("i-codigo")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(100)));
		driver.findElement(By.id("i-descricao")).sendKeys(fornecedor);

		driver.findElement(By.id("bt-salvar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();

		System.out.println("Fornecedor+ " + fornecedor + "					" + horizon.time(starttime));
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/documentacao/segquimica/pesquisa_docs_segquimica.html");

		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-gerenciar")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='adicionarDSQ()']"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		driver.findElement(By.id("bt-configuracoes")).click();

		driver.findElement(By.xpath("//a [@href='fornecedordsq.html']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@data-tooltip='Pesquisar']"))));
		this.horizon.waitLoad2();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(fornecedor);

		driver.findElement(By.xpath("//button [@data-tooltip='Pesquisar']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ fornecedor +"')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='excluirFornecedor()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='modalConfirmacaoExclusao()']"))));
		driver.findElement(By.xpath("//button [@ng-click='modalConfirmacaoExclusao()']")).click();

		this.horizon.waitLoad2();
	}
}
