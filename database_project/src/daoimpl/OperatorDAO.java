package daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import connector.Connector;
import daointerfaces.DALException;
import daointerfaces.IOperatorDAO;
import dto.OperatorDTO;

public class OperatorDAO implements IOperatorDAO {
	
	private PreparedStatement ps;
	private ResultSet rs;
	
	public OperatorDTO getOperator(int oprId) throws DALException {
		try {
			ps = Connector.prepare("SELECT opr_name, ini, cpr, password FROM operator WHERE opr_id = ?");
			ps.setInt(1, oprId);
			rs = ps.executeQuery();
			
			if (!rs.first()) {
				return null;
			} else {
				return new OperatorDTO(oprId, rs.getString("opr_name"), rs.getString("ini"), rs.getString("cpr"),
					rs.getString("password"));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	public List<OperatorDTO> getOperatorList() throws DALException {
		List<OperatorDTO> list = new ArrayList<OperatorDTO>();
		try {
			rs = Connector.doQuery("SELECT opr_id, opr_name, ini, cpr, password FROM operator");
			while (rs.next()) {
				list.add(new OperatorDTO(rs.getInt("opr_id"), rs.getString("opr_name"), rs.getString("ini"), rs.getString("cpr"),
						rs.getString("password")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}
	
	public void createOperator(OperatorDTO operator) throws DALException {
		try {
			ps = Connector.prepare("INSERT INTO operator(opr_id, opr_name, ini, cpr, password) VALUES " + "(?, ?, ?, ?, ?)");
			ps.setString(1, null);
			ps.setString(2, operator.getOprName());
			ps.setString(3, operator.getIni());
			ps.setString(4, operator.getCpr());
			ps.setString(5, operator.getPassword());
			ps.execute();
			operator.setOprID(Connector.getLastInsert(ps));
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	public void updateOperator(OperatorDTO operator) throws DALException {
		try {
			ps = Connector.prepare("UPDATE operator SET opr_name = ?, ini = ?, cpr = ?, password = ? WHERE opr_id = ?");
			ps.setString(1, operator.getOprName());
			ps.setString(2, operator.getIni());
			ps.setString(3, operator.getCpr());
			ps.setString(4, operator.getPassword());
			ps.setInt(5, operator.getOprID());
			ps.execute();
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

}
