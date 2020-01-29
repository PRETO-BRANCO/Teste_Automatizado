package shiningDarkness;

import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PontoDistribuicao {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private long starttime;	
	private String ed;
	private String unidade;
	private String ponto;
	
	public PontoDistribuicao(WebDriver driver, WebDriverWait wdw, Signal horizon,long starttime, String ed, String unidade) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;		
		this.starttime = starttime;
		this.ed = ed;
		this.unidade = unidade;
	}
	
	public void criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/modulos/#/so/gestaoepi/ponto-distribuicao");
		
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [contains(text(),'Novo') and (@class='btn apls-button')]"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();
		
		driver.findElement(By.xpath("//button [contains(text(),'Novo') and (@class='btn apls-button')]")).click();
		
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input [@formcontrolname='descricao']"))));
		
		ponto =this.horizon.generateString(200);
		int cod = ThreadLocalRandom.current().nextInt(1000000000);
		
		driver.findElement(By.xpath("//input [@formcontrolname='descricao']")).sendKeys(ponto);
		driver.findElement(By.xpath("//input [@formcontrolname='codigo']")).sendKeys(String.format("%08d",cod));
		
		for(int x=0; x<200;x++) {		
		driver.findElement(By.xpath("//input [@formcontrolname='nome']")).sendKeys(this.horizon.generateString(40));
		driver.findElement(By.xpath("//input [@formcontrolname='cpf']")).sendKeys("96570927019");
		driver.findElement(By.xpath("//input [@formcontrolname='email']")).sendKeys(this.horizon.generateString2(150) +"@"+ this.horizon.generateString2(49));
		driver.findElement(By.xpath("//input [@formcontrolname='telefone']")).sendKeys(String.format("%08d",cod));
		
		driver.findElement(By.xpath("//div [@class='apls-checkbox-inner-container']")).click();
		
		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar']")).click();		
		}
		
		driver.findElement(By.id("apls-tab-label-0-1")).click();
		
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//apls-select [@formcontrolname='area1']"))));
		this.horizon.sleep(350);
		this.horizon.waitLoad();
		
		driver.findElement(By.xpath("//apls-select [@formcontrolname='area1']")).click();
		driver.findElement(By.xpath("//span [contains(text(),'"+ unidade +"') and (@class='apls-option-text')]")).click();		

		this.horizon.waitLoad();
		
		driver.findElement(By.xpath("//button [@aplstooltip='Adicionar' and @class='btn apls-button']")).click();
		
		this.horizon.waitLoad();
		
		driver.findElement(By.xpath("//button [@class='btn-salvar btn apls-button']")).click();
		
		System.out.println("Ponto " + ponto + "				" + horizon.time(starttime));		
	}
}
