package daointerfaces;

import java.util.List;

import dto.MaterialBatchDTO;

public interface MaterialBatchDAO {
	MaterialBatchDTO getRaavareBatch(int rbId) throws DALException;
	List<MaterialBatchDTO> getRaavareBatchList() throws DALException;
	List<MaterialBatchDTO> getRaavareBatchList(int raavareId) throws DALException;
	void createRaavareBatch(MaterialBatchDTO raavarebatch) throws DALException;
	void updateRaavareBatch(MaterialBatchDTO raavarebatch) throws DALException;
}

