package data;

public class Assignment {
	private String name;
	private String className;
	private String type;
	private double pointsReceived;
	private double possiblePoints;
	private double score;
	private boolean included;
	
	/**
	 * getter for name
	 * @author titzman
	 * @return Assignment's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * setter for name
	 * @author titzman
	 * @param name The new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * getter for className
	 * @author titzman
	 * @return Assignment's className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * setter for className
	 * @author titzman
	 * @param className The new className
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * getter for type
	 * @author titzman
	 * @return Assignment's type
	 */
	public String getType() {
		return type;
	}
	/**
	 * setter for type
	 * @author titzman
	 * @param type The new type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * getter for pointsReceived
	 * @author titzman
	 * @return Assignment's pointsReceived
	 */
	public double getPointsReceived() {
		return pointsReceived;
	}
	/**
	 * setter for pointsReceived
	 * @author titzman
	 * @param pointsReceived The new pointsReceived
	 */
	public void setPointsReceived(double pointsReceived) {
		this.pointsReceived = pointsReceived;
	}
	/**
	 * getter for possiblePoints
	 * @author titzman
	 * @return Assignment's possiblePoints
	 */
	public double getPossiblePoints() {
		return possiblePoints;
	}
	/**
	 * setter for possiblePoints
	 * @author titzman
	 * @param possiblePoints The new possiblePoints
	 */
	public void setPossiblePoints(double possiblePoints) {
		this.possiblePoints = possiblePoints;
	}
	/**
	 * getter for score
	 * @author titzman
	 * @return Assignment's score
	 */
	public double getScore() {
		return score;
	}
	/**
	 * setter for score
	 * @author titzman
	 * @param score The new score
	 */
	public void setScore(double score) {
		this.score = score;
	}
	/**
	 * getter for included
	 * @author titzman
	 * @return Assignment's included
	 */
	public boolean isIncluded() {
		return included;
	}
	/**
	 * setter for included
	 * @author titzman
	 * @param included The new included
	 */
	public void setIncluded(boolean included) {
		this.included = included;
	}
}
