import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Builder {
    private String name;
    private Tile currentTile;
    private List<Block> inventory;

    /***
     * Initialise the Builder with name and starting tile
     * @param name
     * @param startingTile
     */
    public Builder(String name, Tile startingTile) {
        this.name = new String(name);
        this.currentTile = startingTile;

    }

    /***
     * Initialise the Builder with name, starting tile and inventory
     * @param name
     * @param startingTile
     * @param startingInventory
     * @throws InvalidBlockException
     */
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

    /***
     * Get the name of the current builder instance
     * @return
     */
    public String getName() {
        return this.name;
    }

    /***
     * Get the tile on which the builder is currently on.
     * @return
     */
    public Tile getCurrentTile() {
        return this.currentTile;
    }

    /***
     * Get a list of the blocks currently in the builder inventory
     * @return
     */
    public List<Block> getInventory() {
        return this.inventory;
    }

    /***
     * Drop the inventory item given at index on the current tile.
     * Only possible if some conditions are met.
     * @param inventoryIndex
     * @throws InvalidBlockException
     * @throws TooHighException
     */
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

    /**
     * Try to dig on the current tile. If tile is not diggable the function
     * will throw an exception. TooLowException if there are no blocks on the
     * current tile or InvalidBlockException if the block is not diggable.
     * @throws TooLowException
     * @throws InvalidBlockException
     */
    public void digOnCurrentTile() throws TooLowException,
            InvalidBlockException {
        //The dig function already throws the exceptions when too low
        //or if the block is non diggable.
        Block topBlock = currentTile.dig();
        //Check if the block is carryable and then if yes add to inventory
        if (topBlock.isCarryable()) {
            this.inventory.add(topBlock);
        } else {
            //don't add it to the inventory
        }
    }

    /***
     * Check if it is possible to enter the tile given by newTile
     * @param newTile
     * @return
     */
    public boolean canEnter(Tile newTile) {
        if (newTile == null) {
            return false;
        }
        Map<String, Tile> exits = this.currentTile.getExits();
        Iterator iterator = exits.entrySet().iterator();
        boolean isEntryInExits = false;
        //Check all the entries in the map
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String name = (String) entry.getKey(); //Get the name key
            Tile tile = (Tile) entry.getValue(); //Get the tile value
            if (newTile == tile) {
                isEntryInExits = true;
                break;
            }
        }
        //Check if the entry is in the current tile entries map
        if (isEntryInExits) {
            //The entry is in the exits map so can enter if height is good
            if (Math.abs(currentTile.getBlocks().size() -
                    newTile.getBlocks().size()) <= 1) {
                //The tile is the correct height
                return true;

            } else {
                //Valid tile but height is no good
                return false;
            }

        } else {
            //Tile is not in exits map
            return false;
        }
    }

    /***
     * Move the builder to the new tile if tile is valid
     * @param newTile
     * @throws NoExitException
     */
    public void moveTo(Tile newTile) throws NoExitException {
        if (!this.canEnter(newTile)) {
            //Can't eneter the new tile
            throw new NoExitException();
        } else {
            this.currentTile = newTile;
        }

    }


}
