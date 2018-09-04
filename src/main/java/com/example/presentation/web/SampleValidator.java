package com.example.presentation.web;

import java.time.LocalDate;
import java.time.YearMonth;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.presentation.web.SampleController.SampleForm;

@Component
public class SampleValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SampleForm.class.equals(clazz);
    }

    /*
     * yearMonth と localDate の年月が同じであればOKという謎バリデーション
     */
    @Override
    public void validate(Object target, Errors errors) {
        // いずれかが null の場合はバリデーションしない
        SampleForm form = (SampleForm) target;

        YearMonth ym = form.getYearMonth();
        LocalDate ld = form.getLocalDate();

        if (ym == null || ld == null) {
            return;
        }

        if (!ym.equals(YearMonth.from(ld))) {
            errors.rejectValue("yearMonth", "SampleValidation.sampleForm.yearMonth", "yearMonth と localDate の年月が一致しません");
            errors.rejectValue("localDate", "SampleValidation.sampleForm.localDate", "");
        }
    }
}
