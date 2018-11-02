package terminal;

import db.LanguageDB.Type;

public class Language implements Searchable {

    private int id;
    private final String name;
    private final boolean availableDictionary;
    private final Type type;

    public Language(String name, Type type, boolean availableDictionary) {
        this.id = -1;
        this.name = name;
        this.type = type;
        this.availableDictionary = availableDictionary;
    }

    public Language(int id, String name, Type type, boolean availableDictionary) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.availableDictionary = availableDictionary;
    }

    public String merge() {
        return name;
    }

    public boolean isAvailableDictionary() {
        return availableDictionary;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "MainLogic.Language{" +
                "name='" + name + '\'' +
                ", availableDictionary=" + availableDictionary +
                ", type=" + type +
                '}';
    }

    public String getName() {
        return this.name;
    }

    @Override
    public <T> boolean merge(T id) {
        if (id.getClass() == String.class) {
            return this.name.equals(id.toString());
        }
        return false;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
