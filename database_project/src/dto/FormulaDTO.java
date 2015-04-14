package dto;

/**
 * Recept Data Objekt
 * 
 * @author mn/tb
 * @version 1.2
 */

public class FormulaDTO {
	/** Recept nr i omraadet 1-99999999 */
	private int formulaID;
	/** formulaName min. 2 max. 20 karakterer */
	private String formulaName;

	/** liste af kompenenter i recepten */

	public FormulaDTO(int formulaID, String formulaName) {
		this.formulaID = formulaID;
		this.formulaName = formulaName;
	}

	public int getFormulaID() {
		return formulaID;
	}

	public void setFormulaID(int formulaID) {
		this.formulaID = formulaID;
	}

	public String getFormulaName() {
		return formulaName;
	}

	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}

	public String toString() {
		return formulaID + "\t" + formulaName;
	}
}
