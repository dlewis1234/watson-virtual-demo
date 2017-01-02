package com.perficient.watson.virtual.demo;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.perficient.watson.virtual.demo.WatsonWorkerVerticle;
import static junit.framework.TestCase.fail;

@RunWith(VertxUnitRunner.class)
public class WatsonWorkerVerticleTest {

	private Vertx vertx;

	@Before
	public void setup(TestContext context) {
		vertx = Vertx.vertx();
		vertx.deployVerticle(WatsonWorkerVerticle.class.getName(),
				context.asyncAssertSuccess());
	}

	@After
	public void tearDown(TestContext context) {
		vertx.close(context.asyncAssertSuccess());
	}

	@Test
	public void myAppTest(TestContext context) {
	}
}