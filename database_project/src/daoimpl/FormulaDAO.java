package daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;
import daointerfaces.DALException;
import daointerfaces.IFormulaDAO;
import dto.FormulaDTO;

public class FormulaDAO implements IFormulaDAO {

	private ResultSet rs;
	private PreparedStatement ps;
	
	@Override
	public FormulaDTO getFormula(int formulaID) throws DALException {
		try{
			ps = Connector.prepare("SELECT formula_id, formula_name FROM formula WHERE formula_id = ?");
			ps.setInt(1, formulaID);
			rs = ps.executeQuery();
			if(!rs.first()){
				return null;
			} else {
				return new FormulaDTO(rs.getInt("formula_id"), rs.getString("formula_name"));
			}
		} catch (SQLException e){
			throw new DALException(e);
		}
	}

	@Override
	public List<FormulaDTO> getFormulaList() throws DALException {
		List<FormulaDTO> list = new ArrayList<FormulaDTO>();
		try {
			rs = Connector.doQuery("SELECT formula_id, formula_name FROM formula");
			while (rs.next()) {
				list.add(new FormulaDTO(rs.getInt("formula_id"), rs.getString("formula_name")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void createFormula(FormulaDTO formula) throws DALException {
		try{
			ps = Connector.prepare("INSERT INTO formula(formula_id, formula_name) VALUES (?, ?);");
			ps.setInt(1, formula.getFormulaID());
			ps.setString(2, formula.getFormulaName());
			ps.execute();
		} catch(SQLException e){
			throw new DALException(e);
		}
	}

	@Override
	public void updateFormula(FormulaDTO formula) throws DALException {
		try {
			ps = Connector.prepare("UPDATE formula SET formula_name = ? WHERE formula_id = ?");
			ps.setString(1, formula.getFormulaName());
			ps.setInt(2, formula.getFormulaID());
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

}
