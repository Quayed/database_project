package daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;
import daointerfaces.DALException;
import daointerfaces.IFormulaCompDAO;
import dto.FormulaCompDTO;

public class FormulaCompDAO implements IFormulaCompDAO {

	private ResultSet rs;
	private PreparedStatement ps;

	@Override
	public FormulaCompDTO getFormulaComp(int formulaID, int materialID) throws DALException {
		try {
			ps = Connector.prepare("SELECT formula_id, material_id, nom_netto, tolerance FROM formula_component "
					+ "	WHERE material_id = ? AND formula_id = ?");
			ps.setInt(1,  materialID);
			ps.setInt(2, formulaID);
			rs = ps.executeQuery();
		
			if (!rs.first()) {
				return null;
			} else {
				return new FormulaCompDTO(rs.getInt("formula_id"), rs.getInt("material_id"), rs.getDouble("nom_netto"),
						rs.getDouble("tolerance"));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<FormulaCompDTO> getFormulaCompList() throws DALException {
		rs = Connector.doQuery("SELECT formula_id, material_id, nom_netto, tolerance FROM formula_component");
		List<FormulaCompDTO> list = new ArrayList<FormulaCompDTO>();
		try {
			while (rs.next()) {
				list.add(new FormulaCompDTO(rs.getInt("formula_id"), rs.getInt("material_id"), rs.getDouble("nom_netto"), rs
						.getDouble("tolerance")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public List<FormulaCompDTO> getFormulaCompList(int formulaID) throws DALException {
		List<FormulaCompDTO> list = new ArrayList<FormulaCompDTO>();
		try {
			ps = Connector.prepare("SELECT formula_id, material_id, nom_netto, tolerance FROM formula_component WHERE formula_id =  ?");
			ps.setInt(1, formulaID);
			rs = ps.executeQuery();
			if (!rs.first()) {
				throw new DALException("No formula with ID " + formulaID + " could be found.");
			} else {
				while (rs.next()) {
					list.add(new FormulaCompDTO(rs.getInt("formula_id"), rs.getInt("material_id"), rs.getDouble("nom_netto"), rs
							.getDouble("tolerance")));
				}
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void createFormulaComp(FormulaCompDTO formulaComponent) throws DALException {
		try{
			ps = Connector.prepare("INSERT INTO formula_component(formula_id, material_id, nom_netto, tolerance VALUES(?,?,?,?)");
			ps.setInt(1,  formulaComponent.getFormulaID());
			ps.setInt(2, formulaComponent.getMaterialID());
			ps.setDouble(3, formulaComponent.getNomNetto());
			ps.setDouble(4, formulaComponent.getTolerance());
			ps.execute();
		} catch (SQLException e){
			throw new DALException(e);
		}
	}

	@Override
	public void updateFormulaComp(FormulaCompDTO formulaComponent) throws DALException {
		Connector.doUpdate("UPDATE formula_component SET formula_id = " + formulaComponent.getFormulaID() + ", material_id = "
				+ formulaComponent.getMaterialID() + ", nom_netto = " + formulaComponent.getNomNetto() + ", tolerance = "
				+ formulaComponent.getTolerance());
		try{
			ps = Connector.prepare("UPDATE formula_component SET formula_id = ?, material_id = ?, nom_netto = ?, tolerance = ?");
			ps.setInt(1,  formulaComponent.getFormulaID());
			ps.setInt(2, formulaComponent.getMaterialID());
			ps.setDouble(3, formulaComponent.getNomNetto());
			ps.setDouble(4, formulaComponent.getTolerance());
			ps.execute();
		} catch(SQLException e){
			throw new DALException(e);
		}
	}

}
