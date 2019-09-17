package com.example.JacksonAnnotations;

import com.example.JacksonAnnotations.beans.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class JacksonAnnotationsApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void ObjectMapperReader() throws JsonParseException, IOException, JsonMappingException {
        ObjectMapper objectMapper = new ObjectMapper();
        final User user =
                objectMapper.
                        readValue("{\"id\":1,\"firstName\":\"Dummy\",\"lastName\":\"User\",\"emailId\":\"dummy.user@mail.com\",\"password\":\"NONE\"}"
                                , User.class);

        Assert.assertEquals("Dummy", user.getFirstName());
    }

    @Test
    public void ObjectMapperReaderError() throws JsonParseException, IOException, JsonMappingException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            final User user =
                    objectMapper.
                            readValue("{\"id\":1,\"firstName\":\"Dummy\",\"lastName\":\"User\",\"emailId1\":\"dummy.user@mail.com\",\"password\":\"NONE\"}"
                                    , User.class);
            Assert.assertEquals("Dummy", user.getFirstName());
        } catch (Exception ex) {
            /**
             * @JsonIgnoreProperties(ignoreUnknown = true)
             * input contains "emailId1" which is not valid for json ,
             * exception will be thrown if above annotation is not added on top of User class
             */
            ex.printStackTrace();
        }
    }
}
