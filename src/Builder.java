import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
        /*
        Check if the Builder can enter a tile from the current tile.
    Returns true if:
    the tiles are connected via an exit (i.e. there is an exit from the current tile to the new tile), and
    the height of the new tile (number of blocks) is the same or different by 1 from the current tile (i.e. abs(current tile height - new tile) <= 1)
    If newTile is null return false.
         */

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
            //The entry exists in the exits map so can enter
            return true;
        }else {
            return false;
        }
    }

    public void moveTo(Tile newTile) throws NoExitException {
        //TODO Implement Tile moveTo() function

    }


}
