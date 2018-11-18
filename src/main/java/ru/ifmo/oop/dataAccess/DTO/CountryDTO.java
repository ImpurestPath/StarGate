package ru.ifmo.oop.dataAccess.DTO;

public class CountryDTO {


    private int id;
    private final String name;
    private final long area;
    private int idPlanet;

    public CountryDTO(int id, String name, long area, int idPlanet) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.idPlanet = idPlanet;
    }
    public CountryDTO(String name, long area, int idPlanet) {
        this.id = -1;
        this.name = name;
        this.area = area;
        this.idPlanet = idPlanet;
    }

    public long getArea() {
        return area;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getIdPlanet() {
        return idPlanet;
    }
}
