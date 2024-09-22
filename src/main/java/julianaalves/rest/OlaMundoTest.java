package julianaalves.rest;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTest {
	@Test

	public void testOlaMundo() {
		Response response = request(Method.GET, "https://restapi.wcaquino.me/ola");
		Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		Assert.assertTrue(response.statusCode() == 200);
		Assert.assertTrue("O status code deveria ser 200 ", response.statusCode() == 200);
		Assert.assertEquals(200, response.statusCode());

		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);

	}
	
// 	Montando a estrutura de Teste	

	@Test
	public void devoConhecerOutrasFormasRestAssured() {
//		1ª opção: Forma mais prolixa de escrever a requisição de teste
		Response response = request(Method.GET, "https://restapi.wcaquino.me/ola");
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);

//		2ª opção: Forma mais simplificada de escrever a requisição de teste
		get("https://restapi.wcaquino.me/ola").then().statusCode(200);

//		3ª opção: Forma ideal de escrever a requisição de teste (Given, When, Then)
		given() //(Pré condições) Dado que, ao fazer uma requisição, 
		.when() //(Ação de Fato, o que será realizado) Quando eu fizer uma requisição para a página https://restapi.wcaquino.me/ola
			.get("https://restapi.wcaquino.me/ola")
		.then() //(São as verificações, as Assertivas) Então o meu status code deverá ser 200
//			.assertThat() Então verifique que 
			.statusCode(200); // O status code é 200
	}
	
//	Entendendo Hamcrest
	@Test
	public void devoConhecerMatcherHamcrest() {
		Assert.assertThat("Maria", Matchers.is("Maria"));
		Assert.assertThat(128, Matchers.is(128));
		Assert.assertThat(128, Matchers.isA(Integer.class));
		Assert.assertThat(128d, Matchers.isA(Double.class));
		Assert.assertThat(128d, Matchers.greaterThan(120d));
		Assert.assertThat(128d, Matchers.lessThan(130d));
		
		List<Integer> impares= Arrays.asList(1,3,5,7,9);
		assertThat(impares,hasSize(5));
		assertThat(impares, contains(1,3,5,7,9));
		assertThat(impares, containsInAnyOrder(1,3,5,7,9));
		assertThat(impares, hasItem(5));
		assertThat(impares, hasItems(5, 7));
		
		assertThat("Maria", is(not("João")));
		assertThat("Maria", not("João"));
		assertThat("Bia", anyOf(is("Maria"), is("Bia")));
		assertThat("Joaquina", allOf(startsWith("Joa"), endsWith("ina"), containsString("qui")));
		
	}

}
