import java.util.Scanner;

public class TheHuntingGames{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int daysOfAdv = Integer.parseInt(scanner.nextLine());
        int countOfPlayers = Integer.parseInt(scanner.nextLine());
        double energy =Double.parseDouble (scanner.nextLine());
        double waterPerDay = Double.parseDouble(scanner.nextLine());
        double totalWater = waterPerDay * daysOfAdv * countOfPlayers;
        double foodPerDay = Double.parseDouble(scanner.nextLine());
        double totalFood = foodPerDay * daysOfAdv * countOfPlayers;
        boolean isDone = true;
        for (int i = 1; i <= daysOfAdv; i++) {
           double energyLost = Double.parseDouble(scanner.nextLine());
           energy -= energyLost;
           if (energy <= 0){
               isDone = false;
               break;
           }
           if (i % 2 ==0){
               energy += energy * 0.05;
               totalWater -= totalWater * 0.3;
           }
           if (i % 3 == 0){
               energy  += energy * 0.1;
               totalFood -= totalFood / (double) countOfPlayers;
           }
        }

        if (isDone){
            System.out.println(String.format("You are ready for the quest. You will be left with - %.2f energy!",energy));
        }else {
            System.out.println(String.format("You will run out of energy. You will be left with %.2f food and %.2f water.",totalFood,totalWater));
        }

    }
}
