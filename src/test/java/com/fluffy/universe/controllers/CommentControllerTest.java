package com.fluffy.universe.controllers;

import com.fluffy.universe.models.Comment;
import com.fluffy.universe.models.User;
import com.fluffy.universe.services.CommentService;
import com.fluffy.universe.utils.SessionUtils;
import io.javalin.Javalin;
import io.javalin.core.validation.Validator;
import io.javalin.http.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

class CommentControllerTest {
    private CommentController testable;

    @BeforeEach
    void init() {
        testable = new CommentController(mock(Javalin.class));
    }

    @Test
    void shouldStoreCommentAndRedirectToPost() {
        // Given
        Context mockContext = mock(Context.class);
        when(mockContext.formParam("parent-id")).thenReturn(null);
        when(mockContext.formParam("post-id")).thenReturn("1");
        when(mockContext.formParam("description")).thenReturn("Test comment");
        when(mockContext.formParamAsClass("post-id", Integer.class)).thenReturn(new Validator<>("10", Integer.class, "post-id"));
        User user = User.builder()
                .email("email")
                .firstName("firstname")
                .lastName("lastname")
                .birthday("1000-10-10")
                .build();

        MockedStatic<SessionUtils> mockedStatic = mockStatic(SessionUtils.class);
        mockedStatic.when(() -> SessionUtils.getCurrentUser(any())).thenReturn(user);
        MockedStatic<CommentService> serviceMockedStatic = mockStatic(CommentService.class);


        // When
        testable.store(mockContext);

        // Then
        verify(mockContext, times(1)).formParam("parent-id");
        verify(mockContext, times(1)).formParamAsClass("post-id", Integer.class);
        verify(mockContext, times(1)).formParam("description");
        verify(mockContext, times(1)).redirect("/posts/10");
        mockedStatic.close();
        serviceMockedStatic.close();
    }
}