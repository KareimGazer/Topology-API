import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
    class Topology: represents the topology in memory
    contains: - it own id
              - list of connected devices
              - netList all devices contained
              - file for writing to json file
 */
public class Topology{
    private String id;
    private ArrayList<Device> devicesList;
    private HashMap<String, ArrayList<String>> netlist;
    private static FileWriter file;

    // constructor
    public Topology(String ID){
        id = ID;
        devicesList = new ArrayList<Device>();
        netlist = new HashMap<String, ArrayList<String>>();
    }

    /*
        reads the json file and transfer the info into topology class and hence to memory.
        input: filePath (String), the string specifying the file location.
        output: void.
     */
    public void buildFromJson(String filePath){
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject =  (JSONObject) obj;

            String name = (String) jsonObject.get("id");
            id = name;
            // loop array
            JSONArray components = (JSONArray) jsonObject.get("components");
            for (int i=0; i< components.size(); i++)
            {
                JSONObject jsonObject1 = (JSONObject) components.get(i);
                if(jsonObject1.get("type").equals("resistor")){
                    Resistor resistor = new Resistor();
                    resistor.buildFromJson(jsonObject1);
                    addDevice(resistor);
                }
                else if(jsonObject1.get("type").equals("nmos")){
                    NMOS nmos = new NMOS();
                    nmos.buildFromJson(jsonObject1);
                    addDevice(nmos);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /*
       writes the topology to a json file, the file name is the topology id
        input: void.
        output: void.
     */
    public void writeJson(){
        JSONObject topObj = new JSONObject();
        topObj.put("id", getId());

        JSONArray components = new JSONArray();
        for(Device dev: devicesList){
            components.add(dev.getJsonObject());
        }
        topObj.put("components", components);

        try {
            file = new FileWriter(getId());
            file.write(topObj.toJSONString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
            }
        }
    }

    public String getId(){
        return id;
    }

    public void setId(String ID){
        id = ID;
    }

    /*
       adds a device to the topology.
        input: dev (Device).
        output: void.
     */
    public void addDevice(Device dev){
        devicesList.add(dev);
        if(dev.getType().equals("resistor")){
            Resistor res = (Resistor) dev;
            addToNetList(res.getT1(), res.getId());
            addToNetList(res.getT2(), res.getId());
        }
        else if(dev.getType().equals("nmos")){
            NMOS res = (NMOS) dev;
            addToNetList(res.getDrain(), res.getId());
            addToNetList(res.getGate(), res.getId());
            addToNetList(res.getSource(), res.getId());
        }
        // System.out.println("+1");
    }

    /*
       helper function used to modify the netlist hashmap
        input: mapKey (String), newDev String.
        output: void.
     */
    public synchronized void addToNetList(String mapKey, String newDev) {
        ArrayList<String> devList = netlist.get(mapKey);

        // if list does not exist create it
        if(devList == null) {
            devList = new ArrayList<String>();
            devList.add(newDev);
            netlist.put(mapKey, devList);
        } else {
            // add if item is not already in list
            if(!devList.contains(newDev)) devList.add(newDev);
        }
    }

    /*
       removes the device with the specified id.
        input: id (String).
        output: void.
     */
    public void removeDevice(String id){
      for(int i=0; i<devicesList.size();i++){
        if(devicesList.get(i).getId().equals(id)){
            devicesList.remove(i);
        }
      }
    }

    /*
       returns the device with the specified id.
        input: id (String).
        output: Device.
     */
    public Device getDevice(String id){
        for(Device dev : devicesList){
            if(dev.getId().equals(id)){
                return dev;
            }
        }
        return null;
    }

    /*
       returns a list of all devices in the topology.
        input: void.
        output: list of devices.
     */
    public ArrayList<Device> getDevices(){
        return devicesList;
    }

    /*
       returns a list of all devices connected to a given netlist node
        input: netlistName (String), the id of the netlist.
        output: list of Strings of devices ids.
     */
    public ArrayList<String> getNetlistDevices(String netlistName){
        return netlist.get(netlistName);
    }
}