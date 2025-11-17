import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Ejercicio14 extends DefaultHandler {

    private String texto = "";

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Comezo a ler");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
         
        System.out.print("<" + qName);

        System.out.print(">");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
       
        texto = new String(ch, start, length);
        System.out.print(texto);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
      
       
        
        System.out.print("</" + qName + ">");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Fin da lectura");
    }
}
