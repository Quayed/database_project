package daointerfaces;

import java.util.List;

import dto.MaterialBatchDTO;

public interface IMaterialBatchDAO {
	MaterialBatchDTO getMaterialBatch(int mbID) throws DALException;
	List<MaterialBatchDTO> getMaterialBatchList() throws DALException;
	List<MaterialBatchDTO> getMaterialBatchList(int materialID) throws DALException;
	void createMaterialBatch(MaterialBatchDTO materialbatch) throws DALException;
	void updateMaterialBatch(MaterialBatchDTO materialbatch) throws DALException;
}

