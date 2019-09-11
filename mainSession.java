import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This class is the back-end to the application
 * @author Olimjon Nematov
 *
 */
public class mainSession {
	public ArrayList<location>locations= new ArrayList<>();
	public ArrayList<location>flagged= new ArrayList<>();
	public ArrayList<location>starred= new ArrayList<>();
	public String myLongitude= "-97.797796";
	public String myLatitude ="30.455889";
	public location user= new location("User", null, null, myLongitude,myLatitude , false, false);
/**
 * constructor of the class
 */
	public mainSession() {
		
	

	//-----------------------------------------------------------------------------	1
		Scanner Scan;
			Scan = new Scanner(this.getClass().getResourceAsStream("locations.txt"));
			
			while(Scan.hasNextLine()) {
				String l=Scan.nextLine();
				String[] entryparts= l.split("\t");
				String names=entryparts[0];
				String address= entryparts[1];
				String type=entryparts[2];
				String longitude=(entryparts[3]);		
				String latitude= (entryparts[4]);
				
				locations.add(new location(names, address, type, longitude, latitude, false, false));
				
			}
			Scan.close();
	

	}	 
	/**
	 * flags the location
	 * @param location
	 */
	public void flag(location l) {
		l.setFlag(true);
		flagged.add(l);
	}
	/**
	 * stars the location
	 * @param location
	 */
	public void star(location l) {
		l.setStar(true);
		starred.add(l);
	}
	/**
	 * unflags the location
	 * @param location
	 */
	public void unFlag(location l) {
		l.setFlag(false);
		flagged.remove(l);
	}
	/**
	 * unstar the location
	 * @param location
	 */
	public void unStar(location l) {
		l.setStar(false);
		starred.remove(l);
	}
	/**
	 * searches the locations closest to the user that match the category asked for
	 * @param Category, list of locations in the database, user location
	 */
	public ArrayList<location> searchByCat(String c, ArrayList<location>ls,location user) {
		String foundLocations="";
		ArrayList<location> closestLocations= new ArrayList<>();
		
		double longUser=Math.abs(user.getLongitude());
		double latUser=Math.abs(user.getLatitude());
		
		for(int x=0; x<ls.size();x++) {
			if((ls.get(x).getType()).equalsIgnoreCase(c)) {
				closestLocations.add(ls.get(x));
			}				
		}
		
		for(location l:closestLocations) {
			double distance =Math.sqrt((Math.pow(Math.abs(longUser)-Math.abs(l.getLongitude()),2))+(Math.pow(Math.abs(latUser)-Math.abs(l.getLatitude()), 2)));
			l.setDistance(distance);
		}
		
		Collections.sort(closestLocations);
			
		while(closestLocations.size()>8) {
			closestLocations.remove(8);
		}

		for(location l:closestLocations) {
			foundLocations+=l.getName()+"--"+l.getAddress()+"--"+l.getDistance()+"\n";
		}
		
		return closestLocations;
	}
	/**
	 * searches the locations closest to the user that match the name of the company asked for
	 * @param company name, list of all the locations, user location
	 */
	public ArrayList<location> searchByTitle(String t,ArrayList<location>ls, location user) {
		String foundLocations="";
		ArrayList<location> closestLocations= new ArrayList<>();
		
		double longUser=Math.abs(user.getLongitude());
		double latUser=Math.abs(user.getLatitude());
		
		for(int x=0; x<ls.size();x++) {
			if(ls.get(x).getName().compareTo(t)==0) {
				closestLocations.add(ls.get(x));
			}				
		}
		
		for(location l:closestLocations) {
			double distance =Math.sqrt((Math.pow(longUser-Math.abs(l.getLongitude()),2))+(Math.pow(latUser-Math.abs(l.getLatitude()), 2)));
			l.setDistance(distance);
		}
		
		Collections.sort(closestLocations);
			
		while(closestLocations.size()>8) {

			closestLocations.remove(8);
		}

		for(location l:closestLocations) {
			foundLocations+=l.getName()+"--"+l.getAddress()+"--"+l.getDistance()+"\n";
			System.out.println(l.getAddress());
		}
		
		return closestLocations;
	}

	
	

}
