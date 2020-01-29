package TestePreto;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Signal {
	private WebDriver driver;
	private WebDriverWait wdw;
	private Actions act;

	public Signal(WebDriver driver, WebDriverWait wdw) {		
		this.driver = driver;
		this.wdw = wdw;
		this.act = new Actions(driver);
	}

	public String time(long starttime) {
		return 	(((System.currentTimeMillis()-starttime)/1000)/3600) + ":"+ (((System.currentTimeMillis()-starttime)/1000)%3600/60) +":"+ (((System.currentTimeMillis()-starttime)/1000)%3600%60)+"."+((((System.currentTimeMillis()-starttime)%3600000)%60000)%1000);
	}

	public void sleep(long mil) {
		try {
			Thread.sleep(mil);
		} catch (InterruptedException e) {			
		}
	}

	public String generateString(int lenght) {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = lenght;
		boolean flag = true;

		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);

		for (int i = 0; i < targetStringLength-1; i++) {
			if(random.nextInt(5) == 0 && !flag) {
				int randomLimitedInt = 32;
				buffer.append((char) randomLimitedInt);
				flag = true;
			}
			else {
				int randomLimitedInt = leftLimit + (int) 
						(random.nextFloat() * (rightLimit - leftLimit + 1));
				buffer.append((char) randomLimitedInt);
				flag = false;
			}
		}
		int randomLimitedInt = leftLimit + (int) 
				(random.nextFloat() * (rightLimit - leftLimit + 1));
		buffer.append((char) randomLimitedInt);
		return buffer.toString();
	}

	public String generateString2(int lenght) {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = lenght;

		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);

		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) 
					(random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		return buffer.toString();
	}

	public void goNovo() {
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-novo']"))));
		this.waitLoad();

		this.driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-novo']")).click();
	}

	public void showItens() {
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary tooltip-left\"]"))));
		this.waitLoad();

		WebElement ele = driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary tooltip-left\"]"));
		ele.click();
	}

	public void selectFirst() {	
		this.showItens();

		this.waitLoad();

		List<WebElement> lista = driver.findElements(By.xpath("//tr [contains(@class,'ng-scope')]"));		
		this.act.moveToElement(lista.get(0)).doubleClick().perform();

		this.sleep(500);
		this.waitLoad();
	}

	public void excluir() {
		this.showItens();	

		this.waitLoad();

		List<WebElement> lista = driver.findElements(By.xpath("//tr [contains(@class,'ng-scope')]"));			

		this.sleep(500);		

		this.act.moveToElement(lista.get(0)).click().perform();
		this.driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default pull-right bt-excluir ng-scope\"]")).click();
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='modalConfirmacaoExclusao()']"))));
		this.driver.findElement(By.xpath("//button [@onclick='modalConfirmacaoExclusao()']")).click();
	}

	public void salvar() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary pull-right\"]"))));
		this.driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-primary pull-right\"]")).click();
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao lb_nao\"]"))));
		this.driver.findElement(By.cssSelector("button[class=\"btn btn-sm btn-default margin-left btn-nao lb_nao\"]")).click();
	}

	public void escolherStatus() {
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ddn-status-cadastro"))));		

		WebElement tog = driver.findElement(By.id("ddn-status-cadastro"));		
		List<WebElement> lista = driver.findElements(By.xpath("//li [contains(@class,'cadastro item-status-option') and not(ancestor::div[contains(@style,'display:none')]) and not(ancestor::div[contains(@style,'display: none')])]"));	
		tog.click();
		lista.get(ThreadLocalRandom.current().nextInt(2)).click();				
	}

	public void waitLoad() {
		try {
			this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//img [@class='loader-apollus']"))));
		}catch(Exception e) {			
		}
	}

	public void waitLoad2() {
		try {
			this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("pre-loader"))));
			this.wdw.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div [contains(@class,'bg-modal-fake fade in')]"))));
		}catch(Exception e) {			
		}
	}
}