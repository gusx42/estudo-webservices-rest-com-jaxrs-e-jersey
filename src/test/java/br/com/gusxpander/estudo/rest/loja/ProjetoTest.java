package br.com.gusxpander.estudo.rest.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.Servidor;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Projeto;
import junit.framework.Assert;

public class ProjetoTest {

	
	private HttpServer server;
	Client client;

	@Before
	public void iniciaServidorAntesDoTeste() {
		server = Servidor.iniciarInstanciaDoServidor();
		client = ClientBuilder.newClient();
	}
	
	@After
	public void mataServidor() {
		server.stop();
	}
	
	
	   @Test
	    public void testaQueBuscarUmCarrinhoTrasUmCarrinho() {
	        WebTarget target = client.target("http://localhost:8080");
	        Carrinho carrinho = target.path("/carrinhos/1").request().get(Carrinho.class);
	        Assert.assertEquals("Rua Vergueiro 3185, 8 andar",carrinho.getRua());
	    }

	    @Test
	    public void testaQueAConexaoComOServidorFuncionaNoPathDeProjetos() {
	        WebTarget target = client.target("http://localhost:8080");
	        Projeto projeto = target.path("/projetos/1").request().get(Projeto.class);
	        Assert.assertEquals(1L, projeto.getId(),0);

	    }
	
}
