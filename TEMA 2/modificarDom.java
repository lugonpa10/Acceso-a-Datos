import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class modificarDom {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
        String ruta = "archivo.xml";
        Document doc = creaArbol(ruta);

        String tit = "Cars";
        String dir = "Walt Disney";
        String est = "2006";
        String gen = "carreras";
        cachao(doc, tit, dir, est, gen);
        grabarDOM(doc,ruta);
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

    public static void cachao(Document doc, String tit, String dir, String est, String gen) {

        Element nodoPelicula = doc.createElement("Película"); // etiqueta pelicula
        nodoPelicula.setAttribute("genero", gen); // clave,valor
        nodoPelicula.appendChild(doc.createTextNode("\n")); // salto de linea

        Element nodoTitulo = doc.createElement("Título"); // etiqueta titulo
        Text textTitulo = doc.createTextNode(tit);
        nodoPelicula.appendChild(nodoTitulo);
        nodoTitulo.appendChild(textTitulo);
        nodoPelicula.appendChild(doc.createTextNode("\n")); 

        Element nodoDirector = doc.createElement("Director");
        Text textDirector = doc.createTextNode(dir);
        nodoPelicula.appendChild(nodoDirector);
        nodoDirector.appendChild(textDirector);
        nodoPelicula.appendChild(doc.createTextNode("\n"));

        Element nodoEstreno = doc.createElement("Estreno");
        nodoEstreno.appendChild(doc.createTextNode(est));
        nodoPelicula.appendChild(nodoEstreno);
        nodoPelicula.appendChild(doc.createTextNode("\n"));

        Node raiz = doc.getFirstChild();
        raiz.appendChild(nodoPelicula);
        System.out.println("Listo");
    }

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
