package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import connector.Connector;
import daoimpl.FormulaDAO;
import daointerfaces.DALException;
import dto.FormulaDTO;

public class TestFormulaDAO {
	FormulaDAO formulaDAO;
	FormulaDTO formulaDTO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		new Connector();
	}

	@Before
	public void setUp() throws Exception {
		formulaDAO = new FormulaDAO();
		formulaDTO = null;
	}

	@Test // test getFormula
	public void getFormula() {
		try {
			formulaDTO = formulaDAO.getFormula(1);
		} catch (DALException e) {
			fail(e.toString());
		}
		assertEquals(1,formulaDTO.getFormulaID());
		assertTrue(formulaDTO.getFormulaName() != null);
	}
	
	@Test // test getFormulaList
	public void getFormulaList(){
		List<FormulaDTO> result = null;
		try {
			result= formulaDAO.getFormulaList();
		} catch (DALException e) {
			fail(e.toString());		
		}
		if(result.isEmpty()){
			fail("The list is empty");
		}
	}
}
