import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Ejercicio16 extends DefaultHandler {

    private int n = 2;
    private int contDir = 0;
    private String texto = "";
    private String tituloActual = "";

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName == "director") {
            contDir++;

        }

        
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        texto = new String(ch, start, length);
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        if (qName == "pelicula") {
            if (contDir >= n) {
                System.out.println(tituloActual);
            }
            contDir = 0;
        }
        if (qName == "titulo") {
            tituloActual = texto;
        }

    }

    @Override
    public void endDocument() throws SAXException {

    }
}