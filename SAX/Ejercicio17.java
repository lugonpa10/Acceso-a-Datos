import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Ejercicio17 extends DefaultHandler {

    ArrayList<String> generos = new ArrayList<>();
    int contgen = 0;
    boolean repe = false;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // TODO Auto-generated method stub
        super.characters(ch, start, length);
    }

    @Override
    public void endDocument() throws SAXException {
        // TODO Auto-generated method stub
        super.endDocument();
        System.out.println("Hay " + contgen);
        for (int i = 0; i < generos.size(); i++) {

            System.out.println(generos.get(i));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

    }

    @Override
    public void startDocument() throws SAXException {
        // TODO Auto-generated method stub
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("pelicula")) {

            String genero = attributes.getValue("genero");

            if (genero != null && !genero.isEmpty()) {
                repe = false;

            }
            for (String g : generos) {
                if (g.equals(genero)) {
                    repe = true;
                    break;
                }

            }
            if (!repe) {
                contgen++;
                generos.add(genero);

            }
        }

    }

}
