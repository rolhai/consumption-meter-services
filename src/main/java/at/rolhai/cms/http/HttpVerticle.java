/**
 * Copyright (c) 2020 by rolhai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.rolhai.cms.http;

import at.rolhai.cms.api.model.Meterpoint;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * @author rolhai
 */
public class HttpVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) {
        Router baseRouter = Router.router(vertx);
        Router apiRouter = Router.router(vertx);

        baseRouter.route("/").handler(requestHandler -> {
            HttpServerResponse response = requestHandler.response();
            response.putHeader("content-type", "text/plain").end("consumption-meter service is running");
        });

        apiRouter.route("/meterpoints*").handler(BodyHandler.create());
        apiRouter.post("/meterpoints").handler(this::createMeterpoint);
        baseRouter.mountSubRouter("/api", apiRouter);

        vertx.createHttpServer()
        .requestHandler(baseRouter)
        .listen(8080, result -> {
            if (result.succeeded()) {
                startFuture.complete();
            }
            else {
                startFuture.fail(result.cause());
            }
        });
    }

    private void createMeterpoint(RoutingContext routingContext) {
        Meterpoint meterpoint = new Meterpoint();
        meterpoint.type("electricity").subtype("day").value(12345.67).unit("kWh").date("2020-01-30");
        routingContext.response().setStatusCode(201)
        .putHeader("content-type", "application/json, charste=utf-8")
        .end(Json.encodePrettily(meterpoint));
    }
}
