import java.util.Scanner;

public class goofyGoober {
    public static void main(String[] args) {
        String budget = args[0];

        Scanner input = new Scanner(System.in);

        //choosing a category
        System.out.println("Video Games");

        //unending cycle of betting
        while (input.hasNextLine()) {
            String vidInfo = input.nextLine();

            if (vidInfo.startsWith("S ")) {
                continue; // dont need the summary
            }

            //bidding
            //testing purposes always bidding, start 1, max 10
            System.out.println("1 5");

            //processing result
            if (input.hasNextLine()) {
                String result = input.nextLine();
            }
        }
    }
}