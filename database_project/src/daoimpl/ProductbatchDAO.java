package daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector.Connector;
import daointerfaces.DALException;
import daointerfaces.IProductbatchDAO;
import dto.MaterialbatchDTO;
import dto.ProductbatchDTO;

public class ProductbatchDAO implements IProductbatchDAO {

	@Override
	public ProductbatchDTO getProductbatch(int pbId) throws DALException {
		try {
			PreparedStatement ps = Connector.prepare("SELECT * FROM productbatch WHERE pb_id = ?");
			ps.setInt(1, pbId);

			ResultSet rs = ps.executeQuery();
			if (!rs.first()) {
				return null;
			} else {
				return new ProductbatchDTO(rs.getInt("pb_id"), rs.getInt("status"), rs.getInt("formula_id"));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<ProductbatchDTO> getProductbatchList() throws DALException {
		ArrayList<ProductbatchDTO> list = new ArrayList<ProductbatchDTO>();

		ResultSet rs = Connector.doQuery("SELECT * FROM productbatch");
		try {
			while (rs.next()) {
				list.add(new ProductbatchDTO(rs.getInt("pb_id"), rs.getInt("status"), rs.getInt("formula_id")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void createProductbatch(ProductbatchDTO produktbatch) throws DALException {
		Connector.doUpdate("UPDATE materialbatch SET formula_id = " + produktbatch.getFormulatID() + ", status = '" + produktbatch.getStatus() + "' WHERE pb_id = " + produktbatch.getPbID());
	}

	@Override
	public void updateProductbatch(ProductbatchDTO produktbatch) throws DALException {
		// TODO Auto-generated method stub

	}

}
