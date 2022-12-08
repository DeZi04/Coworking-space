package ch.zli.m223;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;




import ch.zli.m223.model.Member;
import ch.zli.m223.model.RoleEnum;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.inject.Inject;
import io.quarkus.test.h2.H2DatabaseTestResource;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestMethodOrder(OrderAnnotation.class)
@TestSecurity(user = "test@example.com", roles = "Admin")
public class MemberControllerTest {

    @Inject
    IntegrationTestDataService dataService;

  @Test
  @Order(1)
  public void testMemberIndexEndpoint() {
    given()
      .when().get("/members")
      .then()
       .statusCode(200)
       .body("size()", is(3));
  }

  @Test
  @Order(2)
  public void testDeleteMemberEndpoint() {
    given()
      .when().delete("/members/" + 1)
      .then()
        .statusCode(204);
    

    given()
      .when().get("/members")
      .then()
      .statusCode(200)
      .body("size()", is(2));
  }

  @Test
  @Order(3)
  @TestSecurity(user = "test@example.com", roles = "User")
  public void testDeleteEndpointFrobidden() {
    given()
      .when().delete("/members/" + 1)
      .then()
        .statusCode(403);
  }

  @Test
  @Order(4)
  public void testPostEndpoint() {
    var payload = new Member("Marvin", "Zeiter", "marvinzeiter@bluewin.ch", "Zli1234", RoleEnum.MEMBER, null);

    given()
      .when().contentType(ContentType.JSON).body(payload).post("/members")
      .then().
      statusCode(200)
    .body("firstname", is("Marvin"))
    .body("lastname", is("Zeiter"))
    .body("email" , is("marvinzeiter@bluewin.ch"));
  }

  @Test
  @Order(4)
  public void testPutMemberEndpoint() {
    var payload = new Member("Remo", "Waelchli", "remowaelchli@zli.ch", "yeahyeet", RoleEnum.MEMBER, null);
    
    given()
      .when().contentType(ContentType.JSON).body(payload).put("/members/" + 1)
      .then()
      .statusCode(200)
      .body("firstname", is("Remo"))
      .body("lastname", is("Waelchli"))
      .body("email" , is("remowaelchli@zli.ch"));
  }
    
}
