
// driver code for debugging
public class Main {
    public static void main(String[] args) {
        String fileName = "src/main/java/topology.json";
        API api = new API();
        api.readJSON(fileName);
        api.queyDeviceWithNetlistNode("top1", "vss");
    }
}