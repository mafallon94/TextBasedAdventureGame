import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapping {

    public static final int INITIAL_LOCATION = 95;

    /**
     * TODO
     * create a static LocationMap object
     */
    static LocationMap lm = new LocationMap();

    /**
     * TODO
     * create a vocabulary HashMap to store all directions a user can go
     */
    Map<String, String> vocabulary = new HashMap<>();

    /**
     * TODO
     * create a FileLogger object
     */
    FileLogger flyLo = new FileLogger();

    /**
     * TODO
     * create a ConsoleLogger object
     */
    ConsoleLogger conLo = new ConsoleLogger();


    public Mapping() {
        //vocabulary.put("QUIT", "Q"); //example
        /** TODO
         * complete the vocabulary HashMap <Key, Value> with all directions.
         * use the directions.txt file and crosscheck with the ExpectedInput and ExpectedOutput files to find the keys and the values
         */
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("NORTHWEST", "NW");
        vocabulary.put("WEST", "W");
        vocabulary.put("SOUTHWEST", "SW");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("SOUTHEAST", "SE");
        vocabulary.put("EAST", "E");
        vocabulary.put("NORTHEAST", "NE");
        vocabulary.put("UP", "U");
        vocabulary.put("DOWN", "D");
    }

    public void mapping() {

        /** TODO
         * create a Scanner object
         */
        Scanner sc = new Scanner(System.in);

        /**
         * initialise a location variable with the INITIAL_LOCATION
         */
        Integer currentLocation = INITIAL_LOCATION;


        while (true) {

            /** TODO
             * get the location and print its description to both console and file
             * use the FileLogger and ConsoleLogger objects
             */

            Location loc = lm.get(currentLocation);
            String description = loc.getDescription();
            conLo.log(description + "\n");
            flyLo.log(description + "\n");


            /** TODO
             * verify if the location is exit
             */
            if (loc.getLocationId() == 0) {
                break;
            }


            /** TODO
             * get a map of the exits for the location
             */

            Map<String, Integer> exits;
            exits = loc.getExits();


            /** TODO
             * print the available exits (to both console and file)
             * crosscheck with the ExpectedOutput files
             * Hint: you can use a StringBuilder to append the exits
             */

            conLo.log("Available exits are ");
            flyLo.log("Available exits are ");

            for (Map.Entry<String, Integer> entry : exits.entrySet()) {
                conLo.log(entry.getKey() + ", ");
                flyLo.log(entry.getKey() + ", ");
            }

            conLo.log("\n");
            flyLo.log("\n");


            /** TODO
             * input a direction
             * ensure that the input is converted to uppercase
             */
            String userInput = sc.nextLine().toUpperCase().trim();


            /** TODO
             * are we dealing with a letter / word for the direction to go to?
             * available inputs are: a letter(the HashMap value), a word (the HashMap key), a string of words that contains the key
             * crosscheck with the ExpectedInput and ExpectedOutput files for examples of inputs
             * if the input contains multiple words, extract each word
             * find the direction to go to using the vocabulary mapping
             * if multiple viable directions are specified in the input, choose the last one
             */


            String[] userInputArray = userInput.split(" ");
            String userDirection = "";

            if (userInputArray.length == 1) {
                for (String s : userInputArray) {
                    if (vocabulary.containsKey(s)) {
                        userDirection = s;
                    } else if (vocabulary.containsValue(s)) {
                        userDirection = s;
                    }
                }
            } else {
                for (String s : userInputArray) {
                    if (vocabulary.containsKey(s)) {
                        userDirection = s;
                    }
                }
            }


            /** TODO
             * if user can go in that direction, then set the location to that direction
             * otherwise print an error message (to both console and file)
             * check the ExpectedOutput files
             */

            String directionKVSwapper;

            if (vocabulary.containsKey(userDirection)) {

                directionKVSwapper = userDirection;
                userDirection = vocabulary.get(directionKVSwapper);
                if (exits.containsKey(userDirection)) {
                    currentLocation = exits.get(userDirection);
                } else {
                    conLo.log("You cannot go in that direction\n");
                    flyLo.log("You cannot go in that direction\n");
                }

            } else if (vocabulary.containsValue(userDirection)) {
                if (exits.containsKey(userDirection)) {
                    currentLocation = exits.get(userDirection);
                } else {
                    conLo.log("You cannot go in that direction\n");
                    flyLo.log("You cannot go in that direction\n");
                }
            } else {
                conLo.log("You cannot go in that direction\n");
                flyLo.log("You cannot go in that direction\n");
            }
        }
    }

    public static void main(String[] args) {
        /**TODO
         * run the program from here
         * create a Mapping object
         * start the game
         */
        Mapping run = new Mapping();

        run.mapping();
    }

}
