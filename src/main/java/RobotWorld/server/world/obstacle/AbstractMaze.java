package RobotWorld.server.world.obstacle;


import RobotWorld.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMaze implements Maze{
    private int width;
    private int height;
    @Override
    public boolean blocksPath(Position a, Position b) {

        for (var obs :
                getObstacles()) {
            if (obs.blocksPath(a,b)){
                return true;
            }
        }
        return false;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
