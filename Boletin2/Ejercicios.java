import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
// import javax.swing.text.html.parser.Element; // Remove this line
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class Ejercicios {
    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
        String ruta = "peliculas.xml";
        Document doc = creaArbol(ruta);

        // Ejercicio2
        // titulos(doc);

        // Ejercicio3
        // directores(doc);

        // Ejercicio5
        // numDirectores(doc, 1);

        // Ejercicio6
        // numGeneros(doc);

        // Ejercicio7
        // String titulo = "Matrix";
        // String val = "10";
        // añadirAtributo(doc, titulo, val);
        // eliminarAtributo(doc,titulo,val);
        // grabarDOM(doc, ruta);

        // Ejercicio8
        // String titulo = "Depredador";
        // String gen = "accion";
        // String idioma = "ing";
        // String dirNom = "John";
        // String dirApe = "Tiernan";

        // String anho = "1987";
        // añadirDepredador(doc, titulo, gen, dirNom, dirApe, idioma, anho);

        // Ejercicio9
        // cambiarNombre(doc);

        // Ejercicio10
        // añadirDirector(doc);

        // Ejercicio11
        // String titulo = "Fargo";
        // borrarPeliculas(doc, titulo);
        // grabarDOM(doc, ruta);

    }

    public static Document creaArbol(String ruta) {
        Document doc = null;
        try {
            DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
            factoria.setIgnoringComments(true);
            DocumentBuilder builder = factoria.newDocumentBuilder();
            doc = builder.parse(ruta);
            System.out.println("Árbol DOM generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error generando el árbol DOM: " + e.getMessage());
        }
        return doc;
    }

    public static void titulos(Document doc) {

        NodeList titulos = doc.getElementsByTagName("titulo");

        for (int index = 0; index < titulos.getLength(); index++) {

            System.out.println("Titulos: " + titulos.item(index).getTextContent());

        }

    }

    public static void directores(Document doc) {
        NodeList directores;

        directores = doc.getElementsByTagName("director");
        for (int index = 0; index < directores.getLength(); index++) {
            Node director = directores.item(index);
            NodeList nodosDentroDeDirector = director.getChildNodes();

            System.out.print("Director:");
            for (int j = 0; j < nodosDentroDeDirector.getLength(); j++) {
                if (nodosDentroDeDirector.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    System.out.print(" " + nodosDentroDeDirector.item(j).getTextContent());
                }

                // if (nodosDentroDeDirector.item(j).getNodeName() == "nombre") {
                // System.out.print(" " + nodosDentroDeDirector.item(j).getTextContent());
                // }
            }
            System.out.println();

        }

    }

    public static void numDirectores(Document doc, int n) {

        NodeList peliculas = doc.getElementsByTagName("pelicula");

        for (int index = 0; index < peliculas.getLength(); index++) {
            Node nodo = peliculas.item(index);

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {

                Element pelicula = (Element) nodo;

                String titulo = pelicula.getElementsByTagName("titulo").item(0).getTextContent();

                NodeList directores = pelicula.getElementsByTagName("director");

                int numDir = directores.getLength();

                if (numDir > n) {

                    System.out.println(titulo);

                }
            }
        }

    }

    public static void numGeneros(Document doc) {

        String genero;

        NodeList peliculas = doc.getElementsByTagName("pelicula");

        ArrayList<String> generos = new ArrayList<>();

        for (int index = 0; index < peliculas.getLength(); index++) {

            Node nodo = peliculas.item(index);

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {

                Element pelicula = (Element) nodo;

                genero = pelicula.getAttribute("genero");

                if (!generos.contains(genero)) {
                    generos.add(genero);
                }

            }

        }

        System.out.println("Hay " + generos.size() + " generos");
        for (int i = 0; i < generos.size(); i++) {
            System.out.println(generos.get(i));

        }

    }

    public static void añadirAtributo(Document doc, String titulo, String atributo) {

        NodeList pelicula = doc.getElementsByTagName("pelicula");

        for (int i = 0; i < pelicula.getLength(); i++) {
            Element peliculas = (Element) pelicula.item(i);

            NodeList titulos = peliculas.getElementsByTagName("titulo");

            if (titulos.getLength() > 0) {
                String tituloActual = titulos.item(0).getTextContent().trim();

                if (tituloActual.equalsIgnoreCase(titulo)) {

                    if (!peliculas.hasAttribute("valoracion")) {

                        peliculas.setAttribute("valoracion", atributo);

                        System.out.println("Se ha añadido el atributo a " + titulo);

                    }

                }

            }

        }

    }

    public static void eliminarAtributo(Document doc, String titulo, String atributo) {

        NodeList peliculas = doc.getElementsByTagName("pelicula");

        for (int index = 0; index < peliculas.getLength(); index++) {

            Element pelicula = (Element) peliculas.item(index);

            NodeList titulos = pelicula.getElementsByTagName("titulo");

            if (titulos.getLength() > 0) {

                String tituloActual = titulos.item(0).getTextContent().trim();

                if (tituloActual.equalsIgnoreCase(titulo)) {

                    if (pelicula.hasAttribute("valoracion")) {

                        pelicula.removeAttribute("valoracion");

                        System.out.println("Se ha borrado el atributo de " + titulo);

                    }

                }

            }

        }

    }

    public static void añadirDepredador(Document doc, String titulo, String gen, String dirNom, String dirApe,
            String idioma, String anho) {

        Element nodoPelicula = doc.createElement("pelicula");
        nodoPelicula.setAttribute("genero", gen);
        nodoPelicula.setAttribute("idioma", idioma);
        nodoPelicula.setAttribute("año", anho);
        nodoPelicula.appendChild(doc.createTextNode("\n"));

        Element nodoTitulo = doc.createElement("titulo");
        Text textTitulo = doc.createTextNode(titulo);
        nodoPelicula.appendChild(nodoTitulo);
        nodoTitulo.appendChild(textTitulo);
        nodoPelicula.appendChild(doc.createTextNode("\n"));

        Element nodoDirector = doc.createElement("director");
        Element nodoNombre = doc.createElement("nombre");
        Element nodoApellidos = doc.createElement("apellido");

        nodoPelicula.appendChild(nodoDirector);
        nodoDirector.appendChild(nodoNombre);
        nodoDirector.appendChild(nodoApellidos);

        Text textNombre = doc.createTextNode(dirNom);
        nodoNombre.appendChild(textNombre);
        Text textApellido = doc.createTextNode(dirApe);
        nodoApellidos.appendChild(textApellido);

        doc.getDocumentElement().appendChild(nodoPelicula);

        System.out.println("listo");

    }

    public static void cambiarNombre(Document doc) {
        NodeList peliculas = doc.getElementsByTagName("pelicula");

        for (int index = 0; index < peliculas.getLength(); index++) {
            Element pelicula = (Element) peliculas.item(index);

            NodeList directores = pelicula.getElementsByTagName("director");

            for (int i = 0; i < directores.getLength(); i++) {
                if (directores.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element director = (Element) directores.item(i);

                    String nombre = director.getElementsByTagName("nombre").item(0).getTextContent();
                    String apellido = director.getElementsByTagName("apellido").item(0).getTextContent();

                    if (nombre.equals("Larry") && apellido.equals("Wachowski")) {
                        director.getElementsByTagName("nombre").item(0).setTextContent("Lana");
                    }

                }

            }
        }

    }

    public static void añadirDirector(Document doc) {
        NodeList peliculas = doc.getElementsByTagName("pelicula");
        for (int i = 0; i < peliculas.getLength(); i++) {

            Element pelicula = (Element) peliculas.item(i);

            String nomTit = pelicula.getElementsByTagName("titulo").item(0).getTextContent();

            if (nomTit.equalsIgnoreCase("Dune")) {

                Element nodoDirector = doc.createElement("director");
                Element nodoNombre = doc.createElement("nombre");
                Element nodoApellido = doc.createElement("apellido");

                Text textNombre = doc.createTextNode("Alfredo");
                nodoNombre.appendChild(textNombre);
                Text textApellido = doc.createTextNode("Landa");
                nodoApellido.appendChild(textApellido);

                nodoDirector.appendChild(nodoNombre);
                nodoDirector.appendChild(nodoApellido);

                pelicula.appendChild(nodoDirector);

            }

        }

    }

    public static void borrarPeliculas(Document doc, String titulo) {
        NodeList peliculas = doc.getElementsByTagName("pelicula");

        for (int i = 0; i < peliculas.getLength(); i++) {
            Element pelicula = (Element) peliculas.item(i);

            String tit = pelicula.getElementsByTagName("titulo").item(0).getTextContent();

            if (tit.equalsIgnoreCase(titulo)) {

                pelicula.getParentNode().removeChild(pelicula);
                System.out.println("Pelicula borrada");

            }

        }

    }

    public static void nuevoDoc(Document doc,String nombre,String apellidos) {

        Element nodoCompañia = doc.createElement("compañia");
        nodoCompañia.appendChild(doc.createTextNode("\n"));



        Element nodoEmpregado = doc.createElement("empregado");
        nodoEmpregado.setAttribute("id", "1");

        Element nodoNombre = doc.createElement("nome");
        Text textNombre = doc.createTextNode(nombre);

        Element nodoApellido = doc.createElement("apelidos");
        Text textApellidos = doc.createTextNode(apellidos);

        Element nodo

        // <?xml version="1.0" encoding="UTF-8" standalone="no" ?>
        // <compañia>
        // <empregado id="1">
        // <nome>Juan</nome>
        // <apelidos>López Pérez</apelidos>
        // <alcume>Juanín</alcume >
        // <salario>1000</salario>
        // </ empregado >
        // </compañia>

    }
    //https://prod.liveshare.vsengsaas.visualstudio.com/join?DD9800BEB9D320322F87D19A7DB6DDF55005

    public static void grabarDOM(Document document, String ficheroSalida)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, FileNotFoundException {
        DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
        DOMImplementationLS ls = (DOMImplementationLS) registry.getDOMImplementation("XML 3.0 LS 3.0");
        // Se crea un destino vacio
        LSOutput output = ls.createLSOutput();
        output.setEncoding("UTF-8");
        // Se establece el flujo de salida
        output.setByteStream(new FileOutputStream(ficheroSalida));
        // output.setByteStream(System.out);
        // Permite escribir un documento DOM en XML
        LSSerializer serializer = ls.createLSSerializer();
        // Se establecen las propiedades del serializador
        serializer.setNewLine("\r\n");
        ;
        serializer.getDomConfig().setParameter("format-pretty-print", true);
        // Se escribe el documento ya sea en un fichero o en una cadena de texto
        serializer.write(document, output);
        // String xmlCad=serializer.writeToString(document);
    }

}
