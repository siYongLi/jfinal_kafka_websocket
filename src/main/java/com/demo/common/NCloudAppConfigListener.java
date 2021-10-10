/*
 * Copyright (c) 2015-2018, Eric Huang 黄鑫 (ninemm@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.demo.common;

import com.demo.kafka.KafkaService;
import com.jfinal.config.Constants;
import com.jfinal.config.Interceptors;
import com.jfinal.config.Routes;
import com.jfinal.template.Engine;
import io.jboot.aop.jfinal.JfinalHandlers;
import io.jboot.aop.jfinal.JfinalPlugins;
import io.jboot.core.listener.JbootAppListenerBase;


/**
 * 应用程序初始化入口
 */
public class NCloudAppConfigListener extends JbootAppListenerBase {

    @Override
    public void onConstantConfig(Constants constants) {
    }

    @Override
    public void onRouteConfig(Routes routes) {
    }

    @Override
    public void onEngineConfig(Engine engine) {

    }

    @Override
    public void onInterceptorConfig(Interceptors interceptors) {

    }


    @Override
    public void onPluginConfig(JfinalPlugins plugins) {

    }

    @Override
    public void onHandlerConfig(JfinalHandlers handlers) {
        handlers.add(new OptionsHandler());
    }

    @Override
    public void onStart() {
        KafkaService.getSingleton();
    }

    @Override
    public void onStop() {
        KafkaService.getSingleton().close();
    }

    @Override
    public void onStartBefore() {

    }
}
