package daointerfaces;

import java.util.List;

import dto.FormulaCompDTO;

public interface IFormulaCompDAO {
	FormulaCompDTO getFormulaComp(int formulaID, int materialID) throws DALException;

	List<FormulaCompDTO> getFormulaCompList(int formulaID) throws DALException;

	List<FormulaCompDTO> getFormulaCompList() throws DALException;

	void createFormulaComp(FormulaCompDTO formulaComponent) throws DALException;

	void updateFormulaComp(FormulaCompDTO formulaComponent) throws DALException;
}
