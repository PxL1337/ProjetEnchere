package fr.eni.projetenchere.dal;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
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
            InputStream settingsFile = Settings.class.getResourceAsStream("/META-INF/context.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(settingsFile));
            doc.getDocumentElement().normalize();

            NodeList resourceNodeList = doc.getElementsByTagName("Resource");
            Node resourceNode = resourceNodeList.item(0);
            if (resourceNode.getNodeType() == Node.ELEMENT_NODE) {
                Element resourceElement = (Element) resourceNode;
                url = resourceElement.getAttribute("url");
                user = System.getenv(resourceElement.getAttribute("username"));
                password = System.getenv(resourceElement.getAttribute("password"));
                driver = resourceElement.getAttribute("driverClassName");

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
