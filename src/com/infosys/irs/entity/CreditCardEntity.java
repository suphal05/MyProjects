/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infosys.irs.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "CREDITCARD_DETAILS")
public class CreditCardEntity {
	@Id
	@NotEmpty(message = "Please enter the Card Number")
	private String cardNumber;
	@NotEmpty(message = "Please enter the Card Holder Name")
	private String cardHolderName;
	@NotEmpty(message = "Please enter cvv")
	private String cvv;
	@NotEmpty(message = "Please enter the pin")
	private String securePin;
	@NotEmpty(message = "Please enter the Expiry Month")
	private String expiryMonth;
	@NotEmpty(message = "Please enter the Expiry Year")
	private String expiryYear;
	private String totalBill;

	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber
	 *            the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return the cardHolderName
	 */
	public String getCardHolderName() {
		return cardHolderName;
	}

	/**
	 * @param cardHolderName
	 *            the cardHolderName to set
	 */
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	/**
	 * @return the cvv
	 */
	public String getCvv() {
		return cvv;
	}

	/**
	 * @param cvv
	 *            the cvv to set
	 */
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	/**
	 * @return the apin
	 */
	public String getSecurePin() {
		return securePin;
	}

	/**
	 * @param apin
	 *            the apin to set
	 */
	public void setSecurePin(String apin) {
		this.securePin = apin;
	}

	/**
	 * @return the expiryMonth
	 */
	public String getExpiryMonth() {
		return expiryMonth;
	}

	/**
	 * @param expiryMonth
	 *            the expiryMonth to set
	 */
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	/**
	 * @return the expiryYear
	 */
	public String getExpiryYear() {
		return expiryYear;
	}

	/**
	 * @param expiryYear
	 *            the expiryYear to set
	 */
	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}

	/**
	 * @return the totalBill
	 */
	public String getTotalBill() {
		return totalBill;
	}

	/**
	 * @param totalBill
	 *            the totalBill to set
	 */
	public void setTotalBill(String totalBill) {
		this.totalBill = totalBill;
	}

}
