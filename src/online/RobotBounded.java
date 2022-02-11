package online;

public class RobotBounded {
    public boolean isRobotBounded(String instructions) {

        // we know that robot can move in 4 directions
        int[][] directions = {
                {1,0}, // UP (N)
                {-1,0}, // LEFT(W) -> index 1 in instruction parse
                {0,-1}, // DOWN(S)
                {0,1} // RIGHT(E) -> index 3 in instruction parse
        };

        int x  = 0, y = 0; // it's origin (Initial position is in the center)

        // we have to keep tracking those cases where it's not rotating.
        // if in the end of execution, we see it's not rotating we will return false;
        // facing north
        int i = 0;

        for (char instruction : instructions.toCharArray()) {
            if (instruction == 'L') i = ( i + 1) % 4;
            else if (instruction == 'R') i = (i + 3) % 4;
            else {
                x = x + directions[i][0];
                y = y + directions[i][1];
            }
        }
        return (x == 0 && y == 0) || (i != 0);
    }
}
