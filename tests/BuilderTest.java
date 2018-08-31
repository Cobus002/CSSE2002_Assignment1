/*
    This file tests the functionality of all Builder class with the use of
    JUnit4 tests.
 */

import org.junit.Test;
import org.omg.CORBA.DynAnyPackage.Invalid;

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
    public void getInventory() throws InvalidBlockException {
        Block testBlock = new WoodBlock();
        testInventory.add(testBlock);
        testBuilder = new Builder(BUILDER_NAME, testTile, testInventory);
        assertSame(testInventory, testBuilder.getInventory()); //Pass
        assertEquals(1, testBuilder.getInventory().size());
        assertSame(testBlock, testBuilder.getInventory().get(0));
    }

    @Test
    public void dropFromInventory() throws InvalidBlockException,
            TooHighException {
        Block testBlock = new WoodBlock();
        testInventory.add(testBlock);
        testBuilder = new Builder(BUILDER_NAME, testTile, testInventory);
        int inventorySize = testBuilder.getInventory().size();
        testBuilder.dropFromInventory(inventorySize - 1);
        assertEquals(inventorySize - 1,
                testBuilder.getInventory().size());
    }

    @Test(expected = InvalidBlockException.class)
    public void dropFromInventoryInvalidBlockExceptionNoBlocks() throws
            InvalidBlockException, TooHighException {
        //Initialise with empty inventory
        testBuilder = new Builder(BUILDER_NAME, testTile, testInventory);
        assertEquals(0, testBuilder.getInventory().size()); //Pass
        testBuilder.dropFromInventory(0); //Fail IndexOutOfBound
    }

    @Test(expected = InvalidBlockException.class)
    public void dropFromInventoryInvalidBlockExceptionOutOfRange() throws
            InvalidBlockException, TooHighException {
        testInventory.add(new WoodBlock());
        //Initialise with empty inventory
        testBuilder = new Builder(BUILDER_NAME, testTile, testInventory);
        assertEquals(1, testBuilder.getInventory().size()); //Pass
        testBuilder.dropFromInventory(1); //Fail IndexOutOfBound
    }


    @Test(expected = TooHighException.class)
    public void dropFromInventoryTooHighExceptionGroundBlock() throws
            InvalidBlockException, TooHighException {
        testInventory.add(new SoilBlock());
        //Initialise with empty inventory
        testBuilder = new Builder(BUILDER_NAME, testTile, testInventory);
        assertEquals(1, testBuilder.getInventory().size()); //Pass
        testBuilder.dropFromInventory(0); //Fail TooHighException
    }


    @Test(expected = TooHighException.class)
    public void dropFromInventoryTooHighExceptionEightBlocks() throws
            InvalidBlockException, TooHighException {

        for (int i = 0; i < 5; i++) {
            //Add 5 more blocks so there is 8 blocks on tile
            testTile.placeBlock(new WoodBlock());
        }
        //Check that there are 8 blocks on the tile
        assertEquals(8, testTile.getBlocks().size());
        //Add block to drop
        testInventory.add(new WoodBlock());
        //Initialise with empty inventory
        testBuilder = new Builder(BUILDER_NAME, testTile, testInventory);
        assertEquals(1, testBuilder.getInventory().size()); //Pass
        testBuilder.dropFromInventory(0); //Fail TooHighException
    }

    @Test
    public void digOnCurrentTile() throws
            InvalidBlockException, TooHighException, TooLowException {
        //Check dig on tile and add to inventory test
        Block topBlock = new WoodBlock();
        testTile.placeBlock(topBlock);
        testBuilder = new Builder(BUILDER_NAME, testTile); //No inventory
        int numBlockOnTile = testTile.getBlocks().size(); //should equal 4
        assertEquals(numBlockOnTile, testTile.getBlocks().size());
        testBuilder.digOnCurrentTile(); //Pass
        //Check if the topBlock has been removed
        assertEquals(numBlockOnTile - 1, testTile.getBlocks().size());
        //Check that the block has been added to inventory of builder
        assertSame(topBlock, testBuilder.getInventory().get(0));
    }

    @Test(expected = TooLowException.class)
    public void digOnCurrentTileTooLowException() throws
            TooLowException, InvalidBlockException {
        //There are only 3 blocks on a default tile dig through them
        for (int i = 0; i < 3; i++) {
            testBuilder.digOnCurrentTile();
        }
        //Check current blocks are zero
        assertEquals(0, testTile.getBlocks().size());
        testBuilder.digOnCurrentTile();//Fail
    }

    @Test(expected = InvalidBlockException.class)
    public void digOnCurrentTileInvalidBlockException() throws
            TooLowException, InvalidBlockException, TooHighException {
        //Check for not diggable block
        testTile.placeBlock(new StoneBlock());
        testBuilder.digOnCurrentTile();

    }

    @Test
    public void canEnter() throws TooHighException, InvalidBlockException,
            NoExitException {
        Tile tileToEnter = new Tile();//Default 3 block tile
        testTile.placeBlock(new WoodBlock());//Add a block to the tile
        testTile.addExit("North", tileToEnter);
        testBuilder = new Builder(BUILDER_NAME, testTile);
        assertTrue(testBuilder.canEnter(tileToEnter));//Can enter tile
        assertFalse(testBuilder.canEnter(new Tile()));//Can't enter random tile
        tileToEnter.placeBlock(new WoodBlock());//Make same height as current
        assertTrue(testBuilder.canEnter(tileToEnter));//Pass
        tileToEnter.placeBlock(new WoodBlock());//Make 1 heigher than current
        assertTrue(testBuilder.canEnter(tileToEnter));//Pass
        tileToEnter.placeBlock(new WoodBlock());//Make 2 heigher
        assertFalse(testBuilder.canEnter(tileToEnter));//Can't eneter 2 heigher
    }

    @Test
    public void moveTo() throws NoExitException, TooHighException,
            InvalidBlockException {
        Tile tileToMoveTo = new Tile();//Default 3 block tile
        testTile.placeBlock(new WoodBlock());//Add a block to the tile
        testTile.addExit("North", tileToMoveTo);
        testBuilder = new Builder(BUILDER_NAME, testTile);
        //Move to the tile
        testBuilder.moveTo(tileToMoveTo);
        assertSame(tileToMoveTo, testBuilder.getCurrentTile());//Pass
    }

    @Test (expected = NoExitException.class)
    public void moveToNoExitException() throws NoExitException,
            TooHighException,
            InvalidBlockException {
        Tile tileToMoveTo = new Tile();//Default 3 block tile
        testTile.placeBlock(new WoodBlock());//Add a block to the tile
        testBuilder = new Builder(BUILDER_NAME, testTile);
        //Move to the tile
        testBuilder.moveTo(tileToMoveTo);

    }
}