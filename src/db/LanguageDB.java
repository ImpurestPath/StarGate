package db;

import terminal.Language;

public class LanguageDB {
    public enum Type {
        VISUAL,
        VOICE,
        VIBRATION
    }
    private int id;
    private final String _name;
    private final boolean _availableDictionary;
    private final Type _type;

    LanguageDB(String name, Type type, boolean availableDictionary) {
        this._name = name;
        this._type = type;
        this._availableDictionary = availableDictionary;
    }
    LanguageDB(String name, String type, boolean availableDictionary) {
        this._name = name;
        this._type = type.equals("VISUAL") ? Type.VISUAL : type.equals("VOICE") ? Type.VOICE : Type.VIBRATION;
        this._availableDictionary = availableDictionary;
    }

    LanguageDB(String name, String type, int availableDictionary) {
        this._name = name;
        this._type = type.equals("VISUAL") ? Type.VISUAL : type.equals("VOICE") ? Type.VOICE : Type.VIBRATION;
        this._availableDictionary = availableDictionary == 1;
    }
    LanguageDB(int id, String name, String type, boolean availableDictionary) {
        this._name = name;
        this._type = type.equals("VISUAL") ? Type.VISUAL : type.equals("VOICE") ? Type.VOICE : Type.VIBRATION;
        this._availableDictionary = availableDictionary;
        this.id = id;
    }
    LanguageDB(int id, String name, Type type, boolean availableDictionary) {
        this._name = name;
        this._type = type;
        this._availableDictionary = availableDictionary;
        this.id = id;
    }

    LanguageDB(int id, String name, String type, int availableDictionary) {
        this._name = name;
        this._type = type.equals("VISUAL") ? Type.VISUAL : type.equals("VOICE") ? Type.VOICE : Type.VIBRATION;
        this._availableDictionary = availableDictionary == 1;
        this.id = id;
    }

    LanguageDB(Language language) {
        this._name = language.merge();
        this._type = language.getType();
        this._availableDictionary = language.isAvailableDictionary();
    }

    public String getName() {
        return _name;
    }

    public boolean isAvailableDictionary() {
        return _availableDictionary;
    }

    public Type getType() {
        return _type;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
