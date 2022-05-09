public class DistanceFilter implements Filter
{
    private Location location;
    private double distance;
    private String filterName;
    
    public DistanceFilter(Location loc, double max, String filterName)
    {
        location = loc;
        distance = max;
        this.filterName = filterName;
    }

    public boolean satisfies(QuakeEntry qe)
    {
        return qe.getLocation().distanceTo(location) < distance;
    }
    
    public String getName() {
        return filterName;
    }
}
