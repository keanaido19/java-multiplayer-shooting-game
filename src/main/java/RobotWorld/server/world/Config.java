package RobotWorld.server.world;

import RobotWorld.Position;

public class Config {
    private final int width;
    private final int height;
    private final int maximumShield;

    public Config(int width, int height, int maximumShield){
        this.width = width;
        this.height = height;
        this.maximumShield = maximumShield;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMaximumShield() {
        return maximumShield;
    }

    public Position bottomRight(){
        return new Position(width/2,-height/2);
    }

    public Position topLeft(){
        return new Position(-width/2,height/2);
    }
}
