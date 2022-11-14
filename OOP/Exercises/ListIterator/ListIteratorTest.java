package p03_IteratorTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ListIteratorTest {
    private ListIterator listIterator;
    private static final String[] DATA = {"X", "Y", "Z"};

    @Before
    public void setUp() throws OperationNotSupportedException {
        listIterator = new ListIterator(DATA);
    }
    //конструктор
    //1. не му подаваме елементи (null)
    @Test(expected = OperationNotSupportedException.class)
    public void testCreateListWithNullParam() throws OperationNotSupportedException {
        new ListIterator(null);
    }
    //2. да работи правилно
    //hasNext
    @Test
    public void testHasNextReturnCorrectBoolean () {
        //"X", "Y", "Z"
        Assert.assertTrue(listIterator.hasNext());
        listIterator.move();
        Assert.assertTrue(listIterator.hasNext());
        listIterator.move();
        Assert.assertFalse(listIterator.hasNext());
    }
    //move
    @Test
    public void testMove() {
        //"X", "Y", "Z"
        Assert.assertTrue(listIterator.move());
        Assert.assertTrue(listIterator.move());
        Assert.assertFalse(listIterator.move());
    }
    //print
    //1. ако няма елементи
    @Test(expected = IllegalStateException.class)
    public void testPrintEmptyList() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator();
        listIterator.print();
    }
    //2. ako има елементи
    @Test
    public void testPrintCorrectly() {
        int index = 0;
        while(listIterator.hasNext()){
            Assert.assertEquals(DATA[index], listIterator.print());
            index++;
            listIterator.move();
        }
    }
}