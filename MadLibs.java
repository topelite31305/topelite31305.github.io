import java.util.Scanner;

/**
 * A class for this one assignment about madlibs
 * @link https://hsccsd.instructure.com/courses/1244642/assignments/14607931
 *
 */
public class MadLibs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // int wholeNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Integer: ");
        Integer wholeNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Adjective: ");
        String text1 = scanner.nextLine();
        System.out.print("Noun (ideally one with a normal plural form): ");
        String text2 = scanner.nextLine() + "s";
        System.out.print("Adjective: ");
        String text3 = scanner.nextLine();

        System.out.printf("Today, I had %d %s %s! They were so %s!\n", wholeNumber, text1, text2, text3);
        scanner.close();  
    }
}