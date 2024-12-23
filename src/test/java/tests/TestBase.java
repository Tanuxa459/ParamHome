package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://moscow-emb.mfa.gov.tr";
        //Configuration.baseUrl = "https://carltonmoscow.com";
        Configuration.browserSize = "1980x1080";
        Configuration.pageLoadStrategy = "eager";
    }


}