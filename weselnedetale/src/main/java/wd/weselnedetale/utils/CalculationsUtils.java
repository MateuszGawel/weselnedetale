package wd.weselnedetale.utils;

import wd.weselnedetale.model.fx.PaperFx;
import wd.weselnedetale.model.fx.ProductFx;

public class CalculationsUtils {

	/**
	 * calculates how many products can fit into provided sheet of paper. It check in both orientations and chooses greater one.
	 * 
	 * @param productFx
	 * @param paperFx
	 * @return
	 */
	public static int calculateCountOnPaper(ProductFx productFx, PaperFx paperFx) {
		int paperWidth = paperFx.getWidth();
		int paperHeight = paperFx.getHeight();
		int productWidth = productFx.getWidth();
		int productHeight = productFx.getHeight();
		
		int widthCapacity = paperWidth / productWidth;
		int heightCapacity = paperHeight / productHeight;
		int verticalOrientationCapacity = widthCapacity * heightCapacity;
		
		widthCapacity = paperHeight / productWidth;
		heightCapacity = paperWidth / productHeight;
		
		int horizontalOrientationCapacity = widthCapacity * heightCapacity;
				
		return verticalOrientationCapacity > horizontalOrientationCapacity ? verticalOrientationCapacity : horizontalOrientationCapacity;
	}

	/**
	 * calculates how many products will be printed, considering how many of them fits to one sheet of paper.
	 * 
	 * @param productAmount
	 * @param countOnPaper
	 * @return
	 */
	public static int calculateTotalCount(int productAmount, int countOnPaper) {
		int totalCount = productAmount + (productAmount % countOnPaper > 0 ? (countOnPaper - productAmount % countOnPaper) : 0);
		return totalCount;
	}


	/**
	 * calculates how many papers are needed
	 * 
	 * @param totalCount
	 * @param countOnPaper
	 * @return
	 */
	public static int calculatePaperCount(int totalCount, int countOnPaper) {
		return totalCount/countOnPaper;
	}

	/**
	 * calculates how much single product costs
	 * 
	 * @param paperPrice
	 * @param printCost
	 * @param countOnPaper
	 * @return
	 */
	public static double calculateSingleCost(double paperPrice, double printCost, int countOnPaper) {
		return (paperPrice + printCost) / countOnPaper;
	}

	/**
	 * calculates total cost of orderProduct
	 * 
	 * @param singleCost
	 * @param totalCount
	 * @return
	 */
	public static double calculateTotal(double singleCost, int totalCount) {
		return singleCost * totalCount;
	}

}
