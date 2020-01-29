package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Classe {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;	
	private String classe;

	public Classe(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime, String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.starttime = starttime;
		this.ed = ed;
	}

	public String criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/equipamento/classe.html");

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//button [@ng-click='adicionarClasse()']")).click();		

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		classe = this.horizon.generateString(15);

		driver.findElement(By.id("codigo")).sendKeys(String.format("%08d", ThreadLocalRandom.current().nextInt(100000000)));
		driver.findElement(By.id("descricao")).sendKeys(classe);
		Select slek = new Select(driver.findElement(By.id("cbCategoria")));
		List<WebElement> lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));

		driver.findElement(By.id("acCampo")).sendKeys("cor");
		driver.findElement(By.xpath("//li [contains(text(),'Cor') and contains(@class,'ui-menu-item')]")).click();
		driver.findElement(By.xpath("//button [@data-tooltip='Adicionar']")).click();

		driver.findElement(By.id("acCampo")).sendKeys("Marca");
		driver.findElement(By.xpath("//li [contains(text(),'Marca') and contains(@class,'ui-menu-item')]")).click();
		driver.findElement(By.xpath("//button [@data-tooltip='Adicionar']")).click();

		driver.findElement(By.id("acCampo")).sendKeys("Modelo");
		driver.findElement(By.xpath("//li [contains(text(),'Modelo') and contains(@class,'ui-menu-item')]")).click();
		driver.findElement(By.xpath("//button [@data-tooltip='Adicionar']")).click();

		driver.findElement(By.id("acCampo")).sendKeys("alfa");
		driver.findElement(By.xpath("//li [contains(text(),'alfa') and contains(@class,'ui-menu-item')]")).click();
		driver.findElement(By.xpath("//button [@data-tooltip='Adicionar']")).click();

		driver.findElement(By.id("acCampo")).sendKeys("Num");
		driver.findElement(By.xpath("//li [contains(text(),'Num') and contains(@class,'ui-menu-item')]")).click();
		driver.findElement(By.xpath("//button [@data-tooltip='Adicionar']")).click();

		driver.findElement(By.id("bt-salvar")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();		

		System.out.println("Classe " + classe + "							" + horizon.time(starttime));
		return classe;
	}

	public void editar() {
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='buscarClasses(1, true)']"))));

		driver.findElement(By.id("iDescricao")).sendKeys(classe);

		driver.findElement(By.xpath("//button [@ng-click='buscarClasses(1, true)']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ classe +"')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='editarClasse()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad2();

		classe = this.horizon.generateString(15);

		driver.findElement(By.id("descricao")).sendKeys(classe);
		Select slek = new Select(driver.findElement(By.id("cbCategoria")));
		List<WebElement> lista = slek.getOptions();
		slek.selectByIndex(ThreadLocalRandom.current().nextInt(1,lista.size()));

		driver.findElement(By.id("bt-salvar")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad2();		

		System.out.println("Classe+ " + classe + "							" + horizon.time(starttime));
	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/cadastro/equipamento/classe.html");

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='buscarClasses(1, true)']"))));
		this.horizon.waitLoad2();	

		driver.findElement(By.id("iDescricao")).sendKeys(classe);

		driver.findElement(By.xpath("//button [@ng-click='buscarClasses(1, true)']")).click();

		this.horizon.waitLoad2();

		driver.findElement(By.xpath("//td [contains(text(),'"+ classe +"')]")).click();

		driver.findElement(By.xpath("//button [@ng-click='excluirClasse()']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='modalConfirmacaoExclusao()']"))));
		driver.findElement(By.xpath("//button [@ng-click='modalConfirmacaoExclusao()']")).click();
	}
}