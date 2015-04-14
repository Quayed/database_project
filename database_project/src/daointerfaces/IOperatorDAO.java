package daointerfaces;

import java.util.List;

import dto.OperatorDTO;

public interface IOperatorDAO {
	OperatorDTO getOperatoer(int oprId) throws DALException;

	List<OperatorDTO> getOperatorList() throws DALException;

	void createOperator(OperatorDTO opr) throws DALException;

	void updateOperator(OperatorDTO opr) throws DALException;
}
