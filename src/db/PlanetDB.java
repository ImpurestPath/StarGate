package db;


public class PlanetDB {
    private final int _temperature;
    private final long _pressure;
    private int _id;
    private final String _name;

    PlanetDB(int id, String name, int temperature, long pressure) {
        this._name = name;
        this._temperature = temperature;
        this._pressure = pressure;
        this._id = id;
    }
    public PlanetDB(String name, int temperature, long pressure) {
        this._name = name;
        this._temperature = temperature;
        this._pressure = pressure;
        this._id = -1;
    }


    public int getTemperature() {
        return _temperature;
    }

    public long getPressure() {
        return _pressure;
    }

    public String getName() {
        return _name;
    }

    public int getId() {
        return _id;
    }

    void setId(int id) {
        this._id = id;
    }

}
