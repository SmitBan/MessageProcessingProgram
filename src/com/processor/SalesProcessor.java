package com.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.logger.SalesLogger;
import com.product.VO.ProductAccumulationVO;
import com.product.VO.ProductLineInfoVO;

public class SalesProcessor {
	// to create logs
	public SalesLogger log;
	
	public int counter = 0;

	Map<String, ProductAccumulationVO> productMap = new HashMap<String, ProductAccumulationVO>();

	List<String> adjustmentList = new ArrayList<String>();

	/**
	 * @param fileInput
	 * @return
	 */
	public boolean lineProcessor(String fileInput) {
		LineParser parser = new LineParser();
		ProductAccumulationVO accumulationVO;		
		ProductLineInfoVO productLineInfoVO = parser.msgParser(fileInput);
		if (null != productLineInfoVO.getProductType()) {
			accumulationVO = productMap.get(productLineInfoVO.getProductType());
			if (null != accumulationVO) {
				if (null == productLineInfoVO.getOperator()) {
					accumulationVO.setTotalProductQuantity(
							accumulationVO.getTotalProductQuantity() + productLineInfoVO.getProductQuantity());
					accumulationVO.setTotalProductCost(accumulationVO.getTotalProductCost()
							+ (productLineInfoVO.getProductQuantity() * productLineInfoVO.getProductCost()));
				} else {
					double productCostBeforeOperation = accumulationVO.getTotalProductCost();
					if (productLineInfoVO.getOperator().equalsIgnoreCase("Add")) {
						accumulationVO.setTotalProductCost(accumulationVO.getTotalProductCost()
								+ accumulationVO.getTotalProductQuantity() * productLineInfoVO.getProductCost());
					} else if (productLineInfoVO.getOperator().equalsIgnoreCase("Subtract")) {
						accumulationVO.setTotalProductCost(accumulationVO.getTotalProductCost()
								- accumulationVO.getTotalProductQuantity() * productLineInfoVO.getProductCost());
					} else if (productLineInfoVO.getOperator().equalsIgnoreCase("Multiply")) {
						accumulationVO.setTotalProductCost(accumulationVO.getTotalProductCost()
								* accumulationVO.getTotalProductQuantity() * productLineInfoVO.getProductCost());
					}
					
					adjustmentList.add("For " + accumulationVO.getTotalProductQuantity() + " "
							+ productLineInfoVO.getProductType() + " " + "performed " + productLineInfoVO.getOperator()
							+ " "+String.format("%-2.2f", productLineInfoVO.getProductCost()) + "p operation " + " & price is adjusted from " + String.format("%-2.2f", productCostBeforeOperation)
							+ "p to " + String.format("%-2.2f", accumulationVO.getTotalProductCost())+"p");
				}
				
			} else {
				accumulationVO = new ProductAccumulationVO();
				accumulationVO.setProductType(productLineInfoVO.getProductType());
				accumulationVO.setTotalProductQuantity(productLineInfoVO.getProductQuantity());
				accumulationVO.setTotalProductCost(
						productLineInfoVO.getProductQuantity() * productLineInfoVO.getProductCost());
				
				productMap.put(accumulationVO.getProductType(), accumulationVO);
			}

			counter++;
			if (counter % 10 == 0) {
				log = new SalesLogger();
				log.createReport(productMap);
				productMap.put(accumulationVO.getProductType(), accumulationVO);
			}
			if (counter == 50) {
				log = new SalesLogger();
				log.createAdjustmentReport(adjustmentList);
				System.exit(1);
			}
		}

		return true;
	}

}
