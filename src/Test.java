import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String args[]){

        List<Block> blocks = new ArrayList<Block>();
        //Create the default blocks
        Block soil1 = new SoilBlock();
        Block soil2 = new SoilBlock();
        Block grass1 = new GrassBlock();
        Block grass2 = new GrassBlock();
        //Add the default blocks to the list
        blocks.add(soil1);
        blocks.add(soil2);
        blocks.add(grass1);
        blocks.add(grass2);

        System.out.println(grass1.getBlockType());

        //Test the Tile
        Tile tile;
        try {
            tile = new Tile(blocks);
            System.out.println(tile.getBlocks());
            System.out.println(tile.getExits().size());
        }catch(TooHighException e){
            System.out.println("TooHighException caught");
        }
        Map<String, Block> map = new HashMap<String, Block>();
        System.out.println(map.containsKey("Hello"));
        map.put("grass", grass1);
        System.out.println(map.containsKey("grass"));

    }




}
