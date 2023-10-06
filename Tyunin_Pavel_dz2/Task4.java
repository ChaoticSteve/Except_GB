import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите текст: ");
        String userInput = scanner.nextLine();

        try {
            if (userInput.isEmpty()) {
                throw new Exception("Пустые строки вводить нельзя.");
            }

            // Далее можно выполнить какие-либо действия с введенным текстом, если он не пустой.
            System.out.println("Вы ввели: " + userInput);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
