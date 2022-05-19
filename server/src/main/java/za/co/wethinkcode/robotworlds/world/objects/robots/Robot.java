package za.co.wethinkcode.robotworlds.world.objects.robots;

import za.co.wethinkcode.robotworlds.world.Position;
import za.co.wethinkcode.robotworlds.world.World;
import za.co.wethinkcode.robotworlds.world.data.RobotData;
import za.co.wethinkcode.robotworlds.world.enums.ObjectType;
import za.co.wethinkcode.robotworlds.world.enums.Status;
import za.co.wethinkcode.robotworlds.world.objects.WorldObject;

public class Robot extends WorldObject {

    private final String name;
    private final int maximumShields;
    private final int maximumShots;
    private final int range;

    private World world;
    private Status currentStatus = Status.NORMAL;

    private int currentShields;
    private int currentShots;

    public Robot(
            String name,
            Position position,
            int maximumShields,
            int maximumShots,
            int range
    ) {
        super(position, ObjectType.ROBOT, 4);
        this.name = name;
        this.maximumShields = maximumShields;
        this.currentShields = maximumShields;
        this.maximumShots = maximumShots;
        this.currentShots = maximumShots;
        this.range = range;
    }

    public String getName() {
        return name;
    }

    public int getMaximumShields() {
        return maximumShields;
    }

    public int getMaximumShots() {
        return maximumShots;
    }

    public int getRange() {
        return range;
    }

    public int getShields() {
        return currentShields;
    }

    public int getShots() {
        return currentShots;
    }

    public Status getRobotStatus() {
        return currentStatus;
    }

    public RobotData getRobotData() {
        return new RobotData(
                getCenterPosition().getPositionAsList(),
                getDirection(),
                currentShields,
                currentShots,
                currentStatus
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Robot robot = (Robot) o;
        return name.equals(robot.getName());
    }

    public void setRobotStatus(Status status) {
        this.currentStatus = status;
    }

    public void joinWorld(World world) {
        this.world = world;
    }

    public void repair() {
        currentShields = maximumShields;
    }

    public void reload() {
        currentShots = maximumShots;
    }

    public void fire() {
        if (currentShots > 0) {
            currentShots --;
        }
    }

    public void damageRobot() {
        if (world != null) {
            if (currentShields <= 0) {
                currentStatus = Status.DEAD;
                world.removeObjectFromWorld(this);
                world = null;
            } else {
                currentShields --;
            }
        }
    }

    @Override
    public String toString() {
        return " * " + name + " At " + getCenterPosition() + ", Facing " +
                getDirection() + ", Current Status is " + currentStatus +
                ", (Shields=" + currentShields + ", Shots=" + currentShots +
                ") * ";
    }

    private static synchronized void statusTimer(
            Robot robot,
            Status status,
            int milliSeconds
    ) {
        Status dead = Status.DEAD;

        if (dead.equals(robot.getRobotStatus())) return;

        robot.setRobotStatus(status);

        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (dead.equals(robot.getRobotStatus())) return;
        if (status.equals(Status.RELOAD)) robot.reload();
        if (status.equals(Status.REPAIR)) robot.repair();
        if (status.equals(robot.getRobotStatus()))
            robot.setRobotStatus(Status.NORMAL);
    }

    private static class StatusTimer implements Runnable {
        private final Robot robot;
        private final Status status;
        private final int milliSeconds;

        public StatusTimer(Robot robot, Status status, int milliSeconds) {
            this.robot = robot;
            this.status = status;
            this.milliSeconds = milliSeconds;
        }

        @Override
        public void run() {
            Robot.statusTimer(robot, status, milliSeconds);
        }
    }

    public void timer(Status status, int milliSeconds) {
        new Thread(new StatusTimer(this, status, milliSeconds)).start();
    }
}
