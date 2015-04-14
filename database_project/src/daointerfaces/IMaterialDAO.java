package daointerfaces;

import java.util.List;

import dto.MaterialDTO;

public interface IMaterialDAO {
	MaterialDTO getMaterial(int materialID) throws DALException;
	List<MaterialDTO> getMaterialList() throws DALException;
	void createMaterial(MaterialDTO material) throws DALException;
	void updateMaterial(MaterialDTO material) throws DALException;
}
