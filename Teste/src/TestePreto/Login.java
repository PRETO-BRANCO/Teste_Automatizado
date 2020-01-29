package TestePreto;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Login {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private String endereco;
	private Long starttime;
	static String login;
	static String senha;
	static String nomeArquivo;
	static String dir;
	
	
	public Login(WebDriver driver, WebDriverWait wdw, Signal horizon, Long starttime, String endereco, String login, String senha, String nomeArquivo, String dir) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;
		this.endereco = endereco;
		this.starttime = starttime;
		this.login = login;
		this.senha = senha;
		this.nomeArquivo = nomeArquivo;
		this.dir = dir;
		
	}

public void Logar() {
	driver.get("https://"+ endereco +".apollusehs.com.br/apollus/login.html");
	this.horizon.waitLoad();
	
	WebElement login = driver.findElement(By.xpath("//*[@id='modalLogin']/div/div/div/div/div[1]/div/div[2]/form/div[1]/div[1]/input"));
	login.sendKeys(this.login);
	
	WebElement senha = driver.findElement(By.xpath("//*[@id='modalLogin']/div/div/div/div/div[1]/div/div[2]/form/div[1]/div[2]/input"));
	senha.sendKeys(this.senha);
	
	long inicio = System.currentTimeMillis();
	driver.findElement(By.xpath("//*[@id='modalLogin']/div/div/div/div/div[1]/div/div[2]/form/button[2]")).click();
	wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//* [contains(@class,'mandala-2')]"))));
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
			bw.write("Tempo de resposta no Login:" + segundos + " segundos.\n");
			bw.close(); 
			fw.close();
	}catch (IOException e) {
		e.printStackTrace();
	}

}
}