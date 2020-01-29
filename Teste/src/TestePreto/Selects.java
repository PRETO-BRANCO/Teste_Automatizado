package TestePreto;

import java.util.List;
import org.openqa.selenium.OutputType;
import java.io.File;
import org.openqa.selenium.TakesScreenshot;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selects {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private WebElement campo;
	private WebElement opcao;
	
	public Selects(WebDriver driver, WebDriverWait wdw, Signal horizon, Long starttime, WebElement campo, WebElement opcao){
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;
		this.starttime = starttime;
		this.campo = campo;
		this.opcao = opcao;
	}
	
	public String selecionar() {
		
		return "ok";
	}
}



