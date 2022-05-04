package RobotWorld.world;

import RobotWorld.robot.Position;

public class SquareObstacle implements Obstacle{
    private int x;
    private int y;

    public SquareObstacle(int x, int y){
        this.x = x;
        this.y = y;

    }

    @Override
    public int getBottomLeftX() {
        return this.x;
    }

    @Override
    public int getBottomLeftY() {
        return this.y;
    }

    @Override
    public int getSize() {
        return 5;
    }

    @Override
    public boolean blocksPosition(Position position) {
        if ( this.x<= position.getX()&&position.getX() < this.x+5  &&
                this.y <= position.getY()&&position.getY() < this.y+5)
        {
            return true;
        }
        return  false;
    }

    @Override
    public boolean blocksPath(Position a, Position b) {
            if(a.getY()<= this.y&&this.y< b.getY() &&
                this.x <= a.getX()&&a.getX() < this.x +5){
                 return true;
            }
            else
                if(a.getX() <= this.x&& this.x< b.getX() &&
                    this.y <= a.getY()&&a.getY()< this.y +5 ){
                    return true;
        }

        return false;
    }
}
