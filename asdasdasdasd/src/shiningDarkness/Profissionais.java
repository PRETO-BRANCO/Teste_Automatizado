package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Profissionais {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private String ed;

	public Profissionais(WebDriver driver, WebDriverWait wdw, Signal horizon, String ed) {
		this.driver = driver;
		this.wdw = wdw;
		this.horizon = horizon;
		this.ed = ed;
	}

	public void criar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/si/cadastros/#/profissionais");

		this.horizon.goNovo();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ac-pessoa"))));		

		driver.findElement(By.id("ac-pessoa")).sendKeys("Epy");	
		driver.findElement(By.xpath("//li [contains(text(),'Master Epy')]")).click();
		driver.findElement(By.id("ck-medico")).click();
		driver.findElement(By.id("i-registro")).sendKeys(String.format("%07d",ThreadLocalRandom.current().nextInt(10000000)));
		Select slek = new Select(driver.findElement(By.id("cb-orgao")));
		slek.selectByValue(String.valueOf(1));
		slek = new Select(driver.findElement(By.id("cb-uf")));
		slek.selectByValue("RS");
		driver.findElement(By.id("i-cargo")).sendKeys(this.horizon.generateString(25));	
		driver.findElement(By.id("i-endereco")).sendKeys(this.horizon.generateString(150));	
		driver.findElement(By.id("i-telefone")).sendKeys(String.format("%01d",ThreadLocalRandom.current().nextInt(10)));

		driver.findElement(By.xpath("//label [@for='ck-dom']")).click();
		driver.findElement(By.id("i-dom-inicio")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(7,11)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
		driver.findElement(By.id("i-dom-fim")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(15,21)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));

		driver.findElement(By.xpath("//label [@for='ck-seg']")).click();
		driver.findElement(By.id("i-seg-inicio")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(7,11)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
		driver.findElement(By.id("i-seg-fim")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(15,21)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));

		driver.findElement(By.xpath("//label [@for='ck-ter']")).click();
		driver.findElement(By.id("i-ter-inicio")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(7,11)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
		driver.findElement(By.id("i-ter-fim")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(15,21)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));

		driver.findElement(By.xpath("//label [@for='ck-qua']")).click();
		driver.findElement(By.id("i-qua-inicio")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(7,11)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
		driver.findElement(By.id("i-qua-fim")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(15,21)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));

		driver.findElement(By.xpath("//label [@for='ck-qui']")).click();
		driver.findElement(By.id("i-qui-inicio")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(7,11)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
		driver.findElement(By.id("i-qui-fim")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(15,21)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));

		driver.findElement(By.xpath("//label [@for='ck-sex']")).click();
		driver.findElement(By.id("i-sex-inicio")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(7,11)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
		driver.findElement(By.id("i-sex-fim")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(15,21)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));

		driver.findElement(By.xpath("//label [@for='ck-sab']")).click();
		driver.findElement(By.id("i-sab-inicio")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(7,11)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
		driver.findElement(By.id("i-sab-fim")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(15,21)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));

		driver.findElement(By.id("i-tempo-padrao")).sendKeys("00",String.format("%02d", 5* ThreadLocalRandom.current().nextInt(1,11)));

		driver.findElement(By.id("i-intervalo-inicio")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(11,13)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
		driver.findElement(By.id("i-intervalo-fim")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(13,15)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));

		this.horizon.salvar();		

		this.horizon.waitLoad();
	}

	public void editar() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/si/cadastros/#/profissionais");

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-profissional-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-profissional-filtro")).sendKeys("Master Epy");

		this.horizon.showItens();

		this.horizon.waitLoad();

		driver.findElement(By.xpath("//td [contains(text(),'Master Epy')]")).click();			
		driver.findElement(By.xpath("//button [@data-ng-click='editarProfissionalMedicina()']")).click();					

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ck-medico"))));
		this.horizon.sleep(750);
		this.horizon.waitLoad();

		driver.findElement(By.id("ck-medico")).click();		
		Select slek = new Select(driver.findElement(By.id("cb-orgao")));
		slek.selectByValue(String.valueOf(ThreadLocalRandom.current().nextInt(1,9)));		
		driver.findElement(By.id("i-cargo")).sendKeys(this.horizon.generateString(25));	
		driver.findElement(By.id("i-registro")).sendKeys(String.format("%07d",ThreadLocalRandom.current().nextInt(10000000)));

		List<WebElement> lista = driver.findElements(By.xpath("//label [contains(@for,'ck-')]"));
		for(int i = 0;i<lista.size();i++) {
			lista.get(i).click();
		}

		switch(ThreadLocalRandom.current().nextInt(1,8)) {
		case 1:
			driver.findElement(By.xpath("//label [@for='ck-dom']")).click();
			driver.findElement(By.id("i-dom-inicio")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(7,11)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
			driver.findElement(By.id("i-dom-fim")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(15,21)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
			break;

		case 2:
			driver.findElement(By.xpath("//label [@for='ck-seg']")).click();
			driver.findElement(By.id("i-seg-inicio")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(7,11)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
			driver.findElement(By.id("i-seg-fim")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(15,21)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
			break;

		case 3:
			driver.findElement(By.xpath("//label [@for='ck-ter']")).click();
			driver.findElement(By.id("i-ter-inicio")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(7,11)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
			driver.findElement(By.id("i-ter-fim")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(15,21)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
			break;

		case 4:
			driver.findElement(By.xpath("//label [@for='ck-qua']")).click();
			driver.findElement(By.id("i-qua-inicio")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(7,11)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
			driver.findElement(By.id("i-qua-fim")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(15,21)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
			break;

		case 5:
			driver.findElement(By.xpath("//label [@for='ck-qui']")).click();
			driver.findElement(By.id("i-qui-inicio")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(7,11)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
			driver.findElement(By.id("i-qui-fim")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(15,21)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
			break;

		case 6:
			driver.findElement(By.xpath("//label [@for='ck-sex']")).click();
			driver.findElement(By.id("i-sex-inicio")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(7,11)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
			driver.findElement(By.id("i-sex-fim")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(15,21)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
			break;

		case 7:
			driver.findElement(By.xpath("//label [@for='ck-sab']")).click();
			driver.findElement(By.id("i-sab-inicio")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(7,11)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
			driver.findElement(By.id("i-sab-fim")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(15,21)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
			break;
		}

		driver.findElement(By.id("i-tempo-padrao")).sendKeys("00",String.format("%02d", 5* ThreadLocalRandom.current().nextInt(1,11)));

		driver.findElement(By.id("i-intervalo-inicio")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(11,13)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));
		driver.findElement(By.id("i-intervalo-fim")).sendKeys(String.format("%02d", ThreadLocalRandom.current().nextInt(13,15)),String.format("%02d", ThreadLocalRandom.current().nextInt(60)));

		this.horizon.salvar();

	}

	public void excluir() {
		driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/si/cadastros/#/profissionais");

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-profissional-filtro"))));	
		this.horizon.sleep(350);
		this.horizon.waitLoad();

		driver.findElement(By.id("i-profissional-filtro")).sendKeys("Master Epy");

		this.horizon.excluir();		

		this.horizon.waitLoad();
	}
}