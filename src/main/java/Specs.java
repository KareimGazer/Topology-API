/*
 class Specs: represents the specs and extreme values that the device can have
 */

public class Specs{
    private double defVal; // default value, in case no assigned
    private double min; // minimum value
    private double max; // maximum value

    // constructor
    public Specs(double dv, double low, double high){
        defVal = dv;
        min = low;
        max = high;
    }

    // normal setters and getters

    public void setDefVal(double defVal) {
        this.defVal = defVal;
    }

    public double getDefVal() {
        return defVal;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMax() {
        return max;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMin() {
        return min;
    }
}