package com.hisoybean.plugin.factory.property

import com.hisoybean.plugin.base.HBBasePlugin
import com.hisoybean.plugin.factory.NormalConfigProperty

class SyncWithCleanGradleCache: NormalConfigProperty() {
    override fun apply(value: Any) {
         HBBasePlugin.sProject.configurations.all {
//             af
         }
    }
}