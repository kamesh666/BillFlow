package com.billflow.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {
    private String name;
    private String phone;
    private String email;
    private String address;
    private String gstNumber;
}
