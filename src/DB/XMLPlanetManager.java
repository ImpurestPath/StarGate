package DB;

import Terminal.Planet;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;


public class XMLPlanetManager implements PlanetDAO {
    private PlanetWriter writer;
    private PlanetReader reader;

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

    public boolean delete(int id) throws ExceptionDAO {
        try {
            boolean successful = writer.deletePlanet(id);
            reader.updateDocument();
            return successful;
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new ExceptionDAO(e);
        }
    }

    public boolean update(int id, PlanetDB planet) throws ExceptionDAO {
        try {
            boolean successful = writer.updatePlanet(id, planet);
            reader.updateDocument();
            return successful;
        }catch (IOException | SAXException | ParserConfigurationException e) {
            throw new ExceptionDAO(e);
        }
    }
}
