package data;

public enum Language {
    RU("Русский", "ПОСОЛЬСТВО ТУРЕЦКОЙ РЕСПУБЛИКИ В МОСКВЕ"),
    TUR("Türkçe", "T.C. MOSKOVA BÜYÜKELÇİLİĞİ");

    public final String description;
    public final String titleOfPage;

    Language(String description, String titleOfPage) {
        this.description = description;
        this.titleOfPage = titleOfPage;
    }
}

