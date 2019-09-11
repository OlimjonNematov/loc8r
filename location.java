/**
 * This class sets up each location with its parameters
 * @author Olimjon Nematov
 *
 */
public class location implements Comparable<location>{
	private String name,address,type;
	private String longitude, latitude;
	private boolean flag, star;
	private double distance;
	/**
	 * flags the location
	 * @param name of locations
	 * @param address of location
	 * @param lonmgitude of location
	 * @param latitude of location
	 * @param flagged or not
	 * @param starred or not
	 */
	public location(String n, String a, String t, String longit,String latitud,boolean f,boolean s ) {
		name=n;
		address=a;
		type=t;
		longitude=longit;
		latitude=latitud;
		flag=f; 
		star=s; 

		//distance=Math.sqrt(Math.pow(Double.parseDouble(longitude),2)+Math.pow(Double.parseDouble(latitude),2));
	}
	/**
	 * gets name of location
	 */
	public String getName() {
		return name;
	}
	/**
	 * gets distance of location
	 */
	public double getDistance() {
		return distance;
	}
	/**
	 * gets address of location
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * gets catagory of location
	 */
	public String getType() {
		return type;
	}
	/**
	 * gets longitude of location
	 */
	public double getLongitude() {
		return Double.parseDouble(longitude);
	}
	/**
	 * gets latitude of location
	 */
	
	public double getLatitude() {
		return Double.parseDouble(latitude);
	}
	/**
	 * sets distance of location
	 * @param distance
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}
	/**
	 * gets flagged condition of location
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * gets starred condition of location
	 */
	public boolean isStar() {
		return star;
	}
	/**
	 * sets flag condition of location
	 */
	public void setFlag(boolean t) {
		flag=t;
	}
	/**
	 * sets star condition of location
	 */
	public void setStar(boolean t) {
		star=t;
	}
	/**
	 * sets the value that is being compared
	 */
	@Override
	public int compareTo(location that) {
		// TODO Auto-generated method stub
		double d=this.getDistance()-that.getDistance();
		if(d<0) {
			return -1;
		}
		else if(d>0) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
