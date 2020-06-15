package http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProtocolTest {

    @DisplayName("프로토콜 정보를 이름과 버전으로 파싱한다.")
    @Test
    void test_parsing_protocol_should_pass() {
        // given
        String protocolInfo = "HTTP/1.1";
        // when
        Protocol protocol = Protocol.from(protocolInfo);
        // then
        assertThat(protocol.equals(Protocol.HTTP_V1_1)).isTrue();
    }

    @DisplayName("유효하지 않은 프로토콜명인 경우 IllegalArgumentException 발생")
    @Test
    void test_parsing_invalid_protocol_should_fail() {
        // given
        String protocolInfo = "HTDP/1.1";
        // when
        // then
        assertThatThrownBy(() -> {
            Protocol protocol = Protocol.from(protocolInfo);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효하지 않은 프로토콜버전인 경우 IllegalArgumentException 발생")
    @Test
    void test_parsing_invalid_version_should_fail() {
        // given
        String protocolInfo = "HTTP/0.0";
        // when
        // then
        assertThatThrownBy(() -> {
            Protocol protocol = Protocol.from(protocolInfo);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}