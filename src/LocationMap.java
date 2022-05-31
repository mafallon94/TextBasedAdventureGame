import java.io.*;
import java.util.*;

//class that behaves like a map
public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME = "locations.txt";
    private static final String DIRECTIONS_FILE_NAME = "directions.txt";

    /**
     * TODO
     * create a static locations HashMap
     */
    static HashMap<Integer, Location> locations = new HashMap<>();


    static {
        /** TODO
         * create a FileLogger object
         */
        FileLogger flyLo = new FileLogger();

        /** TODO
         * create a ConsoleLogger object
         */
        ConsoleLogger conLo = new ConsoleLogger();

        /** TODO
         * Read from LOCATIONS_FILE_NAME so that a user can navigate from one location to another
         * use try-with-resources/catch block for the FileReader
         * extract the location and the description on each line
         * print all locations and descriptions to both console and file
         * check the ExpectedOutput files
         * put the location and a new Location object in the locations HashMap, using temporary empty hashmaps for exits
         */

        Integer locationId;
        String description;
        String locLn;
        final int LOC_REGEX_LIMIT = 2;

        conLo.log("Available locations:\n");
        flyLo.log("Available locations:\n");

        try (BufferedReader locRead = new BufferedReader(new FileReader(LOCATIONS_FILE_NAME))) {
            while ((locLn = locRead.readLine()) != null) {
                String[] locData = locLn.split(",", LOC_REGEX_LIMIT);
                locationId = Integer.valueOf(locData[0]);
                description = locData[1];
                Location loc = new Location(locationId, description, new HashMap<>());
                locations.put(locationId, loc);
                conLo.log(locationId + ": " + description + "\n");
                flyLo.log(locationId + ": " + description + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        /**TODO
         * Read from DIRECTIONS_FILE_NAME so that a user can move from A to B, i.e. current location to next location
         * use try-with-resources/catch block for the FileReader
         * extract the 3 elements  on each line: location, direction, destination
         * print all locations, directions and destinations to both console and file
         * check the ExpectedOutput files
         * add the exits for each location
         */


        String dirLn;

        conLo.log("Available directions:\n");
        flyLo.log("Available directions:\n");

        try (BufferedReader dirRead = new BufferedReader(new FileReader(DIRECTIONS_FILE_NAME))) {
            while ((dirLn = dirRead.readLine()) != null) {
                String[] dirData = dirLn.split(",");
                locationId = Integer.valueOf(dirData[0]);
                String direction = dirData[1];
                Integer destination = Integer.valueOf(dirData[2]);
                locations.get(locationId).addExit(direction, destination);
                conLo.log(locationId + ": " + direction + ": " + destination + "\n");
                flyLo.log(locationId + ": " + direction + ": " + destination + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * TODO
     * implement all methods for Map
     *
     * @return
     */


    @Override
    public int size() {
        //TODO

        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        //TODO

        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        //TODO

        boolean containsKey;

        containsKey = locations.containsKey(key);

        return containsKey;
    }

    @Override
    public boolean containsValue(Object value) {
        //TODO

        boolean containsValue;
        containsValue = locations.containsValue(value);

        return containsValue;
    }

    @Override
    public Location get(Object key) {
        //TODO

        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        //TODO

        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        //TODO

        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        //TODO

        locations.putAll(m);
    }

    @Override
    public void clear() {
        //TODO
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        //TODO

        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        //TODO

        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        //TODO

        return locations.entrySet();
    }
}
