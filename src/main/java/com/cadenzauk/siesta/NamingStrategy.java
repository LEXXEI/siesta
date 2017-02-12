/*
 * Copyright (c) 2017 Cadenza United Kingdom Limited.
 *
 * All rights reserved.   May not be used without permission.
 */

package com.cadenzauk.siesta;

public interface NamingStrategy {
    String tableName(String rowClass);
    String columnName(String fieldName);
}
