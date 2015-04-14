package daointerfaces;

import java.util.List;

import dto.FormulaCompDTO;

public interface IFormulaCompDAO {
	FormulaCompDTO getFormulaComp(int receptId, int raavareId) throws DALException;

	List<FormulaCompDTO> getFormulaCompList(int receptId) throws DALException;

	List<FormulaCompDTO> getFormulaCompList() throws DALException;

	void createFormulaComp(FormulaCompDTO receptkomponent) throws DALException;

	void updateFormulaComp(FormulaCompDTO receptkomponent) throws DALException;
}
