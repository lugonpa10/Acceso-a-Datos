import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Ejemplo1 extends DefaultHandler {

    String etiqueta = "";
    

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // TODO Auto-generated method stub
        super.characters(ch, start, length);
        String cad = new String(ch,start,length);
        if(etiqueta.equals("Título")){
            // System.out.println(cad);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        // TODO Auto-generated method stub
        super.endDocument();
        System.out.println("Final documento");
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
        System.out.println("Inicio Documento");
    }

    @Override
    public void startElement(String uri, String localName, String qName , Attributes attributes) throws SAXException {
        // TODO Auto-generated method stub
        super.startElement(uri, localName, qName, attributes);
        // System.out.println(qName); // Dice las etiquetas (pelicula)
        // etiqueta = qName;
      
        if (qName.equals("Película")) {
            for (int index = 0; index < attributes.getLength(); index++) { 

                System.out.println(attributes.getLocalName(index) + " - " + attributes.getValue(index));
                
            }
            
        }

    }
    
}
