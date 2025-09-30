import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class HomeWork0203Test extends BasePage {

    /**
     * Средняя сложность:
     * Откроет график платежей и убедится, что туда корректно перенесены данные со страницы результатов расчёта.
     */

    @Test
    @DisplayName("03. Откроет график платежей и убедится, что туда корректно перенесены данные со страницы результатов расчёта.")
    void test_calc() {
        $x(String.format(Locators.fieldName, "Сумма кредита, руб.")).setValue("10000");
        $x(String.format(Locators.fieldName, "Срок кредита, месяцев")).setValue("10");
        $x(String.format(Locators.fieldName, "Процентная ставка, % годовых")).setValue("10");
        $x(String.format(Locators.radioName, "Аннуитетный")).click();
        $x(String.format(Locators.buttonName, "Рассчитать")).click();
        //$x(String.format(Locators.textR, "Прервать расчёт")).shouldBe(disappear);
        //Не смог справиться с ожиданием, когда пропадет модалка подсчёта, перепробовал кучу методов, не получается
        //Пришлось увеличить время ожидания и появления кнопки открытия графика платежей
        //(просто как одного из элементов страницы)
        //Использовал слип, потом shouldBe(visible) для элемента на странице.
        //Но как проверить отсутствие модалки - не понял (
        //sleep(20000);
        $x(String.format(Locators.textP, "Общая сумма выплат: ")).shouldBe(visible);
        $x(String.format(Locators.buttonName, "Открыть график платежей")).click();
        switchTo().window(1);
        $x(String.format(Locators.pageTitleH2, "График платежей")).shouldBe(text("График платежей"));
        $x(String.format(Locators.textP, "Сумма кредита: ")).shouldBe(text("10000.00"));
        $x(String.format(Locators.textP, "Срок кредита: ")).shouldBe(text("10"));
        $x(String.format(Locators.textP, "Процентная ставка: ")).shouldBe(text("10"));
        $x(String.format(Locators.textP, "Тип платежа: ")).shouldBe(text("Аннуитетный"));
    }
}
