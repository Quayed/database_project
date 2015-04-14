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
		try {
			List<FormulaDTO> result= formulaDAO.getFormulaList();
		} catch (DALException e) {
			fail(e.toString());		
		}
	}
	
	@Test // test createFormula
	public void createFormula(){
		formulaDTO = new FormulaDTO(10, "Test");
		try {
			formulaDAO.createFormula(formulaDTO);
			FormulaDTO result = formulaDAO.getFormula(formulaDTO.getFormulaID());
			assertEquals(formulaDTO.getFormulaID(), result.getFormulaID()); // Check that ID matches
			assertEquals(formulaDTO.getFormulaName(), result.getFormulaName()); // Check that name matches
			Connector.doUpdate("DELETE FROM formula WHERE formula_id = " + formulaDTO.getFormulaID());
		} catch (DALException e) {
			fail(e.toString());
		}
	}
	
	@Test // test updateFormula
	public void updateFormula() throws DALException{
		formulaDTO = new FormulaDTO(10, "Test");
		try {
			formulaDAO.createFormula(formulaDTO);
			FormulaDTO result = formulaDAO.getFormula(formulaDTO.getFormulaID());
			assertEquals(formulaDTO.getFormulaID(), result.getFormulaID()); // Check that ID matches after creation
			assertEquals(formulaDTO.getFormulaName(), result.getFormulaName()); // Check that name matches after creation
			formulaDTO.setFormulaName("Mathias");
			formulaDAO.updateFormula(formulaDTO);
			result = formulaDAO.getFormula(formulaDTO.getFormulaID());
			assertEquals(formulaDTO.getFormulaID(), result.getFormulaID()); // Check that ID matches after update
			assertEquals(formulaDTO.getFormulaName(), result.getFormulaName()); // Check that name matches after update
		} catch (DALException e) {
			fail(e.toString());
		} finally{
			Connector.doUpdate("DELETE FROM formula WHERE formula_id = " + formulaDTO.getFormulaID());
		}
	}
}
