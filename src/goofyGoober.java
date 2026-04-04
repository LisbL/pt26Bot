import java.util.Scanner;

public class goofyGoober {
    public static void main(String[] args) {
        String budget = args[0];
        //choosing a category
        System.out.println("Video Games");
        System.out.flush();

        Scanner input = new Scanner(System.in);


        //unending cycle of betting
        while (true) {
            String vidInfo = input.nextLine();
            //System.err.println("Line: " + vidInfo);

            if (vidInfo.startsWith("video.")) {
                //checking if Video Games is present in line
                boolean isRelevant = vidInfo.contains("video.category=Video Games") ||
                        vidInfo.contains("viewer.interests=Video Games");
                //bidding
                if (isRelevant) {
                    System.out.println("400 8000");
                } else {
                    System.out.println("50 300");
                }
                System.out.flush();

            } else if (vidInfo.startsWith("W ") || vidInfo.equals("L")) {
                //result
                System.err.println("Result: " + vidInfo);
            } else if (vidInfo.startsWith("S ")) {
                //Summary -> moving on
            } else {

            }
        }
    }
}