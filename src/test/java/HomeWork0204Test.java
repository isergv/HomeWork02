import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class HomeWork0204Test extends BasePage {

    /**
     * (*) Сложность чуть выше среднего:
     * Добавьте 2-й параметризованный автотест, который проверит расчёт для разных сроков кредита.
     */

    @ParameterizedTest
    @DisplayName("04. Параметризованный автотест")
    @CsvSource({
            "10000, 10, 10, 10464.04",
            "50000, 12, 15, 54154.99",
            "100000, 24, 20, 122149.93"
    })
    void test01_calc(String amount, String term, String rate, String result)  {
        Configuration.timeout = 20000;
        $x(String.format(Locators.fieldName, "Сумма кредита, руб.")).setValue(amount);
        $x(String.format(Locators.fieldName, "Срок кредита, месяцев")).setValue(term);
        $x(String.format(Locators.fieldName, "Процентная ставка, % годовых")).setValue(rate);
        $x(String.format(Locators.radioName, "Аннуитетный")).click();
        $x(String.format(Locators.buttonName, "Рассчитать")).click();
        $x(String.format(Locators.calculation)).shouldHave(attribute("style", "display: none;"));
        $x(String.format(Locators.textP, "Общая сумма выплат: ")).shouldBe(visible);
        $x(String.format(Locators.textP, "Общая сумма выплат: ")).shouldBe(text(result));

    }
}
