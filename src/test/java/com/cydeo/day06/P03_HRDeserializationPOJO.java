package com.cydeo.day06;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Region;
import com.cydeo.utilities.HrTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;

public class P03_HRDeserializationPOJO extends HrTestBase {

    @DisplayName("GET regions to desserializate to POJO - LOMBOK -JSON PROPERTY")
    @Test
    public void test1(){
        JsonPath jsonPath = get("/regions")
                .then().statusCode(200)
                .extract().jsonPath();

        //get first region from items array and convert it to Region class
        Region region1 = jsonPath.getObject("items[0]", Region.class);

        System.out.println("region1 = " + region1);

//        System.out.println("region1.getRegion_id() = " + region1.getRegion_id());
//        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());

        System.out.println("region1.getRegionName() = " + region1.getRegionName());
        System.out.println("region1.getRegionId() = " + region1.getRegionId());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());

    }

    @DisplayName("GET employee to deserialization to POJO with only required fields")
    @Test
    public void test2(){

        JsonPath jsonPath = get("/employees")
                .then().statusCode(200)
                .extract().jsonPath();

        Employee employee1 = jsonPath.getObject("items[0]", Employee.class);

        System.out.println("employee1 = " + employee1);


    }



        /*
    TASK

    Given accept is application/json
    When send request  to /regions endpoint
    Then status should be 200
            verify that region ids are 1,2,3,4
            verify that regions names Europe ,Americas , Asia, Middle East and Africa
            verify that count is 4
        -- Create Regions POJO
        -- And ignore field that you dont need
     */



    /*
        ----LOMBOK-----
    -It is a library to create boiler platers code(getter,setter,contrr,toString etc) with annotations
    -It will generate boiler plate codes when we need auomatilcally without typing getter setter manually
    so we can use @Getter,@Setter and @ToString for working with pojos, because setters will be used by Jackson to deserialize values of JSON response

    or @Data
    will cover Getter,setter,toString and more, so we can directly use @Data annotation
     */
}
