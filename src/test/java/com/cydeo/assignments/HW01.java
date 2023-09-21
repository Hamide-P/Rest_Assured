package com.cydeo.assignments;

import com.cydeo.utilities.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class HW01 extends HrTestBase {

    // - Given accept type is Json
    //- When users sends request to /countries/US
    //- Then status code is 200
    //- And Content - Type is application/json
    //- And response contains United States of America

    @DisplayName("GET Request /countries/US")
    @Test
    public void test1() {
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get("/countries/US");

        //- Then status code is 200
        int actualStatusCode = response.statusCode();
        assertEquals(HttpStatus.SC_OK, actualStatusCode);
        System.out.println("actualStatusCode = " + actualStatusCode);

        //- And Content - Type is application/json
        String actualContentType = response.contentType();
        assertEquals("application/json", actualContentType);
        System.out.println("actualContentType = " + actualContentType);

        //- And response contains United States of America
        String actualBody = response.body().asString(); // returns Response body to String
        assertTrue(actualBody.contains("United States of America"));
        System.out.println("actualBody = " + actualBody);

        // another solution
        String actualPath = response.path("country_name");
        assertEquals(actualPath, "United States of America");
        System.out.println("actualPath = " + actualPath);

    }

    // - Given accept type is Json
    //- When users sends request to /employees/1
    //- Then status code is 404

    @DisplayName("GET Request /employees/1")
    @Test
    public void test2() {


        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get("/employees/1");

        // response.prettyPrint();

        //- Then status code is 404
        int actualStatusCode = response.statusCode();
        assertEquals(HttpStatus.SC_NOT_FOUND, actualStatusCode);
        System.out.println("actualStatusCode = " + actualStatusCode);

    }


    //- Given accept type is Json
    //- When users sends request to /regions/1
    //- Then status code is 200
    //- And Content - Type is application/json
    //- And response contains Europe
    //- And header should contains Date
    //- And Transfer-Encoding should be chunked
    @DisplayName("GET Request /employees/1")
    @Test
    public void test3() {

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get("/regions/1");

        //- Then status code is 200
        int actualStatusCode = response.statusCode();
        assertEquals(HttpStatus.SC_OK, actualStatusCode);
        System.out.println("actualStatusCode = " + actualStatusCode);

        //- And Content - Type is application/json
        String actualContentType = response.getContentType();

        assertEquals("application/json", actualContentType);
        System.out.println("actualContentType = " + actualContentType);

        //- And response contains Europe
        String actualBody = response.body().asString(); // returns Response body to String
        assertTrue(actualBody.contains("Europe"));
        System.out.println("actualBody = " + actualBody);

        //- And header should contains Date

        boolean hasHeaderWithDate = response.headers().hasHeaderWithName("Date");
        assertTrue(hasHeaderWithDate);
        System.out.println("hasHeaderWithDate = " + hasHeaderWithDate);


        //- And Transfer-Encoding should be chunked

        String actualTransferEncoding = response.header("Transfer-Encoding");
        assertEquals("chunked", actualTransferEncoding);
        System.out.println("actualTransferEncoding = " + actualTransferEncoding);


    }


}
