package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import connector.Connector;
import daoimpl.FormulaCompDAO;
import daoimpl.ProductbatchCompDAO;
import daointerfaces.DALException;
import daointerfaces.IFormulaCompDAO;
import daointerfaces.IProductbatchCompDAO;
import dto.FormulaCompDTO;
import dto.ProductbatchCompDTO;

public class TestProductbatchCompDAO {

	private static int pbID = 1;
	private static int mbID = 3;

	private static IProductbatchCompDAO productbatchCompDAO;
	private ProductbatchCompDTO productbatchCompDTO;

	@BeforeClass
	public static void connect() {

		ConnectorTest.connect();

		productbatchCompDAO = new ProductbatchCompDAO();

	}

	@Test
	public void getFormulaComp() {
		try {
			productbatchCompDTO = productbatchCompDAO.getProductbatchComp(1, 1);
			assertNotNull(productbatchCompDTO);
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void getFormulaCompList() throws DALException {
		try {
			productbatchCompDAO.getProductbatchCompList();
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void getFormulaCompList2() throws DALException {
		try {
			productbatchCompDAO.getProductbatchCompList(3);
		} catch (DALException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void createUpdateFormulaComp() {

		productbatchCompDTO = new ProductbatchCompDTO(pbID, mbID, 2, 0.5, 3.65);

		try {

			productbatchCompDAO.createProductbatchComp(productbatchCompDTO);
		} catch (DALException e) {
			fail(e.getMessage());
		}

		productbatchCompDTO.setNetto(2.76);
		try {
			productbatchCompDAO.updateProductbatchComp(productbatchCompDTO);
		} catch (DALException e) {
			fail(e.getMessage());
		}
		try {
			assertEquals(productbatchCompDTO.getNetto(), productbatchCompDAO.getProductbatchComp(pbID, mbID).getNetto(), 0);
		} catch (DALException e) {
			fail(e.getMessage());
		}

	}

	@AfterClass
	public static void close() {
		try {
			Connector.doUpdate("DELETE FROM productbatch_component WHERE pb_id = " + pbID + " AND mb_id = " + mbID);
		} catch (DALException e) {
		}
		ConnectorTest.close();
	}
}
