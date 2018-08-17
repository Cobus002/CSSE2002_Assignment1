import java.util.List;
import java.util.TooManyListenersException;

public class Builder {
    private String name;
    private Tile currentTile;
    private List<Block> inventory;

    public Builder(String name, Tile startingTile) {
        this.name = new String(name);
        this.currentTile = startingTile;

    }

    public Builder(String name, Tile startingTile,
                   List<Block> startingInventory) throws InvalidBlockException {
        this.name = name;
        this.currentTile = startingTile;
        //Check that the blocks can be carried
        for (Block block : startingInventory) {
            if (!block.isCarryable()) {
                throw new InvalidBlockException();
            }
        }
        //Blocks are all good
        this.inventory = startingInventory;


    }

    public String getName() {
        return this.name;
    }

    public Tile getCurrentTile() {
        return this.currentTile;
    }

    public List<Block> getInventory() {
        return this.inventory;
    }

    public void dropFromInventory(int inventoryIndex)
            throws InvalidBlockException, TooHighException {

        Block dropBlock;
        int currentTileBlockCount = this.currentTile.getBlocks().size();

        try {
            dropBlock = this.inventory.get(inventoryIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidBlockException();
        }

        if (dropBlock instanceof GroundBlock && currentTileBlockCount >= 3) {
            throw new TooHighException();
        } else if (currentTileBlockCount >= 8) {
            throw new TooHighException();
        }

        this.currentTile.placeBlock(dropBlock);


    }

    public void digOnCurrentTile() throws TooLowException,
            InvalidBlockException {
        /**
         Attempt to dig in the current tile and add tile to the inventory
         If the top block (given by getCurrentTile().getTopBlock()) is diggable,
         remove the top block of the tile and destroy it, or add it to the end of the inventory (given by getInventory()).
         Handle the following cases:
         If there are no blocks on the current tile, throw a TooLowException
         If the top block is not diggable, throw a InvalidBlockException
         If the top block is not carryable, remove the block, but do not add it to the inventory.
         Hint: call Tile.dig()***/


    }

    public boolean canEnter(Tile newTile) {
        /****Still to implement***/
        return false;
    }

    public void moveTo(Tile newTile) throws NoExitException {
        /****Still to implement***/

    }


}
