import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class Calc {

    private static final int numberOfColumns = 5;

    // Данные для проверки: Месяц, Ежемесячный платёж, Основной долг, Проценты,Остаток долга
    /*String[][] creditInfo = {
            {"1", "474.86", "469.00", "5.86", "1876.00"},
            {"2", "473.69", "469.00", "4.69", "1407.00"},
            {"3", "472.52", "469.00", "3.52", "938.00"},
            {"4", "471.35", "469.00", "2.35", "469.00"},
            {"5", "470.17", "469.00", "1.17", "0.00"}
    };*/

    void creditCalc(String[][] creditInfo) {
        int lenght = creditInfo.length;
        for (int i = 0; i < lenght; i++) {
            for (int column = 0; column < numberOfColumns; column++) {
                //$x(String.format("//tbody/tr[%s]/td[%s]", i + 1, column + 1))
                $x(String.format(Locators.table, i + 2, column + 1))
                        .shouldHave(text(creditInfo[i][column]));
                System.out.println("Значения = " + $x(String.format(Locators.table, i + 2, column + 1)).getText() +
                        " массив =" + creditInfo[i][column]);
            }
        }
    }
}
