package io.quarkus.ts.sqldb.compatibility;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class AbstractSqlDatabaseIT {

    private static final int EXPECTED_SIZE = 7;

    @Test
    @Order(1)
    public void getAll() {
        given()
                .get("/book")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("", hasSize(EXPECTED_SIZE));
    }

    @Test
    @Order(2)
    public void get() {
        given()
                .get("/book/7")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("title", equalTo("Perdido Street Station"))
                .body("author", equalTo("China Miéville"));
    }
}
