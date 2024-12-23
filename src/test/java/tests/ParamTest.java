package tests;

import data.Language;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ParamTest extends TestBase {

    @EnumSource(Language.class)
    @Tag("SMOKE")
    @ParameterizedTest(name = "Проверка заголовка на соответствующем языке {0}")
    void titleLanguageTest(Language language) {
        open("/Mission");
        $("#langs").click();
        $(byTagAndText("a", language.description)).click();
        $("#logoTextSuffix").shouldHave(text(language.titleOfPage));
    }

    static Stream<Arguments> checkAtributeOfMenuOnSuchLanguage() {
        return Stream.of(
                Arguments.of(Language.RU,
                        List.of("Посольство", "Объявления", "Речи", "Предыдущие Послы")
                ),

                Arguments.of(Language.TUR,
                        List.of("Büyükelçilik", "Duyurular", "Konuşma Metinleri", "Önceki Büyükelçilerimiz")
                )
        );
    }
    @MethodSource
    @Tag("WEB")
    @ParameterizedTest(name = "Проверка наличия кнопок {1} на соответствующем языке {0}")
    void checkAtributeOfMenuOnSuchLanguage(Language language, List<String> expectedButtons) {
        open("/Mission");
        $("#langs").click();
        $(byTagAndText("a", language.description)).click();
        $("#logoTextSuffix").shouldHave(text(language.titleOfPage));
        $("#visaMenu").click();
        $(".dropdown-menu").$$("a").shouldHave(texts(expectedButtons));

    }
    @CsvSource(value = {
            "Русский , ПОСОЛЬСТВО ТУРЕЦКОЙ РЕСПУБЛИКИ В МОСКВЕ",
            "Türkçe , T.C. MOSKOVA BÜYÜKELÇİLİĞİ"
    })
    @Tag("SMOKE")
    @ParameterizedTest(name = "Проверка заголовка на соответствующем языке {0}")
    void titleLanguageTest1(String lang, String title) {
        open("/Mission");
        $("#langs").click();
        $(byTagAndText("a", lang)).click();
        $("#logoTextSuffix").shouldHave(text(title));
    }

}
