package hello.login.web.Session;


import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

class SessionManagerTest {
    SessionManager sessionManager = new SessionManager();

    @Test
    public void test(){
        Member member = new Member();
        MockHttpServletResponse response = new MockHttpServletResponse();

        sessionManager.createSession(member, response);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        Object result = sessionManager.getSession(request);

        assertThat(member).isEqualTo(result);

        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isEqualTo(null);


        //given

        //when

        //then

    }
}