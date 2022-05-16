package RobotWorld.server.world.obstacle;

import RobotWorld.Position;

public class SquareObstacle implements Obstacle {
    private int x;
    private int y;
    public final int size;

    public SquareObstacle(int x, int y)
    {
        this.x = x;
        this.size = 5;
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
        return size;
    }

    @Override
    public boolean blocksPosition(Position position)
    {
        int xCor = position.getX(), yCor = position.getY();

        return this.x <= xCor&&xCor < this.x + size
                && this.y <= yCor&&yCor < this.y + size;
    }

    @Override
    public boolean blocksPath(Position a, Position b)
    {
        int maxX = Math.max(a.getX(),b.getX()), minX = Math.min(a.getX(),b.getX());
        int maxY = Math.max(a.getY(),b.getY()), minY = Math.min(a.getY(),b.getY());

        if (a.getX() == b.getX())
            return minY <= this.y&&this.y <= maxY && this.x <= a.getX()&&a.getX() < this.x + size;
        else
            return minX <= this.x&&this.x <= maxX && this.y <= a.getY()&&a.getY() < this.y + size;

    }
}
