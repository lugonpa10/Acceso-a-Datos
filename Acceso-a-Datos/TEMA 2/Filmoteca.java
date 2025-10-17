import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

public class Filmoteca {

    // Método que crea el árbol DOM a partir del fichero XML
    public static Document creaArbol(String ruta) {
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(new File(ruta));
        } catch (Exception e) {
            System.out.println("Error creando el árbol DOM: " + e.getMessage());
        }
        return doc;
    }

    // Método que muestra todas las películas con sus directores y género
    public static void mostrarPeliculas(Document doc) {
        NodeList peliculas = doc.getElementsByTagName("pelicula");

        for (int i = 0; i < peliculas.getLength(); i++) {
            Element pelicula = (Element) peliculas.item(i);

            // Obtengo el género de la película
            String genero = pelicula.getAttribute("genero");

            // Obtengo el título
            String titulo = pelicula.getElementsByTagName("titulo").item(0)
                    .getTextContent();

            System.out.println("🎬 Película: " + titulo);
            System.out.println("   Género: " + genero);
            System.out.println("   Director(es):");

            // Lista de directores
            NodeList directores = pelicula.getElementsByTagName("director");
            for (int j = 0; j < directores.getLength(); j++) {
                Element director = (Element) directores.item(j);
                String nombre = director.getElementsByTagName("nombre").item(0).getTextContent();
                String apellido = director.getElementsByTagName("apellido").item(0).getTextContent();
                System.out.println("      - " + nombre + " " + apellido);
            }
            System.out.println("--------------------------------------");
        }
    }

    // Método principal
    public static void main(String[] args) {
        Document doc = creaArbol("filmoteca.xml");
        if (doc != null) {
            mostrarPeliculas(doc);
        }
    }
}
