class Calculation {
    public static String calc(String input) throws IllegalArgumentException {

        String[] numbers = input.split(" ");

        if (numbers.length < 3) {
            throw new CalculationException(
                    "throws Exception //т.к. строка не является математической операцией"
            );
        } else if (numbers.length > 3) {
            throw new CalculationException(
                    "throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)"
            );
        }

        int a;
        int b;
        String sign = numbers[1];
        String result;

        if (isNumeric(numbers[0]) && isNumeric(numbers[2])) {
            a = Integer.parseInt(numbers[0]);
            b = Integer.parseInt(numbers[2]);

            if (isCorrect(a, b))
                throw new CalculationException("Введено число вне диапазона");

            result = String.valueOf(operation(a, b, sign));
        } else if (isRomanNumbers(numbers[0]) && isRomanNumbers(numbers[2])) {

            a = RomanNumbers.valueOf(numbers[0]).getArabicNumerals();
            b = RomanNumbers.valueOf(numbers[2]).getArabicNumerals();

            if (isCorrect(a, b))
                throw new CalculationException("Введено число вне диапазона");

            int number = operation(a, b, sign);

            if (number <= 0)
                throw new CalculationException("throws Exception //т.к. в римской системе только натуральные числа");

            result = ArabicToRoman(number);
        } else
            throw new CalculationException("throws Exception //т.к. используются одновременно разные системы счисления");
        return result;
    }

    static int operation(int a, int b, String sign) {

        int result;

        switch (sign) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sign);
        }

        return result;
    }

    static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static boolean isRomanNumbers(String str) {
        try {
            RomanNumbers.valueOf(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    static boolean isCorrect(int a, int b) {
        return a < 1 || a > 10 || b < 1 || b > 10;
    }

    static String ArabicToRoman(int number) {
        StringBuilder x = new StringBuilder();
        int y = number;

        while (y > 0) {
            if (y / 100 >= 1) {
                x.append(RomanNumbers.C);
                y -= 100;
            }

            if (y / 50 >= 1) {
                x.append(RomanNumbers.L);
                y -= 50;
            }

            if (y / 10 >= 1) {
                int count = y / 10;

                for (int i = 0; i < count; i++) {
                    x.append(RomanNumbers.X);
                    y -= 10;
                }
            }

            if (y >0) {
                x.append(RomanNumbers.values()[y - 1]);
                y -= y;
            }
        }
        return x.toString();
    }
}
