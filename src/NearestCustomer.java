
public class NearestCustomer implements Comparable<NearestCustomer> {
	private double distance;
	private String classType;
	
	public NearestCustomer(double distance, String classType) {
		this.distance = distance;
		this.classType = classType;
	}
	
	public double getDistance() {
		return distance;
	}
	public String getClassType() {
		return classType;
	}

	public int compareTo(NearestCustomer other) {
		if (this.getDistance() < other.getDistance()) return -1;
        if (this.getDistance() > other.getDistance()) return 1;
        if (this.getClassType().compareTo(other.getClassType()) != 0) return this.getClassType().compareTo(other.getClassType());
        return 0;
	}
}
