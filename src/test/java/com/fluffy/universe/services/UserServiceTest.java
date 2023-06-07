package com.fluffy.universe.services;

import com.fluffy.universe.models.User;
import com.fluffy.universe.utils.DataSource;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.sql2o.Connection;
import org.sql2o.Query;

import static com.fluffy.universe.services.UserService.encodePassword;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Test
    void shouldGetUserByEmail() {
        // Given
        String email = "test@example.com";
        User expectedUser = new User();
        Connection mockConnection = mock(Connection.class);
        Query mockQuery = mock(Query.class);
        when(mockConnection.createQuery(anyString())).thenReturn(mockQuery);
        when(mockQuery.addParameter(anyString(), anyString())).thenReturn(mockQuery);
        when(mockQuery.executeAndFetchFirst(User.class)).thenReturn(expectedUser);
        MockedStatic<DataSource> mockedDataSource = mockStatic(DataSource.class);
        mockedDataSource.when(DataSource::getConnection).thenReturn(mockConnection);

        // When
        User resultUser = UserService.getUserByEmail(email);

        // Then
        assertThat(resultUser).isEqualTo(expectedUser);
        verify(mockConnection).createQuery(any());
        verify(mockQuery).addParameter("email", email);
        verify(mockQuery).executeAndFetchFirst(User.class);
        mockedDataSource.close();
    }

    @Test
    void shouldGetUserByResetPasswordToken() {
        // Given
        String resetPasswordToken = "abcd1234";
        User expectedUser = new User();
        Connection mockConnection = mock(Connection.class);
        Query mockQuery = mock(Query.class);
        when(mockConnection.createQuery(anyString())).thenReturn(mockQuery);
        when(mockQuery.addParameter(anyString(), anyString())).thenReturn(mockQuery);
        when(mockQuery.executeAndFetchFirst(User.class)).thenReturn(expectedUser);
        MockedStatic<DataSource> mockedDataSource = mockStatic(DataSource.class);
        mockedDataSource.when(DataSource::getConnection).thenReturn(mockConnection);

        // When
        User resultUser = UserService.getUserByResetPasswordToken(resetPasswordToken);

        // Then
        assertThat(resultUser).isEqualTo(expectedUser);
        verify(mockConnection, times(1)).createQuery(any());
        verify(mockQuery, times(1)).addParameter(eq("resetPasswordToken"), eq(resetPasswordToken));
        verify(mockQuery, times(1)).executeAndFetchFirst(eq(User.class));
        mockedDataSource.close();
    }

    @Test
    void shouldSaveUser() {
        // Given
        User user = new User();
        user.setId(null);
        Connection mockConnection = mock(Connection.class);
        Query mockQuery = mock(Query.class);
        when(mockConnection.createQuery(anyString())).thenReturn(mockQuery);
        when(mockConnection.getKey(Integer.class)).thenReturn(10);
        when(mockQuery.bind(any())).thenReturn(mockQuery);
        when(mockQuery.executeUpdate()).thenReturn(mockConnection);
        MockedStatic<DataSource> mockedDataSource = mockStatic(DataSource.class);
        mockedDataSource.when(DataSource::getConnection).thenReturn(mockConnection);

        // When
        UserService.saveUser(user);

        // Then
        assertThat(user.getId()).isNotNull();
        verify(mockConnection, times(1)).createQuery(any());
        verify(mockQuery, times(1)).bind(eq(user));
        verify(mockQuery, times(1)).executeUpdate();
        mockedDataSource.close();
    }

    @Test
    void shouldEncodePassword() {
        // Given
        String password = "password";

        // When
        String result = encodePassword(password);

        // Then
        assertThat(result).isNotEqualTo(password);
    }

    @Test
    void shouldReturnTrueAsCorrectPassword() {
        // Given
        String password = "password";
        String encodedPassword = encodePassword("password");

        // When
        boolean result = UserService.isCorrectPassword(password, encodedPassword);

        // Then
        assertThat(result).isTrue();
    }

}