import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();       
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();        
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }

        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();      
        for (QuakeEntry qe : quakeData) {
            if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth) {
                answer.add(qe);
            }
        }
        
        return answer;
    }
        
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, 
    String where, String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            String title = qe.getInfo();
            if(where.equals("start") && title.startsWith(phrase)){
                answer.add(qe);
            }
            else if(where.equals("end") && title.endsWith(phrase)){
                answer.add(qe);
            }
            else if(where.equals("any") && title.contains(phrase)){
                answer.add(qe);
            }   
        }
        return answer;
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> magnitude = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : magnitude) {
            System.out.println(qe);
        }
        System.out.println("Found " + magnitude.size() + " quakes that match that criteria");

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        // Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> closeToCity = filterByDistanceFrom(list, 1000000, city);
        
        for (QuakeEntry qe : closeToCity) {
            System.out.println((qe.getLocation().distanceTo(city) / 1000) + " " + qe.getInfo());
        }
        
        System.out.println("Found " + closeToCity.size() + " quakes that match that criteria");
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        double minDepth = -10000.0;
        double maxDepth = -8000.0;
        ArrayList<QuakeEntry> depth = filterByDepth(list, minDepth, maxDepth);
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        for (QuakeEntry qe : depth) {
            System.out.println(qe);
        }
        
        System.out.println("Found " + depth.size() + " quakes that match that criteria");
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        String where = "any";
        String phrase = "Creek";
        // String where = "end";
        // String phrase = "California";
        ArrayList<QuakeEntry> answer = filterByPhrase(list, where, phrase);
        
        /* for (QuakeEntry qe : answer) {
            System.out.println(qe);
        } */
        
        System.out.println("Found " + answer.size() + " quakes that match " + phrase + " at " + where);
    }
    

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
