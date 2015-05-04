package daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;
import daointerfaces.DALException;
import daointerfaces.IProductbatchCompDAO;
import dto.ProductbatchCompDTO;

public class ProductbatchCompDAO implements IProductbatchCompDAO {

	private PreparedStatement ps;
	private ResultSet rs;
	
	@Override
	public ProductbatchCompDTO getProductbatchComp(int pbID, int mbID) throws DALException {
		try {
			ps = Connector.prepare("SELECT opr_id, tare, netto FROM productbatch_component WHERE pb_id = ? && mb_id = ?");
			ps.setInt(1, pbID);
			ps.setInt(2, mbID);
			rs = ps.executeQuery();
			if (!rs.first()) {
				return null;
			} else {
				return new ProductbatchCompDTO(pbID, mbID, rs.getInt("opr_id"), rs.getDouble("tare"), rs.getDouble("netto"));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}
	
	@Override
	public List<ProductbatchCompDTO> getProductbatchCompList() throws DALException {
		List<ProductbatchCompDTO> list = new ArrayList<ProductbatchCompDTO>();
		try {
			rs = Connector.doQuery("SELECT pb_id, mb_id, opr_id, tare, netto FROM productbatch_component");
			while (rs.next()) {
				list.add(new ProductbatchCompDTO(rs.getInt("pb_id"), rs.getInt("mb_id"), rs.getInt("opr_id"), rs.getDouble("tare"), rs.getDouble("netto")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}
	
	@Override
	public List<ProductbatchCompDTO> getProductbatchCompList(int pbID) throws DALException {
		List<ProductbatchCompDTO> list = new ArrayList<ProductbatchCompDTO>();
		try {
			ps = Connector.prepare("SELECT mb_id, opr_id, tare, netto FROM productbatch_component WHERE pb_id = ?");
			ps.setInt(1, pbID);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ProductbatchCompDTO(pbID, rs.getInt("mb_id"), rs.getInt("opr_id"), rs.getDouble("tare"), rs.getDouble("netto")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void createProductbatchComp(ProductbatchCompDTO productbatchComponent) throws DALException {
		try {
			ps = Connector.prepare("INSERT INTO productbatch_component(pb_id, mb_id, opr_id, tare, netto) VALUES (?,?,?,?,?)");
			ps.setInt(1, productbatchComponent.getPbID());
			ps.setInt(2, productbatchComponent.getMbID());
			ps.setInt(3, productbatchComponent.getOprID());
			ps.setDouble(4, productbatchComponent.getTare());
			ps.setDouble(5, productbatchComponent.getNetto());
			ps.execute();
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public void updateProductbatchComp(ProductbatchCompDTO productbatchComponent) throws DALException {
		try {
			ps = Connector.prepare("UPDATE productbatch_component SET tare = ?, netto = ?, opr_id = ? WHERE pb_id = ? AND mb_id = ?");
			ps.setDouble(1, productbatchComponent.getTare());
			ps.setDouble(2, productbatchComponent.getNetto());
			ps.setInt(3, productbatchComponent.getOprID());
			ps.setInt(4, productbatchComponent.getPbID());
			ps.setInt(5, productbatchComponent.getMbID());
			ps.execute();
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

}
