package eu.learnpad.simulator.mon.coverage;

import java.util.Date;

public class Bpmn {

	private String id;
	private Date extractionDate;
	private int idCategory;
	private float absoluteBpScore;
	
	public Bpmn(String id, Date extractionDate) {
	
		this.id = id;
		this.extractionDate = extractionDate;
		this.idCategory = 0;
		this.absoluteBpScore = 0;
	}

	public Bpmn(String id, Date extractionDate, int idCategory) {
		this.id = id;
		this.extractionDate = extractionDate;
		this.idCategory = idCategory;
		this.absoluteBpScore = 0;
	}
	
	public Bpmn(String id, Date extractionDate, float absoluteBpScore) {
		this.id = id;
		this.extractionDate = extractionDate;
		this.absoluteBpScore = absoluteBpScore;
		this.absoluteBpScore = 0;
	}
	
	public Bpmn(String id, Date extractionDate, int idCategory, float absoluteBpScore) {
		this.id = id;
		this.extractionDate = extractionDate;
		this.idCategory = idCategory;
		this.absoluteBpScore = absoluteBpScore;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getExtractionDate() {
		return extractionDate;
	}
	
	public void setExtractionDate(Date extractionDate) {
		this.extractionDate = extractionDate;
	}
	
	public int getIdCategory() {
		return idCategory;
	}
	
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	
	public float getAbsoluteBpScore() {
		return absoluteBpScore;
	}
	
	public void setAbsoluteBpScore(float absoluteBpScore) {
		this.absoluteBpScore = absoluteBpScore;
	}
}
