package com.example.presentation.web;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * HTTPリクエストで受け付けた未入力（空文字、空白文字）の String フォームを null にバインドする ControllerAdvice クラス。
 *
 * <p>
 * 空文字および空白文字をすべて null にバインドするため、 Bean Validation の以下の3つのアノテーションはすべて同じ扱いになる。
 * <ul>
 * <li>{@link javax.validation.constraints.NotNull NotNull}</li>
 * <li>{@link javax.validation.constraints.NotEmpty NotEmpty}</li>
 * <li>{@link javax.validation.constraints.NotBlank NotBlank}</li>
 * </ul>
 * </p>
 *
 */
@ControllerAdvice
public class EmptyToNullControllerAdvice {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
