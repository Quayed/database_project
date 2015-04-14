package daointerfaces;

import java.util.List;

import dto.ProductbatchCompDTO;

public interface IProductbatchCompDAO {
	ProductbatchCompDTO getProductbatchComp(int pbID, int mbID) throws DALException;
	List<ProductbatchCompDTO> getProductbatchCompList() throws DALException;
	List<ProductbatchCompDTO> getProductbatchCompList(int pbID) throws DALException;
	void createProductbatchComp(ProductbatchCompDTO productbatchComponent) throws DALException;
	void updateProductbatchComp(ProductbatchCompDTO productbatchComponent) throws DALException;	
}

