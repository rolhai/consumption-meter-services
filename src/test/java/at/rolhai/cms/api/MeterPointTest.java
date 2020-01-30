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
package at.rolhai.cms.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import at.rolhai.cms.http.HttpVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.WebClient;

@RunWith(VertxUnitRunner.class)
public class MeterPointTest {

    private Vertx vertx;

    private WebClient webClient;

    @Before
    public void initTest(TestContext testContext) {
        vertx = Vertx.vertx();
        webClient = WebClient.create(vertx);
        vertx.deployVerticle(new HttpVerticle());
    }

    @After
    public void endTest() {
        vertx.close();
    }

    @Test
    public void testPostMeterPoint(TestContext testContext) {
        Async async = testContext.async();

        JsonObject meterpoint = new JsonObject()
                .put("type", "electricity")
                .put("subtype", "day")
                .put("date", "2020-01-30")
                .put("value", "12345.67")
                .put("unit", "kWh");

        webClient.post(8080, "localhost", "/api/meterpoints")
        .putHeader("content-type", "application/json")
        .sendJson(meterpoint, ar -> {
            if (ar.succeeded()) {
                testContext.assertEquals(201, ar.result().statusCode());
                JsonObject returnedJson = ar.result().bodyAsJsonObject();
                testContext.assertEquals("electricity", returnedJson.getString("type"));
                testContext.assertEquals("day", returnedJson.getString("subtype"));
                testContext.assertEquals(12345.67, returnedJson.getDouble("value"));
                testContext.assertEquals("2020-01-30", returnedJson.getString("date"));
                testContext.assertEquals("kWh", returnedJson.getString("unit"));
                async.complete();
            }
            else {
                testContext.assertTrue(ar.succeeded());
                async.complete();
            }
        });
    }
}
