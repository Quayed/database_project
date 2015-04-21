package dto;

public class ProductbatchCompDTO {
	private int pbID; // produktbatchets id
	private int mbID; // i omraadet 1-99999999
	private int oprID; // operatoer-nummer
	private double tara;
	private double netto;

	public ProductbatchCompDTO(int pbID, int mbID, int oprID, double tara, double netto) {
		this.pbID = pbID;
		this.mbID = mbID;
		this.oprID = oprID;
		this.tara = tara;
		this.netto = netto;
		
	}

	public int getPbID() {
		return pbID;
	}

	public void setPbID(int pbID) {
		this.pbID = pbID;
	}

	public int getMbID() {
		return mbID;
	}

	public void setMbID(int mbID) {
		this.mbID = mbID;
	}

	public int getOprID() {
		return oprID;
	}

	public void setOprID(int oprID) {
		this.oprID = oprID;
	}
	
	public double getTara() {
		return tara;
	}

	public void setTara(double tara) {
		this.tara = tara;
	}

	public double getNetto() {
		return netto;
	}

	public void setNetto(double netto) {
		this.netto = netto;
	}

	public String toString() {
		return pbID + "\t" + mbID + "\t" + "\t" + oprID + tara + "\t" + netto;
	}
}
