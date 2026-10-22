import java.util.Scanner;
public class DaySwitch {
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
    switch (dayNumber) {
        case 1:
            System.out.println("Monday");
            break;
        case 2:
            System.out.println("Tuesday");
            break;
        case 3:
            System.out.println("Wednesday");
            break;
        case 4:
            System.out.println("Thursday");
            break;
        case 5:
            System.out.println("Friday");
            break;
        case 6:
            System.out.println("Saturday");
            break;
        case 7:
            System.out.println("Sunday");
            break;
        default:
            System.out.println("Invalid day.");
            break;
    }
    input.close();
  }
}