import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class prueba {
    public static void main(String[] args) {

        String ruta = "archivo.xml";
        Document doc = creaArbol(ruta);
        recorrerArbol(doc, "Indiana Jones y la última cruzada");

    }

    public static void recorrerArbol(Document doc, String titulo) {
        NodeList titulos = doc.getElementsByTagName("Título");
        Node tit;
        NodeList aux,aux2; 
        Element padre;

        for (int index = 0; index < titulos.getLength(); index++) {

            tit = titulos.item(index);
            
            if (tit.getTextContent().trim().equals(titulo)) {
                padre =  (Element) tit.getParentNode(); // Pelicula
                aux = padre.getElementsByTagName("Director");
                aux2 = padre.getElementsByTagName("Estreno");
                System.out.println(aux.item(0).getFirstChild().getNodeValue() + " - " + aux2.item(0).getTextContent()); 
                
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
            System.out.println("Arbol DOM creado");
        } catch (Exception e) {
            System.out.println("Error generando el árbol DOM: " + e.getMessage());
        }
        return doc;
    }
}
