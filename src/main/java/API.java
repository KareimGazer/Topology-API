import java.io.*;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.List;

public class API {
    ArrayList<Topology> topologies = new ArrayList<Topology>();

    public int readJSON(String fileName){
        Topology top = new Topology("");
        top.buildFromJson(fileName);
        topologies.add(top);
        // Device dev = top.getDevice("m1");
        // dev.printDevice();
        return 0;
    }

    int getTopIdx(String topologyId){
        for(int i=0; i<topologies.size();i++){
            if(topologies.get(i).getId().equals(topologyId)){
                return i;
            }
        }
        return -1;
    }

    public int writeJson(String topologyId){
        int idx = getTopIdx(topologyId);
        if(getTopIdx(topologyId) != -1){
            topologies.get(idx).writeJson();
            return 0;
        }
        else return -1;
    }

    ArrayList<Topology> queryTopologies(){
        return topologies;
    }

    public int deleteTopology(String topologyId){
        int idx = getTopIdx(topologyId);
        if(getTopIdx(topologyId) != -1){
            topologies.remove(idx);
            return 0;
        }
        else return -1;
    }

    public ArrayList<Device> queryDevices(String topologyId){
        int idx = getTopIdx(topologyId);
        if(getTopIdx(topologyId) != -1){
            return topologies.get(idx).getDevices();
        }
        else return null;
    }

    ArrayList<Device> queyDeviceWithNetlistNode(String topologyId, String netlistNodeId){
        Topology topology = null;
        int idx = getTopIdx(topologyId);
        if(getTopIdx(topologyId) != -1){
            topology =  topologies.get(idx);
            for(String s: topology.getNetlistDevices(netlistNodeId)){
                ArrayList<Device> devs = new ArrayList<Device>();
                devs.add(topology.getDevice(s));
                // System.out.println(s);
            }
            return null;
        }
        else return null;
    }
}
