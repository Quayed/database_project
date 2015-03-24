package daointerfaces;

import java.util.List;

import dto.ProductBatchCompDTO;

public interface ProductBatchCompDAO {
	ProductBatchCompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException;
	List<ProductBatchCompDTO> getProduktBatchKompList(int pbId) throws DALException;
	List<ProductBatchCompDTO> getProduktBatchKompList() throws DALException;
	void createProduktBatchKomp(ProductBatchCompDTO produktbatchkomponent) throws DALException;
	void updateProduktBatchKomp(ProductBatchCompDTO produktbatchkomponent) throws DALException;	
}

