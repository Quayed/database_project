package daointerfaces;

import java.util.List;

import dto.FormulaCompDTO;

public interface IFormulaCompDAO {
	FormulaCompDTO getReceptKomp(int receptId, int raavareId) throws DALException;

	List<FormulaCompDTO> getReceptKompList(int receptId) throws DALException;

	List<FormulaCompDTO> getReceptKompList() throws DALException;

	void createReceptKomp(FormulaCompDTO receptkomponent) throws DALException;

	void updateReceptKomp(FormulaCompDTO receptkomponent) throws DALException;
}
