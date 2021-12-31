import java.util.ArrayList;

public class API {
    ArrayList<Topology> topologies = new ArrayList<Topology>();

    public int readJSON(String fileName){
        Topology top = new Topology("");
        top.buildFromJson(fileName);
        topologies.add(top);
        top.getDevice("m1").printDevice();
        return 0;
    }

    ArrayList<Topology> queryTopologies(){
        return topologies;
    }

    public void deleteTopology(String id){
        for(int i=0; i<topologies.size();i++){
            if(topologies.get(i).getId().equals(id)){
                topologies.remove(i);
            }
        }
    }

    public ArrayList<Device> queryDevices(String id){
        for(int i=0; i<topologies.size();i++){
            if(topologies.get(i).getId().equals(id)){
                return topologies.get(i).getDevices();
            }
        }
        return null;
    }
}
