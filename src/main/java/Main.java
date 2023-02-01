import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите два операнда и один оператор из чисел от 1 до 10, либо от I до X и знака(+, -, *, /) : ");
        String input = scanner.nextLine();

        String result = Calculation.calc(input);

        System.out.println(result);
    }
}
