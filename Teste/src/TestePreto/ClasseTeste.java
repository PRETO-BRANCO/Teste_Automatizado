package TestePreto;

import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import TestePreto.Signal;


public class ClasseTeste {
	static long starttime = System.currentTimeMillis();	
	static WebDriver driver;
	static WebDriverWait wdw;
	static Actions act;
	static int x;
	static String endereco;
	static String unidade;
	static String login;
	static String senha;
	
	public static void main(String[] args) {
	// TODO Auto-generated method stub
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
	Date date = new Date();
	String pasta = "Arquivos";
	File dir = new File(pasta); 
	dir.mkdir();
	String dataFormatada = dateFormat.format(date);
	//setting the driver executable
	System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");

	//Initiating your chromedriver
	WebDriver driver = new ChromeDriver();
	//Applied wait time
	wdw = new WebDriverWait(driver, 20);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//maximize window
	driver.manage().window().maximize();
	Signal horizonSignal = new Signal(driver,wdw);
	endereco = "empresa";
	
	//LOGIN
	Login logarAdm = new Login (driver, wdw, horizonSignal, starttime, endereco, "Adm1", "Empresa2019@@", dataFormatada, pasta);
	logarAdm.Logar();
	
	//GEST�O DE RISCOS
	TipoRisco tprisco1 = new TipoRisco(driver, wdw, horizonSignal, starttime, endereco, "Status", "Teste Automatizado", "Perigo", "Dano", "Probabilidade", "Impacto", "NR", dataFormatada, pasta);
	String resultado = tprisco1.criar();
	
	
	try {
		File f = new File(dir, dataFormatada+".txt");
		f.createNewFile();
		FileWriter fw = new FileWriter(f,true);
		BufferedWriter bw = new BufferedWriter(fw);
		if (resultado.equals("tpRisco Salvo")){
			bw.write("Mensagem exibida de forma correta!" + "\n");
			bw.close(); 
			fw.close();

		}if (resultado.equals("tpRisco existente")) {
			bw.write("Mensagem de desc. duplicada exibida!" + "\n");
			bw.close(); 
			fw.close();
		}
	}catch (IOException e) {
		e.printStackTrace();
	}
	TipoRisco tprisco2 = new TipoRisco(driver, wdw, horizonSignal, starttime, endereco, "Status", "Teste Automatizado", "Perigo", "Dano", "Probabilidade", "Impacto", "NR", dataFormatada, pasta);
	String resultado2 = tprisco2.criar();
	try {
		File f = new File(dir, dataFormatada+".txt");
		f.createNewFile();
		FileWriter fw = new FileWriter(f,true);
		BufferedWriter bw = new BufferedWriter(fw);
		if (resultado2.equals("tpRisco existente")){
			bw.write("Mensagem de desc. duplicada exibida!" + "\n");
			bw.close(); 
			fw.close();
		}if (resultado2.equals("erro")) {
			bw.write("Mensagem vermelha exibida!"+ "\n");
			bw.close(); 
			fw.close();
		}
	}catch (IOException e) {
		e.printStackTrace();
	}
}
}