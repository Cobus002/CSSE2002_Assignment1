import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class BuilderTest {
    //Set up some reusable elements
    private Tile testTile = new Tile();
    private static String BUILDER_NAME = new String("Cobus");
    private Builder testBuilder = new Builder(BUILDER_NAME, testTile);
    private List<Block> testInventory = new LinkedList<>();

    @Test
    public void nonDefaultConstructor() throws InvalidBlockException {
        testInventory.add(new WoodBlock());
        testBuilder = new Builder(BUILDER_NAME, testTile, testInventory); //Pass
    }

    @Test(expected = InvalidBlockException.class)
    public void nonDefaultConstructorInvalidBlockException() throws
            InvalidBlockException {
        testInventory.add(new StoneBlock());
        testBuilder = new Builder(BUILDER_NAME, testTile, testInventory); //Fail
    }

    //Test the get name function
    @Test
    public void getName() {

        assertEquals(BUILDER_NAME, testBuilder.getName());
    }

    @Test
    public void getCurrentTile() {
        assertSame(testTile, testBuilder.getCurrentTile());

    }

    @Test
    public void getInventory() throws InvalidBlockException{
        Block testBlock = new WoodBlock();
        testInventory.add(testBlock);
        testBuilder = new Builder(BUILDER_NAME, testTile, testInventory);
        assertSame(testInventory, testBuilder.getInventory()); //Pass
        assertEquals(1, testBuilder.getInventory().size());
        assertSame(testBlock, testBuilder.getInventory().get(0));
    }

    @Test
    public void dropFromInventory() {
    }

    @Test
    public void digOnCurrentTile() {
    }

    @Test
    public void canEnter() {
    }

    @Test
    public void moveTo() {
    }
}