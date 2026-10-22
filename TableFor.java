import java.util.*;

public class TableFor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("How many rows? ");
        int intRow = scanner.nextInt();
        System.out.print("How many columns? ");
        int intColumn = scanner.nextInt();
        
        for (int i = 1; i <= intRow; i++) {
            for (int j = 1;  j <= intColumn; j++) {
                System.out.printf("%4d", i * j);
            }
            System.out.println();
        }

        scanner.close();
    }
}