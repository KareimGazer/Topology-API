import org.json.simple.JSONObject;

public abstract class Device{
    private String type;
    private String id;
    private Specs specifications;

    public Device(String t, String ID, double defVal, double min, double max){
        type = t;
        id = ID;
        specifications = new Specs(defVal, min, max);
    }
    public void buildFromJson(JSONObject jsonObject){

    }
    public void printDevice(){

    }
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