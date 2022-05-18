package RobotWorld.server.world.obstacle;

import RobotWorld.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMaze extends AbstractMaze {
    @Override
    public List<Obstacle> getObstacles() {
        List<Obstacle> obstacleList = new ArrayList<>();
        Random rand = new Random();
        int numberOfObstances = rand.nextInt(11);

        do {
            int x= rand.nextInt(getWidth()-(-getWidth()))+(-getWidth());
            int y = rand.nextInt(getHeight()-(-(getHeight())))+(-getHeight());
            obstacleList.add(new SquareObstacle(x,y));
            numberOfObstances--;
        }while (numberOfObstances>0);

        return obstacleList;
    }

}
