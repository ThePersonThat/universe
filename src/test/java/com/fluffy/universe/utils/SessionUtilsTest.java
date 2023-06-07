package com.fluffy.universe.utils;


import com.fluffy.universe.models.User;
import io.javalin.http.Context;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SessionUtilsTest {

    @Test
    void shouldReturnCurrentUser() {
        // given
        User expectedUser = buildUser();
        Context context = getMockedContext(SessionKey.USER, expectedUser);

        // when
        User actualUser = SessionUtils.getCurrentUser(context);

        // then
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void shouldReturnCurrentModel() {
        // given
        Map<String, String> expectedMap = Map.of("model", "value");
        Context context = getMockedContext(SessionKey.MODEL, expectedMap);

        // when
        Map<String, Object> actualModel = SessionUtils.getCurrentModel(context);

        // then
        assertThat(actualModel).isEqualTo(expectedMap);
    }

    @Test
    void shouldReturnCurrentServerData() {
        // given
        ServerData expectedServerData = new ServerData();
        Context context = getMockedContext(SessionKey.SERVER_DATA, expectedServerData);

        // when
        ServerData actualServerData = SessionUtils.getCurrentServerData(context);

        // then
        assertThat(actualServerData).isEqualTo(expectedServerData);
    }

    private static User buildUser() {
        return User.builder()
                .firstName("Oleksii")
                .lastName("Haidabrus")
                .email("oleksiihaidabrus@gmail.com")
                .address("Sumy")
                .build();
    }


    private static <T> Context getMockedContext(String key, T value) {
        Context context = mock(Context.class);
        when(context.sessionAttribute(key)).thenReturn(value);

        return context;
    }
}