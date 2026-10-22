import java.util.Scanner;

public class DayIfElse {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Day number (1-7): ");
    int dayNumber = 0;
    try {
        dayNumber = input.nextInt();
    } catch (java.util.InputMismatchException nonIntException) {
        System.out.println("Must be an integer!");
        System.exit(1);
    }
    String[] days = {
        "Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday", "Sunday"
    };
    if (dayNumber < 1 || dayNumber > 7) {
        System.out.println("Invalid day.");
    } else {
        System.out.println(days[dayNumber - 1]);
    }
    
    input.close();
  }
}