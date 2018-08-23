public class WoodBlock implements Block {
    private String type, colour;
    private boolean carryable, diggable, moveable;

    public WoodBlock() {
        this.type = new String("wood");
        this.colour = new String("brown");
        this.carryable = true;
        this.diggable = true;
        this.moveable = true;

    }


    @Override
    public String getBlockType() {
        return this.type;
    }

    @Override
    public String getColour() {
        return this.colour;
    }

    @Override
    public boolean isCarryable() {
        return this.carryable;
    }

    @Override
    public boolean isDiggable() {
        return this.diggable;
    }

    @Override
    public boolean isMoveable() {
        return this.moveable;
    }
}
