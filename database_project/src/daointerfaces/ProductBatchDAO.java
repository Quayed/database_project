package daointerfaces;

import java.util.List;

import dto.ProductBatchDTO;

public interface ProductBatchDAO {
	ProductBatchDTO getProduktBatch(int pbId) throws DALException;
	List<ProductBatchDTO> getProduktBatchList() throws DALException;
	void createProduktBatch(ProductBatchDTO produktbatch) throws DALException;
	void updateProduktBatch(ProductBatchDTO produktbatch) throws DALException;
}