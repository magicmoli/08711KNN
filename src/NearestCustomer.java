
public class NearestCustomer implements Comparable<NearestCustomer> {
	private double similarityScore;
	private String classType;
	
	public NearestCustomer(double distance, String classType) {
		this.similarityScore = distance;
		this.classType = classType;
	}
	
	public double getSimilarityScore() {
		return similarityScore;
	}
	public String getClassType() {
		return classType;
	}

	public int compareTo(NearestCustomer other) {
		if (this.getSimilarityScore() < other.getSimilarityScore()) return -1;
        if (this.getSimilarityScore() > other.getSimilarityScore()) return 1;
        if (this.getClassType().compareTo(other.getClassType()) != 0) return this.getClassType().compareTo(other.getClassType());
        return 0;
	}
}
