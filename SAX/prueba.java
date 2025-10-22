import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class prueba extends DefaultHandler {

    private int sang = 0;      // controla a sangría
    private String texto = "";   // gardaremos aquí o texto actual

    private void sangria() {
        for (int i = 0; i < sang; i++) {
            System.out.print(" ");
        }
    }

    @Override
    public void startDocument() throws SAXException {
        // non fai falta imprimir cabeceira, só comezamos
    }

    @Override
    public void endDocument() throws SAXException {
        // nada ao final do documento
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        sangria();
        System.out.println("<" + qName + ">");
        sang += 1;
        // texto = ""; // limpar texto acumulado
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // concatenamos os fragmentos de texto que poden chegar en varias chamadas
        texto = texto + new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String valor = texto.trim();
        if (!valor.equals("")) {
            sangria();
            System.out.println(valor);
        }
        sang -= 1;
        sangria();
        System.out.println("</" + qName + ">");
        texto = "";
    }
}
