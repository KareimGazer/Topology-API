
public class Main {
    public static void main(String[] args) {
        String fileName = "src/main/java/topology.json";
        API api = new API();
        api.readJSON(fileName);
    }
}