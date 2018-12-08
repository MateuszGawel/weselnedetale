package wd.weselnedetale.database.dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import wd.weselnedetale.database.model.BaseModel;
import wd.weselnedetale.database.model.WeddingSet;
import wd.weselnedetale.database.model.WeddingSet_Paper;
import wd.weselnedetale.database.model.WeddingSet_Product;
import wd.weselnedetale.utils.exception.ApplicationException;

public class WeddingSetDao extends CommonDao {

	@Override
	public void createOrUpdate(BaseModel weddingSet) {
		try {
			super.createOrUpdate(weddingSet);
			WeddingSet ws = (WeddingSet) weddingSet;
			persistPapers(ws);
			persistProducts(ws);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	private void persistProducts(WeddingSet weddingSet) {
		removeNotRequiredProducts(weddingSet);
		addRequiredProducts(weddingSet);
	}
	
	private void persistPapers(WeddingSet weddingSet) {
		removeNotRequiredPapers(weddingSet);
		addRequiredPapers(weddingSet);
	}

	private void addRequiredProducts(WeddingSet ws) {
		ws.getProducts().forEach(p -> {
			initProductCollectionIfNeeded(ws);
			WeddingSet_Product weddingset_product = new WeddingSet_Product();
			weddingset_product.setWeddingSet(ws);
			weddingset_product.setProduct(p);
			try {
				ws.getWeddingSet_product().add(weddingset_product);
			}
			catch(IllegalStateException e) {
				//TODO because key is unique. We dont need duplicated many to many rows. log this!
			}
		});
	}
	
	private void removeNotRequiredProducts(WeddingSet ws) {
		WeddingSet_ProductDao weddingSet_ProductDao = new WeddingSet_ProductDao();
		List<WeddingSet_Product> weddingSetProducts = weddingSet_ProductDao.getByWeddingSet(ws);
		
		weddingSetProducts.forEach(wp -> {
			if(!ws.getProducts().stream().filter(p -> p.equals(wp.getProduct())).findFirst().isPresent()) {
				try {
					weddingSet_ProductDao.deleteById(WeddingSet_Product.class, wp.getId());
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	private void addRequiredPapers(WeddingSet ws) {
		ws.getPapers().forEach(p -> {
			initPaperCollectionIfNeeded(ws);
			
			WeddingSet_Paper weddingset_paper = new WeddingSet_Paper();
			weddingset_paper.setWeddingSet(ws);
			weddingset_paper.setPaper(p);
			try {
				ws.getWeddingSet_paper().add(weddingset_paper);
			}
			catch(IllegalStateException e) {
				//TODO because key is unique. We dont need duplicated many to many rows. log this!
			}
		});
	}

	private void removeNotRequiredPapers(WeddingSet ws) {
		WeddingSet_PaperDao weddingSet_PaperDao = new WeddingSet_PaperDao();
		List<WeddingSet_Paper> weddingSetPapers = weddingSet_PaperDao.getByWeddingSet(ws);
		
		weddingSetPapers.forEach(wp -> {
			if(!ws.getPapers().stream().filter(p -> p.equals(wp.getPaper())).findFirst().isPresent()) {
				try {
					weddingSet_PaperDao.deleteById(WeddingSet_Paper.class, wp.getId());
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	
	private void initPaperCollectionIfNeeded(WeddingSet ws) {
		if (ws.getWeddingSet_paper() == null) {
			try {
				Dao<WeddingSet, Long> weddingSetDao = new CommonDao().getDao(WeddingSet.class);
				ws.setWeddingSet_paper(weddingSetDao.getEmptyForeignCollection("WEDDINGSET_PAPER_ID"));
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	private void initProductCollectionIfNeeded(WeddingSet ws) {
		if (ws.getWeddingSet_product() == null) {
			try {
				Dao<WeddingSet, Long> weddingSetDao = new CommonDao().getDao(WeddingSet.class);
				ws.setWeddingSet_product(weddingSetDao.getEmptyForeignCollection("WEDDINGSET_PRODUCT_ID"));
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void update(BaseModel weddingSet) {
		try {
			super.update(weddingSet);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(BaseModel weddingSet) {
		try {
			super.delete(weddingSet);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
