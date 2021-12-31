import java.util.ArrayList;
import java.util.HashMap;

// devices will be added one by one no set devices
public class Topology{
    private String id;
    private ArrayList<Device> devicesList;
    private HashMap<String, ArrayList<String>> netlist;

    public Topology(String ID){
        id = ID;
        devicesList = new ArrayList<Device>();
        netlist = new HashMap<String, ArrayList<String>>();
    }
    public String getId(){
        return id;
    }
    public void setId(String ID){
        id = ID;
    }
    public void addDevice(Device dev){
        devicesList.add(dev);
    }
    public void removeDevice(String id){
      for(int i=0; i<devicesList.size();i++){
        if(devicesList.get(i).getId().equals(id)){
            devicesList.remove(i);
        }
      }
    }
    public Device getDevice(String id){
        for(Device dev : devicesList){
            if(dev.getId().equals(id)){
                return dev;
            }
        }
        return null;
    }
    public ArrayList<Device> getDevices(){
        return devicesList;
    }
}