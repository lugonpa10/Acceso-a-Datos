import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CreacionDom {

    public static void main(String[] args) {
        Node peliculas, p,hijo;
        NodeList pelicula, hijos;
        String ruta = "archivo.xml";
        NamedNodeMap atributos;
        Document doc = creaArbol(ruta);
        peliculas = doc.getFirstChild();
        pelicula = peliculas.getChildNodes();
        for (int i = 0; i < pelicula.getLength(); i++) { 
            p = pelicula.item(i);
            if (p.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println(p.getNodeName());
                hijos = p.getChildNodes();

                if (p.hasAttributes()) {
                    atributos = p.getAttributes();
                    for (int j = 0; j < atributos.getLength(); j++) {
                        System.out.println("Atributo: " + atributos.item(j).getNodeName() + " - " + atributos.item(j).getNodeValue());
                    }
                }
                for (int j = 0; j < hijos.getLength(); j++) {
                    hijo = hijos.item(j);
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println(hijo.getNodeName() + " - " + hijo.getFirstChild().getNodeValue());
                    } 
                }

            }

        }
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


}
