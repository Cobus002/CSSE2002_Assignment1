import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class TileTest {
    private static int DEFAULT_NUM_BLOCKS = 3;
    private Tile testTile;
    private List<Block> testBlocks;
    private static Block woodBlock = new WoodBlock();

    @Before
    public void before() {
        //Initialise some stuff to test
        testBlocks = new LinkedList<Block>();
        testTile = new Tile();
    }

    @Test
    public void getExitsDefault() {
        //By default there should be no exits
        assertEquals(0, testTile.getExits().size());
    }

    @Test
    public void getBlocks() throws TooHighException{
        testBlocks.add(new WoodBlock());
        testTile = new Tile(testBlocks);
        assertEquals(1, testTile.getBlocks().size()); //Check size equal
        assertSame(testBlocks, testTile.getBlocks()); //Check memory equal
    }

    @Test
    public void getTopBlock() throws TooLowException, TooHighException {
        testBlocks.add(woodBlock);
        testTile = new Tile(testBlocks);
        //Check if return the exact object
        assertSame(woodBlock, testTile.getTopBlock());
    }

    @Test(expected = TooLowException.class)
    public void getTopBlockTooLowException() throws TooLowException,
            TooHighException {

        List<Block> startBlocks = new LinkedList<Block>();
        //startBlocks.add(woodBlock);
        Tile testTile2 = new Tile(startBlocks);
        testTile2.getTopBlock();
    }

    @Test
    public void removeTopBlock() throws TooLowException {


    }

    @Test
    public void removeTopBlockTooLowException() throws TooLowException {

    }


    @Test
    public void addExit() {
    }

    @Test
    public void removeExit() {
    }

    @Test
    public void dig() {
    }

    @Test
    public void moveBlock() {
    }

    @Test
    public void placeBlock() {
    }
}