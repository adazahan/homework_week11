import domain.Athlete;
import service.AthletesRank;

import java.util.List;


public class main {

    public static void main(String[] args) {
        AthletesRank athletesrank = new AthletesRank();
        List<Athlete> athletes = athletesrank.readAthletesFromCSV("files\\in\\biathlonAthlete.csv");

        System.out.println("Print Athletes' List before calculating final ski time result:");
        for (Athlete a : athletes) {
            System.out.println(a);

        }

        athletesrank.calculateFinalSkiTimeResult(athletes);

        System.out.println();
        System.out.println("Print Athletes' List after calculating final ski time result:");
        for (Athlete a : athletes) {
            System.out.println(a);
        }

        System.out.println();
        System.out.println("Print Athletes' Rank (sorted by FINAL ski time result):");
        athletesrank.sortAthletesByFinalSkiTimeResult(athletes);
        for (Athlete a : athletes) {
            System.out.println(a);
        }

        System.out.println();
        System.out.println("The first 3 places are:");
        System.out.println("Winner: " + athletes.get(0).getAthleteName() + " " + athletes.get(0).getSkiTimeResult());
        System.out.println("Runner-up: " + athletes.get(1).getAthleteName() + " " + athletes.get(1).getSkiTimeResult());
        System.out.println("Third Place: " + athletes.get(2).getAthleteName() + " " + athletes.get(2).getSkiTimeResult());


    }

}
