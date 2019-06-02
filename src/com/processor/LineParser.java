package com.processor;

import com.product.VO.ProductLineInfoVO;

/**
 * @author smita.banerjee
 *
 */
public class LineParser {

	/**
	 * @param fileInput
	 * @return
	 */
	public ProductLineInfoVO msgParser(String fileInput) {
		ProductLineInfoVO productLineInfoVO = new ProductLineInfoVO();
		if (fileInput.isEmpty() || fileInput.equals(null)) {
			return productLineInfoVO;
		}
		String[] msg = fileInput.trim().split("\\s+");
		String firstElement = msg[0];
		if (firstElement.matches("Add|Subtract|Multiply")) {
			return msgParserType3(msg, productLineInfoVO);
		} else if (firstElement.matches("^\\d+")) {
			return msgParserType2(msg, productLineInfoVO);
		} else if (msg.length == 3 && msg[1].contains("at")) {
			return msgParserType1(msg, productLineInfoVO);
		} else
			System.out.println("incorrect message");
		return productLineInfoVO;
	}

	/**
	 * @param msg
	 * @param productLineInfoVO
	 * @return
	 */
	private ProductLineInfoVO msgParserType1(String[] msg, ProductLineInfoVO productLineInfoVO) {
		if (msg.length > 3 || msg.length < 3)
			return productLineInfoVO;
		productLineInfoVO.setProductType(productTypeParser(msg[0]));
		productLineInfoVO.setProductCost(productCostParser(msg[2]));
		productLineInfoVO.setProductQuantity(1);
		return productLineInfoVO;
	}

	/**
	 * @param msg
	 * @param productLineInfoVO
	 * @return
	 */
	private ProductLineInfoVO msgParserType2(String[] msg, ProductLineInfoVO productLineInfoVO) {
		// TODO Auto-generated method stub
		if (msg.length > 7 || msg.length < 7) {
			return productLineInfoVO;
		}
		productLineInfoVO.setProductType(productTypeParser(msg[3]));
		productLineInfoVO.setProductCost(productCostParser(msg[5]));
		productLineInfoVO.setProductQuantity(Integer.parseInt(msg[0]));
		return productLineInfoVO;
	}

	/**
	 * @param msg
	 * @param productLineInfoVO
	 * @return
	 */
	private ProductLineInfoVO msgParserType3(String[] msg, ProductLineInfoVO productLineInfoVO) {
		if (msg.length > 3 || msg.length < 3) {
			return null;
		}
		productLineInfoVO.setProductType(productTypeParser(msg[2]));
		productLineInfoVO.setProductCost(productCostParser(msg[1]));
		productLineInfoVO.setProductQuantity(0);
		productLineInfoVO.setOperator(msg[0]);
		return productLineInfoVO;
	}

	/**
	 * @param msg
	 * @return
	 */
	private double productCostParser(String msg) {
		double sp = Double.parseDouble(msg.replaceAll("p", ""));
		if (!msg.contains(".")) {
			sp = Double.valueOf(Double.valueOf(sp) / Double.valueOf(100));
		}

		return sp;
	}

	/**
	 * @param msg
	 * @return
	 */
	private String productTypeParser(String msg) {
		String product = "";
		String productInSingularFormat = msg.substring(0, msg.length() - 1);
		if (msg.endsWith("o")) {
			product = String.format("%soes", productInSingularFormat);
		} else if (msg.endsWith("y")) {
			product = String.format("%sies", productInSingularFormat);
		} else if (msg.endsWith("h")) {
			product = String.format("%shes", productInSingularFormat);
		} else if (!msg.endsWith("s")) {
			product = String.format("%ss", msg);
		} else
			product = String.format("%s", msg);
		return product;
	}
}
