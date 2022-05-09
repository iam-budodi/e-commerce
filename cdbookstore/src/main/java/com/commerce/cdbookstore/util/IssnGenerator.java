package com.commerce.cdbookstore.util;

import java.util.Random;
 

@EightDigits
public class IssnGenerator implements NumberGenerator {

    // ======================================
    // =          Business methods          =
    // ======================================

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String generateNumber() {
        return "8-" + new Random().nextInt() / 1000;
    }
}