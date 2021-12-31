public class Resistor extends Device{
    String t1;
    String t2;

    public Resistor(String t, String ID, double defVal, double min,
                    double max, String T1, String T2){
        super(t, ID, defVal, min, max);
        t1 = T1; t2 = T2;
    }

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
}
