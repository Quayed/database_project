package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import connector.Connector;
import daoimpl.FormulaDAO;
import daointerfaces.DALException;
import dto.FormulaDTO;

public class TestFormulaDAO {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		new Connector();
	}

	@Before
	public void setUp() throws Exception {
		FormulaDAO formulaDAO = new FormulaDAO();
		FormulaDTO formulaDTO = null;
	}

	@Test
	public void testGetFormula() {
		// test select getFormula
		try {
			formulaDTO = formulaDAO.getFormula(1);
		} catch (DALException e) {
			fail(e.toString());
		}
		assertEquals(1,formulaDTO.getFormulaID());
		assertTrue(formulaDTO.getFormulaName() != null);
	}

}
