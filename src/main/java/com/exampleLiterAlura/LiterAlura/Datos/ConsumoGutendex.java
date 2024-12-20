package com.exampleLiterAlura.LiterAlura.Datos;

import com.exampleLiterAlura.LiterAlura.Datos.Interfaces.IConsumoGutendex;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.exampleLiterAlura.LiterAlura.Servicios.GutendexServicio.API_URL;

public class ConsumoGutendex implements IConsumoGutendex {

    private URI direccionUrl;
    private HttpClient httpClient;

    public ConsumoGutendex(){

        this.httpClient =  this.httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS) // Permite redirecciones automáticas
                .build();
    }




    public String obtenerTodosLibros() {


        this.direccionUrl = URI.create(API_URL);

        HttpRequest solicitud = HttpRequest.newBuilder().uri(this.direccionUrl).build();

        HttpResponse<String> respuesta;
        try {

            respuesta = httpClient.send(solicitud, HttpResponse.BodyHandlers.ofString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return respuesta.body();
    }

    @Override
    public String obtenerLibroPorNombre(String busqueda) {
        this.direccionUrl = URI.create(API_URL+"?search="+busqueda);

        HttpRequest solicitud = HttpRequest.newBuilder().uri(this.direccionUrl).build();

        HttpResponse<String> respuesta;
        try {

            respuesta = httpClient.send(solicitud, HttpResponse.BodyHandlers.ofString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return respuesta.body();
    }

    @Override
    public String obtenerLibrosPorIdioma(String idioma) {
        this.direccionUrl = URI.create(API_URL+"?languages="+idioma);

        HttpRequest solicitud = HttpRequest.newBuilder().uri(this.direccionUrl).build();

        HttpResponse<String> respuesta;
        try {

            respuesta = httpClient.send(solicitud, HttpResponse.BodyHandlers.ofString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return respuesta.body();
    }
}
