import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner; // Импортируем Сканер для ввода данных из консоли

public class Calc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // создаем экземпляр сканера для чтения с консоли
        System.out.println("Введите одной строкой математическое выражение, состоящее из двух операндов и одного оператора, ");
        System.out.println("Внимание: принимаются только арабские или только римские числа от 1 до 10 включительно и один из операторов (+, -, /, *), через пробелы");
        System.out.print("-> ");
        String formula = sc.nextLine(); // записываем выражение в строку с именем formula
        sc.close();

        formula = formula.trim();       // обрезаем лишние пробелы вначале и в конце
//        int spaceIndex = formula.indexOf(' '); // находим индекс 1-го пробела (перед оператором)
//        String operand1 = formula.substring(0, spaceIndex); // извлекаем операнд 1
//        String operator = formula.substring(spaceIndex + 1, spaceIndex + 2);   // извлекаем оператор
//        String operand2 = formula.substring(spaceIndex + 3, formula.length());  // извлекаем операнд 2

        String[] parameters = formula.split("\\s+");    // разбираем строку на массив элементов, разделитель - пробел
//        for (String parameter : parameters) {        // распечатка массива для проверки
//
//            System.out.println(parameter);
//        }
// Проверяем, являются ли введенные данные корректным математическим выражением
        if (parameters.length > 3) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("throws Exception //т.к. формат математической операции не соответствует заданию - два операнда и один оператор (+, -, /, *)");
            }
        } else if(parameters.length < 3) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("throws Exception //т.к. строка не является математической операцией");
            }
        }

        String operator = parameters[1]; // выводим оператор в отдельную переменную для проверки и использования
//        String[] correctOperators = {"+", "-", "/", "*"}; // определяем корректные операторы
        String operandS1 = parameters[0]; // выводим операнд 1 в переменную для проверок
        operandS1 = operandS1.toUpperCase();
        String operandS2 = parameters[2]; // выводим операнд 2 в переменную для проверок
        operandS2 = operandS2.toUpperCase();
        String[] numRoman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"}; // Определяем корректные римские числа
        String[] numArabian = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}; // Определяем корректные арабские числа
//        String[] subset = {operandS1, operandS2};


        // Делаем проверки введенных значений на соответствие Римским (2) или Арабским (1) или (0) нито ни сё
        int operS1 = 0; // флаги для проверок
        int operS2 = 0;
        for(String s : numArabian) { // проверяем на соответствие арабским цифрам
            if (operandS1.equals(s)) {
                operS1 = 1;     // если соответствует - устанавливаем флаг 1
            }
            if (operandS2.equals(s)) {
                operS2 = 1;     // если соответствует - устанавливаем флаг 1
            }
        }
        for(String s : numRoman) { // проверяем на соответствие Римским цифрам
            if (operandS1.equals(s)) {
                operS1 = 2;     // если соответствует - устанавливаем флаг 2
            }
            if (operandS2.equals(s)) {
                operS2 = 2;     // если соответствует - устанавливаем флаг 2
            }
        }

        if (operS1 == 1 && operS2 == 1) { // Если оба операнда - арабские, выполняем код для арабских цифр
            System.out.println("Все арабские");
            int operand1 = Integer.parseInt(parameters[0]);
            int operand2 = Integer.parseInt(parameters[2]);
            int total = Calc.count(operator, operand1, operand2);
            System.out.println(total);
        } else if (operS1 == 2 && operS2 == 2) { // Если оба операнда - римские, выполняем код для римских цифр
            // System.out.println("Все Римские");
            Romans num1 = Romans.valueOf(operandS1); // Инициализируем num1 равную занчению римской цифры из enum Romans
            Romans num2 = Romans.valueOf(operandS2); // Инициализируем num2 равную занчению римской цифры из enum Romans
            // int totalRome = num1.getI() + num2.getI(); // проверяем как прошло
            int total = Calc.count(operator, num1.getI(), num2.getI()); // вызываем метод count со значениями соответствующих переменных
            if (total > 0) { // если получилось 1 и более - выводим римское число
                String totalRome = intToRoman(total);
                System.out.println(totalRome);
            } else { // если меньше 1 - выводим исключение
                System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
            }

        } else if (operS1 == 0 || operS2 == 0) { // Если введены посторонние символы
            System.out.println("throws Exception //Введите правильные цифры: римские или арабские от 1 до 10");
        } else { // если один операнд римский другой арабский
            System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
        }



    }
    static int count(String operator, int operand1, int operand2){ // Метод проведения операций
        int total = 0;
        switch(operator){
            case "+": total = operand1 + operand2;
                break;
            case "-": total = operand1 - operand2;
                break;
            case "/": total = operand1 / operand2;
                break;
            case "*": total = operand1 * operand2;
                break;
            default:
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("throws Exception //т.к. некорректная математическая операция, требуется +, -, /, *");
                }
        }
        return total;
    }

    static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int times = 0;
        String[] romans = new String[] { "I", "IV", "V", "IX", "X", "XL", "L",
                "XC", "C", "CD", "D", "CM", "M" };
        int[] ints = new int[] { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500,
                900, 1000 };
        for (int i = ints.length - 1; i >= 0; i--) {
            times = num / ints[i];
            num %= ints[i];
            while (times > 0) {
                sb.append(romans[i]);
                times--;
            }
        }
        return sb.toString();
    }

}

