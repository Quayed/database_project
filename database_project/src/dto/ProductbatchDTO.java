package dto;

public class ProductbatchDTO {
	private int pbID; // i omraadet 1-99999999
	private int status; // 0: ikke paabegyndt, 1: under produktion, 2: afsluttet
	private int formulaID;

	public ProductbatchDTO(int pbID, int status, int receptID) {
		this.pbID = pbID;
		this.status = status;
		this.formulaID = receptID;
	}

	public int getPbID() {
		return pbID;
	}

	public void setPbId(int pbID) {
		this.pbID = pbID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getFormulatID() {
		return formulaID;
	}

	public void setFormulaID(int formulaID) {
		this.formulaID = formulaID;
	}

	public String toString() {
		return pbID + "\t" + status + "\t" + formulaID;
	}
}
