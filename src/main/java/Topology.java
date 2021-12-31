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

// devices will be added one by one no set devices
public class Topology{
    private String id;
    private ArrayList<Device> devicesList;
    private HashMap<String, ArrayList<String>> netlist;
    private static FileWriter file;

    public Topology(String ID){
        id = ID;
        devicesList = new ArrayList<Device>();
        netlist = new HashMap<String, ArrayList<String>>();
    }
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