package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Test;

import connector.Connector;
import daoimpl.OperatorDAO;
import daointerfaces.DALException;
import daointerfaces.IOperatorDAO;
import dto.OperatorDTO;

public class TestOperatorDAO {

	@Test
	public void test() {
		try {
			new Connector();
		} catch (InstantiationException e) {
			fail(e.getMessage());
		} catch (IllegalAccessException e) {
			fail(e.getMessage());
		} catch (ClassNotFoundException e) {
			fail(e.getMessage());
		} catch (SQLException e) {
			fail(e.getMessage());
		}

		IOperatorDAO opr = new OperatorDAO();

		try {
			opr.getOperatoer(3);
		} catch (DALException e) {
			fail(e.getMessage());
		}

		OperatorDTO oprDTO = new OperatorDTO(4, "Don Juan", "DJ", "000000-0000", "iloveyou");
		try {
			opr.createOperator(oprDTO);
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			oprDTO = opr.getOperatoer(4);
		} catch (DALException e) {
			fail(e.getMessage());
		}

		oprDTO.setIni("DoJu");
		try {
			opr.updateOperator(oprDTO);
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			assertEquals(oprDTO.getIni(), opr.getOperatoer(4).getIni());
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			opr.getOperatorList();
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			opr.getOperatoer(5);
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void logout() {
		try {
			Connector.doUpdate("DELETE FROM operator WHERE opr_id = '4'");
		} catch (DALException e) {}
	}

}
