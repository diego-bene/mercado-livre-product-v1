package br.com.mercadolivre.model;

public enum PurchaseStatus {
	CREATED,     // The purchase has been initiated but not yet completed
	CANCELED,    // The purchase was canceled by the user or system
	COMPLETED    // The purchase was successfully completed
}
