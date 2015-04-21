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
			oprDTO = oprDAO.getOperatoer(3);
		} catch (DALException e) {
			fail(e.getMessage());
		}
		assertTrue(oprDTO != null);
	}
	
	@Test
	public void createUpdateOperator() {
		oprDTO = new OperatorDTO(4, "Don Juan", "DJ", "000000-0000", "iloveyou");
		try {
			oprDAO.createOperator(oprDTO);
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			oprDTO = oprDAO.getOperatoer(4);
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
			assertEquals(oprDTO.getIni(), oprDAO.getOperatoer(4).getIni());
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
			assertNull(oprDAO.getOperatoer(5));
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void logout() {
		try {
			Connector.doUpdate("DELETE FROM operator WHERE opr_id = '4'");
		} catch (DALException e) {}
		try {
			Connector.close();
		} catch (SQLException e) {}
	}

}
