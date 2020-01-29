package TestePreto;
import java.util.Date;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
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

public class TipoRisco {
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
	static String nomeArquivo;
	static String dir;
	
	public TipoRisco(WebDriver driver, WebDriverWait wdw, Signal horizon, Long starttime, String endereco, String status, String tipoRisco, String idVar1, String idVar2, String avVar1, String avVar2, String avRes, String nomeArquivo, String dir) {
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
		this.nomeArquivo = nomeArquivo;
		this.dir = dir;
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
		
		long inicio = System.currentTimeMillis();
		WebElement btNovo = driver.findElement(By.xpath("//*[@id='btn-novo']"));
		btNovo.click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("input"))));
		long fim = System.currentTimeMillis();
		long tempoDecorrido = fim - inicio;
		long segundos = tempoDecorrido/1000;
		this.horizon.waitLoad();
		File dir = new File(this.dir); 
		dir.mkdir();
		
		try {
			File f = new File(dir, this.nomeArquivo+".txt");
			f.createNewFile();
			FileWriter fw = new FileWriter(f,true);
			BufferedWriter bw = new BufferedWriter(fw);
				bw.write("Tempo de resposta ao clicar no bot�o Novo no cad. de Tipo de Risco: " + segundos + " segundo(s).\n");
				bw.close(); 
				fw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		 
		//PREENCHER CAMPOS
		this.horizon.waitLoad();
		
		//driver.findElement(By.xpath("//* [contains(@class,'col-md-4 mt-2')]"));
		//WebElement tipoRiscos = driver.findElement(By.xpath("//* [contains(@class,'apls-input')]"));
		WebElement tipoRiscos = driver.findElement(By.tagName("input"));
		tipoRiscos.sendKeys(this.tipoRisco);
		this.horizon.waitLoad();
		
		List<WebElement> lista = driver.findElements(By.xpath("//* [contains(@id, 'apls-select-')]"));
		lista.get(1).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//* [contains(text(),'"+this.idVar1+"')]"))));
		driver.findElement(By.xpath("//* [contains(text(),'"+this.idVar1+"')]")).click();
		lista.get(2).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//* [contains(text(),'"+this.idVar2+"')]"))));
		driver.findElement(By.xpath("//* [contains(text(),'"+this.idVar2+"')]")).click();
		lista.get(3).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//* [contains(text(),'"+this.avVar1+"')]"))));
		driver.findElement(By.xpath("//* [contains(text(),'"+this.avVar1+"')]")).click();
		lista.get(4).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//* [contains(text(),'"+this.avVar2+"')]"))));
		driver.findElement(By.xpath("//* [contains(text(),'"+this.avVar2+"')]")).click();
		lista.get(5).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//* [contains(text(),'"+this.avRes+"')]"))));
		driver.findElement(By.xpath("//* [contains(text(),'"+this.avRes+"')]")).click();
		
		//Salvar
		WebElement btSalvar = driver.findElement(By.xpath("//html/body/app-root/app-aplicacao/apls-gestao-home-riscos-raiz/main/apls-cadastro/footer/div[3]/button[2]"));
		btSalvar.click();
		
		WebElement message = driver.findElement(By.xpath("//* [contains(@class,'toast-message')]"));
		String message2 = message.getAttribute("aria-label");
		this.horizon.waitLoad();
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
			String dataFormat = dateFormat.format(date);
			FileUtils.copyFile(src, new File("Arquivos/"+dataFormat+".png"));
		}catch (IOException e) {
			e.printStackTrace();
		}
		if (message2.equals("Opera��o realizada com sucesso.")){
			return "tpRisco Salvo";
		}if (message2.equals("J� existe um Tipo de Risco cadastrado com essa descri��o. Favor verificar.")){
			return "tpRisco existente";
		}else{
			return "erro";
		}	
	}
}
