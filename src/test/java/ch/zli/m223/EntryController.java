package ch.zli.m223;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.aayushatharva.brotli4j.decoder.DecoderJNI.Status;

import ch.zli.m223.model.Entry;
import ch.zli.m223.model.StatusEnume;
import ch.zli.m223.model.TimeframeEnume;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDate;

import io.quarkus.test.h2.H2DatabaseTestResource;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestMethodOrder(OrderAnnotation.class)
@TestSecurity(user = "test@example.com", roles = "User")
public class EntryController {

  @Test
  @Order(1)
  public void testIndexEndpoint() {
    given()
      .when().get("/entries")
      .then()
       .statusCode(200)
       .body("size()", is(3));
  }

  @Test
  @Order(2)
  public void testDeleteEndpoint() {
    given()
      .when().delete("/entries/" + 1)
      .then()
        .statusCode(204);
    

    given()
      .when().get("/entries")
      .then()
      .statusCode(200)
      .body("size()", is(2));
  }

  @Test
  @Order(3)
  public void testPostEndpoint() {
    var payload = new Entry( StatusEnume.PENDING, LocalDate.now().plusDays(2), TimeframeEnume.MORNING, null, null);

    given()
      .when().contentType(ContentType.JSON).body(payload).post("/entries")
      .then().
      statusCode(200)
    .body("status", is("PENDING"))
    .body("date", is(payload.getDate().toString()))
    .body("timeframe" , is("MORNING"));
  }

  @Test
  @Order(4)
  public void testPutEndpoint() {
    var payload = new Entry(StatusEnume.ACCEPTED, LocalDate.now().plusDays(1), TimeframeEnume.FULL_DAY, null, null);
    
    given()
      .when().contentType(ContentType.JSON).body(payload).put("/entries/" + 1)
      .then()
      .statusCode(200)
    .body("date", is(payload.getDate().toString()))
    .body("timeframe", is("FULL_DAY"));
  }
}