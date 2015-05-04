package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import connector.Connector;
import daoimpl.MaterialBatchDAO;
import daointerfaces.DALException;
import daointerfaces.IMaterialBatchDAO;
import dto.MaterialbatchDTO;

public class TestMaterialBatchDAO {

	private static int insertID;

	private static IMaterialBatchDAO materialbatchDAO;
	private MaterialbatchDTO materialbatchDTO;

	@BeforeClass
	public static void connect() {

		ConnectorTest.connect();

		materialbatchDAO = new MaterialBatchDAO();

	}

	@Test
	public void getMaterialbatch() {
		try {
			materialbatchDTO = materialbatchDAO.getMaterialBatch(1);
		} catch (DALException e) {
			fail(e.getMessage());
		}
		assertTrue(materialbatchDTO != null);
	}

	@Test
	public void getMaterialbatchList() {
		try {
			materialbatchDAO.getMaterialBatchList();
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void createUpdateMaterialbatch() {
		materialbatchDTO = new MaterialbatchDTO(0, 3, 100);
		try {
			materialbatchDAO.createMaterialBatch(materialbatchDTO);
			insertID = materialbatchDTO.getMaterialID();
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			materialbatchDTO = materialbatchDAO.getMaterialBatch(insertID);
		} catch (DALException e) {
			fail(e.getMessage());
		}

		materialbatchDTO.setQuantity(200);
		try {
			materialbatchDAO.updateMaterialBatch(materialbatchDTO);
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			assertEquals(materialbatchDTO.getQuantity(), materialbatchDAO.getMaterialBatch(insertID).getQuantity(), 0);
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void close() {
		try {
			Connector.doUpdate("DELETE FROM materialbatch WHERE mb_id = " + insertID);
		} catch (DALException e) {
		}
		ConnectorTest.close();
	}

}
