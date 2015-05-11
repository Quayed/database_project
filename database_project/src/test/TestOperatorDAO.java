package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import connector.Connector;
import daoimpl.OperatorDAO;
import daointerfaces.DALException;
import daointerfaces.IOperatorDAO;
import dto.OperatorDTO;

public class TestOperatorDAO{

	private static int insertID;
	
	private static IOperatorDAO operatorDAO;
	private OperatorDTO operatorDTO;
	
	@BeforeClass
	public static void connect() {
		
		ConnectorTest.connect();
		
		operatorDAO = new OperatorDAO();

	}
	
	@Test
	public void getOperator() {
		try {
			operatorDTO = operatorDAO.getOperator(3);
		} catch (DALException e) {
			fail(e.getMessage());
		}
		assertTrue(operatorDTO != null);
	}
	
	@Test
	public void getOperatorList() {
		try {
			operatorDAO.getOperatorList();
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void createUpdateOperator() {
		operatorDTO = new OperatorDTO(0, "Don Juan", "DJ", "000000-0000", "iloveyou");
		try {
			operatorDAO.createOperator(operatorDTO);
			insertID = operatorDTO.getOprID();
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			operatorDTO = operatorDAO.getOperator(insertID);
		} catch (DALException e) {
			fail(e.getMessage());
		}

		operatorDTO.setIni("DoJu");
		try {
			operatorDAO.updateOperator(operatorDTO);
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			assertEquals(operatorDTO.getIni(), operatorDAO.getOperator(insertID).getIni());
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			assertNull(operatorDAO.getOperator(insertID+1));
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void close() {
		try {
			Connector.doUpdate("DELETE FROM operator WHERE opr_id = "+insertID);
		} catch (SQLException e) {}
		ConnectorTest.close();
	}

}
