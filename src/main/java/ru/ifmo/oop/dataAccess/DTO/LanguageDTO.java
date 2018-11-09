package ru.ifmo.oop.dataAccess.DTO;

public class LanguageDTO {
    public enum Type {
        VISUAL,
        VOICE,
        VIBRATION
    }
    private int id;
    private final String name;
    private final boolean availableDictionary;
    private final Type type;

    public LanguageDTO(String name, Type type, boolean availableDictionary) {
        this.name = name;
        this.type = type;
        this.availableDictionary = availableDictionary;
    }
    LanguageDTO(String name, String type, boolean availableDictionary) {
        this.name = name;
        this.type = type.equals("VISUAL") ? Type.VISUAL : type.equals("VOICE") ? Type.VOICE : Type.VIBRATION;
        this.availableDictionary = availableDictionary;
    }

    LanguageDTO(String name, String type, int availableDictionary) {
        this.name = name;
        this.type = type.equals("VISUAL") ? Type.VISUAL : type.equals("VOICE") ? Type.VOICE : Type.VIBRATION;
        this.availableDictionary = availableDictionary == 1;
    }
    LanguageDTO(int id, String name, String type, boolean availableDictionary) {
        this.name = name;
        this.type = type.equals("VISUAL") ? Type.VISUAL : type.equals("VOICE") ? Type.VOICE : Type.VIBRATION;
        this.availableDictionary = availableDictionary;
        this.id = id;
    }
    LanguageDTO(int id, String name, Type type, boolean availableDictionary) {
        this.name = name;
        this.type = type;
        this.availableDictionary = availableDictionary;
        this.id = id;
    }

    public LanguageDTO(int id, String name, String type, int availableDictionary) {
        this.name = name;
        this.type = type.equals("VISUAL") ? Type.VISUAL : type.equals("VOICE") ? Type.VOICE : Type.VIBRATION;
        this.availableDictionary = availableDictionary == 1;
        this.id = id;
    }

    /*public LanguageDB(Language language) {
        this.name = language.merge();
        this.type = language.getType();
        this.availableDictionary = language.isAvailableDictionary();
    }*/

    public String getName() {
        return name;
    }

    public boolean isAvailableDictionary() {
        return availableDictionary;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
