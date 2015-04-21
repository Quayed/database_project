package daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;
import daointerfaces.DALException;
import daointerfaces.IMaterialBatchDAO;
import dto.MaterialbatchDTO;

public class MaterialBatchDAO implements IMaterialBatchDAO {

	@Override
	public MaterialbatchDTO getMaterialBatch(int mbID) throws DALException {
		try {
			PreparedStatement ps = Connector.prepare("SELECT * FROM materialbatch WHERE mb_id = ?");
			ps.setInt(1, mbID);

			ResultSet rs = ps.executeQuery();
			if (!rs.first()) {
				return null;
			} else {
				return new MaterialbatchDTO(rs.getInt("mb_id"), rs.getInt("material_id"), rs.getDouble("quantity"));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<MaterialbatchDTO> getMaterialBatchList() throws DALException {
		ArrayList<MaterialbatchDTO> list = new ArrayList<MaterialbatchDTO>();

		ResultSet rs = Connector.doQuery("SELECT * FROM materialbatch");
		try {
			while (rs.next()) {
				list.add(new MaterialbatchDTO(rs.getInt("mb_id"), rs.getInt("material_id"), rs.getDouble("quantity")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public List<MaterialbatchDTO> getMaterialBatchList(int materialID) throws DALException {
		ArrayList<MaterialbatchDTO> list = new ArrayList<MaterialbatchDTO>();
		try {
			PreparedStatement ps = Connector.prepare("SELECT * FROM materialbatch WHERE material_id = ?");
			ps.setInt(1, materialID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new MaterialbatchDTO(rs.getInt("mb_id"), rs.getInt("material_id"), rs.getDouble("quantity")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void createMaterialBatch(MaterialbatchDTO materialbatch) throws DALException {
		try {
			PreparedStatement ps = Connector.prepare("INSERT INTO productbatch_component(mb_id, material_id, quantity) VALUES (?,?,?)");
			ps.setInt(1, materialbatch.getMbID());
			ps.setInt(2, materialbatch.getMaterialID());
			ps.setDouble(3, materialbatch.getQuantity());
			ps.execute();
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public void updateMaterialBatch(MaterialbatchDTO materialbatch) throws DALException {
		try {
			PreparedStatement ps = Connector.prepare("UPDATE materialbatch SET material_id = ?, quantity = ? WHERE mb_id = ?");
			ps.setInt(1, materialbatch.getMaterialID());
			ps.setDouble(2, materialbatch.getQuantity());
			ps.setInt(3, materialbatch.getMbID());
			ps.execute();
		} catch (SQLException e) {
			throw new DALException(e);
		}
		
//		Connector.doUpdate("UPDATE materialbatch SET material_id = " + materialbatch.getMaterialID() + ", quantity = '" + materialbatch.getQuantity() + 
//				"' WHERE mb_id = " + materialbatch.getMbID());
	}

}
