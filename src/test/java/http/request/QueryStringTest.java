package http.request;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryStringTest {
    @Test
    void of() throws UnsupportedEncodingException {
        QueryString queryString = QueryString.parse("userId=javajigi&password=password&name=JaeSung");

        assertThat(queryString.getValue("userId")).isEqualTo("javajigi");
        assertThat(queryString.getValue("password")).isEqualTo("password");
        assertThat(queryString.getValue("name")).isEqualTo("JaeSung");
    }
}