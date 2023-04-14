package loadtestexample;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class GetUsersList extends Simulation {

  {
    HttpProtocolBuilder httpProtocol = http
      .baseUrl("http://localhost:3000")
      .inferHtmlResources()
      .acceptHeader("*/*")
      .acceptEncodingHeader("gzip, deflate")
      .acceptLanguageHeader("es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
      .userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/111.0");
    
    Map<CharSequence, String> headers_0 = new HashMap<>();
    headers_0.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
    headers_0.put("Upgrade-Insecure-Requests", "1");
    
    Map<CharSequence, String> headers_1 = new HashMap<>();
    headers_1.put("Accept", "image/avif,image/webp,*/*");
    
    Map<CharSequence, String> headers_2 = new HashMap<>();
    headers_2.put("Origin", "http://localhost:3000");
    
    Map<CharSequence, String> headers_4 = new HashMap<>();
    headers_4.put("Access-Control-Request-Headers", "content-type");
    headers_4.put("Access-Control-Request-Method", "POST");
    headers_4.put("Origin", "http://localhost:3000");
    
    Map<CharSequence, String> headers_5 = new HashMap<>();
    headers_5.put("Content-Type", "application/json");
    headers_5.put("Origin", "http://localhost:3000");
    
    Map<CharSequence, String> headers_6 = new HashMap<>();
    headers_6.put("If-None-Match", "W/\"2-l9Fw4VUO7kr8CvBlt4zaMCqXZ0w\"");
    headers_6.put("Origin", "http://localhost:3000");
    
    String uri1 = "localhost";

    ScenarioBuilder scn = scenario("RecordedSimulation")
      .exec(
        http("request_0")
          .get("/")
          .headers(headers_0)
          .resources(
            http("request_1")
              .get("/static/media/logo.6ce24c58023cc2f8fd88fe9d219db6c6.svg")
              .headers(headers_1),
            http("request_2")
              .get("http://" + uri1 + ":5000/api/users/list")
              .headers(headers_2),
            http("request_3")
              .get("/logo192.png")
              .headers(headers_1)
          )
      )
      .pause(4)
      .exec(
        http("request_4")
          .options("http://" + uri1 + ":5000/api/users/add")
          .headers(headers_4)
          .resources(
            http("request_5")
              .post("http://" + uri1 + ":5000/api/users/add")
              .headers(headers_5)
              .body(RawFileBody("test1/recordedsimulation/0005_request.txt")),
            http("request_6")
              .get("http://" + uri1 + ":5000/api/users/list")	
              .headers(headers_6)
          )
      );

	  setUp(scn.injectOpen(constantUsersPerSec(30).during(15))).protocols(httpProtocol);
  }
}