package com.commerce.cdbookstore.util;

import java.io.Serializable;
 
public interface NumberGenerator extends Serializable {

    // ======================================
    // =          Business methods          =
    // ======================================

    String generateNumber();
}