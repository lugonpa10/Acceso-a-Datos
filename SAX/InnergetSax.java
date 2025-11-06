import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class InnergetSax {
    public  static void getSax(String entradaXML) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

       
        Ejercicio16 parserSax = new Ejercicio16(); // ParserSAX es la clase que se deberá

        parser.parse(entradaXML, parserSax); // implementar y que hereda de DafaultHandler
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        getSax("archivo.xml"); 
    }

}
