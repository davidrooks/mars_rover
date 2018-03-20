package hsbc;

import java.util.*;
import java.lang.RuntimeException;

public class Instructions {
    static private List<String> outputs = new ArrayList<String>();

    public static void main(String[] args){
        Instructions result;
        if (args.length == 1) {
            System.out.println(args[0]);
            System.out.println(args[0].getClass().getSimpleName());
            result = new Instructions(args[0]);
        } else {
            result = new Instructions("5 3\n1 1 E\nRFRFRFRF\n3 2 N\nFRRFLLFFRRFLL\n0 3 W\nLLFFFLFLFL");
        }

        for (int i = 0; i < outputs.size(); i++) {
            System.out.println(outputs.get(i));
        }
    }

    public Instructions(String instructions){
        Planet planet = null;
        Robot robot = null;
        String[] lines = instructions.split("\n");
        System.out.println(lines.length);
        for (int i = 0; i < lines.length; i++){
            if (i == 0){
                //first line = size of world
                try {
                    System.out.println(lines[i]);
                    String[] bounds = lines[i].split(" ");
                    int x = Integer.parseInt(bounds[0]);
                    int y = Integer.parseInt(bounds[1]);
                    planet = new Planet(x,y);
                } catch (Exception e) {
                    throw new RuntimeException("invalid world co-ordinats");
                }
            } else if ((i % 2) == 1){
                //set position of robot within world
                try {
                    String[] position = lines[i].split(" ");
                    int x = Integer.parseInt(position[0]);
                    int y = Integer.parseInt(position[1]);
                    char d = position[2].charAt(0);
                    robot = new Robot(planet,x,y,d);
                } catch (Exception e) {
                    throw new RuntimeException("invalid robot start position");
                }
            } else {
                // move the robot
                try {
                    outputs.add(robot.move(lines[i]));
                } catch (Exception e) {
                    throw new RuntimeException("invalid robot movements");
                }
            }
        }

        if ((lines.length == 2) && (robot != null)) {
            // no move instructions provided
            outputs.add(robot.outputPostionandOrientation());
        }
    }

    public List<String> getResult(){
        return outputs;
    }
}
