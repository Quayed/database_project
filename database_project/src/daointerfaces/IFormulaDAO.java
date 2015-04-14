package daointerfaces;

import java.util.List;

import dto.FormulaDTO;

public interface IFormulaDAO {
	FormulaDTO getFormula(int receptId) throws DALException;
	List<FormulaDTO> getFormulaList() throws DALException;
	void createFormula(FormulaDTO recept) throws DALException;
	void updateFormula(FormulaDTO recept) throws DALException;
}
