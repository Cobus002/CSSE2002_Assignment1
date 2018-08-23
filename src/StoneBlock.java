public class StoneBlock implements Block {
    private String type, colour;
    boolean carryable, diggable, moveable;

    public StoneBlock() {
        type = new String("stone");
        colour = new String("gray");
        carryable = false;
        diggable = false;
        moveable = false;

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
