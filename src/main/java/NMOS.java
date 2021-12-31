public class NMOS extends Device{
    String drain; String gate; String source;

    public NMOS(String t, String ID, double defVal, double min,
                double max, String d, String g, String s){
        super(t, ID, defVal, min, max);
        drain = d; gate = g; source = s;
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
