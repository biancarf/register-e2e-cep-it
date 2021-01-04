package StepDefinitions;


import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class ConsultaCep {
	private static final String url = "https://viacep.com.br/ws/";
	RequestSpecification request;
	private  static  Response response;

	
	@Given("usuário efetue uma consulta de cep via api")
	public void usuário_efetue_uma_consulta_de_cep_via_api() {
		RestAssured.baseURI = url;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		
	}

	@When("inserir {string} valido")
	public void inserir_valido(String cep) {
		response = request.get(cep + "/json/");
		System.out.println("\n" + "RESULTADO: " + response + "\n");
		
	}

	@Then("Sistema de retornar {string}")
	public void sistema_de_retornar(String statuscode) {
		assertEquals(statuscode, response.getStatusCode());
	}

}
