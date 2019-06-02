/**
 * 
 */
package com.product.VO;

/**
 * @author smita.banerjee
 *
 */
public class ProductAccumulationVO {
	private String productType;
	private double totalProductCost;
	private int totalProductQuantity;

	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType
	 *            the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return the totalProductCost
	 */
	public double getTotalProductCost() {
		return totalProductCost;
	}

	/**
	 * @param totalProductCost
	 *            the totalProductCost to set
	 */
	public void setTotalProductCost(double totalProductCost) {
		this.totalProductCost = totalProductCost;
	}

	
	/**
	 * @return the totalProductQuantity
	 */
	public int getTotalProductQuantity() {
		return totalProductQuantity;
	}

	/**
	 * @param totalProductQuantity
	 *            the totalProductQuantity to set
	 */
	public void setTotalProductQuantity(int totalProductQuantity) {
		this.totalProductQuantity = totalProductQuantity;
	}

}
