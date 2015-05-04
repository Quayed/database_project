package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import connector.Connector;
import daoimpl.ProductbatchDAO;
import daointerfaces.DALException;
import daointerfaces.IProductbatchDAO;
import dto.ProductbatchDTO;

public class TestProductbatchDAO {

	private static int insertID;

	private static IProductbatchDAO productbatchDAO;
	private ProductbatchDTO productbatchDTO;

	@BeforeClass
	public static void connect() {

		ConnectorTest.connect();

		productbatchDAO = new ProductbatchDAO();

	}

	@Test
	public void getProductbatch() {
		try {
			productbatchDTO = productbatchDAO.getProductbatch(1);
		} catch (DALException e) {
			fail(e.getMessage());
		}
		assertTrue(productbatchDTO != null);
	}

	@Test
	public void getProductbatchList() {
		try {
			productbatchDAO.getProductbatchList();
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void createUpdateProductbatch() {
		productbatchDTO = new ProductbatchDTO(0, 2, 0);
		try {
			productbatchDAO.createProductbatch(productbatchDTO);
			insertID = productbatchDTO.getPbID();
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			productbatchDTO = productbatchDAO.getProductbatch(insertID);
		} catch (DALException e) {
			fail(e.getMessage());
		}

		productbatchDTO.setStatus(1);
		try {
			productbatchDAO.updateProductbatch(productbatchDTO);
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			assertEquals(productbatchDTO.getStatus(), productbatchDAO.getProductbatch(insertID).getStatus());
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void close() {
		try {
			Connector.doUpdate("DELETE FROM productbatch WHERE pb_id = " + insertID);
		} catch (DALException e) {
		}
		ConnectorTest.close();
	}

}
