package com.hisoybean.plugin.listener

import com.hisoybean.plugin.factory.HBAConfigProperty

interface  HBAConfigChangedCallback<R, in T : HBAConfigProperty<R>> {
    fun onChange(hbaConfigProperty: T, value: R)
}