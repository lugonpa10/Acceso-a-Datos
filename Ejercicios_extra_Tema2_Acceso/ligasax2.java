import javax.xml.namespace.QName;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ligasax2 extends DefaultHandler {

    boolean esEvento = false;
    int cont = 0;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // TODO Auto-generated method stub
        super.characters(ch, start, length);
    }

    @Override
    public void endDocument() throws SAXException {
        // TODO Auto-generated method stub
        System.out.println("Partidos: " + cont);

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // TODO Auto-generated method stub
        super.endElement(uri, localName, qName);
        if (qName.equals("eventos")) {
            esEvento = false;
            
        }

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

        if (qName.equals("eventos")) {
            esEvento = true;
            
        }

        if ( esEvento && qName.equals("evento")) {

            cont++;
            
        }

    }

}
