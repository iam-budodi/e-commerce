package com.commerce.cdbookstore.util;

import java.util.Random;
 

@ThirteenDigits
public class IsbnGenerator implements NumberGenerator {

    // ======================================
    // =          Business methods          =
    // ======================================

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String generateNumber() {
        return "13-" + new Random().nextInt();
    }
}