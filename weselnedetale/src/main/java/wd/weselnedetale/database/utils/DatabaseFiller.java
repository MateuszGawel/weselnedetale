package wd.weselnedetale.database.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wd.weselnedetale.database.dao.PaperDao;
import wd.weselnedetale.database.dao.ProductDao;
import wd.weselnedetale.database.dao.WeddingSetDao;
import wd.weselnedetale.database.dao.WeddingSet_PaperDao;
import wd.weselnedetale.database.dao.WeddingSet_ProductDao;
import wd.weselnedetale.database.model.Paper;
import wd.weselnedetale.database.model.Product;
import wd.weselnedetale.database.model.WeddingSet;
import wd.weselnedetale.database.model.WeddingSet_Paper;
import wd.weselnedetale.database.model.WeddingSet_Product;
import wd.weselnedetale.utils.exception.ApplicationException;

/**
 * Helper class to fill up database during development with dummy data
 */
@Service
public class DatabaseFiller {
	private WeddingSetDao weddingSetDao;
	private ProductDao productDao;
	private PaperDao paperDao;
	private WeddingSet_PaperDao weddingSet_PaperDao;
	private WeddingSet_ProductDao weddingSet_ProductDao;

	@Autowired
	public DatabaseFiller(WeddingSetDao weddingSetDao, ProductDao productDao, PaperDao paperDao, WeddingSet_PaperDao weddingSet_PaperDao, WeddingSet_ProductDao weddingSet_ProductDao) {
		this.weddingSetDao = weddingSetDao;
		this.productDao = productDao;
		this.paperDao = paperDao;
		this.weddingSet_PaperDao = weddingSet_PaperDao;
		this.weddingSet_ProductDao = weddingSet_ProductDao;
		try {
			fillDatabase();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	private void fillDatabase() throws ApplicationException {
		// products
		Product smallCard = createProduct("mała podklejka", 65, 95, true, false);
		Product bigCard = createProduct("duża podklejka", 109, 157, true, false);
		Product bigPrint = createProduct("duży druk", 109, 157, true, true);
		Product smallPrint = createProduct("mały druk", 65, 95, true, true);
		Product string = createProduct("sznurek", 0, 0, false, false);

		// wedding sets
		WeddingSet jasneRoze = createWeddingSet("Jasne Róże");
		WeddingSet folkowyWianek = createWeddingSet("Folkowy Wianek");

		// products <-> wedding sets
		addProductToWeddingSet(jasneRoze, smallCard);
		addProductToWeddingSet(jasneRoze, smallPrint);
		addProductToWeddingSet(jasneRoze, bigCard);
		addProductToWeddingSet(jasneRoze, bigPrint);
		addProductToWeddingSet(folkowyWianek, smallCard);
		addProductToWeddingSet(folkowyWianek, smallPrint);
		addProductToWeddingSet(folkowyWianek, string);

		// papers
		Paper sirioRed = createPaper("sirio color red", 1.38d);
		Paper sirioBlue = createPaper("sirio color blue", 1.38d);
		Paper elfenbens = createPaper("elfenbens kratka", 1.40d);

		// elements <-> papers
		addPaperToWeddingSet(jasneRoze, sirioRed);
		addPaperToWeddingSet(folkowyWianek, sirioBlue);
		addPaperToWeddingSet(jasneRoze, elfenbens);
		addPaperToWeddingSet(folkowyWianek, elfenbens);
	}

	private void addPaperToWeddingSet(WeddingSet weddingSet, Paper paper) throws ApplicationException {
		WeddingSet_Paper weddingSet_paper = new WeddingSet_Paper();
		weddingSet_paper.setWeddingSet(weddingSet);
		weddingSet_paper.setPaper(paper);
		weddingSet_PaperDao.createOrUpdate(weddingSet_paper);
	}

	private Product createProduct(String name, int width, int height, boolean paperProduct, boolean printed) throws ApplicationException {
		Product product = new Product();
		product.setName(name);
		product.setWidth(width);
		product.setHeight(height);
		product.setPaperProduct(paperProduct);
		product.setPrinted(printed);
		productDao.createOrUpdate(product);
		return product;
	}

	private Paper createPaper(String name, double price) throws ApplicationException {
		Paper sirioRed = new Paper();
		sirioRed.setName(name);
		sirioRed.setHeight(488);
		sirioRed.setWidth(330);
		sirioRed.setPrice(price);
		paperDao.createOrUpdate(sirioRed);
		return sirioRed;
	}

	private WeddingSet createWeddingSet(String name) throws ApplicationException {
		WeddingSet weddingSet = new WeddingSet();
		weddingSet.setName(name);
		weddingSetDao.createOrUpdate(weddingSet);
		return weddingSet;
	}

	private void addProductToWeddingSet(WeddingSet weddingSet, Product product) throws ApplicationException {
		WeddingSet_Product product_WeddingSet = new WeddingSet_Product();
		product_WeddingSet.setWeddingSet(weddingSet);
		product_WeddingSet.setProduct(product);
		weddingSet_ProductDao.createOrUpdate(product_WeddingSet);
	}
}
