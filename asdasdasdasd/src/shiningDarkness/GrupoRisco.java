package shiningDarkness;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GrupoRisco {

	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String grupo;

	public GrupoRisco(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime,String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.starttime = starttime;
		this.ed = ed;
	}

	public String criar() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/si/cadastros/#/");

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//li [@ui-sref='/gruporisco']")).click();

		this.horizon.goNovo();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao"))));

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		grupo = this.horizon.generateString(50);	

		this.driver.findElement(By.id("i-codigo")).sendKeys(cod);
		this.driver.findElement(By.id("i-descricao")).sendKeys(grupo);
		this.driver.findElement(By.xpath("//input[@ng-model = 'grupoRisco.restrito']")).click();
		this.driver.findElement(By.id("ck-aso")).click();
		this.driver.findElement(By.id("i-aso")).sendKeys(this.horizon.generateString(1000));

		this.driver.findElement(By.xpath("//button [@ng-click='salvarCadastro()']")).click();
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao lb_nao\"]"))));
		this.driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao lb_nao\"]")).click();

		System.out.println("Grupo " + grupo + "		" + horizon.time(starttime));
		return grupo;
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(grupo);

		this.horizon.showItens();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'" + grupo +"')]")).click();

		this.driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary pull-right bt-visualizar ng-scope\"]")).click();		

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao"))));
		this.horizon.sleep(500);
		this.horizon.waitLoad();

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		grupo = this.horizon.generateString(50);

		this.horizon.escolherStatus();
		this.driver.findElement(By.id("i-codigo")).sendKeys(cod);
		this.driver.findElement(By.id("i-descricao")).sendKeys(grupo);
		this.driver.findElement(By.xpath("//input[@ng-model = 'grupoRisco.restrito']")).click();
		this.driver.findElement(By.id("ck-aso")).click();

		this.driver.findElement(By.xpath("//button [@ng-click='salvarCadastro()']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao lb_nao\"]"))));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao lb_nao\"]")).click();

		System.out.println("Grupo+ " + grupo + "		" + horizon.time(starttime));
	}

	public void excluir() {		
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(grupo);

		this.horizon.excluir();
	}
}