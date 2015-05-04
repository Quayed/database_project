package daointerfaces;

import java.util.List;

import dto.OperatorDTO;

public interface IOperatorDAO {
	OperatorDTO getOperator(int oprId) throws DALException;

	List<OperatorDTO> getOperatorList() throws DALException;

	void createOperator(OperatorDTO operator) throws DALException;

	void updateOperator(OperatorDTO operator) throws DALException;
}
