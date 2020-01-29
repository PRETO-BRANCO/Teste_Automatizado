package shiningDarkness;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Agendamento {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private String agendamento;

	public Agendamento(WebDriver driver, WebDriverWait wdw, Signal horizon,Long starttime,String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;
		this.starttime = starttime;
		this.ed = ed;
	}

	public void criar() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/si/cadastros/#/");

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//li [@ui-sref='/agendamento']")).click();

		this.horizon.goNovo();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao"))));

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		agendamento =this.horizon.generateString(20);		

		this.horizon.escolherStatus();
		driver.findElement(By.id("i-codigo")).sendKeys(cod);
		driver.findElement(By.id("i-descricao")).sendKeys(agendamento);

		this.horizon.salvar();

		System.out.println("Agendamento " + agendamento + "					" + horizon.time(starttime));
	}

	public void editar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(agendamento);

		this.horizon.selectFirst();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-codigo"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		Random rand = new Random();
		String cod = String.format("%04d",rand.nextInt(10000));
		agendamento = this.horizon.generateString(20);

		this.horizon.escolherStatus();
		driver.findElement(By.id("i-codigo")).sendKeys(cod);
		driver.findElement(By.id("i-descricao")).sendKeys(agendamento);

		this.horizon.salvar();

		System.out.println("Agendamento+ " + agendamento + "					" + horizon.time(starttime));
	}

	public void excluir() {	
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-descricao-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-descricao-filtro")).sendKeys(agendamento);

		this.horizon.excluir();
	}
}