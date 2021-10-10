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
 *
 */

package com.demo.common;

import com.jfinal.handler.Handler;
import com.jfinal.render.RenderManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 解决跨域请求时，过滤options请求
 */
public class OptionsHandler extends Handler {

    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] booleans) {
        String method = request.getMethod().toLowerCase();
        if (!method.equals("options")) {
            this.next.handle(target, request, response, booleans);
            return ;
        }

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Jwt");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        RenderManager.me().getRenderFactory().getJsonRender().setContext(request, response);
    }

}
