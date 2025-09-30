import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class HomeWork0201Test extends BasePage {

    /**
     Самая низкая сложность. Напишите автотест, который:
     1) Заполнит 3 поля и нажмёт кнопку "Рассчитать".
     */

    @Test
    @DisplayName("01. Заполнить 3 поля и нажать кнопку 'Рассчитать'")
    void test_calc() {
        $x(String.format(Locators.fieldName, "Сумма кредита, руб.")).setValue("1234");
        $x(String.format(Locators.fieldName, "Срок кредита, месяцев")).setValue("12");
        $x(String.format(Locators.fieldName, "Процентная ставка, % годовых")).setValue("15");
        $x(String.format(Locators.buttonName, "Рассчитать")).click();
        $x(String.format(Locators.textR, "Прервать расчёт")).shouldBe(text("Прервать расчёт"));
    }
}
