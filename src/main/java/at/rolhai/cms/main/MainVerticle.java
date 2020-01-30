package at.rolhai.cms.main;

import io.vertx.core.AbstractVerticle;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start() {
    vertx.createHttpServer()
        .requestHandler(req -> req.response().end("Hello Consumption-Meter-Services!"))
        .listen(8080);
  }

}
