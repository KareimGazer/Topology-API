import static org.junit.jupiter.api.Assertions.*;

/*
    Main API tests
 */
class APITest {

    @org.junit.jupiter.api.Test
    void readJSON() {
        String fileName = "src/main/java/topology.json";
        API api = new API();
        assertEquals(0, api.readJSON(fileName));
    }

    @org.junit.jupiter.api.Test
    void writeJson() {
        String fileName = "src/main/java/topology.json";
        API api = new API();
        api.readJSON(fileName); // reading to get a topology
        //writing the topology with the id in the file
        assertEquals(0, api.writeJson("top1"));
    }

    @org.junit.jupiter.api.Test
    void queryTopologies() {
        String fileName = "src/main/java/topology.json";
        API api = new API();
        api.readJSON(fileName);
        // managed to read and return a topology
        assertEquals(1, api.queryTopologies().size());
    }

    @org.junit.jupiter.api.Test
    void deleteTopology() {
        String fileName = "src/main/java/topology.json";
        API api = new API();
        api.readJSON(fileName); // reading to get a topology
        api.deleteTopology("top1"); // getting one and deleting one
        assertEquals(0, api.queryTopologies().size());
    }

    @org.junit.jupiter.api.Test
    void queryDevices() {
        String fileName = "src/main/java/topology.json";
        API api = new API();
        api.readJSON(fileName);
        assertEquals(2, api.queryDevices("top1").size());
    }

    @org.junit.jupiter.api.Test
    void queyDeviceWithNetlistNode() {
        String fileName = "src/main/java/topology.json";
        API api = new API();
        api.readJSON(fileName);// reading to get a topology
        // we can see in the file that only one node is in vdd nodelist
        assertEquals(1, api.queyDeviceWithNetlistNode("top1", "vdd").size());
    }
}