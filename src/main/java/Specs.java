public class Specs{
    private double defVal; // default value
    private double min; // minimum value
    private double max; // maximum value

    // constructor
    public Specs(double dv, double low, double high){
        defVal = dv;
        min = low;
        max = high;
    }

    // defVal setter
    public void setDefVal(double defVal) {
        this.defVal = defVal;
    }

    public double getDefVal() {
        return defVal;
    }

    // max setter
    public void setMax(double max) {
        this.max = max;
    }

    public double getMax() {
        return max;
    }

    // min setter
    public void setMin(double min) {
        this.min = min;
    }

    public double getMin() {
        return min;
    }
}