package com.logger;

import java.util.List;
import java.util.Map;

import com.product.VO.ProductAccumulationVO;

public class SalesLogger {
	/**
	 * @param productMap
	 */
	public void createReport(Map<String, ProductAccumulationVO> productMap) {
		ProductAccumulationVO accumulationVO;
		System.out.println("**************************        Sales Report      ***************************");
		System.out.println("PRODUCT TYPE             " + "PRODUCT QUANTITY             " + "PRODUCT PRICE");
		double totalCost = 0.0;
		for (Map.Entry<String, ProductAccumulationVO> entry : productMap.entrySet()) {
			accumulationVO = entry.getValue();
			totalCost = totalCost + accumulationVO.getTotalProductCost();
			String formattedOutput = String.format("|%-20s|%-20d|%-20.2f|", accumulationVO.getProductType(),
					accumulationVO.getTotalProductQuantity(), accumulationVO.getTotalProductCost());
			System.out.println(formattedOutput);
			// System.out.println("+++++++++++++++"+totalCost);
		}
		System.out.println("----------------------------------------------------------------");
		System.out.println("TOTAL SALES                            " + String.format("%-2.2f", totalCost));
		System.out.println("----------------------------------------------------------------");
		System.out.println("END");
	}

	/**
	 * @param adjustmentList
	 */
	public void createAdjustmentReport(List<String> adjustmentList) {
		// TODO Auto-generated method stub
		System.out.println(":: 50 messages reached!!! Sorry, application cannot log any further..! "
				+ "Please find the following adjustments made ::");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------");
		for (String s : adjustmentList) {
			System.out.println(s);

		}
	}

}
