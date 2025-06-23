public class Main {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }
    public static void main(String[] args) {
        System.out.println("Are you trying to execute Java with Jenkins...???");
        Main calc = new Main();
        System.out.println("Add: " + calc.add(5, 3));        // Output: 8
        System.out.println("Subtract: " + calc.subtract(5, 3)); // Output: 2
    }
}