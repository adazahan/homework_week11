package service;

import domain.Athlete;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AthletesRankTest {
    private AthletesRank athletesrank;
    private Athlete athlete;
    private List<Athlete> athletes;

    @Before
    public void setUp() {
        System.out.println("in setup");
        AthletesRank athletesrank = new AthletesRank();
    }

    @Test
    public void testReadFromFileIsOk() throws NullPointerException {
        List<Athlete> athletes = athletesrank.readAthletesFromCSV("files\\in\\biathlonAthlete.csv");
        assertEquals(athletes.size(), 4);
    }

    @Test
    public void testCalculateFinalSkiTimeResult() {
        List<Athlete> athletes = new ArrayList<>();
        Athlete a1 = new Athlete(11, "Peter Bjorgson", "D", "30:15", "xxoxxx", "xxxxx", "xxxxx");
        athletes.add(a1);
        athletesrank.calculateFinalSkiTimeResult(athletes);
        assertThat(athletes.get(0).getSkiTimeResult(), is("30:25"));
    }

}