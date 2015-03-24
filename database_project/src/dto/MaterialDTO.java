package dto;

/**
 * Raavare Data Objekt
 * 
 * @author mn/sh/tb
 * @version 1.2
 */

public class MaterialDTO {
	/** i omraadet 1-99999999 vaelges af brugerne */
	int materialID;
	/** min. 2 max. 20 karakterer */
	String materialName;
	/** min. 2 max. 20 karakterer */
	String provider;

	public MaterialDTO(int materialID, String materialName, String provider) {
		this.materialID = materialID;
		this.materialName = materialName;
		this.provider = provider;
	}

	public int getmaterialID() {
		return materialID;
	}

	public void setmaterialID(int materialID) {
		this.materialID = materialID;
	}

	public String getmaterialName() {
		return materialName;
	}

	public void setmaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getprovider() {
		return provider;
	}

	public void setprovider(String provider) {
		this.provider = provider;
	}

	public String toString() {
		return materialID + "\t" + materialName + "\t" + provider;
	}
}
