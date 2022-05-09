public class MagnitudeFilter implements Filter
{
    // instance variables 
    private double magMin;
    private double magMax;
    private String filterName;
    
    public MagnitudeFilter(double min, double max, String filterName) {
        magMin = min;
        magMax = max;
        this.filterName = filterName;
    }

    public boolean satisfies(QuakeEntry qe) {   
        return qe.getMagnitude() >= magMin && qe.getMagnitude() <= magMax;
    } 
    
    public String getName() {
        return filterName;
    }
}
