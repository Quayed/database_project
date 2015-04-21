package daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;
import daointerfaces.DALException;
import daointerfaces.IMaterialDAO;
import dto.MaterialDTO;

public class MaterialDAO implements IMaterialDAO {

	@Override
	public MaterialDTO getMaterial(int materialID) throws DALException {
		try {
			PreparedStatement ps = Connector.prepare("SELECT material_id, material_name, provider FROM material WHERE material_id = ?");
			ps.setInt(1, materialID);
			ResultSet rs = ps.executeQuery();
			if (!rs.first())
				return null;
			return new MaterialDTO(rs.getInt("material_id"), rs.getString("material_name"), rs.getString("provider"));
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<MaterialDTO> getMaterialList() throws DALException {
		ResultSet rs = Connector.doQuery("SELECT material_id, material_name FROM material");
		List<MaterialDTO> list = new ArrayList<MaterialDTO>();
		try {
			while (rs.next()) {
				list.add(new MaterialDTO(rs.getInt("material_id"), rs.getString("material_name"), rs.getString("provider")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void createMaterial(MaterialDTO material) throws DALException {
		PreparedStatement ps;
		try {
			ps = Connector.prepare("INSERT INTO material(material_id, material_name) VALUES (?, ?, ?);");
			ps.setInt(1, material.getMaterialID());
			ps.setString(2, material.getMaterialName());
			ps.setString(2, material.getProvider());
			ps.execute();
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public void updateMaterial(MaterialDTO material) throws DALException {
		try{
		PreparedStatement ps = Connector.prepare("UPDATE metarial SET material_id = ?, material_name = ?, provider = ? WHERE material_id = ?");
		ps.setInt(1, material.getMaterialID());
		ps.setString(2, material.getMaterialName());
		ps.setString(3, material.getProvider());
		ps.setInt(4, material.getMaterialID());
		ps.execute();
		} catch(SQLException e){
			throw new DALException(e);
		}
	}

}
