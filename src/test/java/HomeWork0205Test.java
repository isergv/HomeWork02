import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class HomeWork0205Test extends BasePage {

    /**
     * (**) Наибольшая сложность: напишите полный набор автотестов
     * для тестирования этого кредитного калькулятора.
     */

    //Проверки страницы "Калькулятор кредита"
    //Заполнение полей. ОК на нажатие кнопки "Рассчитать"
    @Test
    @DisplayName("01. Заполнить 3 поля и нажать кнопку 'Рассчитать' Аннуитетный. ")
    void test_calc_01() {
        $x(String.format(Locators.fieldName, "Сумма кредита, руб.")).setValue("1234");
        $x(String.format(Locators.fieldName, "Срок кредита, месяцев")).setValue("12");
        $x(String.format(Locators.fieldName, "Процентная ставка, % годовых")).setValue("15");
        $x(String.format(Locators.radioName, "Аннуитетный")).click();
        $x(String.format(Locators.buttonName, "Рассчитать")).click();
        $x(String.format(Locators.textP, "Общая сумма выплат: ")).shouldBe(visible);
    }

    @Test
    @DisplayName("02. Заполнить 3 поля и нажать кнопку 'Рассчитать' Дифференцированный. ")
    void test_calc_02() {
        $x(String.format(Locators.fieldName, "Сумма кредита, руб.")).setValue("1234");
        $x(String.format(Locators.fieldName, "Срок кредита, месяцев")).setValue("12");
        $x(String.format(Locators.fieldName, "Процентная ставка, % годовых")).setValue("15");
        $x(String.format(Locators.radioName, "Дифференцированный")).click();
        $x(String.format(Locators.buttonName, "Рассчитать")).click();
        $x(String.format(Locators.textP, "Общая сумма выплат: ")).shouldBe(visible);
    }

    //Заполнение полей, нажатие кнопки "Очистить", проверка placeholder
    @ParameterizedTest
    @DisplayName("03. Заполнение полей, нажатие кнопки \"Очистить\", проверка placeholder")
    @CsvSource({
            "'Сумма кредита, руб.', 12345, 100000",
            "'Срок кредита, месяцев', 15, 12",
            "'Процентная ставка, % годовых', 20, 15.5"
    })
    void test_calc_03(String name, String value, String placeholder) {
        $x(String.format(Locators.fieldName, name)).setValue(value);
        $x(String.format(Locators.buttonName, "Очистить")).click();
        $x(String.format(Locators.hintTitleAttribute, name))
                .shouldHave(attribute("placeholder", placeholder));
    }

    //Заполнение полей некорректными значениями, проверка текста ошибки
    @ParameterizedTest
    @DisplayName("04. Заполнение полей некорректными значениями, проверка текста ошибки")
    @CsvSource({
            "'Сумма кредита, руб.', -1, 'Ошибка: Введите сумму от 1000 до 10000000 рублей'",
            "'Сумма кредита, руб.', 0, 'Ошибка: Введите сумму от 1000 до 10000000 рублей'",
            "'Сумма кредита, руб.', 10000001, 'Ошибка: Введите сумму от 1000 до 10000000 рублей'",
            "'Срок кредита, месяцев', -1, 'Ошибка: Введите срок от 1 до 360 месяцев'",
            "'Срок кредита, месяцев', 0, 'Ошибка: Введите срок от 1 до 360 месяцев'",
            "'Срок кредита, месяцев', 361, 'Ошибка: Введите срок от 1 до 360 месяцев'",
            "'Процентная ставка, % годовых', -1, 'Ошибка: Введите ставку в процентах: от 0.01 до 100'",
            "'Процентная ставка, % годовых', -0, 'Ошибка: Введите ставку в процентах: от 0.01 до 100'",
            "'Процентная ставка, % годовых', 101, 'Ошибка: Введите ставку в процентах: от 0.01 до 100'"
    })
    void test_calc_04(String name, String value, String errorText) {
        $x(String.format(Locators.fieldName, name)).setValue(value);
        $x(String.format(Locators.buttonName, "Рассчитать")).click();
        $x(String.format(Locators.error, name, errorText)).shouldHave(text(errorText));
    }

    // todo Не смог поймать локатор, чтобы мышкой делать увеличение и уменьшение значений
    //Проверка увеличения значения с клавиатуры
    @ParameterizedTest
    @DisplayName("05. Проверка увеличения значения с клавиатуры")
    @CsvSource({
            "'Сумма кредита, руб.', 10, 11",
            "'Срок кредита, месяцев', 10, 11",
            "'Процентная ставка, % годовых', '0,02', '0.03'",
    })
    void test_calc_05(String name, String value, String result) {
        $x(String.format(Locators.fieldName, name)).setValue(value);
        $x(String.format(Locators.fieldName, name)).sendKeys(Keys.ARROW_UP);
        $x(String.format(Locators.fieldName, name)).shouldBe(value(result));
    }

    //Проверка уменьшения значения с клавиатуры
    @ParameterizedTest
    @DisplayName("06. Проверка уменьшения значения с клавиатуры")
    @CsvSource({
            "'Сумма кредита, руб.', 10, 9",
            "'Срок кредита, месяцев', 10, 9",
            "'Процентная ставка, % годовых', '0,02', '0.01'",
    })
    void test_calc_06(String name, String value, String result) {
        $x(String.format(Locators.fieldName, name)).setValue(value);
        $x(String.format(Locators.fieldName, name)).sendKeys(Keys.ARROW_DOWN);
        $x(String.format(Locators.fieldName, name)).shouldBe(value(result));
    }

    //Проверки страницы "Результаты расчёта"
    //Заполнение полей. ОК на нажатие кнопки 'Рассчитать'. Проверка значений на странице "Результаты расчёта"
    @ParameterizedTest
    @CsvSource({
            "10000.00, 10, 10, 10000.00, 10, 10, Аннуитетный, 1046.40, 10464.04, 10464.04",
    })
    @DisplayName("07. Заполнение полей. ОК на нажатие кнопки 'Рассчитать'. Проверка значений на странице 'Результаты расчёта'. Аннуитетный. ")
    void test_calc_07(String amount, String term, String rate, String result_amount, String result_term,
                      String result_rate, String result_payment_type, String monthly_payment, String total_payment, String result) {
        $x(String.format(Locators.fieldName, "Сумма кредита, руб.")).setValue(amount);
        $x(String.format(Locators.fieldName, "Срок кредита, месяцев")).setValue(term);
        $x(String.format(Locators.fieldName, "Процентная ставка, % годовых")).setValue(rate);
        $x(String.format(Locators.radioName, "Аннуитетный")).click();
        $x(String.format(Locators.buttonName, "Рассчитать")).click();
        $x(String.format(Locators.calculation)).shouldHave(attribute("style", "display: none;"));
        $x(String.format(Locators.textP, "Общая сумма выплат: ")).shouldBe(visible);
        $x(String.format(Locators.textP, "Сумма кредита: ")).shouldBe(text(result_amount));
        $x(String.format(Locators.textP, "Срок кредита: ")).shouldBe(text(result_term));
        $x(String.format(Locators.textP, "Процентная ставка: ")).shouldBe(text(result_rate));
        $x(String.format(Locators.textP, "Тип платежа: ")).shouldBe(text(result_payment_type));
        $x(String.format(Locators.textP, "Ежемесячный платёж: ")).shouldBe(text(monthly_payment));
        $x(String.format(Locators.textP, "Общая сумма выплат: ")).shouldBe(text(total_payment));
        $x(String.format(Locators.textP, "Общая сумма выплат: ")).shouldBe(text(result));
    }

    //Заполнение полей. ОК на нажатие кнопки 'Рассчитать'. Проверка значений на странице "Результаты расчёта"
    @ParameterizedTest
    @CsvSource({
            "10000.00, 10, 10, 10000.00, 10, 10, Дифференцированный, 1083.33, 1008.33," + /*'Платежи уменьшаются каждый месяц. Детальную информацию смотрите в графике платежей',*/ "458.33, 10458.33"
    })
    @DisplayName("08. Заполнение полей. ОК на нажатие кнопки 'Рассчитать'. Проверка значений на странице 'Результаты расчёта'. Дифференцированный. ")
    void test_calc_08(String amount, String term, String rate, String result_amount, String result_term,
                      String result_rate, String result_payment_type, String first_payment, String last_payment/*, String text*/, String overpayment, String total_payment) {
        $x(String.format(Locators.fieldName, "Сумма кредита, руб.")).setValue(amount);
        $x(String.format(Locators.fieldName, "Срок кредита, месяцев")).setValue(term);
        $x(String.format(Locators.fieldName, "Процентная ставка, % годовых")).setValue(rate);
        $x(String.format(Locators.radioName, "Дифференцированный")).click();
        $x(String.format(Locators.buttonName, "Рассчитать")).click();
        $x(String.format(Locators.calculation)).shouldHave(attribute("style", "display: none;"));
        $x(String.format(Locators.textP, "Общая сумма выплат: ")).shouldBe(visible);
        $x(String.format(Locators.textP, "Сумма кредита: ")).shouldBe(text(result_amount));
        $x(String.format(Locators.textP, "Срок кредита: ")).shouldBe(text(result_term));
        $x(String.format(Locators.textP, "Процентная ставка: ")).shouldBe(text(result_rate));
        $x(String.format(Locators.textP, "Тип платежа: ")).shouldBe(text(result_payment_type));
        $x(String.format(Locators.textP, "Первый платёж: ")).shouldBe(text(first_payment));
        $x(String.format(Locators.textP, "Последний платёж: ")).shouldBe(text(last_payment));
        $x(String.format(Locators.textR, "Платежи уменьшаются каждый месяц. Детальную информацию смотрите в графике платежей"))
                .shouldBe(text("Платежи уменьшаются каждый месяц. Детальную информацию смотрите в графике платежей"));
        $x(String.format(Locators.textP, "Переплата по кредиту: ")).shouldBe(text(overpayment));
        $x(String.format(Locators.textP, "Общая сумма выплат: ")).shouldBe(text(total_payment));
    }

    //todo Кнопка Выполнить новый расчёт
    //todo Кнопка Открыть график платежей
    //todo Кнопка Проверки в окне График платежей
}
