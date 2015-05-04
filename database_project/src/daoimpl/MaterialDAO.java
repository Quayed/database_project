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

	private PreparedStatement ps;
	private ResultSet rs;
	
	@Override
	public MaterialDTO getMaterial(int materialID) throws DALException {
		try {
			ps = Connector.prepare("SELECT material_name, provider FROM material WHERE material_id = ?");
			ps.setInt(1, materialID);
			rs = ps.executeQuery();
			if (!rs.first()){
				return null;
			} else {
				return new MaterialDTO(materialID, rs.getString("material_name"), rs.getString("provider"));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<MaterialDTO> getMaterialList() throws DALException {
		List<MaterialDTO> list = new ArrayList<MaterialDTO>();
		try {
			rs = Connector.doQuery("SELECT material_id, material_name, provider FROM material");
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
		try {
			ps = Connector.prepare("INSERT INTO material (material_name, provider) VALUES (?, ?);");
			ps.setString(1, material.getMaterialName());
			ps.setString(2, material.getProvider());
			ps.execute();
			material.setMaterialID(Connector.getLastInsert(ps));
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public void updateMaterial(MaterialDTO material) throws DALException {
		try{
			ps = Connector.prepare("UPDATE material SET material_name = ?, provider = ? WHERE material_id = ?");
			ps.setString(1, material.getMaterialName());
			ps.setString(2, material.getProvider());
			ps.setInt(3, material.getMaterialID());
			ps.execute();
		} catch(SQLException e){
			throw new DALException(e);
		}
	}

}
