package za.co.wethinkcode.robotworlds.jsonresponse.enums;

/**
 * Enum Direction.
 */
public enum Direction {
    /**
     * NORTH value
     */
    NORTH {
        @Override
        public int getDegrees() {
            return 0;
        }
    },
    /**
     * EAST value
     */
    EAST {
        @Override
        public int getDegrees() {
            return 270;
        }
    },
    /**
     * SOUTH value
     */
    SOUTH {
        @Override
        public int getDegrees() {
            return 180;
        }
    },
    /**
     * WEST value
     */
    WEST {
        @Override
        public int getDegrees() {
            return 90;
        }
    };

    /**
     * Gets the direction in degrees.
     *
     * @return the direction in degrees
     */
    public abstract int getDegrees();
}

