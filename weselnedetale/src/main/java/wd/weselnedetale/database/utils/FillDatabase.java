package wd.weselnedetale.database.utils;

import wd.weselnedetale.database.dao.CommonDao;
import wd.weselnedetale.database.model.Paper;
import wd.weselnedetale.database.model.Product;
import wd.weselnedetale.database.model.WeddingSet;
import wd.weselnedetale.database.model.WeddingSet_Paper;
import wd.weselnedetale.database.model.WeddingSet_Product;
import wd.weselnedetale.utils.exception.ApplicationException;

public class FillDatabase {

	private static CommonDao commonDao = new CommonDao();

	public static void fillDatabase() {
		try {
			
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
			
		} catch (ApplicationException e) {
			e.printStackTrace();
		} finally {
			DbManager.closeConnectionSource();
		}

	}

	private static void addPaperToWeddingSet(WeddingSet weddingSet, Paper paper) throws ApplicationException {
		WeddingSet_Paper weddingset_paper = new WeddingSet_Paper();
		weddingset_paper.setWeddingSet(weddingSet);
		weddingset_paper.setPaper(paper);
		commonDao.createOrUpdate(weddingset_paper);
	}
	
	private static Product createProduct(String name, int width, int height, boolean paperProduct, boolean printed) throws ApplicationException {
		Product product = new Product();
		product.setName(name);
		product.setWidth(width);
		product.setHeight(height);
		product.setPaperProduct(paperProduct);
		product.setPrinted(printed);
		commonDao.createOrUpdate(product);
		return product;
	}

	private static Paper createPaper(String name, double price) throws ApplicationException {
		Paper sirioRed = new Paper();
		sirioRed.setName(name);
		sirioRed.setHeight(488);
		sirioRed.setWidth(330);
		sirioRed.setPrice(price);
		commonDao.createOrUpdate(sirioRed);
		return sirioRed;
	}

	private static WeddingSet createWeddingSet(String name) throws ApplicationException {
		WeddingSet weddingSet = new WeddingSet();
		weddingSet.setName(name);
		commonDao.createOrUpdate(weddingSet);
		return weddingSet;
	}

	private static void addProductToWeddingSet(WeddingSet weddingSet, Product product) throws ApplicationException {
		WeddingSet_Product product_WeddingSet = new WeddingSet_Product();
		product_WeddingSet.setWeddingSet(weddingSet);
		product_WeddingSet.setProduct(product);
		commonDao.createOrUpdate(product_WeddingSet);
	}
}
