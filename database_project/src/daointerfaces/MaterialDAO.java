package daointerfaces;

import java.util.List;

import dto.MaterialDTO;

public interface MaterialDAO {
	MaterialDTO getRaavare(int raavareId) throws DALException;
	List<MaterialDTO> getRaavareList() throws DALException;
	void createRaavare(MaterialDTO raavare) throws DALException;
	void updateRaavare(MaterialDTO raavare) throws DALException;
}
