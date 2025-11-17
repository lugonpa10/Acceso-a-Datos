import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Ejercicio15 extends DefaultHandler {

    String texto = "";
    boolean flag = false;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // TODO Auto-generated method stub
        super.characters(ch, start, length);
        texto = new String(ch,start,length);
         if (flag) {
            System.out.print(texto + "\n");
            flag = false;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        // TODO Auto-generated method stub
        super.endDocument();
            }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // TODO Auto-generated method stub
        super.endElement(uri, localName, qName);
    }

    @Override
    public void startDocument() throws SAXException {
        // TODO Auto-generated method stub
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // TODO Auto-generated method stub
        super.startElement(uri, localName, qName, attributes);

        if (qName.equals("pelicula")) {
            System.out.println("\nPelicula" );
            for (int i = 0; i < attributes.getLength(); i++) {
                if (attributes.getLocalName(i).equals("genero")) {
                    System.out.printf("%s: %s\n", attributes.getLocalName(i), attributes.getValue(i));
                }
            }
        } else if (qName.equals("titulo")) {
            flag = true;
            System.out.printf("%s: ", qName);
        } else if (qName.equals("nombre")) {
            flag = true;
            System.out.printf("%s: ", qName);
        } else if (qName.equals("apellido")) {
            System.out.printf("%s: ", qName);
            flag = true;
        }
    }
}
