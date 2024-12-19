package com.exampleLiterAlura.LiterAlura.Presentacion;

import com.exampleLiterAlura.LiterAlura.Datos.AutorDA;
import com.exampleLiterAlura.LiterAlura.Datos.Interfaces.IPrincipal;
import com.exampleLiterAlura.LiterAlura.Datos.LibroDA;
import com.exampleLiterAlura.LiterAlura.Dominio.Autor;
import com.exampleLiterAlura.LiterAlura.Dominio.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal implements IPrincipal {

    private final LibroDA libroDA;
    private final AutorDA autorDA;
    private final Scanner scanner;

    @Autowired
    public Principal(LibroDA libroDA, AutorDA autorDA) {
        this.libroDA = libroDA;
        this.autorDA = autorDA;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        while (true) {
            var menu = """
                Bienvenido, usuario! Digite la opción elegida
                1.Buscar libro por nombre
                2.Obtener libros registrados
                3.Obtener autores registrados
                4.Obtener autores vivos en determinado año
                5.Obtener libros por idioma
                6.Salir""";

            System.out.println(menu);

            int opcion = this.scanner.nextInt();

            if (opcion == 6) {
                break;
            }

            this.procesarOpciones(opcion);
        }
    }

    public void registrarNuevoLibro(Libro libro) {
        boolean resultadoOperacion = this.libroDA.registrarNuevoLibro(libro);

        if (!resultadoOperacion) {
            System.out.println("El libro no pudo ser registrado");
            return;
        }
        System.out.println("Libro registrado exitosamente");
    }

    public void obtenerLibrosRegistrados() {
        List<Libro> libros = this.libroDA.obtenerLibrosRegistrados();

        if(libros.isEmpty()){
            System.out.println("No existen libros registrados");
            return;
        }

        libros.forEach(System.out::println);
    }

    @Override
    public void obtenerAutoresRegistrados() {

        List<Autor> autores = this.autorDA.obtenerTodosAutores();

        if (autores.isEmpty()){
            System.out.println("No existen autores registrados");
            return;
        }

        autores.forEach(System.out::println);
    }

    @Override
    public void obtenerAutoresVivosPorAnio() {

        System.out.println("Inserte el año: ");

        int anioBuscado = this.scanner.nextInt();

        List<Autor> autores = this.autorDA.obtenerAutoresVivosPorAnio(anioBuscado);

        if (autores.isEmpty()){
            System.out.println("No existen autores vivos registrados en el año insertado");
            return;
        }

        autores.forEach(System.out::println);
    }

    @Override
    public void obtenerLibrosPorIdioma() {
        System.out.println("Seleccione el idioma del libro a buscar: \n" +
                "1.Español\n" +
                "2.Inglés\n" +
                "3.Francés");

        int opcion = this.scanner.nextInt();

        String opcionProcesada = this.procesarOpcionesIdiomas(opcion);

        List<Libro> libros = this.libroDA.obtenerLibrosPorIdioma(opcionProcesada);

        if(libros.isEmpty()){
            System.out.println("No existen libros registrados en el idioma seleccionado");
            return;
        }

        libros.forEach(System.out::println);
    }

    private void procesarOpciones(int opcion) {
        switch (opcion) {
            case 1:
                Libro libro = buscarLibroPorNombre();
                if (libro != null) {
                    this.registrarNuevoLibro(libro);
                }
                break;
            case 2:
                this.obtenerLibrosRegistrados();
                break;
            case 3:
                this.obtenerAutoresRegistrados();
                break;
            case 4:
                this.obtenerAutoresVivosPorAnio();
                break;
            case 5:
                this.obtenerLibrosPorIdioma();
                break;
        }
    }

    private String procesarOpcionesIdiomas(int opcion){
        switch (opcion){
            case 1:
                return "es";
            case 2:
                return "en";
        }
        return "";
    }

    public Libro buscarLibroPorNombre() {
        System.out.println("Inserte el nombre del libro a buscar:");

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        String nombreABuscar = scanner.nextLine();
        System.out.println("Buscando: " + nombreABuscar);

        Libro libroBuscado = this.libroDA.obtenerLibroPorNombre(nombreABuscar);

        if (libroBuscado == null) {
            System.out.println("El libro no fue encontrado.");
            return null;
        }

        System.out.println("Libro encontrado!\n" +
                libroBuscado);


        return libroBuscado;
    }
}
