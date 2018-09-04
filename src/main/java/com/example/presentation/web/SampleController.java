package com.example.presentation.web;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.util.validation.Confirm;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SampleController {
    private final SampleValidator sampleValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(sampleValidator);
    }

    @ModelAttribute
    public SampleForm setupForm() {
        return new SampleForm();
    }

    @GetMapping(path = "/")
    public String form(Model model) {
        return "form";
    }

    @PostMapping(path = "/")
    public String validate(@Validated SampleForm sampleForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return form(model);
        }
        redirectAttributes.addFlashAttribute("SUCCESS", "バリデーション成功");
        return "redirect:/";
    }

    @Data
    @Confirm(source = "password", target = "confirmPassword")
    public static class SampleForm {
        @NotNull
        @Length(min = 5, max = 10)
        private String string;

        @NotNull
        @Min(10)
        private Integer integer;

        @NotNull
        @Digits(integer = 2, fraction = 2)
        private BigDecimal bigDecimal;

        private YearMonth yearMonth;

        private LocalDate localDate;

        private String password;

        private String confirmPassword;
    }
}
