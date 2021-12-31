import org.json.simple.JSONObject;

public class NMOS extends Device{
    String drain; String gate; String source;

    public NMOS(){
        super("", "", 0.0, 0.0, 0.0);
        drain = ""; gate = ""; source="";
    }

    public NMOS(String t, String ID, double defVal, double min,
                double max, String d, String g, String s){
        super(t, ID, defVal, min, max);
        drain = d; gate = g; source = s;
    }

    public void buildFromJson(JSONObject jsonObject){
        String type = (String) jsonObject.get("type");
        String id = (String) jsonObject.get("id");

        JSONObject obj = (JSONObject) jsonObject.get("m(l)");
        double defVal = (double) obj.get("default");
        double min = (long) obj.get("min");
        double max = (long) obj.get("max");

        obj = (JSONObject) jsonObject.get("netlist");
        String d = (String) obj.get("drain");
        String g = (String) obj.get("gate");
        String src = (String) obj.get("source");

        Specs s = new Specs(defVal, min, max);
        setSpecifications(s);
        setType(type); setId(id); setDrain(d); setGate(g); setSource(src);

    }

    public JSONObject getJsonObject(){
        JSONObject mainObj = new JSONObject();
        mainObj.put("type", getType());
        mainObj.put("id", getId());

        JSONObject ml1 = new JSONObject();
        Specs s = getSpecifications();
        ml1.put("default", s.getDefVal());
        ml1.put("min", s.getMin());
        ml1.put("max", s.getMax());

        mainObj.put("m(l)", ml1);

        JSONObject netlist = new JSONObject();
        netlist.put("drain", getDrain());
        netlist.put("gate", getGate());
        netlist.put("source", getSource());

        mainObj.put("netlist", netlist);

        return mainObj;
    }

    public void printDevice(){
        System.out.println("type: "+getType());
        System.out.println("id: "+getId());

        Specs s = getSpecifications();
        System.out.println("default: "+s.getDefVal());
        System.out.println("min: "+s.getMin());
        System.out.println("max: "+s.getMax());

        System.out.println("drain: "+getDrain());
        System.out.println("gate: "+getGate());
        System.out.println("source: "+getSource());
    }

    public void setDrain(String drain) {
        this.drain = drain;
    }

    public String getDrain() {
        return drain;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getGate() {
        return gate;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}
