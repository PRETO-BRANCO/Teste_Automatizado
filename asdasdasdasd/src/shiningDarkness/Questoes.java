package shiningDarkness;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Questoes {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Actions act;
	private Long starttime;
	private String ed;
	private String questao1;
	private String questao2;
	private String questao3;

	public Questoes(WebDriver driver, WebDriverWait wdw, Signal horizon, Long starttime, String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;
		act = new Actions(driver);	
		this.starttime = starttime;
		this.ed = ed;
	}

	public String criar0() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/si/cadastros/#/");

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//li [@ui-sref='/questoes']")).click();

		this.horizon.goNovo();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-questao"))));

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		questao1 = this.horizon.generateString(100);

		driver.findElement(By.id("i-codigo")).sendKeys(cod);
		driver.findElement(By.id("i-questao")).sendKeys(questao1);

		Select tipo = new Select(driver.findElement(By.id("cb-tipo")));
		tipo.selectByValue("1");

		this.horizon.salvar();

		System.out.println("Questao 1 " + questao1 + "	" + horizon.time(starttime));
		return questao1;
	}

	public String criar1() {
		this.horizon.goNovo();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-questao"))));

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		questao2 = this.horizon.generateString(100);		

		driver.findElement(By.id("i-codigo")).sendKeys(cod);
		driver.findElement(By.id("i-questao")).sendKeys(questao2);
		Select tipo = new Select(driver.findElement(By.id("cb-tipo")));
		tipo.selectByValue("2");
		driver.findElement(By.id("i-alt-descricao")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary alt-form tooltip-top ng-scope\"]")).click();
		driver.findElement(By.id("i-alt-descricao")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("ck-alt-incluir")).click();
		driver.findElement(By.id("i-alt-descritivo")).sendKeys(this.horizon.generateString(60));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary alt-form tooltip-top ng-scope\"]")).click();

		this.horizon.salvar();

		System.out.println("Questao 2 " + questao2 + "	" + horizon.time(starttime));
		return questao2;
	}

	public String criar2() {
		this.horizon.goNovo();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-questao"))));

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		questao3 = this.horizon.generateString(100);		

		driver.findElement(By.id("i-codigo")).sendKeys(cod);
		driver.findElement(By.id("i-questao")).sendKeys(questao3);
		Select tipo = new Select(driver.findElement(By.id("cb-tipo")));
		tipo.selectByValue("3");
		driver.findElement(By.id("i-alt-descricao")).sendKeys(this.horizon.generateString(50));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary alt-form tooltip-top ng-scope\"]")).click();
		driver.findElement(By.id("i-alt-descricao")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("ck-alt-incluir")).click();
		driver.findElement(By.id("i-alt-descritivo")).sendKeys(this.horizon.generateString(60));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary alt-form tooltip-top ng-scope\"]")).click();

		this.horizon.salvar();

		System.out.println("Questao 3 " + questao3 + "	" + horizon.time(starttime));
		return questao3;
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-questao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-questao-filtro")).sendKeys(questao1);
		Select tipo = new Select(driver.findElement(By.id("cb-tipo-filtro")));
		tipo.selectByValue("1");

		this.horizon.selectFirst();		

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		questao1 = this.horizon.generateString(100);

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		this.horizon.escolherStatus();
		driver.findElement(By.id("i-codigo")).sendKeys(cod);
		driver.findElement(By.id("i-questao")).sendKeys(questao1);

		this.horizon.salvar();	

		System.out.println("Questao 1+ " + questao1 + "	" + horizon.time(starttime));

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-questao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-questao-filtro")).sendKeys(questao1);
		tipo = new Select(driver.findElement(By.id("cb-tipo-filtro")));
		tipo.selectByValue("1");

		this.horizon.excluir();

		this.horizon.waitLoad();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-questao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-questao-filtro")).clear();
		driver.findElement(By.id("i-questao-filtro")).sendKeys(questao2);
		tipo = new Select(driver.findElement(By.id("cb-tipo-filtro")));
		tipo.selectByValue("2");

		this.horizon.selectFirst();		

		cod = String.format("%04d",rand.nextInt(10000));
		questao2 = this.horizon.generateString(100);

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		this.horizon.escolherStatus();
		driver.findElement(By.id("i-codigo")).sendKeys(cod);
		driver.findElement(By.id("i-questao")).sendKeys(questao2);
		driver.findElement(By.id("i-alt-descricao")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary alt-form tooltip-top ng-scope\"]")).click();
		List<WebElement> lista =  driver.findElements(By.xpath("//a [@data-tooltip='Excluir']"));
		lista.get(0).click();

		this.horizon.waitLoad();

		this.horizon.salvar();

		System.out.println("Questao 2+ " + questao2 + "	" + horizon.time(starttime));

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-questao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-questao-filtro")).sendKeys(questao2);
		tipo = new Select(driver.findElement(By.id("cb-tipo-filtro")));
		tipo.selectByValue("2");

		this.horizon.excluir();

		this.horizon.waitLoad();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-questao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-questao-filtro")).clear();
		driver.findElement(By.id("i-questao-filtro")).sendKeys(questao3);
		tipo = new Select(driver.findElement(By.id("cb-tipo-filtro")));
		tipo.selectByValue("3");

		this.horizon.selectFirst();		

		cod = String.format("%04d",rand.nextInt(10000));
		questao3 = this.horizon.generateString(100);	

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		this.horizon.escolherStatus();
		driver.findElement(By.id("i-codigo")).sendKeys(cod);
		driver.findElement(By.id("i-questao")).sendKeys(questao3);
		driver.findElement(By.id("i-alt-descricao")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary alt-form tooltip-top ng-scope\"]")).click();
		lista =  driver.findElements(By.xpath("//a [@data-tooltip='Editar']"));
		lista.get(0).click();		

		this.horizon.waitLoad();

		driver.findElement(By.id("i-alt-descricao")).sendKeys(this.horizon.generateString(50));
		act.moveToElement(driver.findElement(By.id("ddn-status-alt-cadastro"))).click().moveByOffset(0, 50).click().perform();
		driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary alt-form tooltip-top ng-scope\"]")).click();;

		this.horizon.waitLoad();

		this.horizon.salvar();

		System.out.println("Questao 3+ " + questao3 + "	" + horizon.time(starttime));

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-questao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-questao-filtro")).sendKeys(questao3);
		tipo = new Select(driver.findElement(By.id("cb-tipo-filtro")));
		tipo.selectByValue("3");

		this.horizon.excluir();
	}

	public void excluir() {	
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-questao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-questao-filtro")).sendKeys(questao1);
		Select tipo = new Select(driver.findElement(By.id("cb-tipo-filtro")));
		tipo.selectByValue("1");

		this.horizon.excluir();

		this.horizon.waitLoad();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-questao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-questao-filtro")).clear();
		driver.findElement(By.id("i-questao-filtro")).sendKeys(questao2);
		tipo = new Select(driver.findElement(By.id("cb-tipo-filtro")));
		tipo.selectByValue("2");

		this.horizon.excluir();

		this.horizon.waitLoad();

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-questao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-questao-filtro")).clear();
		driver.findElement(By.id("i-questao-filtro")).sendKeys(questao3);
		tipo = new Select(driver.findElement(By.id("cb-tipo-filtro")));
		tipo.selectByValue("3");

		this.horizon.excluir();
	}
}