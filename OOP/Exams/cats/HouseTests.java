package cats;

import org.junit.Assert;
import org.junit.Test;

public class HouseTests {
    @Test
    public void testCapacity(){
      //  Cat cat = new Cat("Tom");
        House cats = new House("House",10);
        Assert.assertEquals(10,cats.getCapacity());
       // Assert.assertEquals("House",cats.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCapacity(){
        House cats = new House("House",-4);
    }

    @Test(expected = NullPointerException.class)
    public void testGetIncorrectName(){
        House house = new House(null,5);
    }

    @Test(expected = NullPointerException.class)
    public void testGetIncorrectName2(){
        House house = new House("",5);
    }

    @Test
    public void testCorrectName(){
        House cats = new House("House",10);
       // Assert.assertEquals(10,cats.getCapacity());
        Assert.assertEquals("House",cats.getName());
    }

    @Test
    public void testGetCount(){
        House cats = new House("House",10);
        Cat cat = new Cat("Tom");
        Cat cat1 = new Cat("Pissy");
        cats.addCat(cat);
        Assert.assertEquals(1, cats.getCount());
        cats.addCat(cat1);
        Assert.assertEquals(2,cats.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCatAdd(){
        House cats = new House("House",1);
        Cat cat = new Cat("Tom");
        cats.addCat(cat);
        Cat cat1 = new Cat("Pissy");
        cats.addCat(cat1);
    }

    @Test
    public void testCatAdd(){
        House cats = new House("House",10);
        Cat cat = new Cat("Tom");
        Assert.assertEquals(0,cats.getCount());
        cats.addCat(cat);
        Assert.assertEquals(1,cats.getCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullCat(){
        House cats = new House("House",10);
        cats.removeCat("Tom");
    }

    @Test
    public void testRemoveCatSuccessfully(){
        House cats = new House("House",10);
        Cat cat = new Cat("Tom");
        cats.addCat(cat);
        Assert.assertEquals(1,cats.getCount());
        cats.removeCat("Tom");
        Assert.assertEquals(0,cats.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleNull(){
        House cats = new House("House",10);
        cats.catForSale("Tom");
    }

    @Test
    public void testCatForSale(){
        House cats = new House("House",10);
        Cat cat = new Cat("Tom");
        cats.addCat(cat);
        Cat cat1 = cats.catForSale("Tom");
        Assert.assertFalse(cat1.isHungry());
    }

    @Test
    public void testStatistics(){
        House cats = new House("House",10);
        Cat cat = new Cat("Tom");
        cats.addCat(cat);
        Assert.assertEquals("The cat Tom is in the house House!",cats.statistics());
    }

}
