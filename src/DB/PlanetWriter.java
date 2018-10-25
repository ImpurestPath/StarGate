package DB;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


class PlanetWriter {
    private String file;
    private Document document;

    PlanetWriter(String file) throws IOException, SAXException, ParserConfigurationException {
        this.file = file;
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        document = documentBuilder.parse(file);
    }

    private Element createPlanet(PlanetDB planet) {
        Element pl = document.createElement("currentPlanet");

        Element name = document.createElement("name");

        name.setTextContent(planet.getName());

        Element temperature = document.createElement("temperature");
        temperature.setTextContent(Integer.toString(planet.getTemperature()));

        Element pressure = document.createElement("pressure");
        pressure.setTextContent(Long.toString(planet.getPressure()));

        Element languages = document.createElement("languages");

        //Add language
        List<LanguageDB> languageArrayList = planet.getLanguages();
        for (LanguageDB languageItem :
                languageArrayList) {
            Element language = document.createElement("language");
            Element nameLanguage = document.createElement("nameLanguage");
            nameLanguage.setTextContent(languageItem.getName());
            Element typeLanguage = document.createElement("typeLanguage");
            typeLanguage.setTextContent(languageItem.getType().toString());
            Element availableDictionaryLanguage = document.createElement("availableDictionary");
            availableDictionaryLanguage.setTextContent(languageItem.isAvailableDictionary() ? "true" : "false");
            languages.appendChild(language);
            language.appendChild(nameLanguage);
            language.appendChild(typeLanguage);
            language.appendChild(availableDictionaryLanguage);
        }
        Element countries = document.createElement("countries");

        List<CountryDB> countryArrayList = planet.getCountries();
        for (CountryDB countryItem : countryArrayList) {
            Element country = document.createElement("country");
            Element nameCountry = document.createElement("nameCountry");
            nameCountry.setTextContent(countryItem.getName());
            Element areaCountry = document.createElement("areaCountry");
            areaCountry.setTextContent(Long.toString(countryItem.getArea()));
            Element races = document.createElement("racesCountry");
            List<RaceDB> raceArrayList = countryItem.getRaces();
            for (RaceDB raceItem :
                    raceArrayList) {
                Element race = document.createElement("raceCountry");
                Element nameRace = document.createElement("nameRace");
                nameRace.setTextContent(raceItem.getName());
                Element amountRace = document.createElement("amountRace");
                amountRace.setTextContent(Long.toString(raceItem.getAmount()));
                Element behaviorRace = document.createElement("behaviorRace");
                behaviorRace.setTextContent(raceItem.getBehavior().toString());
                races.appendChild(race);
                race.appendChild(nameRace);
                race.appendChild(amountRace);
                race.appendChild(behaviorRace);
            }

            countries.appendChild(country);
            country.appendChild(nameCountry);
            country.appendChild(areaCountry);
            country.appendChild(races);
        }
        pl.appendChild(name);
        pl.appendChild(pressure);
        pl.appendChild(temperature);
        pl.appendChild(languages);
        pl.appendChild(countries);
        return pl;
    }

    int addNewPlanet(PlanetDB planet) throws TransformerFactoryConfigurationError, DOMException {
        // Получаем корневой элемент
        Node root = document.getDocumentElement();
        Element pl = createPlanet(planet);
        int count = Integer.parseInt(root.getLastChild().getAttributes().getNamedItem("id").getNodeValue());
        planet.setId(count + 1);
        pl.setAttribute("id", Integer.toString(count + 1));
        root.appendChild(pl);
        writeDocument();
        return count + 1;
    }

    void deletePlanet(int id) {
        Node root = document.getDocumentElement();
        NodeList rootChildren = root.getChildNodes();
        for (int i = 0; i < rootChildren.getLength(); i++) {
            if (rootChildren.item(i).getAttributes().getNamedItem("id").getTextContent().equals(Long.toString(id))) {
                root.removeChild(rootChildren.item(i));
                writeDocument();
                return;
            }
        }
    }

    private void writeDocument() throws TransformerFactoryConfigurationError {
        try {

            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileWriter writer = new FileWriter(file, false);
            //FileOutputStream fos = new FileOutputStream(file, false);
            StreamResult result = new StreamResult(writer);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }

    void updatePlanet(int id, PlanetDB planet) {
        Node root = document.getDocumentElement();
        NodeList rootChildren = root.getChildNodes();
        for (int i = 0; i < rootChildren.getLength(); i++) {
            if (rootChildren.item(i).getAttributes().getNamedItem("id").getTextContent().equals(Long.toString(id))) {
                Element newPlanet = createPlanet(planet);
                planet.setId(id);
                newPlanet.setAttribute("id", Integer.toString(id));
                root.replaceChild(newPlanet, rootChildren.item(i));
                writeDocument();
                return;
            }
        }
    }

}

