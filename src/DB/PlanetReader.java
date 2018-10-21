package DB;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class PlanetReader {
    private Document document;
    private String file;

    PlanetReader(String file) throws IOException, ParserConfigurationException, SAXException {
        // Создается построитель документа
        this.file = file;
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // Создается дерево DOM документа из файла
        document = documentBuilder.parse(file);
    }

    List<PlanetDB> getPlanets() {

        // Получаем корневой элемент
        Node root = document.getDocumentElement();
        NodeList rootChildren = root.getChildNodes();
        List<PlanetDB> planets = new ArrayList<>();
        for (int i = 0; i < rootChildren.getLength(); i++) {
            Node planet = rootChildren.item(i);
            NodeList planetChildren = planet.getChildNodes();
            String namePlanet = planetChildren.item(0).getTextContent();
            int temperaturePlanet = Integer.parseInt(planetChildren.item(1).getTextContent());
            //Race.Behavior behaviorPlanet = planetChildren.item(2).getTextContent().equals("ANGRY") ? Race.Behavior.ANGRY : Race.Behavior.NEUTRAL;
            long pressurePlanet = Long.parseLong(planetChildren.item(2).getTextContent());
            NodeList languagesNodes = planetChildren.item(3).getChildNodes();
            List<LanguageDB> languages = new ArrayList<>();
            for (int j = 0; j < languagesNodes.getLength(); j++) {
                NodeList languagesNodesChildren = languagesNodes.item(j).getChildNodes();
                languages.add(new LanguageDB(languagesNodesChildren.item(0).getTextContent(),
                        languagesNodesChildren.item(1).getTextContent().equals("VOICE") ? LanguageDB.Type.VOICE :
                                languagesNodesChildren.item(1).getTextContent().equals("VISUAL") ?
                                        LanguageDB.Type.VISUAL : LanguageDB.Type.VIBRATION,
                        languagesNodesChildren.item(2).getTextContent().equals("true")));
            }
            NodeList countriesNodes = planetChildren.item(4).getChildNodes();
            List<CountryDB> countries = new ArrayList<>();
            for (int j = 0; j < countriesNodes.getLength(); j++) {
                NodeList countriesNodesChildren = countriesNodes.item(j).getChildNodes();
                //Maybe use behavior
                NodeList racesNodes = countriesNodesChildren.item(2).getChildNodes();
                List<RaceDB> races = new ArrayList<>();
                for (int k = 0; k < racesNodes.getLength(); k++) {
                    NodeList racesNodesChildren = racesNodes.item(k).getChildNodes();
                    races.add(new RaceDB(racesNodesChildren.item(0).getTextContent(),
                            Long.parseLong(racesNodesChildren.item(1).getTextContent()),
                            racesNodesChildren.item(2).getTextContent().equals("ANGRY")));
                }
                countries.add(new CountryDB(countriesNodesChildren.item(0).getTextContent(),
                        Long.parseLong(countriesNodesChildren.item(1).getTextContent()), races));

            }
            planets.add(new PlanetDB(namePlanet, temperaturePlanet, pressurePlanet, languages, countries,
                    Integer.parseInt(planet.getAttributes().getNamedItem("id").getTextContent())));
        }
        return planets;
    }

    void updateDocument() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        document = documentBuilder.parse(file);
    }


}
