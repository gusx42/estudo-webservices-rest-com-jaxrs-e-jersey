package br.com.gusxpander.estudo.rest.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.Servidor;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import junit.framework.Assert;

public class CarrinhoTest {

	private HttpServer server;

	@Before
	public void iniciaServidorAntesDoTeste() {
		server = Servidor.iniciarInstanciaDoServidor();
	}

	@After
	public void mataServidor() {
		server.stop();
	}

	@Test
	public void testaQueBuscarOCarrinhoRetornaOCarrinhoEsperado() {

		
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target("http://localhost:8080");
		Carrinho conteudo = target.path("/carrinhos/1").request().get(Carrinho.class);

		System.out.println(conteudo);

		Carrinho carrinho = conteudo;

		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());

	}

	@Test
	public void testaAAdicaoDeUmNovoCarrinho() {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");

		Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
		carrinho.setRua("Rua Verguiro");
		carrinho.setCidade("São Paulo");
		Carrinho novoCarrinho = carrinho;

		Entity<Carrinho> entity = Entity.entity(novoCarrinho, MediaType.APPLICATION_XML);

		Response response = target.path("/carrinhos").request().post(entity);
		String local = response.getHeaderString("Location");
		Assert.assertTrue(local.contains("http://localhost:8080/carrinhos/1"));

	}

}
