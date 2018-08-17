import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TooManyListenersException;

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
     * Get the name of the Builder
     * @return
     */
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
        //The dig function already throws the exceptions when too low
        //or if the block is non diggable.
        Block topBlock = currentTile.dig();
        //Check if the block is carryable and then if yes add to inventory
        if (topBlock.isCarryable()) {
            this.inventory.add(topBlock);
        } else{
            //don't add it to the inventory
        }
    }

    public boolean canEnter(Tile newTile) {
        //TODO Implement Tile canEnter() function

        if (newTile == null) {
            return false;
        }
        Map<String, Tile> exits = this.currentTile.getExits();
        Iterator iterator = exits.entrySet().iterator();
        boolean isEntryInExits = false;

        while(iterator.hasNext()){
            //TODO: Ask tutor about the use of the equals below
            Map.Entry entry = (Map.Entry)iterator.next();
            String name = (String) entry.getKey();
            Tile tile = (Tile) entry.getValue();
            if(newTile.equals(tile)){
                isEntryInExits = true;
                break;
            }
        }

        if(isEntryInExits){
            //The entry is in the exits map so can enter if height is good
            if(Math.abs(currentTile.getBlocks().size()-
                    newTile.getBlocks().size())<=1){
                //The tile is the correct height
                return true;

            }else{
                //Valid tile but height is no good
                return false;
            }

        }else {
            //Tile is not in exits map
            return false;
        }
    }

    public void moveTo(Tile newTile) throws NoExitException {
        //TODO Implement Tile moveTo() function
        /*
         move the builder to a new tile.
         If canEnter(newTile) == true then change the builders current tile
         to be newTile. (i.e. getCurrentTile() == newTile)
         If canEnter(newTile) == false then throw a NoExitException.
         */

        if(canEnter(newTile)) {


        }

    }


}
