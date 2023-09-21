package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;

public class SpartanTestBase {

    public Logger log = LogManager.getLogger(this.getClass());
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://52.200.11.44:8000";
    }


}
