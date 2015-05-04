package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import connector.Connector;
import daoimpl.MaterialBatchDAO;
import daoimpl.MaterialDAO;
import daointerfaces.DALException;
import daointerfaces.IMaterialBatchDAO;
import daointerfaces.IMaterialDAO;
import dto.MaterialDTO;
import dto.MaterialbatchDTO;

public class TestMaterialBatchDAO {
	
	// how to implement: See OperatorDAO and FormulaDAO
	
	private static int insertID;
	
	private static IMaterialBatchDAO materialDAO;
	private MaterialbatchDTO materialDTO;
	
	@BeforeClass
	public static void connect() {
		
		ConnectorTest.connect();
		
		materialDAO = new MaterialBatchDAO();

	}


	@Test
	public void getFormula() {
		try {
			materialDTO = materialDAO.getMaterialBatch(1);
		} catch (DALException e) {
			fail(e.getMessage());
		}
		assertTrue(materialDTO != null);
	}
	
	@Test
	public void getFormulaList(){
		try {
			materialDAO.getMaterialBatchList();
		} catch (DALException e) {
			fail(e.getMessage());		
		}
	}
	
	@Test
	public void createUpdateFormula(){
		materialDTO = new MaterialbatchDTO(0, 3, 100);
		try {
			materialDAO.createMaterialBatch(materialDTO);
			insertID = materialDTO.getMaterialID();
		} catch (DALException e) {
			fail(e.getMessage());
		}
		
		try {
			materialDTO = materialDAO.getMaterialBatch(insertID);
		} catch (DALException e) {
			fail(e.getMessage());
		}
		
		materialDTO.setQuantity(200);
		try {
			materialDAO.updateMaterialBatch(materialDTO);
		} catch (DALException e) {
			fail(e.getMessage());
		}
		
		try {
			assertEquals(materialDTO.getQuantity(), materialDAO.getMaterialBatch(insertID).getQuantity(), 0);
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void close() {
		try {
			Connector.doUpdate("DELETE FROM materialbatch WHERE mb_id = "+insertID);
		} catch (DALException e) {}
		ConnectorTest.close();
	}

}
