package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;
import daointerfaces.DALException;
import daointerfaces.IFormulaCompDAO;
import dto.FormulaCompDTO;

public class FormulaCompDAO implements IFormulaCompDAO {

	@Override
	public FormulaCompDTO getFormulaComp(int formulaID, int materialID) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT formula_id, material_id, nom_netto, tolerance FROM formula_component WHERE material_id = "
				+ formulaID + " AND formula_id = " + materialID);
		try {
			if (!rs.first()) {
				throw new DALException("Formula component does not exist");
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
		ResultSet rs = Connector.doQuery("SELECT formula_id, material_id, nom_netto, tolerance FROM formula_component");
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
		ResultSet rs = Connector.doQuery("SELECT formula_id, material_id, nom_netto, tolerance FROM formula_component WHERE formulaID ="
				+ formulaID);
		List<FormulaCompDTO> list = new ArrayList<FormulaCompDTO>();
		try {
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
		Connector.doUpdate("INSERT INTO formula_component(formula_id, material_id, nom_netto, tolerance VALUES("
				+ formulaComponent.getFormulaID() + "," + formulaComponent.getMaterialID() + "," + formulaComponent.getNomNetto() + ","
				+ formulaComponent.getTolerance() + ")");
	}

	@Override
	public void updateFormulaComp(FormulaCompDTO formulaComponent) throws DALException {
		Connector.doUpdate("UPDATE formula_component SET formula_id = " + formulaComponent.getFormulaID() + ", material_id = "
				+ formulaComponent.getMaterialID() + ", nom_netto = " + formulaComponent.getNomNetto() + ", tolerance = "
				+ formulaComponent.getTolerance());
	}

}
