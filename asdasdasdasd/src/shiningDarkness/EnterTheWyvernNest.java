package shiningDarkness;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnterTheWyvernNest {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private String ed;
	private String login;
	private String senha;

	public EnterTheWyvernNest(WebDriver driver, WebDriverWait wdw, String ed, String login, String senha) {		
		this.driver = driver;
		this.wdw = wdw;		
		this.ed = ed;
		this.login = login;
		this.senha = senha;
	}

	public void login() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/login.html");
		
		wdw.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input [@ng-model='user.username']"))));
		
		WebElement log = driver.findElement(By.xpath("//input [@ng-model='user.username']"));
		log.sendKeys(login);
		WebElement sen = driver.findElement(By.xpath("//input [@ng-model='user.password']"));
		sen.sendKeys(senha);	

		this.wdw.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button [@class='btn btn-primary btn-apollus btn-lg btn-block ng-binding']"))));
		this.driver.findElement(By.xpath("//button [@class='btn btn-primary btn-apollus btn-lg btn-block ng-binding']")).click();
	}
}