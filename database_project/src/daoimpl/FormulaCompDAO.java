package daoimpl;

import java.util.List;

import daointerfaces.DALException;
import daointerfaces.IFormulaCompDAO;
import dto.FormulaCompDTO;

public class FormulaCompDAO implements IFormulaCompDAO {

	@Override
	public FormulaCompDTO getReceptKomp(int receptId, int raavareId)
			throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FormulaCompDTO> getReceptKompList(int receptId)
			throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FormulaCompDTO> getReceptKompList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createReceptKomp(FormulaCompDTO receptkomponent)
			throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateReceptKomp(FormulaCompDTO receptkomponent)
			throws DALException {
		// TODO Auto-generated method stub

	}

}
