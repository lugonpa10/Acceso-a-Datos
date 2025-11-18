
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.print.Doc;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class liga {
    public static void main(String[] args) {
        String ruta = "liga.xml";
        Document doc = creaArbol(ruta);
        // temporada(doc);
        // numPartidos(doc);
        // equipos_fechas(doc);
        equipo_goleador(doc);
        // equipo_Colista(doc);
        // equipo_empates(doc);
        // clasificacion_equipos(doc);
    }

    public static void temporada(Document doc) {
        String temporada = doc.getElementsByTagName("temporada").item(0).getTextContent();
        System.out.println("temporada: " + temporada);

    }

    public static void numPartidos(Document doc) {
        int cont = 0;
        NodeList eventos = doc.getElementsByTagName("eventos");
        for (int i = 0; i < eventos.getLength(); i++) {
            Node nodo = eventos.item(i);

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element evento = (Element) nodo;

                NodeList partidos = evento.getElementsByTagName("evento");
                cont = partidos.getLength() + cont;

            }

        }
        System.out.println("partidos: " + cont);

    }

    public static void equipos_fechas(Document doc) {

        NodeList evento = doc.getElementsByTagName("evento");
        for (int i = 0; i < evento.getLength(); i++) {
            Node nodo = evento.item(i);

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element event = (Element) nodo;

                String fechas = event.getElementsByTagName("fecha").item(0).getTextContent();
                String equipolocal = event.getElementsByTagName("equipolocal").item(0).getTextContent();
                String equipovisiantes = event.getElementsByTagName("equipovisitante").item(0).getTextContent();

                System.out.println("fechas: " + fechas);
                System.out.println("------------");
                System.out.println("Equipo Local: " + equipolocal);
                System.out.println("Equipo Visitante: " + equipovisiantes);
                System.out.println("------------");
            }

        }
    }

    public static void equipo_goleador(Document doc) {
        ArrayList<String> equipoGoleador = new ArrayList<>();
        int maxgoles = 0;
        NodeList team = doc.getElementsByTagName("team");
        for (int i = 0; i < team.getLength(); i++) {

            Node nodo = team.item(i);

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {

                Element equipos = (Element) nodo;
                String nombre = equipos.getElementsByTagName("name").item(0).getTextContent();
                int golesMarcados = Integer.parseInt(equipos.getElementsByTagName("goals_scored").item(0).getTextContent());
                if (golesMarcados > maxgoles) {
                    maxgoles = golesMarcados;
                    equipoGoleador.clear();
                    equipoGoleador.add(nombre);

                }

            }

        }
        for (int i = 0; i < equipoGoleador.size(); i++) {
            System.out.println("Equipo Goleador: " + equipoGoleador.get(i));
            
        }

    }

    public static void equipo_Colista(Document doc) {

        NodeList team = doc.getElementsByTagName("team");
        ArrayList<String> equipoColista = new ArrayList<>();
        int maxrank = -1;
        for (int i = 0; i < team.getLength(); i++) {
            Node nodo = team.item(i);

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element equipos = (Element) nodo;
                String nombre = equipos.getElementsByTagName("name").item(0).getTextContent();
                int rank = Integer.parseInt(equipos.getElementsByTagName("rank").item(0).getTextContent());

                if (rank > maxrank) {
                    maxrank = rank;
                    equipoColista.clear();
                    equipoColista.add(nombre);
                }
            }

        }

        NodeList evento = doc.getElementsByTagName("evento");
        for (int j = 0; j < evento.getLength(); j++) {
            Node nodo2 = evento.item(j);

            if (nodo2.getNodeType() == Node.ELEMENT_NODE) {
                Element partido = (Element) nodo2;

                String nombreloc = partido.getElementsByTagName("equipolocal").item(0).getTextContent();
                String nombrevis = partido.getElementsByTagName("equipovisitante").item(0).getTextContent();
                String fecha = partido.getElementsByTagName("fecha").item(0).getTextContent();

                for (String c : equipoColista) {
                    if (c.equals(nombreloc) || c.equals(nombrevis)) {

                        System.out.println("Colista: " + c);
                        System.out.println("Local: " + nombreloc);
                        System.out.println("Visitante: " + nombrevis);
                        System.out.println("Fecha: " + fecha);

                    }
                }
            }
        }

    }

    public static void equipo_empates(Document doc) {

        NodeList team = doc.getElementsByTagName("team");
        ArrayList<String> equipoEmpatador = new ArrayList<>();
        int maxEmpates = 0;
        for (int i = 0; i < team.getLength(); i++) {

            Node nodo = team.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {

                Element equipos = (Element) nodo;
                String nombre = equipos.getElementsByTagName("name").item(0).getTextContent();

                int empates = Integer.parseInt(equipos.getElementsByTagName("drawn").item(0).getTextContent());
                if (empates > maxEmpates) {
                    maxEmpates = empates;
                    equipoEmpatador.clear();
                    equipoEmpatador.add(nombre);
                } else if (empates == maxEmpates) {
                    equipoEmpatador.add(nombre);

                }
            }

        }

        for (int i = 0; i < equipoEmpatador.size(); i++) {
            System.out.println("Mas empates: " + equipoEmpatador.get(i));
        }

    }

    public static void clasificacion_equipos(Document doc) {
        NodeList evento = doc.getElementsByTagName("evento");
        ArrayList<String> equiposPartido = new ArrayList<>();
        for (int i = 0; i < evento.getLength(); i++) {
            Node nodo = evento.item(2);

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element partido = (Element) nodo;

                String nombreloc = partido.getElementsByTagName("equipolocal").item(0).getTextContent();
                String nombrevis = partido.getElementsByTagName("equipovisitante").item(0).getTextContent();

                equiposPartido.add(nombreloc);
                equiposPartido.add(nombrevis);

            }

        }

        NodeList team = doc.getElementsByTagName("team");
        for (int j = 0; j < team.getLength(); j++) {
            Node nodo2 = team.item(j);
            if (nodo2.getNodeType() == Node.ELEMENT_NODE) {
                Element equipos = (Element) nodo2;
                String nombre = equipos.getElementsByTagName("name").item(0).getTextContent();
                int rank = Integer.parseInt(equipos.getElementsByTagName("rank").item(0).getTextContent());
                if (equiposPartido.contains(nombre)) {
                    System.out.println("Clasificacion: " + rank + " Nombre: " + nombre);

                }
            }

        }
    }

    public static Document creaArbol(String ruta) {
        Document doc = null;
        try {
            DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
            factoria.setIgnoringComments(true);
            DocumentBuilder builder = factoria.newDocumentBuilder();
            doc = builder.parse(ruta);
        } catch (Exception e) {
            System.out.println("Error generando el árbol DOM: " + e.getMessage());
        }
        return doc;
    }

    public void grabarDOM(Document document, String ficheroSalida)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, FileNotFoundException {
        DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
        DOMImplementationLS ls = (DOMImplementationLS) registry.getDOMImplementation("XML 3.0 LS 3.0");
        // Se crea un destino vacio
        LSOutput output = ls.createLSOutput();
        output.setEncoding("UTF-8");
        // Se establece el flujo de salida
        output.setByteStream(new FileOutputStream(ficheroSalida));
        // output.setByteStream(System.out);
        // Permite escribir un documento DOM en XML
        LSSerializer serializer = ls.createLSSerializer();
        // Se establecen las propiedades del serializador
        serializer.setNewLine("\r\n");
        ;
        serializer.getDomConfig().setParameter("format-pretty-print", true);
        // Se escribe el documento ya sea en un fichero o en una cadena de texto
        serializer.write(document, output);
        // String xmlCad=serializer.writeToString(document);
    }
}