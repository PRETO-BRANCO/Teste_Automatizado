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

import TestePreto.Signal;

public class TipoRidsco_bkp {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private String endereco;
	private Long starttime;
	private String status;
	private String tipoRisco;
	private String idVar1;
	private String idVar2;
	private String avVar1;
	private String avVar2;
	private String avRes;
	
	public TipoRidsco_bkp(WebDriver driver, WebDriverWait wdw, Signal horizon, Long starttime, String endereco, String status, String tipoRisco, String idVar1, String idVar2, String avVar1, String avVar2, String avRes) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;
		this.endereco = endereco;
		this.starttime = starttime;
		this.status = status;
		this.tipoRisco = tipoRisco;
		this.idVar1 = idVar1;
		this.idVar2 = idVar2;
		this.avVar1 = avVar1;
		this.avVar2 = avVar2;
		this.avRes = avRes;
	}
	
	public String criar () {
		driver.get("https://"+ endereco +".apollusehs.com.br/apollus/modulos/#/ssma/aipr");
		this.horizon.waitLoad();
		WebElement cadastros = driver.findElement(By.xpath("/html/body/app-root/app-aplicacao/apls-gestao-home-riscos-raiz/main/apls-gestao-riscos-menu/apls-section/section/apls-menu-box/div/div/div[4]/div/div"));
		cadastros.click();
		this.horizon.waitLoad();
		WebElement tpRisco_cadastro = driver.findElement(By.xpath("/html/body/app-root/app-aplicacao/apls-gestao-home-riscos-raiz/main/apls-gestao-riscos-cadastros/apls-section/section/apls-menu-box/div/div/div[3]/div/div"));
		tpRisco_cadastro.click();
		this.horizon.waitLoad();
		
		WebElement btNovo = driver.findElement(By.xpath("//*[@id='btn-novo']"));
		btNovo.click();
		
		//PREENCHER CAMPOS
		this.horizon.waitLoad();
		
		driver.findElement(By.xpath("//* [contains(@class,'col-md-4 mt-2')]"));
		//WebElement tipoRiscos = driver.findElement(By.xpath("//* [contains(@class,'apls-input')]"));
		WebElement tipoRiscos = driver.findElement(By.tagName("input"));
		tipoRiscos.sendKeys(this.tipoRisco);
		this.horizon.waitLoad();
		
		//Identifica��o - Vari�vel 1
		WebElement select_identVar1 = driver.findElement(By.xpath("//*[@id='apls-select-7']/div/div[1]"));
		select_identVar1.click();
		//WebElement selecionar_IdentVar1 = driver.findElement(By.xpath("//*[@id='apls-option-32']/span"));
		//selecionar_IdentVar1.click();
		driver.findElement(By.xpath("//* [contains(@class,'apls-option-text') and contains(text(),'"+this.idVar1+"')]")).click();
		
		//Identifica��o - Vari�vel 2   
		WebElement select_identVar2 = driver.findElement(By.xpath("//*[@id='apls-select-8']/div/div[1]"));
		//this.horizon.waitLoad();
		select_identVar2.click();
		//this.horizon.waitLoad();
		driver.findElement(By.xpath("//* [contains(@class,'apls-option-text') and contains(text(),'"+this.idVar2+"')]")).click();

		//Avalia��o - Vari�vel 1
		WebElement select_avalVar = driver.findElement(By.xpath("//*[@id='apls-select-9']/div/div[1]"));
		select_avalVar.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//* [contains(@class,'apls-option-text') and contains(text(),'"+this.avVar1+"')]")).click();
		
		//Avalia��o - Vari�vel 2
		WebElement select_avalVar2 = driver.findElement(By.xpath("//*[@id='apls-select-10']/div/div[1]"));
		select_avalVar2.click();
		driver.findElement(By.xpath("//* [contains(@class,'apls-option-text') and contains(text(),'"+this.avVar2+"')]")).click();
		
		//Avalia��o - Resultante 
		WebElement select_avalResult = driver.findElement(By.xpath("//*[@id='apls-select-11']/div/div[1]"));
		select_avalResult.click();
		driver.findElement(By.xpath("//* [contains(@class,'apls-option-text') and contains(text(),'"+this.avRes+"')]")).click();
		
		//Salvar
		WebElement btSalvar = driver.findElement(By.xpath("//html/body/app-root/app-aplicacao/apls-gestao-home-riscos-raiz/main/apls-cadastro/footer/div[3]/button[2]"));
		btSalvar.click();
		
		WebElement div = driver.findElement(By.xpath("//* [contains(@class,'toast-title')]"));
		WebElement message = driver.findElement(By.xpath("//* [contains(@class,'toast-message')]"));
		String message2 = message.getAttribute("aria-label");
		this.horizon.waitLoad();
		//System.out.println(message2);
		//TakesScreenshot scrShot =((TakesScreenshot)driver);
		//File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		//File DestFile=new File("Documentos");
		if (message2.equals("Opera��o realizada com sucesso.")){
			System.out.println("foi");
			return "ok";
		} else {
			return "erro";
		}	
			
	}
}
