package com.challenge.financing.Request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Contact is required")
    private String contact;
}
