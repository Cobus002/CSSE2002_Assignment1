import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tile implements Serializable {
    //All the variables that are to be used in the tile class
    List<Block> blocks; //Blocks on this tile
    Map<String, Tile> exits; //Exits from this tile


    public Tile(){
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

    public Tile(List<Block> startingBlocks) throws TooHighException{
        int blockCount = startingBlocks.size();
        //Check the starting blocks...
        if(blockCount>8){
            throw new TooHighException();
        }
        //So far so good, check for GroundBlock at indexes >= 3
        for(int i=blockCount-1; i>=3; i--){
            if(startingBlocks.get(i) instanceof GroundBlock){
                //Yes instance of GroundBlock
                throw new TooHighException();
            }
        }
        //All clear no errors occurred... set the blocks for the tile
        this.blocks=startingBlocks;

        //Initialise the exits with no entries
        exits = new HashMap<String, Tile>();

    }

    public Map<String, Tile> getExits(){
        //Possible wrong implementation
        return this.exits;
    }

    public List<Block> getBlocks(){
        //Return all the blocks on this tile
        return this.blocks;

    }

    public Block getTopBlock(){
        //return only the block on the top
        int blockCount = this.getBlocks().size();
        return this.getBlocks().get(blockCount-1);
    }

    public void removeTopBlock() throws TooLowException {
        //Remove the top block from the tile
        int blockCount = this.getBlocks().size();
        if (blockCount == 0){
            throw new TooLowException();
        }else{
            this.blocks.remove(blockCount-1);
        }
    }

    public void addExit(String name, Tile target) throws NoExitException{
        //Check the inputs
        if(target == null || name == null){
            //Oops not valid
            throw new NoExitException();
        }else{
            //Add to the exits map
            exits.put(name, target);
        }

    }

    public void removeExit(String name) throws NoExitException{
        if(!this.exits.containsKey(name) || name==null){
            //Not valid
            throw new NoExitException();
        }else{
            this.exits.remove(name);
        }
    }

    public Block dig() throws TooLowException, InvalidBlockException{
        Block topBlock;
        //Check if there are blocks
        if(this.blocks.isEmpty()){
            throw new TooLowException();
        }else{
            topBlock=getTopBlock();
        }
        //Continue and check if diggable
        if(topBlock.isDiggable()){
            return topBlock;
        }else{
            throw new InvalidBlockException();
        }
    }

    public void moveBlock(String exitName) throws TooHighException, InvalidBlockException, NoExitException{
        if(!(exitName == null || !this.getExits().containsKey(exitName))){
            //Name null or keyDoesn't exist
            throw new NoExitException();
        }else if(this.getBlocks().size()<=this.exits.get(exitName).getBlocks().size()){
            //Current tile has less tiles than the exit tile
            throw new TooHighException();
        }else if(!this.getTopBlock().isMoveable()){
            //The block is not movable
            throw new InvalidBlockException();
        }
        //All is good move the block
        Tile targetTile = this.getExits().get(exitName);
        targetTile.placeBlock(this.getTopBlock());
        try {
            //Try to remove the top block since we moved it
            this.removeTopBlock();
        }catch(TooLowException e){
            //Too low

        }
    }

    public void placeBlock(Block block) throws TooHighException, InvalidBlockException{
        int blockHeight = this.getBlocks().size();
        if (block == null){
            throw new InvalidBlockException();
        }else if(blockHeight>8){
            throw new TooHighException();
        }else if((blockHeight>3) && (block instanceof  GroundBlock)){
            throw new TooHighException();
        }else{
            this.blocks.add(block);
        }
    }


}
