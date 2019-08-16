package service;

import domain.Athlete;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AthletesRank {
    List<Athlete> athletes;


    public void sortAthletesByFinalSkiTimeResult(List<Athlete> athletes) {

        Collections.sort(athletes);

    }


    public void calculateFinalSkiTimeResult(List<Athlete> athletes) {
        for (int i = 0; i < athletes.size(); i++) {
            String[] tokens = (athletes.get(i).getSkiTimeResult()).split(":");
            int minutes = Integer.parseInt(tokens[0]);
            int seconds = Integer.parseInt(tokens[1]);

            int numberOfMissedShootings = 0;

            for (int f = 0; f < (athletes.get(i).getFirstShootingRange()).length(); f++) {
                if (((athletes.get(i).getFirstShootingRange()).charAt(f)) == 'o') {
                    numberOfMissedShootings++;
                }

            }
            for (int s = 0; s < (athletes.get(i).getSecondShootingRange()).length(); s++) {
                if (((athletes.get(i).getSecondShootingRange()).charAt(s)) == 'o') {
                    numberOfMissedShootings++;
                }

            }
            for (int t = 0; t < (athletes.get(i).getSecondShootingRange()).length(); t++) {
                if (((athletes.get(i).getThirdShootingRange()).charAt(t)) == 'o') {
                    numberOfMissedShootings++;
                }

            }

            seconds = seconds + 10 * numberOfMissedShootings;

            if (seconds > 60) {
                minutes = minutes + seconds / 60;
                seconds = seconds % 60;
            }

            String newSkiTimeResult = String.valueOf(minutes) + ":" + String.valueOf(seconds);

            athletes.get(i).setSkiTimeResult(newSkiTimeResult);
        }
    }


    public List<Athlete> readAthletesFromCSV(String fileName) {
        List<Athlete> athletes = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");
                Athlete athlete = createAthlete(attributes);
                athletes.add(athlete);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


        return athletes;
    }

    public static Athlete createAthlete(String[] metadata) {
        int athleteNumber = Integer.parseInt(metadata[0]);
        String athleteName = metadata[1];
        String countryCode = metadata[2];
        String skiTimeResult = metadata[3];
        String firstShootingRange = metadata[4];
        String secondShootingRange = metadata[5];
        String thirdShootingRange = metadata[6];

        return new Athlete(athleteNumber, athleteName, countryCode, skiTimeResult, firstShootingRange, secondShootingRange, thirdShootingRange);
    }


}
