package com.lobster.common.core;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class DocumentationConfig {

}
    /*
    @Component
    @Primary
    public class DocumentationConfig implements SwaggerResourcesProvider {

        @Override
        public List<SwaggerResource> get() {
            List resources = new ArrayList<>();
            //aigou网关前缀,employee网关路径   ；swagger的路径 ，以后增加了接口就在这配置就ok
            resources.add(swaggerResource("用户系统", "/xuxu/user/v2/api-docs", "2.0"));
            resources.add(swaggerResource("商品系统", "/xuxu/product/v2/api-docs", "2.0"));
            resources.add(swaggerResource("基础系统", "/xuxu/common/v2/api-docs", "2.0"));
            return resources;
        }

        private SwaggerResource swaggerResource(String name, String location, String version) {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion(version);
            return swaggerResource;
        }

    }
    */

