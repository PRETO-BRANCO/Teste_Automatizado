package shiningDarkness;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuestArg {
	private WebDriver driver;
	private WebDriverWait wdw;	
	private Signal horizon;
	private Long starttime;
	private String ed;
	private Actions act;
	private Categorias categoria;
	private String cat;

	public QuestArg(WebDriver driver, WebDriverWait wdw,Signal horizon,Long starttime,String ed) {		
		this.driver = driver;
		this.wdw = wdw;		
		this.horizon = horizon;
		this.starttime = starttime;
		this.ed = ed;
		this.act = new Actions(driver);
		categoria = new Categorias(driver, wdw, horizon, starttime, ed);
		cat = categoria.criar();
	}

	public void criar() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/analise/#/");	

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//a [@ui-sref='questoes']")).click();

		this.horizon.waitLoad();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@ng-click='onClickBtnAdicionar()']"))));

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnAdicionar()']")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-questao"))));

		String quest = this.horizon.generateString(100);			

		driver.findElement(By.id("i-codigo")).sendKeys("0" + String.format("%05d", ThreadLocalRandom.current().nextInt(100000)));
		driver.findElement(By.id("i-questao")).sendKeys(quest);
		driver.findElement(By.id("ta-dica")).sendKeys(this.horizon.generateString(350));
		Select slek = new Select(driver.findElement(By.id("cb-variaveis")));
		slek.selectByValue("3");		
		slek = new Select(driver.findElement(By.id("cb-categoria-questao")));		
		slek.selectByVisibleText(cat);

		driver.findElement(By.id("i-variavel-1")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("i-peso-variavel-1")).sendKeys(String.format("%02.2f", ThreadLocalRandom.current().nextFloat()*100));
		driver.findElement(By.id("i-resposta-1")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("i-nota-resposta-1")).sendKeys(String.format("%02.04f", ThreadLocalRandom.current().nextDouble()*10));
		driver.findElement(By.xpath("//button [@ng-show='!controleTela.isEditandoVariavel1']")).click();
		driver.findElement(By.id("i-resposta-1")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("i-nota-resposta-1")).sendKeys(String.format("%02.04f", ThreadLocalRandom.current().nextDouble()*10));
		driver.findElement(By.xpath("//button [@ng-show='!controleTela.isEditandoVariavel1']")).click();
		driver.findElement(By.id("i-resposta-1")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("i-nota-resposta-1")).sendKeys(String.format("%02.04f", ThreadLocalRandom.current().nextDouble()*10));
		driver.findElement(By.xpath("//button [@ng-show='!controleTela.isEditandoVariavel1']")).click();

		driver.findElement(By.id("bt-variavel-2")).click();
		driver.findElement(By.id("i-variavel-2")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("i-peso-variavel-2")).sendKeys(String.format("%02.2f", ThreadLocalRandom.current().nextFloat()*100));
		driver.findElement(By.id("i-resposta-2")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("i-nota-resposta-2")).sendKeys(String.format("%02.04f", ThreadLocalRandom.current().nextDouble()*10));
		driver.findElement(By.xpath("//button [@ng-show='!controleTela.isEditandoVariavel2']")).click();
		driver.findElement(By.id("i-resposta-2")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("i-nota-resposta-2")).sendKeys(String.format("%02.04f", ThreadLocalRandom.current().nextDouble()*10));
		driver.findElement(By.xpath("//button [@ng-show='!controleTela.isEditandoVariavel2']")).click();
		driver.findElement(By.id("i-resposta-2")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("i-nota-resposta-2")).sendKeys(String.format("%02.04f", ThreadLocalRandom.current().nextDouble()*10));
		driver.findElement(By.xpath("//button [@ng-show='!controleTela.isEditandoVariavel2']")).click();

		driver.findElement(By.id("bt-variavel-3")).click();
		driver.findElement(By.id("i-variavel-3")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("i-peso-variavel-3")).sendKeys(String.format("%02.2f", ThreadLocalRandom.current().nextFloat()*100));
		driver.findElement(By.id("i-resposta-3")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("i-nota-resposta-3")).sendKeys(String.format("%02.04f", ThreadLocalRandom.current().nextDouble()*10));
		driver.findElement(By.xpath("//button [@ng-show='!controleTela.isEditandoVariavel3']")).click();
		driver.findElement(By.id("i-resposta-3")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("i-nota-resposta-3")).sendKeys(String.format("%02.04f", ThreadLocalRandom.current().nextDouble()*10));
		driver.findElement(By.xpath("//button [@ng-show='!controleTela.isEditandoVariavel3']")).click();
		driver.findElement(By.id("i-resposta-3")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.id("i-nota-resposta-3")).sendKeys(String.format("%02.04f", ThreadLocalRandom.current().nextDouble()*10));
		driver.findElement(By.xpath("//button [@ng-show='!controleTela.isEditandoVariavel3']")).click();

		driver.findElement(By.id("lb-recomendacoes")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-nota-recomendacao-inicial"))));

		double f = ThreadLocalRandom.current().nextDouble();

		driver.findElement(By.id("i-nota-recomendacao-inicial")).sendKeys(String.format("%02.4f", ThreadLocalRandom.current().nextDouble(f)*10));
		driver.findElement(By.id("i-nota-recomendacao-final")).sendKeys(String.format("%02.4f", f*10));
		driver.findElement(By.id("ta-recomendacao")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSalvarRecomendacao()']")).click();

		f = ThreadLocalRandom.current().nextDouble();

		driver.findElement(By.id("i-nota-recomendacao-inicial")).sendKeys(String.format("%02.4f", ThreadLocalRandom.current().nextDouble(f)*10));
		driver.findElement(By.id("i-nota-recomendacao-final")).sendKeys(String.format("%02.4f", f*10));
		driver.findElement(By.id("ta-recomendacao")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSalvarRecomendacao()']")).click();

		f = ThreadLocalRandom.current().nextDouble();

		driver.findElement(By.id("i-nota-recomendacao-inicial")).sendKeys(String.format("%02.4f", ThreadLocalRandom.current().nextDouble(f)*10));
		driver.findElement(By.id("i-nota-recomendacao-final")).sendKeys(String.format("%02.4f", f*10));
		driver.findElement(By.id("ta-recomendacao")).sendKeys(this.horizon.generateString(100));
		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSalvarRecomendacao()']")).click();

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSalvar()']")).click();
		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']"))));
		driver.findElement(By.xpath("//button [@onclick='tratarNaoAlertaCadastro()']")).click();

		this.horizon.waitLoad();

		System.out.println("Questao " + quest + " " + horizon.time(starttime));
	}

	public void editar() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/analise/#/");	

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//a [@ui-sref='questoes']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("bt-pesquisar")).click();

		this.horizon.waitLoad();

		List<WebElement> lista = driver.findElements(By.xpath("//tr [contains(@class,'ng-scope')]"));
		act.moveToElement(lista.get(0)).doubleClick().perform();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-questao"))));
		this.horizon.sleep(500);
		this.horizon.waitLoad();

		String quest = this.horizon.generateString(100);			

		driver.findElement(By.id("i-questao")).sendKeys(quest);
		driver.findElement(By.id("ta-dica")).sendKeys(this.horizon.generateString(350));
		Select slek = new Select(driver.findElement(By.id("cb-variaveis")));
		slek.selectByValue("1");	
		driver.findElement(By.id("bt-excluir-sim-modal")).click();

		wdw.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("i-variavel-1"))));
		driver.findElement(By.id("i-variavel-1")).sendKeys(this.horizon.generateString(100));

		lista = driver.findElements(By.xpath("//a [@ng-click='onClickBtnExcluirResposta(resposta, 1)']"));
		lista.get(0).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-excluir-sim-questao"))));
		driver.findElement(By.id("bt-excluir-sim-questao")).click();

		lista = driver.findElements(By.xpath("//a [@ng-click='onClickBtnEditarResposta(resposta, 1)']"));
		lista.get(0).click();	

		driver.findElement(By.id("i-nota-resposta-1")).clear();
		driver.findElement(By.id("i-nota-resposta-1")).sendKeys(String.format("%02.4f", ThreadLocalRandom.current().nextFloat()*10));
		driver.findElement(By.id("i-resposta-1")).clear();
		driver.findElement(By.id("i-resposta-1")).sendKeys(this.horizon.generateString(100));

		driver.findElement(By.xpath("//button [@class='btn btn-primary bt-salvar tooltip-top']")).click();

		driver.findElement(By.id("lb-recomendacoes")).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("i-nota-recomendacao-inicial"))));

		lista = driver.findElements(By.xpath("//a [@ng-click='onClickBtnExcluirRecomendacao(rec)']"));
		lista.get(0).click();

		wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-excluir-sim-questao"))));
		driver.findElement(By.id("bt-excluir-sim-questao")).click();

		lista = driver.findElements(By.xpath("//a [@ng-click='onClickBtnEditarRecomendacao(rec)']"));
		lista.get(0).click();

		driver.findElement(By.id("ta-recomendacao")).sendKeys(this.horizon.generateString(100));

		driver.findElement(By.xpath("//button [@class='btn btn-primary bt-salvar tooltip-top']")).click();

		driver.findElement(By.xpath("//button [@ng-click='onClickBtnSalvar()']")).click();		

		this.horizon.waitLoad();

		System.out.println("Questao+ " + quest + " " + horizon.time(starttime));
	}

	public void excluir() {
		this.driver.get("https://"+ ed +".apollusehs.com.br/apollus/views/hoe/analise/");	

		this.horizon.waitLoad();

		this.driver.findElement(By.xpath("//a [@ui-sref='questoes']")).click();

		this.horizon.waitLoad();

		driver.findElement(By.id("bt-pesquisar")).click();

		this.horizon.waitLoad();
		this.horizon.sleep(350);

		List<WebElement> lista = driver.findElements(By.xpath("//tr [contains(@class,'ng-scope')]"));
		act.moveToElement(lista.get(0)).click().perform();

		this.driver.findElement(By.xpath("//button [@class='btn btn-sm btn-default pull-right bt-excluir']")).click();
		this.wdw.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("bt-excluir-sim-questao"))));
		this.driver.findElement(By.id("bt-excluir-sim-questao")).click();
	}

	public void excat() {
		this.horizon.waitLoad();

		categoria.excluir();
	}

	public String getCat() {
		return cat;
	}
}
