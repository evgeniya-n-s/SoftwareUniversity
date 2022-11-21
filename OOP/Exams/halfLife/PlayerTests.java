package halfLife;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Player

    @Test
    public void testConstructor(){
        Player player = new Player ( "Gosho", 100 );
        Assert.assertNotNull (player);
    }

    @Test
    public void testGetUserName(){
        Player player = new Player ( "Gosho", 100 );
        String actualName = player.getUsername ();
        String expectedName = "Gosho";
        Assert.assertEquals ( expectedName,actualName);
    }

    @Test(expected = NullPointerException.class)
    public void testSetUserNameNull(){
        Player player = new Player ( null, 100 );
    }

    @Test
    public void testGetHealth(){
        Player player = new Player ( "Gosho", 100 );
        int actualHealth = player.getHealth ();
        int expectedHealth = 100;
        Assert.assertEquals ( expectedHealth,actualHealth );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetHealthInvalid(){
        Player player = new Player ( "Gosho", -100);
    }

    @Test
    public void testGetGuns(){
        Player player = new Player ( "Gosho", 100 );
        int actualLength = player.getGuns ().size ();
        int expectedLength = 0;
        Assert.assertEquals ( expectedLength,actualLength );
    }


    @Test
    public void testTakeDamage(){
        Player player = new Player ( "Gosho", 100 );
        player.takeDamage ( 20 );
        int actualHealth = player.getHealth ();
        int expectedHealth = 80;
        Assert.assertEquals ( expectedHealth,actualHealth );
    }

    @Test(expected = NullPointerException.class)
    public void testAddGunInvalid(){
        Player player = new Player ( "Gosho", 100 );
        player.addGun ( null );
    }

    @Test
    public void testAddGunValid(){
        Player player = new Player ( "Gosho", 100 );
        Gun gun = new Gun ( "hdhdhd",20 );
        player.addGun ( gun );
        Gun expectedGun = gun;
        Gun actualGun = player.getGun ( gun.getName () );
        Assert.assertEquals ( expectedGun,actualGun );
    }

    @Test
    public void testRemoveGun(){
        Player player = new Player ( "Gosho", 100 );
        Gun gun = new Gun ( "hdhdhd",20 );
        player.addGun ( gun );
        boolean actualResult = player.removeGun ( gun );
        Assert.assertTrue ( actualResult );
    }

    @Test
    public void testGetGun(){
        Player player = new Player ( "Gosho", 100 );
        Gun gun = new Gun ( "hdhdhd",20 );
        player.addGun ( gun );
        Gun expectedGun = gun;
        Gun actualGun = player.getGun ( gun.getName () );
        Assert.assertEquals ( expectedGun,actualGun );
    }

    @Test
    public void testGetGunInvalid(){
        Player player = new Player ( "Gosho", 100 );
        Gun gun = new Gun ( "hdhdhd",20 );
        player.addGun ( gun );
        Gun expectedGun = null;
        Gun actualGun = player.getGun ( "mila" );
        Assert.assertEquals ( expectedGun,actualGun );
    }

}
