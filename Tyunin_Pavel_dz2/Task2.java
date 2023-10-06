public class Task2 {
    public static void main(String[] args) {
        try {
            int[] intArray = {1, 2, 3, 4, 5, 6, 7}; // Пример массива

            int d = 0;
            int index = 8;

            if (index >= 0 && index < intArray.length) {
                double catchedRes1 = intArray[index] / (double)d;
                System.out.println("catchedRes1 = " + catchedRes1);
            } else {
                System.out.println("Индекс находится вне допустимого диапазона.");
            }
        } catch (ArithmeticException e) {
            System.out.println("Catching exception: " + e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Catching exception: " + e);
        }


    }
}
