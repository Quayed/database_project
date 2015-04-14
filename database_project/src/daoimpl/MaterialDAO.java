package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import connector.Connector;
import daointerfaces.DALException;
import daointerfaces.IMaterialDAO;
import dto.MaterialDTO;

public class MaterialDAO implements IMaterialDAO {

	@Override
	public MaterialDTO getMaterial(int materialID) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM material WHERE material_id = " + materialID);
		try {
			if (!rs.first())
				throw new DALException("Operator " + materialID + " don't exists");
			return new MaterialDTO(rs.getInt("material_id"), rs.getString("material_name"), rs.getString("provider"));
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<MaterialDTO> getMaterialList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createMaterial(MaterialDTO material) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateMaterial(MaterialDTO material) throws DALException {
		// TODO Auto-generated method stub

	}

}
