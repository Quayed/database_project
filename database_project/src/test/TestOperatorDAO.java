package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import connector.Connector;
import daoimpl.OperatorDAO;
import daointerfaces.DALException;
import daointerfaces.IOperatorDAO;
import dto.OperatorDTO;

public class TestOperatorDAO {

	private static IOperatorDAO oprDAO;
	private OperatorDTO oprDTO;
	private static int insertID;
	
	@BeforeClass
	public static void connect() {
		
		try {
			new Connector();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			fail(e.getMessage());
		}
		
		oprDAO = new OperatorDAO();

	}
	
	@Test
	public void getOperator() {
		try {
			oprDTO = oprDAO.getOperator(3);
		} catch (DALException e) {
			fail(e.getMessage());
		}
		assertTrue(oprDTO != null);
	}
	
	@Test
	public void createUpdateOperator() {
		oprDTO = new OperatorDTO(0, "Don Juan", "DJ", "000000-0000", "iloveyou");
		try {
			oprDAO.createOperator(oprDTO);
			insertID = oprDTO.getOprID();
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			oprDTO = oprDAO.getOperator(insertID);
		} catch (DALException e) {
			fail(e.getMessage());
		}

		oprDTO.setIni("DoJu");
		try {
			oprDAO.updateOperator(oprDTO);
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			assertEquals(oprDTO.getIni(), oprDAO.getOperator(insertID).getIni());
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void getOperatorList() {
		try {
			oprDAO.getOperatorList();
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			assertNull(oprDAO.getOperator(insertID+1));
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void logout() {
		try {
			Connector.doUpdate("DELETE FROM operator WHERE opr_id = "+insertID);
			Connector.close();
		} catch (DALException | SQLException e) {}
	}

}
