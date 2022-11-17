package archeologicalExcavations;

import org.junit.Assert;
import org.junit.Test;

import javax.imageio.event.IIOReadProgressListener;

public class ExcavationTests {
    @Test
    public void testGetCount(){
        Excavation excavation = new Excavation("N1",10);
        Archaeologist archaeologist = new Archaeologist("Ivan",50);
        excavation.addArchaeologist(archaeologist);
        Assert.assertEquals(1,excavation.getCount());
    }

    @Test
    public void testGetName(){
        Archaeologist archaeologist = new Archaeologist("Ivan",50);
        Assert.assertEquals("Ivan",archaeologist.getName());
        Excavation excavation = new Excavation("N1",10);
        Assert.assertEquals("N1",excavation.getName());
    }
    
    @Test
    public void testGetCapacity(){
        Excavation excavation = new Excavation("N1",10);
        Assert.assertEquals(10,excavation.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAdded(){
        Excavation excavation = new Excavation("N1",1);
        Archaeologist archaeologist = new Archaeologist("Ivan",50);
        excavation.addArchaeologist(archaeologist);
        Archaeologist archaeologist1 = new Archaeologist("Petio",40);
        excavation.addArchaeologist(archaeologist1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExistingAdded(){
        Excavation excavation = new Excavation("N1",10);
        Archaeologist archaeologist = new Archaeologist("Ivan",50);
        excavation.addArchaeologist(archaeologist);
        excavation.addArchaeologist(archaeologist);
    }

    @Test
    public void testValidAdded(){
        Excavation excavation = new Excavation("N1",10);
        Archaeologist archaeologist = new Archaeologist("Ivan",50);
        Assert.assertEquals(0,excavation.getCount());
        excavation.addArchaeologist(archaeologist);
        Assert.assertEquals(1,excavation.getCount());
    }

    @Test
    public void testRemove(){
        Excavation excavation = new Excavation("N1",10);
        Archaeologist archaeologist = new Archaeologist("Ivan",50);
        excavation.addArchaeologist(archaeologist);
        Assert.assertEquals(1,excavation.getCount());
        excavation.removeArchaeologist("Ivan");
        Assert.assertEquals(0,excavation.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroCapacity(){
        Excavation excavation = new Excavation("N1",-4);
    }

    @Test(expected = NullPointerException.class)
    public void testNullName(){
        Excavation excavation = new Excavation(null,10);
    }

    @Test(expected = NullPointerException.class)
    public void testEmptyName(){
        Excavation excavation = new Excavation("",10);
    }

}
