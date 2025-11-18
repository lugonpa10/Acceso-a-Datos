import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ligasax3 extends DefaultHandler {
    boolean esEvento = false;
    String fecha = "";
    String equipoloc = "";
    String equipovis = "";
    String contenido = "";

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // TODO Auto-generated method stub
        contenido += new String(ch, start, length);

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
        if (esEvento) {

            if (qName.equals("fecha")) {
                fecha = contenido;

            } else if (qName.equals("equipolocal")) {
                equipoloc = contenido;

            } else if (qName.equals("equipovisitante")) {
                equipovis = contenido;

            } else if (qName.equals("evento")) {
                System.out.println("Fecha: " + fecha + " Local: " + equipoloc + " Visitante: " + equipovis);
                esEvento = false;

            }
        }

        contenido = "";

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
        if (qName.equals("evento")) {
            esEvento = true;

            fecha = "";
            equipoloc = "";
            equipovis = "";

        }
        contenido = "";
    }

}
