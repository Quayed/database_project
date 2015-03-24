package daointerfaces;

import java.util.List;

import dto.FormulaDTO;

public interface FormulaDAO {
	FormulaDTO getRecept(int receptId) throws DALException;
	List<FormulaDTO> getReceptList() throws DALException;
	void createRecept(FormulaDTO recept) throws DALException;
	void updateRecept(FormulaDTO recept) throws DALException;
}
