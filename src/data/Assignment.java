package data;

public class Assignment {
	private String name;
	private String className;
	private String type;
	private double pointsReceived;
	private double possiblePoints;
	private double score;
	private boolean included;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPointsReceived() {
		return pointsReceived;
	}
	public void setPointsReceived(double pointsReceived) {
		this.pointsReceived = pointsReceived;
	}
	public double getPossiblePoints() {
		return possiblePoints;
	}
	public void setPossiblePoints(double possiblePoints) {
		this.possiblePoints = possiblePoints;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public boolean isIncluded() {
		return included;
	}
	public void setIncluded(boolean included) {
		this.included = included;
	}
}
