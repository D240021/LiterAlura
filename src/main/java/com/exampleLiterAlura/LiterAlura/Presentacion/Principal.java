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
                
                -------------------------------------------------
                - Bienvenido, usuario! Digite la opción elegida -
                - 1.Buscar libro por nombre                     -
                - 2.Obtener libros registrados                  -
                - 3.Obtener autores registrados                 -
                - 4.Obtener autores vivos en determinado año    -
                - 5.Obtener libros por idioma                   -
                - 6.Salir                                       -
                -------------------------------------------------
                """;

            System.out.println(menu);

            int opcion = 0;

            while (true) {
                try {
                    System.out.print("Seleccione una opción (1-6): ");
                    if (scanner.hasNextInt()) {
                        opcion = scanner.nextInt();
                        if (opcion >= 1 && opcion <= 6) {
                            break;
                        }
                    }
                    System.out.println("Por favor, elija una opción válida (1-6).");
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Intente de nuevo.");
                    scanner.nextLine();
                }
            }

            if (opcion == 6) {
                System.out.println("Saliendo del programa...");
                break;
            }

            this.procesarOpcionesMenu(opcion);
        }
    }

    public void registrarNuevoLibro(Libro libro) {
        boolean resultadoOperacion = this.libroDA.registrarNuevoLibro(libro);

        if (!resultadoOperacion) {
            System.out.println("El libro no pudo ser registrado debido a que ya existe");
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

        System.out.println("Libros registrados en el sistema: ");

        libros.forEach(System.out::println);
    }

    @Override
    public void obtenerAutoresRegistrados() {

        List<Autor> autores = this.autorDA.obtenerTodosAutores();

        if (autores.isEmpty()){
            System.out.println("No existen autores registrados");
            return;
        }

        System.out.println("Autores registrados en el sistema: ");

        autores.forEach(System.out::println);
    }

    @Override
    public void obtenerAutoresVivosPorAnio() {
        int anioBuscado = 0;

        while (true) {
            try {
                System.out.println("Inserte el año (entre -1000 y 2024): ");

                if (scanner.hasNextInt()) {
                    anioBuscado = scanner.nextInt();
                    if (anioBuscado >= -1000 && anioBuscado <= 2024) {
                        break;
                    }
                }

                System.out.println("Por favor, inserte un año válido (entre -1000 y 2024).");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intente de nuevo.");
                scanner.nextLine();
            }
        }

        List<Autor> autores = this.autorDA.obtenerAutoresVivosPorAnio(anioBuscado);

        if (autores.isEmpty()) {
            System.out.println("No existen autores vivos registrados en el año " + anioBuscado + ".");
            return;
        }

        System.out.println("Autores vivos en el año " + anioBuscado + ":");
        autores.forEach(System.out::println);
    }


    @Override
    public void obtenerLibrosPorIdioma() {
        int opcion = 0;

        while (true) {
            try {
                System.out.println("Seleccione el idioma del libro a buscar: \n" +
                        "1. Español\n" +
                        "2. Inglés\n" +
                        "3. Francés");

                if (scanner.hasNextInt()) {
                    opcion = scanner.nextInt();
                    if (opcion >= 1 && opcion <= 3) {
                        break;
                    }
                }

                System.out.println("Por favor, seleccione una opción válida entre 1 y 3.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intente de nuevo.");
                scanner.nextLine();
            }
        }


        String opcionProcesada = this.procesarOpcionesIdiomas(opcion);

        List<Libro> libros = this.libroDA.obtenerLibrosPorIdioma(opcionProcesada);

        if (libros.isEmpty()) {
            System.out.println("No existen libros registrados en el idioma seleccionado");
            return;
        }

        libros.forEach(System.out::println);
    }


    private void procesarOpcionesMenu(int opcion) {
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
            case 3:
                return "fr";
        }
        return "";
    }

    public Libro buscarLibroPorNombre() {
        System.out.println("Inserte el nombre del libro a buscar:");

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        String nombreABuscar = scanner.nextLine();

        if (nombreABuscar.isEmpty()) {
            System.out.println("Debe ingresar un nombre válido.");
            return null;
        }

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
