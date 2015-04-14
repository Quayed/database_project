package dto;

public class ProductbatchCompDTO {
	int pbID; // produktbatchets id
	int mbID; // i omraadet 1-99999999
	double tara;
	double netto;
	int oprID; // operatoer-nummer

	public ProductbatchCompDTO(int pbID, int mbID, double tara, double netto, int oprID) {
		this.pbID = pbID;
		this.mbID = mbID;
		this.tara = tara;
		this.netto = netto;
		this.oprID = oprID;
	}

	public int getpbID() {
		return pbID;
	}

	public void setpbID(int pbID) {
		this.pbID = pbID;
	}

	public int getmbID() {
		return mbID;
	}

	public void setmbID(int mbID) {
		this.mbID = mbID;
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

	public int getoprID() {
		return oprID;
	}

	public void setoprID(int oprID) {
		this.oprID = oprID;
	}

	public String toString() {
		return pbID + "\t" + mbID + "\t" + tara + "\t" + netto + "\t" + oprID;
	}
}
