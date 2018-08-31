import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tile implements Serializable {
    //All the variables that are to be used in the tile class
    List<Block> blocks; //Blocks on this tile
    Map<String, Tile> exits; //Exits from this tile


    public Tile() {
        //Create the default block list
        blocks = new ArrayList<Block>();
        //Create the default blocks
        Block soil1 = new SoilBlock();
        Block soil2 = new SoilBlock();
        Block grass1 = new GrassBlock();
        //Add the default blocks to the list
        blocks.add(soil1);
        blocks.add(soil2);
        blocks.add(grass1);

        //Create the default exits map
        exits = new HashMap<String, Tile>();
    }

    public Tile(List<Block> startingBlocks) throws TooHighException {
        int blockCount = startingBlocks.size();
        //Check the starting blocks are not >8 and all are
        if (blockCount > 8) {
            throw new TooHighException();
        }
        //So far so good, check for GroundBlock at indexes >= 3
        for (int i = blockCount - 1; i >= 3; i--) {
            if (startingBlocks.get(i) instanceof GroundBlock) {
                //Yes instance of GroundBlock
                throw new TooHighException();
            }
        }
        //All clear no errors occurred... set the blocks for the tile
        this.blocks = startingBlocks;

        //Initialise the exits with no entries
        exits = new HashMap<String, Tile>();

    }

    /**
     * Get the exits available from this tile
     * @return
     */
    public Map<String, Tile> getExits() {
        //Possible wrong implementation
        return this.exits;
    }

    public List<Block> getBlocks() {
        //Return all the blocks on this tile
        return this.blocks;

    }

    /**
     * Get the top block on the tile. Throws a TooLowException if there are
     * no blocks on the tile.
     *
     * @return
     * @throws TooLowException
     */
    public Block getTopBlock() throws TooLowException {
        //return only the block on the top
        int blockCount = this.getBlocks().size();
        if (blockCount <= 0) {
            throw new TooLowException();
        } else {
            return this.getBlocks().get(blockCount - 1);
        }

    }

    /**
     * Remove the top most block on the tile. Throws a TooLowException if
     * there are no blocks on the tile.
     *
     * @throws TooLowException
     */
    public void removeTopBlock() throws TooLowException {
        //Remove the top block from the tile
        int blockCount = this.getBlocks().size();
        if (blockCount == 0) {
            throw new TooLowException();
        } else {
            this.blocks.remove(blockCount - 1);
        }
    }

    public void addExit(String name, Tile target) throws NoExitException {
        //Check the input is actually a
        if (target == null || name == null) {
            //Oops not valid
            throw new NoExitException();
        } else {
            //Add to the exits map
            exits.put(name, target);
        }
    }

    public void removeExit(String name) throws NoExitException {
        if (!this.exits.containsKey(name) || name == null) {
            //Not valid
            throw new NoExitException();
        } else {
            this.exits.remove(name);
        }
    }

    /**
     * Attempt to dig in the current tile.
     * If the top block (given by getTopBlock()) is diggable
     * (block.isDiggable()), remove the top block of the tile and return it.
     *
     * @return
     * @throws TooLowException
     * @throws InvalidBlockException
     */
    public Block dig() throws TooLowException, InvalidBlockException {
        Block topBlock;
        int blockCount=0;
        //Check if there are blocks
        if (this.blocks.isEmpty()) {
            throw new TooLowException();
        } else {
            topBlock = getTopBlock();
            blockCount = this.blocks.size();
        }
        //Continue and check if diggable
        if (topBlock.isDiggable()) {
            this.blocks.remove(blockCount-1);
            return topBlock;
        } else {
            throw new InvalidBlockException();
        }
    }

    /**
     * Attempt to move the current top block to another tile. Remove the top
     * block (given by getTopBlock()) from this tile and add it to the tile
     * at the named exit (exitName in getExits()), if the block is moveable
     * (block.isMoveable()) and the height of that tile (the number of blocks
     * given by getBlocks().size()) is less than the current tile *before*
     * the  move.
     *
     * @param exitName
     * @throws TooHighException
     * @throws InvalidBlockException
     * @throws NoExitException
     */
    public void moveBlock(String exitName) throws TooHighException,
            InvalidBlockException, NoExitException {

        Block topBlock = blocks.get(blocks.size() - 1); //May be null check for
        //  this case

        if (!(exitName == null || this.getExits().containsKey(exitName))) {
            //Name null or keyDoesn't exist
            throw new NoExitException();
        } else if (this.getBlocks().size()
                <= this.exits.get(exitName).getBlocks().size()) {
            //Current tile has less tiles than the exit tile
            throw new TooHighException();
        } else if (!topBlock.isMoveable()) {
            //The block is not movable
            throw new InvalidBlockException();
        }
        //All is good move the block
        Tile targetTile = this.getExits().get(exitName);
        targetTile.placeBlock(topBlock);
        try {
            //Try to remove the top block since we moved it
            this.removeTopBlock();
        } catch (TooLowException e) {
            //Too low but shouldn't reach this since above if elses would catch
        }
    }

    /**
     * Place a block on a tile. Add the block to the top of the blocks on
     * this tile.
     *
     * @param block
     * @throws TooHighException
     * @throws InvalidBlockException
     */
    public void placeBlock(Block block) throws TooHighException,
            InvalidBlockException {
        //Get the GroundBlock class to compare to the block's class later
        GroundBlock gBlock = new GrassBlock();
        Class gBlockClass = gBlock.getClass();

        int blockHeight = this.getBlocks().size();
        if (block == null) {
            throw new InvalidBlockException();
        } else if (blockHeight > 8) {
            throw new TooHighException();
        } else if ((blockHeight >= 3) && (gBlockClass.isAssignableFrom(block.getClass()))) {
            throw new TooHighException();
        } else {
            this.blocks.add(block);
        }



    }


}
