package daointerfaces;

import java.util.List;

import dto.ProductbatchDTO;

public interface IProductbatchDAO {
	ProductbatchDTO getProductbatch(int pbId) throws DALException;
	List<ProductbatchDTO> getProductbatchList() throws DALException;
	void createProductbatch(ProductbatchDTO produktbatch) throws DALException;
	void updateProductbatch(ProductbatchDTO produktbatch) throws DALException;
}