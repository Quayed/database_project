package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;
import daointerfaces.DALException;
import daointerfaces.IFormulaDAO;
import dto.FormulaDTO;
import dto.OperatorDTO;

public class FormulaDAO implements IFormulaDAO {

	@Override
	public FormulaDTO getRecept(int formulaID) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT formula_id, formula_name FROM formula WHERE formula_id  = " + formulaID);
		try{
			if(!rs.first()){
				throw new DALException("Formula " + formulaID + ", does not exist");
			} else {
				return new FormulaDTO(rs.getInt("formula_id"), rs.getString("formula_name"));
			}
		} catch (SQLException e){
			throw new DALException(e);
		}
	}

	@Override
	public List<FormulaDTO> getReceptList() throws DALException {
		ResultSet rs = Connector.doQuery("SELECT formula_id, formula_name FROM formula");
		List<FormulaDTO> list = new ArrayList<FormulaDTO>();
		try {
			while (rs.next()) {
				list.add(new FormulaDTO(rs.getInt("formula_id"), rs.getString("formula_name")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void createRecept(FormulaDTO formula) throws DALException {
		Connector.doUpdate("INSERT INTO formula(formula_id, formula_name) VALUES (" + formula.getFormulaID() + ", '" + formula.getFormulaName() + "')");
	}

	@Override
	public void updateRecept(FormulaDTO formula) throws DALException {
		Connector.doUpdate("UPDATE formula SET formula_id = " + formula.getFormulaID() + ", formula_name = '" + formula.getFormulaName() + 
				"') WHERE formula_id = " + formula.getFormulaID());
	}

}
