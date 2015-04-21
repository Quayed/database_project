package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import connector.Connector;
import daoimpl.FormulaDAO;
import daointerfaces.DALException;
import dto.FormulaDTO;

public class TestFormulaDAO {
	private static FormulaDAO formulaDAO;
	private FormulaDTO formulaDTO;
	
	@BeforeClass
	public static void connect() {
		
		try {
			new Connector();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			fail(e.getMessage());
		}
		
		formulaDAO = new FormulaDAO();

	}


	@Test
	public void getFormula() {
		try {
			formulaDTO = formulaDAO.getFormula(1);
		} catch (DALException e) {
			fail(e.getMessage());
		}
		assertTrue(formulaDTO != null);
	}
	
	@Test
	public void getFormulaList(){
		try {
			formulaDAO.getFormulaList();
		} catch (DALException e) {
			fail(e.getMessage());		
		}
	}
	
	@Test
	public void createUpdateFormula(){
		formulaDTO = new FormulaDTO(10, "Test");
		try {
			formulaDAO.createFormula(formulaDTO);
		} catch (DALException e) {
			fail(e.getMessage());
		}
		
		try {
			formulaDTO = formulaDAO.getFormula(10);
		} catch (DALException e) {
			fail(e.getMessage());
		}
		
		formulaDTO.setFormulaName("Bahamas");;
		try {
			formulaDAO.updateFormula(formulaDTO);
		} catch (DALException e) {
			fail(e.getMessage());
		}
		
		try {
			assertEquals(formulaDTO.getFormulaName(), formulaDAO.getFormula(10).getFormulaName());
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void close() {
		try {
			Connector.doUpdate("DELETE FROM formula WHERE formula_id = '10'");
		} catch (DALException e1) {}
		
		try {
			Connector.close();
		} catch (SQLException e) {}
	}
}
