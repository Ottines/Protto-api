package com.protto.api.domain.person.model.dao;


import com.protto.api.domain.person.model.enums.Regex;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public record PersonCreateRequest(@NotEmpty @Length(min = 2, max = 50) String firstName,
                                  @NotEmpty @Length(min = 2, max = 50) String lastName,
                                  @Pattern(regexp = Regex.EMAIL) String email,
                                  @Pattern(regexp = Regex.PASSWORD) String password) {
}
