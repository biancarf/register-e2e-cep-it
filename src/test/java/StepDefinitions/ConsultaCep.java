package StepDefinitions;


import static org.junit.Assert.assertEquals;

import java.text.MessageFormat;

import dataProviders.configFileReader;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class ConsultaCep {
	private String url;
	private RequestSpecification request;
	private Response response;
	configFileReader configFileReader;


	
	@Before
	public void before(Scenario scenario) {
		configFileReader = new configFileReader();
		url = configFileReader.getApplicationUrl("urlApi"); 
		System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
		request = RestAssured.with();
		
	}
	
	@Given("usuario deseje efetuar uma consulta via get na api de cep")
	public void usuario_deseje_efetuar_uma_consulta_via_get_na_api_de_cep() {
		request.contentType(ContentType.JSON).baseUri(url);
		System.out.println("Adiciona URL: " + url);
		
	}

	@When("inserir {string} valido")
	public void inserir_valido(String cep) {
		String eindPointCep = "/ws/" + cep + "/json/";
		response = request.get(eindPointCep);
				
	}

	@Then("sistema deve retornar o status como {string}")
	public void sistema_deve_retornar_o_status_como(String statuscode) {
		assertEquals(Integer.parseInt(statuscode), response.getStatusCode());

	}
	
	
	@When("inserir no eindpoint {string}\\/{string}\\/{string}\\/json")
	public void inserir_no_eindpoint_json(String estado, String nomeCidade, String nomeRua) {
		String eindPointRua = MessageFormat.format("/ws/{0}/{1}/{2}/json/", estado, nomeCidade, nomeRua);
		response = request.get(eindPointRua);
		
	}
	
	@Then("os numeros de cep {string}")
	public void os_numeros_de_cep(String cep) {
		validaCepListado(cep);
		
	}


	@And("o {string} relacionados ao logradouro.")
	public void o_relacionados_ao_logradouro(String cep) {
		validaCepListado(cep);
		
	}
	
	
	@When("inserir o {string} invalido")
	public void inserir_o_invalido(String cep) {
		String eindPointCep = "/ws/" + cep + "/json/";
		response = request.get(eindPointCep);
		
	}

	@And("o {string} no retorno.")
	public void o_no_retorno(String erro) {
		assertEquals(Boolean.parseBoolean(erro), response.getBody().jsonPath().get("erro"));
		
	}
	
	public void validaCepListado(String cep) {
		java.util.List<String> listarCep = response.getBody().jsonPath().get("cep");	
		for (String selecionaCep : listarCep) {
			if (selecionaCep.equals(cep)) {
				assertEquals(cep, selecionaCep);
				break;
			} else {
				assertEquals(cep, selecionaCep);
			}
			
		}

	}
	



}
