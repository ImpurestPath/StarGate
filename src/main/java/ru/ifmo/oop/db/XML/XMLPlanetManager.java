/*
package db;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;


public class XMLPlanetManager implements PlanetDAO {
    private final PlanetWriter writer;
    private final PlanetReader reader;

    public XMLPlanetManager(String fileWay) throws ExceptionDAO {
        try {
            writer = new PlanetWriter(fileWay);
            reader = new PlanetReader(fileWay);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new ExceptionDAO(e);
        }
    }

    public List<PlanetDB> getAll() throws ExceptionDAO {
        try {
            return reader.getPlanets();
        } catch (Exception e) {
            throw new ExceptionDAO(e);
        }
    }

    public int add(PlanetDB planet) throws ExceptionDAO {
        try {
            int id = writer.addNewPlanet(planet);
            reader.updateDocument();
            return id;
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new ExceptionDAO(e);
        }
    }

    public void delete(int id) throws ExceptionDAO {
        try {
            writer.deletePlanet(id);
            reader.updateDocument();
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new ExceptionDAO(e);
        }
    }

    public void update(int id, PlanetDB planet) throws ExceptionDAO {
        try {
            writer.updatePlanet(id, planet);
            reader.updateDocument();
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new ExceptionDAO(e);
        }
    }
}
*/
