/**
 * Copyright 2015-2016 Canoo Engineering AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.canoo.dolphin.server.spring;

import com.canoo.dolphin.BeanManager;
import com.canoo.dolphin.server.DolphinSession;
import com.canoo.dolphin.server.context.DolphinSessionProvider;
import com.canoo.dolphin.server.event.DolphinEventBus;
import com.canoo.dolphin.server.servlet.DolphinPlatformBootstrap;
import org.opendolphin.core.server.ServerDolphin;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Provides all Dolphin Platform Beans and Scopes for CDI
 */
@Configuration
public class SpringBeanFactory {

    /**
     * Method to create a spring managed {@link com.canoo.dolphin.impl.BeanManagerImpl} instance in client scope.
     * @return the instance
     */
    @Bean(name="beanManager")
    @ClientScoped
    protected BeanManager createManager() {
        return DolphinPlatformBootstrap.getInstance().getCurrentContext().getBeanManager();
    }

    /**
     * Method to create a spring managed {@link org.opendolphin.core.server.ServerDolphin} instance in client scope.
     * @return the instance
     */
    @Bean(name="serverDolphin")
    @ClientScoped
    protected ServerDolphin createDolphin() {
        return DolphinPlatformBootstrap.getInstance().getCurrentContext().getDolphin();
    }

    @Bean(name="dolphinSession")
    @ClientScoped
    protected DolphinSession createDolphinSession() {
        return DolphinPlatformBootstrap.getInstance().getCurrentDolphinSession();
    }


    /**
     * Method to create a spring managed {@link DolphinEventBus} instance in singleton scope.
     * @return the instance
     */
    @Bean(name="dolphinEventBus")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    protected DolphinEventBus createEventBus() {
        return DolphinPlatformBootstrap.getInstance().getDolphinEventBus();
    }

    @Bean(name="customScopeConfigurer")
    public CustomScopeConfigurer createClientScope() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        configurer.addScope(ClientScope.CLIENT_SCOPE, new ClientScope(new DolphinSessionProvider() {
            @Override
            public DolphinSession getCurrentDolphinSession() {
                return DolphinPlatformBootstrap.getInstance().getCurrentDolphinSession();
            }
        }));
        return configurer;
    }
}
