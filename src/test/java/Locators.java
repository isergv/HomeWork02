public class Locators {
    //Поля:
    //Текстовые: Сумма кредита, руб. | Срок кредита, месяцев | Процентная ставка, % годовых
    static final String fieldName = "//label[text()='%s']//ancestor::div[@class='form-group']//input";

    //Радио-баттоны: Аннуитетный | Дифференцированный
    static final String radioName = "//*[contains(., '%s')]/input";

    //Кнопки: Рассчитать | Очистить
    static final String buttonName = "//button[text()='%s']";

    //Текст на странице
    //Заголовки: Калькулятор кредита
    static final String pageTitleH1 = "//h1[text()='%s']";
    static final String pageTitleH2 = "//h2[text()='%s']";

    //Подсказки: Введите сумму от 1000 до 10000000 рублей | Введите ставку в процентах: от 0.01 до 100
    static final String fieldTitle = "//div[text()='%s']//ancestor::div[@class='form-group']//input";

    //placeholder: 10000000 | 12 | 15.5
    static final String hintTitle = "//label[text()='%s']//ancestor::div[@class='form-group']//input[@placeholder='%s']";
    //Если надо взять значение атрибута
    static final String hintTitleAttribute = "//label[text()='%s']//ancestor::div[@class='form-group']//input";

    //Ошибки:
    //Ошибка: Введите сумму от 1000 до 10000000 рублей
    //Ошибка: Введите срок от 1 до 360 месяцев
    //Ошибка: Введите ставку в процентах: от 0.01 до 100
    static final String error = "//label[text()='%s']//ancestor::div[@class='form-group']//div[text()='%s']";

    //Результаты расчёта:
    //Текст:
    static final String text = "//*[contains(., '%s')]/span";
    static final String textR = "//*[contains(., '%s')]";
    static final String textP = "//p[contains(., '%s')]/span";

    //Модалка прогресса рассчета
    //Атрибут style="display: block; - модалка на экране
    //Атрибут style="display: none; - модалка не отображается
    static final String calculation = "//div[@id='calculation-modal']";

    //Таблица графика платежей
    static final String table = "//tbody/tr[%s]/td[%s]";
}
