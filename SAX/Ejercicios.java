import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Ejercicios extends DefaultHandler {

    private String texto = "";

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Comezo a ler");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        texto = ""; 
        System.out.print("<" + qName);

        
        if (attributes != null && attributes.getLength() > 0) {
            for (int i = 0; i < attributes.getLength(); i++) {
                System.out.print(" " + attributes.getQName(i) + "=\"" + attributes.getValue(i) + "\"");
            }
        }

        System.out.println(">");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
       
        texto += new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
      
        texto = texto.trim();
        if (!texto.isEmpty()) {
            System.out.println(texto);
        }
        System.out.println("</" + qName + ">");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Fin da lectura");
    }
}
