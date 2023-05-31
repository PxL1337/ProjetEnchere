package fr.eni.projetenchere.dal;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * The type Settings.
 */
public class Settings {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static {
        try {
            File settingsFile = new File("src/main/resources/META-INF/db_config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(settingsFile);
            doc.getDocumentElement().normalize();

            NodeList connectionNodeList = doc.getElementsByTagName("connection");
            Node connectionNode = connectionNodeList.item(0);
            if (connectionNode.getNodeType() == Node.ELEMENT_NODE) {
                Element connectionElement = (Element) connectionNode;
                url = connectionElement.getElementsByTagName("url").item(0).getTextContent();
                user = System.getenv(connectionElement.getElementsByTagName("user").item(0).getTextContent());
                password = System.getenv(connectionElement.getElementsByTagName("password").item(0).getTextContent());
                driver = connectionElement.getElementsByTagName("driver").item(0).getTextContent();

                if (url == null || user == null || password == null) {
                    throw new IllegalStateException("Les variables d'environnement ne sont pas définies correctement.");
                }

            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets property.
     *
     * @param key the key
     * @return the property
     */
    public static String getProperty(String key) {
        switch (key) {
            case "url":
                return url;
            case "user":
                return user;
            case "password":
                return password;
            case "driver":
                return driver;
            default:
                return null;
        }
    }
}
