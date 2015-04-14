package daointerfaces;

import java.util.List;

import dto.FormulaDTO;

public interface IFormulaDAO {
	FormulaDTO getFormula(int receptId) throws DALException;
	List<FormulaDTO> getReceptList() throws DALException;
	void createRecept(FormulaDTO recept) throws DALException;
	void updateRecept(FormulaDTO recept) throws DALException;
}
