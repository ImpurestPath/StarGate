package ru.ifmo.oop.db.DTO;

public class CountryDTO {


    private int id;
    private final String _name;
    private final long _area;

    public CountryDTO(int id, String name, long area) {
        this.id = id;
        this._name = name;
        this._area = area;
    }
    public CountryDTO(String name, long area) {
        this.id = -1;
        this._name = name;
        this._area = area;
    }

    public long getArea() {
        return _area;
    }

    public String getName() {
        return _name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
}
