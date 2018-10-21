package DB;

import Terminal.Language;

public class LanguageDB {
    public enum Type {
        VISUAL,
        VOICE,
        VIBRATION
    }

    private final String _name;
    private final boolean _availableDictionary;
    private final Type _type;

    LanguageDB(String name, Type type, boolean availableDictionary) {
        this._name = name;
        this._type = type;
        this._availableDictionary = availableDictionary;
    }

    LanguageDB(String name, String type, int availableDictionary) {
        this._name = name;
        this._type = type.equals("VISUAL") ? Type.VISUAL : type.equals("VOICE") ? Type.VOICE : Type.VIBRATION;
        this._availableDictionary = availableDictionary == 1;
    }

    LanguageDB(Language language) {
        this._name = language.getName();
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

}
