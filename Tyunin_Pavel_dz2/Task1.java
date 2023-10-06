import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        float userInput = getFloatInput();
        System.out.println("Вы ввели число: " + userInput);
    }

    public static float getFloatInput() {
        Scanner scanner = new Scanner(System.in);
        float result = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Пожалуйста, введите дробное число: ");

            if (scanner.hasNextFloat()) {
                result = scanner.nextFloat();
                validInput = true;
            } else {
                String invalidInput = scanner.next(); // Чтение недопустимого ввода (текста)
                System.out.println("Вы ввели недопустимое значение. Пожалуйста, введите дробное число.");
            }
        }

        return result;
    }
}
