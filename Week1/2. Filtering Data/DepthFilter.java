public class DepthFilter implements Filter
{
    // instance variables - replace the example below with your own
    private double depMin;
    private double depMax;
    private String filterName;
    
    public DepthFilter(double min, double max, String filterName) {
        depMin = min;
        depMax = max;
        this.filterName = filterName;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth() >= depMin && qe.getDepth() <= depMax;
    }
    
    public String getName() {
        return filterName;
    }
}
