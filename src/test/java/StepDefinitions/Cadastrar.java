package StepDefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import dataProviders.configFileReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import pages.CadastroPage;

public class Cadastrar {
	String url;
	WebDriver driver = null;
	CadastroPage cadastroPage;
	configFileReader configFileReader;
	
	
	@Before
	public void iniciar() {
		configFileReader = new configFileReader();
		url = configFileReader.getApplicationUrl(); 
		System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());	
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
	}

	@After
	private void finalizar() {
		driver.quit();
		driver.close();

	}
	
	@Given("que o usuario não esteja cadastrado no sistema")
	public void que_o_usuario_não_esteja_cadastrado_no_sistema() {
	    // Write code here that turns the phrase above into concrete actions
		driver.get(url);
	}

	@And("que o usuário preenche os campos obrigatórios nome com o valor {string}")
	public void que_o_usuário_preenche_os_campos_obrigatórios_nome_com_o_valor(String nome) {	
		cadastroPage = new CadastroPage(driver);
		cadastroPage.digitaNome(nome);

	}

	@And("o sobrenome com {string}")
	public void o_sobrenome_com(String sobrenome) {
		cadastroPage.digitaSobrenome(sobrenome);

	}

	@And("o e-mail {string}")
	public void o_e_mail(String email) {
		cadastroPage.digitaEmail(email);

	}

	@And("o telefone {string}")
	public void o_telefone(String fone) {
		cadastroPage.digitaFone(fone);

	}

	@And("o genero {string}")
	public void o_genero(String genero) {
		cadastroPage.selecionaGenero(genero);

	}

	@And("o pais {string}")
	public void o_pais(String pais) {
		cadastroPage.selecionaListaPais(pais);

	}

	@And("a data {string} de {string} de {string}")
	public void a_data_de_de(String dia, String mes, String ano) {
		cadastroPage.selecionaDia(dia);
		cadastroPage.selecionaMes(mes);
		cadastroPage.selecionaAno(ano);
		
	}

	@And("a senha com a confirmação {string}")
	public void a_senha_com_a_confirmação(String senha) {	
		cadastroPage.digitaSenha(senha);
		cadastroPage.digitaConfirmacaoSenha(senha);
	}

	@When("clicar em {string}")
	public void clicar_em(String submit) {
		cadastroPage.clicaSubmit();
		
	}

	@Then("o usuário é cadastrado com sucesso")
	public void o_usuário_é_cadastrado_com_sucesso() {
		String textoEsperado = "- Double Click on Edit Icon to EDIT the Table Row.";
		cadastroPage.confirmaCadastroSucesso(textoEsperado);

	}	
	
	@Given("que o usuario possua cadastro e tente cadastra com o mesmo nome {string}")
	public void que_o_usuario_possua_cadastro_e_tente_cadastra_com_o_mesmo_nome(String nome) {
		driver.get(url);
		cadastroPage = new CadastroPage(driver);
		cadastroPage.digitaNome(nome);
	}

	@Then("Sistema apresenta mensagem de erro {string}")
	public void sistema_apresenta_mensagem_de_erro(String msgEmailExitente) {
		cadastroPage.msgErroEmailExistente(msgEmailExitente);
	
	}

	@And("mensagem {string}")
	public void mensagem(String msgTelefoneExitente) {
		cadastroPage.msgErroFoneExistente(msgTelefoneExitente);

	}	
}
