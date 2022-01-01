import org.json.simple.JSONObject;

public abstract class Device{
    private String type;
    private String id;
    private Specs specifications;

    // constructor to be overridden by both resistor and NMOS subclasses
    public Device(String t, String ID, double defVal, double min, double max){
        type = t;
        id = ID;
        specifications = new Specs(defVal, min, max);
    }

    // to be overridden
    /*
        builds the device from a json object, used to make initialization of topology easier
        by converting from json file to json object and then to device object
        input: jsonObject (JSONObject), the base object.
        output: void.
     */
    public void buildFromJson(JSONObject jsonObject){

    }

    // to be overridden
    /*
        used to get a json object from the class so it can be written to a json file easily
        input: void.
        output: JSONObject representing the device.
     */
    public JSONObject getJsonObject(){
        return null;
    }

    // for debugging purposes by printing device attributes, to be overridden
    public void printDevice(){

    }

    // normal setters and getters

    public void setType(String t){
        type = t;
    }
    public String getType(){
        return type;
    }
    public void setId(String ID){
        id = ID;
    }
    public String getId(){
        return id;
    }
    public void setSpecifications(Specs specifications) {
        this.specifications = specifications;
    }

    public Specs getSpecifications() {
        return specifications;
    }
}