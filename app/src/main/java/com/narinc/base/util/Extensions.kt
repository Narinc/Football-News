package com.narinc.base.util

import com.google.android.material.datepicker.MaterialDatePicker


fun <S> MaterialDatePicker.Builder<S>.setSelection(pair: Pair<Long, Long>?) {
    if (pair != null) {
        setSelection(Pair(pair.first, pair.second))
    }
}