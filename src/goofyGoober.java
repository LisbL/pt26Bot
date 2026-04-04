import java.util.Scanner;

public class goofyGoober {
    public static void main(String[] args) {
        String budget = args[0];
        //choosing a category
        System.out.println("Video Games");
        System.out.flush();

        Scanner input = new Scanner(System.in);


        //unending cycle of betting
        while (input.hasNextLine()) {
            String vidInfo = input.nextLine();
            System.err.println("SAIN REA: " + vidInfo);

            if (vidInfo.startsWith("video.")) {
                //bidding
                //testing purposes always bidding, start 1, max 10
                System.out.println("1 5");
                System.out.flush();
                System.err.println("SAATSIN PAKKUMISE: 1 5");
            } else if (vidInfo.startsWith("W ") || vidInfo.equals("L")) {
                //result
                System.err.println("TULEMUS KÄES: " + vidInfo);
            } else if (vidInfo.startsWith("S ")) {
                //Summary -> moving on
            }
        }
    }
}