package guru.qa.hwtask;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.hwtask.model.PersonMan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonJsonParsingTest {
    ClassLoader cl = JacksonJsonParsingTest.class.getClassLoader();

    @DisplayName("Checking the contents JSON file personMan.json")
    @Test
    void jacksonJsonParsingTest() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        try (
                InputStream stream = cl.getResourceAsStream("hwtask/personMan.json");
                InputStreamReader reader = new InputStreamReader(stream)
        ) {
            JsonNode jsonNode = mapper.readValue(reader, JsonNode.class);
            assertThat(jsonNode.get("firstName").asText()).isEqualTo("Joe");
            assertThat(jsonNode.get("lastName").asText()).isEqualTo("Jackson");
            assertThat(jsonNode.get("gender").asText()).isEqualTo("male");
            assertThat(jsonNode.get("age").asText()).isEqualTo("28");
            assertThat(jsonNode.get("address").get("streetAddress").asText()).isEqualTo("101");
            assertThat(jsonNode.get("address").get("city").asText()).isEqualTo("San Diego");
            assertThat(jsonNode.get("address").get("state").asText()).isEqualTo("CA");
            assertThat(jsonNode.get("phoneNumbers").findValue("type").asText()).isEqualTo("home");
            assertThat(jsonNode.get("phoneNumbers").findValue("number").asText()).isEqualTo("7349282382");
        }
    }

    @DisplayName("Checking the contents JSON file with model PersonMan.class")
    @Test
    void jacksonJsonParsingImprovedTest() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        try (
                InputStream stream = cl.getResourceAsStream("hwtask/personMan.json");
                InputStreamReader reader = new InputStreamReader(stream)
        ) {
            PersonMan personMan = mapper.readValue(reader, PersonMan.class);
            assertThat(personMan.firstName).isEqualTo("Joe");
            assertThat(personMan.lastName).isEqualTo("Jackson");
            assertThat(personMan.gender).isEqualTo("male");
            assertThat(personMan.age).isEqualTo("28");
            assertThat(personMan.address.streetAddress).isEqualTo("101");
            assertThat(personMan.address.city).isEqualTo("San Diego");
            assertThat(personMan.address.state).isEqualTo("CA");
            assertThat(personMan.phoneNumbers.get(0).type).isEqualTo("home");
            assertThat(personMan.phoneNumbers.get(0).number).isEqualTo("7349282382");
        }
    }
}
