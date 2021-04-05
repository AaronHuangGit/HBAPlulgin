package com.hisoybean.plugin.factory.property

import com.hisoybean.plugin.base.HBBasePlugin
import com.hisoybean.plugin.constants.Plugins
import com.hisoybean.plugin.factory.NormalConfigProperty

class MavenPublish: NormalConfigProperty() {
    override fun apply(value: Any) {
         HBBasePlugin.sProject.plugins.apply(Plugins.MAVEN)
        //todo 添加自动上传maven仓库代码
    }
}