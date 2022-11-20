package football;

import org.junit.Assert;
import org.junit.Test;


public class FootballTeamTests {
    @Test(expected = NullPointerException.class)
    public void testInvalidNameNull(){
        FootballTeam footballTeam = new FootballTeam(null, 10);
    }
    @Test(expected = NullPointerException.class)
    public void testInvalidNameEmpty(){
        FootballTeam footballTeam = new FootballTeam("", 10);
    }

    @Test
    public void testSetValidName(){
        FootballTeam footballTeam = new FootballTeam("Ivan", 10);
        Assert.assertEquals("Ivan",footballTeam.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidVacantPosition(){
        FootballTeam footballTeam = new FootballTeam("Ivan", -4);
    }

    @Test
    public void testValidVacantPosition(){
        FootballTeam footballTeam = new FootballTeam("Ivan", 1);
        Assert.assertEquals(1,footballTeam.getVacantPositions());
    }

    @Test
    public void testGetCount(){
        FootballTeam footballTeam = new FootballTeam("Ivan", 10);
        Footballer footballer = new Footballer("Peter");
        footballTeam.addFootballer(footballer);
        Assert.assertEquals(1,footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectAdded(){
        FootballTeam footballTeam = new FootballTeam("Ivan", 1);
        Footballer footballer = new Footballer("Peter");
        footballTeam.addFootballer(footballer);
        Footballer footballer1 = new Footballer("Niki");
        footballTeam.addFootballer(footballer1);
    }

    @Test
    public void testCorrectAdded(){
        FootballTeam footballTeam = new FootballTeam("Ivan", 1);
        Footballer footballer = new Footballer("Peter");
        Assert.assertEquals(0,footballTeam.getCount());
        footballTeam.addFootballer(footballer);
        Assert.assertEquals(1,footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullFootballer(){
        FootballTeam footballTeam = new FootballTeam("Ivan", 1);
        Footballer footballer = new Footballer("Peter");
        footballTeam.removeFootballer("Peter");
    }

    @Test
    public void testCorrectRemoveFootballer(){
        FootballTeam footballTeam = new FootballTeam("Ivan", 1);
        Footballer footballer = new Footballer("Peter");
        footballTeam.addFootballer(footballer);
        Assert.assertEquals(1,footballTeam.getCount());
        footballTeam.removeFootballer("Peter");
        Assert.assertEquals(0,footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFootballerForSale(){
        FootballTeam footballTeam = new FootballTeam("Ivan", 1);
        Footballer footballer = new Footballer("Peter");
        footballTeam.footballerForSale("Peter");
    }

    @Test
    public void testValidFootballerForSale(){
        FootballTeam footballTeam = new FootballTeam("Ivan", 1);
        Footballer footballer = new Footballer("Peter");
        footballTeam.addFootballer(footballer);
        Assert.assertEquals(1,footballTeam.getCount());
        footballTeam.footballerForSale("Peter");
        Assert.assertFalse(footballer.isActive());
    }

    @Test
    public void testGetStatistic(){
        FootballTeam footballTeam = new FootballTeam("Ivan", 1);
        Footballer footballer = new Footballer("Peter");
        footballTeam.addFootballer(footballer);
        Assert.assertEquals("The footballer Peter is in the team Ivan.",footballTeam.getStatistics());
    }


}
