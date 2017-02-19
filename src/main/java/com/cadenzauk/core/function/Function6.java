/*
 * Copyright (c) 2017 Cadenza United Kingdom Limited.
 *
 * All rights reserved.  May not be used without permission.
 */

package com.cadenzauk.core.function;

import java.io.Serializable;

@FunctionalInterface
public interface Function6<T1, T2, T3, T4, T5, T6, R> extends Serializable {
    R apply(T1 p1, T2 p2, T3 p3, T4 p4, T5 p5, T6 p6);
}
