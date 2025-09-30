import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;

public class BasePage {

    Locators locators = new Locators();

    @BeforeAll
    static void before_all(TestInfo test_info) {
        System.out.println(test_info.getDisplayName() + " - начали выполнение.");
        Configuration.browser = Browsers.CHROME;
    }

    @AfterAll
    static void after_all(TestInfo test_info) {
        System.out.println(test_info.getDisplayName() + " - закончили выполнение.");
        closeWindow();
        closeWebDriver();
    }

    @BeforeEach
    void before_each(TestInfo test_info) {
        Configuration.timeout = 20000;
        System.out.println("Тест " + test_info.getDisplayName() + " - начали выполнение.");
        open("https://slqamsk.github.io/cases/loan-calc/v01/");
    }

    @AfterEach
    void after_each(TestInfo test_info) {
        System.out.println("Тест " + test_info.getDisplayName() + " - закончили выполнение.");
    }
}
