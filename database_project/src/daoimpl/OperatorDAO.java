package daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import connector.Connector;
import daointerfaces.DALException;
import daointerfaces.IOperatorDAO;
import dto.OperatorDTO;

public class OperatorDAO implements IOperatorDAO {
	
	public OperatorDTO getOperatoer(int oprId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM operator WHERE opr_id = " + oprId);
		try {
			if (!rs.first())
				throw new DALException("Operator " + oprId + " don't exists");
			return new OperatorDTO(rs.getInt("opr_id"), rs.getString("opr_name"), rs.getString("ini"), rs.getString("cpr"),
					rs.getString("password"));
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	public void createOperator(OperatorDTO opr) throws DALException {
		Connector.doUpdate("INSERT INTO operator(opr_id, opr_name, ini, cpr, password) VALUES " + "(" + opr.getOprID() + ", '"
				+ opr.getOprName() + "', '" + opr.getIni() + "', '" + opr.getCpr() + "', '" + opr.getPassword() + "')");
	}

	public void updateOperator(OperatorDTO opr) throws DALException {
		Connector.doUpdate("UPDATE operator SET  opr_name = '" + opr.getOprName() + "', ini =  '" + opr.getIni() + "', cpr = '"
				+ opr.getCpr() + "', password = '" + opr.getPassword() + "' WHERE opr_id = " + opr.getOprID());
	}

	public List<OperatorDTO> getOperatorList() throws DALException {
		List<OperatorDTO> list = new ArrayList<OperatorDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM operator");
		try {
			while (rs.next()) {
				list.add(new OperatorDTO(rs.getInt("opr_id"), rs.getString("opr_name"), rs.getString("ini"), rs.getString("cpr"),
						rs.getString("password")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

}
