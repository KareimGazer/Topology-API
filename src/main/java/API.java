import java.util.ArrayList;

public class API {
    ArrayList<Topology> topologies = new ArrayList<Topology>();

    /*
    reads a topology from a json file into the memory by loading the topology and appending it
    to topologies arrayList
    input: fileName (String): the name of the json file
    output: integer, 0 for success and -1 for failure
     */
    public int readJSON(String fileName){
        Topology top = new Topology("");
        top.buildFromJson(fileName);
        topologies.add(top);
        return 0;
    }

    /*
    returns the index of the desired topology in the topologies list
    input: topologyId (String): the name of the desired topology
    output: index (integer) specifying the index or -1 if not found.
     */
    int getTopIdx(String topologyId){
        for(int i=0; i<topologies.size();i++){
            if(topologies.get(i).getId().equals(topologyId)){
                return i;
            }
        }
        return -1;
    }

    /*
    writes a json file of the specified topology with the same name
    input: topologyId (String): the name of the desired topology
    output: integer, 0 for success and -1 for failure
     */
    public int writeJson(String topologyId){
        int idx = getTopIdx(topologyId);
        if(getTopIdx(topologyId) != -1){
            topologies.get(idx).writeJson();
            return 0;
        }
        else return -1;
    }
    /*
    returns all the topologies in memory
    input: topologyId (String): the name of the desired topology
    output: integer, 0 for success and -1 for failure
     */
    ArrayList<Topology> queryTopologies(){
        return topologies;
    }

    /*
    deletes a topology with the name topologyId from the topologies array list
    input: topologyId (String): the name of the desired topology
    output: integer, 0 for success and -1 for failure
     */
    public int deleteTopology(String topologyId){
        int idx = getTopIdx(topologyId);
        if(getTopIdx(topologyId) != -1){
            topologies.remove(idx);
            return 0;
        }
        else return -1;
    }

    /*
    returns a list of the devices in the specified topology
    input: topologyId (String): the name of the desired topology
    output: list of devices
     */
    public ArrayList<Device> queryDevices(String topologyId){
        int idx = getTopIdx(topologyId);
        if(getTopIdx(topologyId) != -1){
            return topologies.get(idx).getDevices();
        }
        else return null;
    }

    /*
    returns a list of the devices that share the specified nodelist
    input: topologyId (String): the name of the desired topology
    output: list of devices
     */
    ArrayList<Device> queyDeviceWithNetlistNode(String topologyId, String netlistNodeId){
        Topology topology = null;
        int idx = getTopIdx(topologyId);
        if(getTopIdx(topologyId) != -1){
            topology =  topologies.get(idx);
            ArrayList<Device> devs = new ArrayList<Device>();
            for(String s: topology.getNetlistDevices(netlistNodeId)){
                devs.add(topology.getDevice(s));
                // System.out.println(s);
            }
            return devs;
        }
        else return null;
    }
}
