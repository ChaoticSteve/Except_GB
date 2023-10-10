import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDataValidator {
    private String inputString;

    public UserDataValidator(String inputString) {
        this.inputString = inputString;
    }

    public int validateAndSaveToFile() {
        // Разбиваем строку на части, разделенные пробелами
        String[] parts = inputString.split(" ");

        // Проверяем, что введено не менее 6 элементов
        if (parts.length < 6) {
            return -1; // Код ошибки: Недостаточно данных
        }

        // Проверяем, что введено не более 6 элементов
        if (parts.length > 6) {
            return -2; // Код ошибки: Слишком много данных
        }

        String lastName = parts[0];
        String firstName = parts[1];
        String middleName = parts[2];
        String birthDateStr = parts[3];
        String phoneNumber = parts[4];
        String gender = parts[5];

        // Проверяем Фамилию, Имя и Отчество (должны состоять из букв)
        if (!validateName(lastName) || !validateName(firstName) || !validateName(middleName)) {
            return -3; // Код ошибки: Некорректное имя, фамилия или отчество
        }

        // Проверяем Дату Рождения (должна быть в формате "dd.MM.yyyy")
        if (!validateDate(birthDateStr)) {
            return -4; // Код ошибки: Некорректная дата рождения
        }

        // Проверяем Номер Телефона (должен быть целым числом)
        if (!validatePhoneNumber(phoneNumber)) {
            return -5; // Код ошибки: Некорректный номер телефона
        }

        // Проверяем Пол (должен быть "М" или "Ж")
        if (!validateGender(gender)) {
            return -6; // Код ошибки: Некорректный пол
        }

        try {
            // Если данные корректны, создаем файл и записываем данные
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(lastName + ".txt", true))) {
                writer.write(inputString);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -7; // Код ошибки: Проблемы с чтением/записью в файл
        }

        return 0; // Все данные корректны
    }

    private boolean validateName(String name) {
        return Pattern.matches("[а-яА-ЯёЁa-zA-Z]+", name);
    }

    private boolean validateDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false); // Строгая проверка даты
        try {
            Date date = dateFormat.parse(dateStr);
            // Проверка, что год рождения находится в разумных пределах (например, 1900-2023)
            int year = Integer.parseInt(dateStr.split("\\.")[2]);
            if (year < 1900 || year > 2023) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        try {
            Integer.parseInt(phoneNumber);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validateGender(String gender) {
        return gender.equals("f") || gender.equals("m");
    }

    public static void main(String[] args) {
        String inputString = "Иванов Иван Иванович 01.01.1990 1234567890 m";
        UserDataValidator validator = new UserDataValidator(inputString);
        int validationResult = validator.validateAndSaveToFile();

        switch (validationResult) {
            case 0:
                System.out.println("Данные корректны и записаны в файл.");
                break;
            case -1:
                System.out.println("Ошибка: Недостаточно данных.");
                break;
            case -2:
                System.out.println("Ошибка: Слишком много данных.");
                break;
            case -3:
                System.out.println("Ошибка: Некорректное имя, фамилия или отчество.");
                break;
            case -4:
                System.out.println("Ошибка: Некорректная дата рождения.");
                break;
            case -5:
                System.out.println("Ошибка: Некорректный номер телефона.");
                break;
            case -6:
                System.out.println("Ошибка: Некорректный пол.");
                break;
            case -7:
                System.out.println("Ошибка: Проблемы с чтением/записью в файл.");
                break;
            default:
                System.out.println("Неизвестная ошибка.");
        }
    }
}
