import java.io.*;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;

public class API {
    ArrayList<Topology> topologies = new ArrayList<Topology>();

    public int readJSON(String fileName){
        Topology top = new Topology("");
        top.buildFromJson(fileName);
        topologies.add(top);
        Device dev = top.getDevice("m1");
        dev.printDevice();
        return 0;
    }

    public int writeJson(String id){
        for(int i=0; i<topologies.size();i++){
            if(topologies.get(i).getId().equals(id)){
                topologies.get(i).writeJson();
                return 0;
            }
        }
        return -1;
    }

    ArrayList<Topology> queryTopologies(){
        return topologies;
    }

    public int deleteTopology(String id){
        for(int i=0; i<topologies.size();i++){
            if(topologies.get(i).getId().equals(id)){
                topologies.remove(i);
                return 0;
            }
        }
        return -1;
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
