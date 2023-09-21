package com.cydeo.assignments;

import com.cydeo.utilities.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class HW02 extends HrTestBase {


    //- Given accept type is Json
    //- Path param value-US
    //- When users sends request to /countries
    //- Then status code is 200
    //- And Content - Type is Json
    //- And country_id is US
    //- And Country_name is United States of America
    //And Region_id is 2

    @DisplayName("GET Request /countries with 'region_id'")
    @Test
    public void test1() {


        Response response = RestAssured.given().accept(ContentType.JSON)
                .and()
                .pathParam("country_id", "US")
                .when()
                .get("/countries/{country_id}");


        //- Then status code is 200
        assertEquals(200, response.statusCode());

        //- And Content - Type is Json
        assertEquals("application/json", response.contentType());

        //- And country_id is US
        assertEquals("US", response.path("country_id"));

        //- And Country_name is United States of America
        assertTrue(response.body().asString().contains("US"));

        //And Region_id is 2
        assertTrue(response.body().asString().contains("2"));

        response.prettyPrint();
    }

    // - Given accept type is Json
    //- Query param value - q={"department_id":80}
    //- When users sends request to /employees
    //- Then status code is 200
    //- And Content - Type is Json
    //- And all job_ids start with 'SA'
    //- And all department_ids are 80
    //- Count is 25

    @DisplayName("GET Request /employees with 'department_id'")
    @Test
    public void test2() {


        Response response = RestAssured.given().accept(ContentType.JSON)
                .and()
                .queryParam("department_id", "80")
                .when()
                .get("/employees");


        //- Then status code is 200
        assertEquals(200, response.statusCode());

        //- And Content - Type is Json
        assertEquals("application/json", response.contentType());

        //- And all job_ids start with 'SA'
        assertTrue(response.body().asString().contains("SA"));


        //- And all department_ids are 80
        //- Count is 25


    }
}
