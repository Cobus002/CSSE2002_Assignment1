import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class TileTest {
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
    public void getExits() {
        //By default there should be no exits
        assertEquals(0, testTile.getExits().size());
    }

    @Test
    public void getExitsWorking() throws NoExitException {
        //Create an exit tile
        Tile exitTile = new Tile();
        //Add an exit to get assume addExit() works
        testTile.addExit("North", exitTile);
        //Check if original exit tile is returned
        assertSame(exitTile, testTile.getExits().get("North"));
    }


    @Test
    public void getBlocks() throws TooHighException {
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
        Tile testTile2 = new Tile(testBlocks);
        testTile2.getTopBlock();
    }

    @Test
    public void removeTopBlock() throws TooLowException {
        //Initialise the default tile configuration
        testTile = new Tile();
        Block topBlock = testTile.getTopBlock();
        testTile.removeTopBlock();
        //The below code assumes that getTopBlock works
        assertNotEquals(topBlock, testTile.getTopBlock());
    }

    @Test
    public void removeTopBlockTooLowException() throws TooLowException {
        //testTile implemented with no tiles
        testTile.removeTopBlock();
    }

    @Test
    public void addExit() throws NoExitException {
        Tile exitTile = new Tile();
        testTile.addExit("North", exitTile);
        assertEquals(1, testTile.getExits().size());
        assertSame(exitTile, testTile.getExits().get("North"));
    }

    @Test(expected = NoExitException.class)
    public void addExitNoExitException() throws NoExitException {
        testTile.addExit(null, null);
    }

    @Test
    public void removeExit() throws NoExitException {
        //Assume that addExit() works
        try {
            testTile.addExit("North", new Tile());
        } catch (NoExitException e) {
            //Squash the exception as it shouldn't throw assuming addExit()
            // works
        }
        testTile.removeExit("North");
    }

    @Test(expected = NoExitException.class)
    public void removeExitNoExitException() throws NoExitException {
        //Assume that addExit() works
        try {
            testTile.addExit("North", new Tile());
        } catch (NoExitException e) {
            //Squash the exception as it shouldn't throw assuming addExit()
            // works
        }
        testTile.removeExit("South");
    }

    @Test
    public void dig() throws TooLowException,
            InvalidBlockException {
        int numBlocks = testTile.getBlocks().size();
        Block topBlock = testTile.getTopBlock();
        Block digBlock = testTile.dig();
        //Check the returned block is actually the topBlock
        //assume getTopBlock() works...
        assertSame(digBlock, topBlock);
        //Check that the block was actually removed from the tile
        assertEquals(numBlocks - 1, testTile.getBlocks().size());
    }

    @Test(expected = TooLowException.class)
    public void digTooLowException() throws TooLowException,
            InvalidBlockException, TooHighException {
        testTile = new Tile(testBlocks);
        testTile.dig();
    }

    @Test(expected = InvalidBlockException.class)
    public void digInvalidBlockException() throws TooLowException,
            InvalidBlockException, TooHighException {
        testTile = new Tile(testBlocks);
        testTile.placeBlock(new StoneBlock());
        testTile.dig();
    }

    @Test
    public void moveBlock() throws TooHighException, InvalidBlockException,
            NoExitException, TooLowException {
        //Create the tile to move to same height as testTile
        Tile moveToTile = new Tile();
        //Create block to add to testTile to make it 1 higher
        Block blockToMove = new WoodBlock();
        testTile.placeBlock(blockToMove);
        testTile.addExit("North", moveToTile);
        assertTrue(testTile.getExits().containsKey("North"));
        testTile.moveBlock("North");
        assertSame(blockToMove, moveToTile.getTopBlock());
    }

    @Test(expected = TooHighException.class)
    public void moveBlockTooHighException() throws TooHighException,
            InvalidBlockException, NoExitException {
        //Create the tile to move to same height as testTile
        Tile moveToTile = new Tile();
        //Add the moveToTile to the testTile exits map
        testTile.addExit("North", moveToTile);
        //Check that it has been added
        assertTrue(testTile.getExits().containsKey("North"));
        //Move the block
        testTile.moveBlock("North"); //Fail
    }

    @Test(expected = InvalidBlockException.class)
    public void moveBlockInvalidBlockException() throws TooHighException,
            InvalidBlockException, NoExitException {
        //Create the tile to move to same height as testTile
        Tile moveToTile = new Tile();
        Block immovableBlock = new StoneBlock();
        //Add imovable block to the tile
        testTile.placeBlock(immovableBlock);
        //Add the moveToTile to the testTile exits map
        testTile.addExit("North", moveToTile);
        //Check that it has been added
        assertTrue(testTile.getExits().containsKey("North"));
        //Move the block
        testTile.moveBlock("North"); //Fail
    }

    @Test(expected = NoExitException.class)
    public void moveBlockNoExitException() throws TooHighException,
            InvalidBlockException,
            NoExitException, TooLowException {
        //Create the tile to move to same height as testTile
        Tile moveToTile = new Tile();
        //Create block to add to testTile to make it 1 higher
        Block blockToMove = new WoodBlock();
        testTile.placeBlock(blockToMove);
        testTile.addExit("North", moveToTile);
        assertTrue(testTile.getExits().containsKey("North"));
        testTile.moveBlock("South"); //Fail, NoExitException
    }

    @Test
    public void placeBlock() throws TooHighException, InvalidBlockException {
        //Create a block to place
        Block blockToPlace = new WoodBlock();
        testTile.placeBlock(blockToPlace);//Succeed
    }

    @Test(expected = TooHighException.class)
    public void placeBlockTooHighException() throws InvalidBlockException,
            TooHighException {
        GrassBlock blockToPlace = new GrassBlock();
        testTile.placeBlock(blockToPlace);//Fail

    }

    @Test(expected = InvalidBlockException.class)
    public void placeBlockInvalidBlockException() throws TooHighException,
            InvalidBlockException {
        testTile.placeBlock(null);//Fail
    }
}