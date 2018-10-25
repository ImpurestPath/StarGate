package Terminal;

import DB.LanguageDB;
import DB.LanguageDB.Type;

public class Language implements Searchable {


    private final String _name;
    private final boolean _availableDictionary;
    private final Type _type;

    public Language(String name, Type type, boolean availableDictionary) {
        this._name = name;
        this._type = type;
        this._availableDictionary = availableDictionary;
    }

    Language(LanguageDB languageDB) {
        this._name = languageDB.getName();
        this._type = languageDB.getType();
        this._availableDictionary = languageDB.isAvailableDictionary();
    }

    public String merge() {
        return _name;
    }

    public boolean isAvailableDictionary() {
        return _availableDictionary;
    }

    public Type getType() {
        return _type;
    }

    @Override
    public String toString() {
        return "MainLogic.Language{" +
                "_name='" + _name + '\'' +
                ", _availableDictionary=" + _availableDictionary +
                ", _type=" + _type +
                '}';
    }
    public String getName(){
        return this._name;
    }
    @Override
    public <T> boolean merge(T id) {
        if (id.getClass() == String.class){
            return this._name.equals(id.toString());
        }
        return false;
    }
}
