package com.perficient.watson.virtual.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;

public class MainVerticle extends AbstractVerticle {

	@Override
	public void start() {
		vertx.deployVerticle("com.perficient.watson.virtual.demo.WatsonWorkerVerticle",
	            new DeploymentOptions().setWorker(true));
		Router router= Router.router(vertx);
		BridgeOptions options = new BridgeOptions().addOutboundPermitted(new PermittedOptions().setAddress("send-msg")).addInboundPermitted(new PermittedOptions().setAddress("send-msg"));
		router.route("/eventbus/*").handler(SockJSHandler.create(vertx).bridge(options));
		router.route().handler(StaticHandler.create("assets"));
		vertx.createHttpServer().requestHandler(router::accept).listen(8080);
	}
}