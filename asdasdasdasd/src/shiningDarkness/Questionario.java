package shiningDarkness;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Questionario {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Questoes quest;
	private	String quest1;
	private String quest2;
	private String quest3;
	private Long starttime;
	private String ed; 
	private String questionario;

	public Questionario(WebDriver driver, WebDriverWait wdw, Signal horizon, Long starttime,String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;			
		this.starttime = starttime;
		this.ed = ed;
		quest = new Questoes(driver,wdw,horizon,starttime, ed);
		quest1 = quest.criar0();
		quest2 = quest.criar1();
		quest3 = quest.criar2();
	}

	public void criar() {	
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/si/cadastros/#/");

		wdw.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//li [@ui-sref='/questionarios']"))));	
		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//li [@ui-sref='/questionarios']")).click();

		this.horizon.goNovo();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao"))));		

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));	
		questionario = this.horizon.generateString(33);

		driver.findElement(By.id("i-codigo")).sendKeys(cod);
		driver.findElement(By.id("i-descricao")).sendKeys(questionario);
		driver.findElement(By.id("ac-questao")).sendKeys(quest1);
		driver.findElement(By.xpath("//a [contains(text(),'"+ quest1 +"')]")).click();
		driver.findElement(By.xpath("//button [@data-ng-click='adicionarQQ()']")).click();

		this.horizon.salvar();

		System.out.println("Questionario " + questionario + "				" + horizon.time(starttime));
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(questionario);		

		this.horizon.selectFirst();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));	
		questionario = this.horizon.generateString(33);

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		this.horizon.escolherStatus();
		driver.findElement(By.id("i-codigo")).sendKeys(cod);
		driver.findElement(By.id("i-descricao")).sendKeys(questionario);
		driver.findElement(By.id("ac-questao")).sendKeys(quest2);
		driver.findElement(By.xpath("//a [contains(text(),'"+ quest2 +"')]")).click();

		driver.findElement(By.xpath("//button [@data-ng-click='adicionarQQ()']")).click();
		List<WebElement> lista =  driver.findElements(By.xpath("//a [@data-tooltip='Excluir']"));
		lista.get(0).click();

		this.horizon.salvar();

		System.out.println("Questionario+ " + questionario + "				" + horizon.time(starttime));

		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(questionario);

		this.horizon.selectFirst();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		cod = String.valueOf(rand.nextInt(10000));

		questionario = this.horizon.generateString(34);

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		this.horizon.escolherStatus();		
		driver.findElement(By.id("i-descricao")).sendKeys(questionario);
		driver.findElement(By.id("ac-questao")).sendKeys(quest3);
		driver.findElement(By.xpath("//a [contains(text(),'"+ quest3 +"')]")).click();

		driver.findElement(By.xpath("//button [@data-ng-click='adicionarQQ()']")).click();
		lista =  driver.findElements(By.xpath("//a [@data-tooltip='Editar']"));
		lista.get(0).click();
		driver.findElement(By.id("ddn-status-ques-cadastro")).click();	
		driver.findElement(By.xpath("//li [contains(@class,'ques-cadastro item-status-option status-inativo') and not(ancestor::div[contains(@style,'display:none')]) and not(ancestor::div[contains(@style,'display: none')])]")).click();
		driver.findElement(By.xpath("//button [@ng-click='salvarEdicaoQQ()']")).click();

		this.horizon.salvar();

		System.out.println("Questionario++ " + questionario + "			" + horizon.time(starttime));
	}

	public void excluir() {	
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(questionario);

		this.horizon.excluir();

		this.horizon.waitLoad();

		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/si/cadastros/#/questoes");

		this.horizon.waitLoad();

		quest.excluir();
	}
}