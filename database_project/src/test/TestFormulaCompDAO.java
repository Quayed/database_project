package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import daoimpl.FormulaCompDAO;
import daointerfaces.DALException;
import daointerfaces.IFormulaCompDAO;
import dto.FormulaCompDTO;

public class TestFormulaCompDAO {
		
	private static IFormulaCompDAO formulaCompDAO;
	private FormulaCompDTO formulaCompDTO;
	private int receptId;
	private int raavareId;
	
	@BeforeClass
	public static void connect() {
		
		ConnectorTest.connect();
		
		formulaCompDAO = new FormulaCompDAO();

	}

	@Test
	public void getFormulaComp() {
		try {
			formulaCompDTO = formulaCompDAO.getFormulaComp(1, 1);
			assertNotNull(formulaCompDTO);
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void getFormulaCompList() throws DALException {
		fail("Not yet implemented");
		
		try {
			formulaCompDAO.getFormulaCompList(3);
		} catch (DALException e) {
			fail(e.getMessage());
		}

	}
	
	@Test
	public void getFormulaCompList2() throws DALException {
		fail("Not yet implemented");
		
		try {
			formulaCompDAO.getFormulaCompList();
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void createUpdateFormulaComp() {
		fail("Not yet implemented");
		
		FormulaCompDTO receptkomponent = new FormulaCompDTO(1, 7, 2.1, 0.1);
		
		try{
			
			formulaCompDAO.createFormulaComp(receptkomponent);

			formulaCompDAO.updateFormulaComp(receptkomponent);
		
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}
	





	
	
}
