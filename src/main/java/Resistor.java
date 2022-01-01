import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Resistor extends Device{
    String t1;
    String t2;

    public Resistor(){
        super("", "", 0.0, 0.0, 0.0);
        t1 = ""; t2 = "";
    }
    public Resistor(String t, String ID, double defVal, double min,
                    double max, String T1, String T2){
        super(t, ID, defVal, min, max);
        t1 = T1; t2 = T2;
    }

    // description can be found in the parent class "Device"
    public void buildFromJson(JSONObject jsonObject){
        String type = (String) jsonObject.get("type");
        String id = (String) jsonObject.get("id");

        JSONObject obj = (JSONObject) jsonObject.get("resistance");
        double defVal = (long) obj.get("default");
        double min = (long) obj.get("min");
        double max = (long) obj.get("max");

        obj = (JSONObject) jsonObject.get("netlist");
        String T1 = (String) obj.get("t1");
        String T2 = (String) obj.get("t2");

        Specs s = new Specs(defVal, min, max);
        setSpecifications(s);
        setType(type); setId(id); setT1(T1); setT2(T2);

    }

    // description can be found in the parent class "Device"
    public JSONObject getJsonObject(){
        JSONObject mainObj = new JSONObject();
        mainObj.put("type", getType());
        mainObj.put("id", getId());

        JSONObject res = new JSONObject();
        Specs s = getSpecifications();
        res.put("default", s.getDefVal());
        res.put("min", s.getMin());
        res.put("max", s.getMax());

        mainObj.put("resistance", res);

        JSONObject netlist = new JSONObject();
        netlist.put("t1", getT1());
        netlist.put("t2", getT2());

        mainObj.put("netlist", netlist);

        return mainObj;
    }

    // setters and getters

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT1() {
        return t1;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getT2() {
        return t2;
    }

    // printing resistor info for debugging purposes
    // description can be found in the parent class "Device"
    public void printDevice(){
        System.out.println("type: "+getType());
        System.out.println("id: "+getId());

        Specs s = getSpecifications();
        System.out.println("default: "+s.getDefVal());
        System.out.println("min: "+s.getMin());
        System.out.println("max: "+s.getMax());

        System.out.println("t1: "+getT1());
        System.out.println("t2: "+getT2());
    }
}
