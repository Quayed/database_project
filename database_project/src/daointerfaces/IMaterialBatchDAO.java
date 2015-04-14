package daointerfaces;

import java.util.List;

import dto.MaterialbatchDTO;

public interface IMaterialBatchDAO {
	MaterialbatchDTO getMaterialBatch(int mbID) throws DALException;
	List<MaterialbatchDTO> getMaterialBatchList() throws DALException;
	List<MaterialbatchDTO> getMaterialBatchList(int materialID) throws DALException;
	void createMaterialBatch(MaterialbatchDTO materialbatch) throws DALException;
	void updateMaterialBatch(MaterialbatchDTO materialbatch) throws DALException;
}

