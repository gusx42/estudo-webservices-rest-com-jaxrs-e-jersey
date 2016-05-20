package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {

	HttpServer server;

	public static void main(String[] args) throws IOException {

		HttpServer server = iniciarInstanciaDoServidor();
		System.in.read();
		server.stop();

	}

	public static HttpServer iniciarInstanciaDoServidor() {

		ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
		URI uri = URI.create("http://localhost:8080/");
		return GrizzlyHttpServerFactory.createHttpServer(uri, config);
	}

}
