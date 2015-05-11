package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import connector.Connector;
import daoimpl.MaterialDAO;
import daointerfaces.DALException;
import daointerfaces.IMaterialDAO;
import dto.MaterialDTO;

public class TestMaterialDAO {

	private static int insertID;

	private static IMaterialDAO materialDAO;
	private MaterialDTO materialDTO;

	@BeforeClass
	public static void connect() {

		materialDAO = new MaterialDAO();

	}

	@Test
	public void getMaterial() {
		try {
			materialDTO = materialDAO.getMaterial(1);
		} catch (DALException e) {
			fail(e.getMessage());
		}
		assertTrue(materialDTO != null);
	}

	@Test
	public void getMaterialList() {
		try {
			materialDAO.getMaterialList();
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void createUpdateMaterial() {
		materialDTO = new MaterialDTO(0, "Origano", "OrIgana A/S");
		try {
			materialDAO.createMaterial(materialDTO);
			insertID = materialDTO.getMaterialID();
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			materialDTO = materialDAO.getMaterial(insertID);
		} catch (DALException e) {
			fail(e.getMessage());
		}

		materialDTO.setMaterialName("Persille");
		try {
			materialDAO.updateMaterial(materialDTO);
		} catch (DALException e) {
			fail(e.getMessage());
		}

		try {
			assertEquals(materialDTO.getMaterialName(), materialDAO.getMaterial(insertID).getMaterialName());
		} catch (DALException e) {
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void close() {
		try {
			Connector.doUpdate("DELETE FROM material WHERE material_id = " + insertID);
		} catch (SQLException e) {
		}
	}

}
