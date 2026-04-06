import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class goofyGoober {
    public static void main(String[] args) throws IOException {
        int budget = Integer.parseInt(args[0]);
        int money = budget;
        //choosing a category
        System.out.println("Video Games");
        System.out.flush();

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        double efficiency = 1.0; // for adjusting bidding intensity

        //unending cycle of betting
        while (true) {
            int adScore = 0; //Score to judge whether the bid should be higher or lower

            String vidInfo = input.readLine();
            if (vidInfo == null) break;
            vidInfo = vidInfo.trim();

            //System.err.println("Line: " + vidInfo);
            if (vidInfo.isEmpty()) continue; //skip trash
            if (vidInfo.contains("video.")) {
                //Category, interests and subscription are important
                //checking if Video Games is present in line
                if (vidInfo.contains("video.category=Video Games")) adScore +=8;
                if (vidInfo.contains("viewer.interests=Video Games")) adScore +=6;
                if (vidInfo.contains("viewer.subscribed=Y")) adScore += 3;

                //engagement

                int comments = extractNumber(vidInfo, "video.commentCount=");
                int views = extractNumber(vidInfo, "video.viewCount=");

                if (views > 0 && (comments * 100 / views) > 2) {
                    adScore += 4;//this means good engagement
                }

                //bidding
                int startBid;
                int endBid;
                int baseBid = (int) (adScore * 20 * efficiency);

                if (adScore >= 15) {//really good
                    startBid = baseBid / 2;
                    endBid = baseBid * 4;
                } else if (adScore >= 8) {//good
                    startBid = baseBid / 4;
                    endBid = baseBid * 2;
                } else if (adScore >= 4) {//so and so
                    startBid = 10;
                    endBid = baseBid;
                } else {
                    startBid = 1;
                    endBid = 5;
                }

                //adding aggression
                if (money < budget * 0.3) {
                    endBid *= 0.5; //saving money
                } else if (money > budget * 0.7) {
                    endBid *= 1.2; //be more aggressive
                }

                //efficiency
                if (efficiency < 3.0) {
                    endBid *= 0.7; //overpaying
                } else if (efficiency > 6.0) {
                    endBid *= 1.2; //stable
                }

                //lastly adding randomness to bidding
                endBid += (int) (Math.random() * 200);

                //don't want to bid more than what I have
                if (endBid > money) endBid = money;
                if (startBid > money) startBid = money;

                //additional safety check to prevent negatives / overflow bids
                if (endBid < startBid) endBid = startBid;
                if (endBid < 1) endBid = 1;
                if (startBid < 1) startBid = 1;

                System.out.println(startBid + " " + endBid);//outgoing bid
                System.out.flush();

            } else if (vidInfo.startsWith("W ")) {
                //Updating budget
                int space = vidInfo.indexOf(' ');
                int cost = Integer.parseInt(vidInfo.substring(space + 1));
                money -= cost;
            } else if (vidInfo.startsWith("S ")) {

                //Updating bidding tactic
                int first = vidInfo.indexOf(' ');
                int second = vidInfo.indexOf(' ', first + 1);

                int points = Integer.parseInt(vidInfo.substring(first + 1, second));
                int spent = Integer.parseInt(vidInfo.substring(second + 1));

                if (spent > 0) {
                    efficiency = (double) points / spent;
                }
            } else {
                continue;
            }
        }
    }
    //helper for getting numbers
    static int extractNumber(String line, String key) {
        int id = line.indexOf(key);
        if ( id == -1) return 0; //safe fallback

        int start = id + key.length();
        int end = line.indexOf(",", start);
        if (end == -1) end = line.length();

        try {
            return Integer.parseInt(line.substring(start, end));
        } catch (Exception e) {
            return 0; //fallback just in case
        }
    }
}