package pages;



import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CadastroPage {
	protected WebDriver driver;
	
	private By CampoNome = By.cssSelector("input[ng-model='FirstName']");
	private By CampoSobrenome = By.cssSelector("input[ng-model='LastName']");
	private By CampoEmail = By.cssSelector("input[ng-model='EmailAdress']");
	private By CampoFone = By.cssSelector("input[ng-model='Phone']");
	private By RadioBtnGenero = By.name("radiooptions");
	private By ListaPais = By.id("countries");
	private By ListaDia = By.id("daybox");
	private By ListaMes = By.cssSelector("select[ng-model='monthbox']");
	private By ListaAno = By.id("yearbox");
	private By CampoSenha = By.id("firstpassword");
	private By CampoConfirmaSenha = By.id("secondpassword");
	private By BotaoSubmit = By.id("submitbtn");
	private By CampoTextoCadFinalizado = By.tagName("h4");
	private By CampoMsgEmail = By.xpath("//*[@id=\"section\"]/div[1]/div/div[1]");
	private By CampoMsgFone = By.xpath("//*[@id=\"section\"]/div[1]/div/div[2]");
	
	public CadastroPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		if (!driver.getTitle().equals("Register")) {
			throw new IllegalStateException("Está não é a pagina Register. A pagina corrente é " + driver.getCurrentUrl());
		}
	}
	
	public void  digitaNome(String nome) {
		driver.findElement(CampoNome).sendKeys(Keys.chord(nome));
	}
	
	public void digitaSobrenome(String sobrenome) {
		driver.findElement(CampoSobrenome).sendKeys(Keys.chord(sobrenome));
		
	}

	public void digitaEmail(String email) {
		driver.findElement(CampoEmail).sendKeys(Keys.chord(email));
		
	}

	public void digitaFone(String fone) {
		driver.findElement(CampoFone).sendKeys(Keys.chord(fone));
		
	}

	public void selecionaGenero(String genero) {
		java.util.List<WebElement> listaRadio = driver.findElements(RadioBtnGenero);
		
		for (WebElement selecionaRadio : listaRadio) {
			String valorRadio = selecionaRadio.getAttribute("value");
			if (valorRadio.equals(genero)) {
				selecionaRadio.click();
				break;
			}			
		}
		
	}
	
	public void selecionaListaPais(String pais) {
		Select selecionaListaPais = new Select(driver.findElement(ListaPais));
		selecionaListaPais.selectByVisibleText(pais);
		
	}
	
	public void selecionaDia(String dia) {
		Select selecionaDia = new Select(driver.findElement(ListaDia));
		selecionaDia.selectByVisibleText(dia);
	}
	
	public void selecionaMes(String mes) {
		Select selecionaMes = new Select(driver.findElement(ListaMes));
		selecionaMes.selectByVisibleText(mes);
		
	}
	
	public void selecionaAno(String ano) {
		Select selecionaAno = new Select(driver.findElement(ListaAno));
		selecionaAno.selectByVisibleText(ano);
		
	}
	
	public void digitaSenha(String senha) {
		driver.findElement(CampoSenha).sendKeys(Keys.chord(senha));
		
	}
	
	public void digitaConfirmacaoSenha(String senha) {
		driver.findElement(CampoConfirmaSenha).sendKeys(Keys.chord(senha));
		
	}
	
	public void clicaSubmit() {
		driver.findElement(BotaoSubmit).isDisplayed();
		driver.findElement(BotaoSubmit).click();
		
	}
	
	public void confirmaCadastroSucesso(String textoEsperado) {
		new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(CampoTextoCadFinalizado));		
		java.util.List<WebElement> obterLocalTexto = driver.findElements(CampoTextoCadFinalizado);

		for (WebElement obterTexto : obterLocalTexto) {
			String texto = obterTexto.getText();
			if (!texto.isEmpty()) {
				assertEquals(textoEsperado, texto);
				break;
			}
		}
		
	}
	
	public void msgErroEmailExistente(String msgEmailExitente) {
		new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(CampoMsgEmail));
		List<WebElement> obterMensagemEmail = driver.findElements(CampoMsgEmail);

		for (WebElement obterTextoMensagem : obterMensagemEmail) {
			String texto = obterTextoMensagem.getText();
			if (!texto.isEmpty()) {
				assertEquals(msgEmailExitente, texto);
				break;
			}
		}
	}
	
	public void msgErroFoneExistente(String msgTelefoneExitente) {
		new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(CampoMsgFone));
		List<WebElement> obterTelefoneEmail = driver.findElements(CampoMsgFone);
		
		for (WebElement obterTextoMensagem : obterTelefoneEmail) {
			String texto = obterTextoMensagem.getText();
			if (!texto.isEmpty()) {
				assertEquals(msgTelefoneExitente, texto);
				break;
			}
		}
	}	
}
