package eu.learnpad.simulator.mon.coverage;

public class Path {

	private int id;
	private int idBpmn;
	private float absoluteSessionScore;
	private String pathRule;
	
	public Path(int id, int idBpmn) {
		this.id = id;
		this.idBpmn = idBpmn;
		this.absoluteSessionScore = 0;
		this.pathRule = "";
	}

	public Path(int id, int idBpmn, float absoluteSessionScore) {
		this.id = id;
		this.idBpmn = idBpmn;
		this.absoluteSessionScore = absoluteSessionScore;
		this.pathRule = "";
	}

	public Path(int id, int idBpmn, float absoluteSessionScore, String pathRule) {
		this.id = id;
		this.idBpmn = idBpmn;
		this.absoluteSessionScore = absoluteSessionScore;
		this.pathRule = pathRule;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdBpmn() {
		return idBpmn;
	}
	public void setIdBpmn(int idBpmn) {
		this.idBpmn = idBpmn;
	}
	public float getAbsoluteSessionScore() {
		return absoluteSessionScore;
	}
	public void setAbsoluteSessionScore(float absoluteSessionScore) {
		this.absoluteSessionScore = absoluteSessionScore;
	}
	public String getPathRule() {
		return pathRule;
	}
	public void setPathRule(String pathRule) {
		this.pathRule = pathRule;
	}
}
