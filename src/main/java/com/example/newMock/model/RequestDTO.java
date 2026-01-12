package com.example.newMock.model;
import lombok.*;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
@Data
public class RequestDTO {
    private String rqUID;
    String name = "Angelina";
    String text = "3214353647";
    int num1 = 20;
    int num2 = 30;
    int sum = num1 + num2;
    double weight = 89.4343;
    private String clientId;
    private String account;
    private String openDate;
    private String closeDate;
}
