import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HomeWork0202Test extends BasePage {

    /**
     * Низкая сложность:
     * 2) Дождётся появления результатов расчёта и проверит, что указанные параметры кредита корректные,
     * а также, что сумма аннуитетного платежа вычислена правильно.
     */

    @Test
    @DisplayName("02. Дождаться проверки результатов расчетов и проверить результат")
    void test_calc() {
        $x(String.format(Locators.fieldName, "Сумма кредита, руб.")).setValue("10000");
        $x(String.format(Locators.fieldName, "Срок кредита, месяцев")).setValue("10");
        $x(String.format(Locators.fieldName, "Процентная ставка, % годовых")).setValue("10");
        $x(String.format(Locators.radioName, "Аннуитетный")).click();
        $x(String.format(Locators.buttonName, "Рассчитать")).click();
        $x(String.format(Locators.calculation)).shouldHave(attribute("style", "display: none;"));
        $x(String.format(Locators.textP, "Общая сумма выплат: ")).shouldBe(visible);
        $x(String.format(Locators.textP, "Общая сумма выплат: ")).shouldBe(text("10464.04"));
    }
}
