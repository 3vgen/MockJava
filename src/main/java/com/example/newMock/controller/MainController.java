package com.example.newMock.controller;

import com.example.newMock.model.RequestDTO;
import com.example.newMock.model.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
public class MainController {
    private final BigDecimal LIMIT_US = BigDecimal.valueOf(5000);
    private final BigDecimal LIMIT_EU = BigDecimal.valueOf(3500);
    private final BigDecimal LIMIT_RUB = BigDecimal.valueOf(12121);
    private Logger log = LoggerFactory.getLogger(MainController.class);

    private ObjectMapper mapper = new ObjectMapper();

    @PostMapping(
            value = "info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalances(@RequestBody RequestDTO requestDTO){
        try{
            String clientID = requestDTO.getClientId();
            char firstDigit = clientID.charAt(0);
            BigDecimal maxLimit;
            String currency;
            String RqUID = requestDTO.getRqUID();

            if (firstDigit == '8'){
                maxLimit = LIMIT_US;
                currency = "US";
            }

            else if(firstDigit == '9') {

                maxLimit = LIMIT_EU;
                currency = "EU";

            }

            else{
                maxLimit = LIMIT_RUB;
                currency = "RUB";

            }

            BigDecimal balance = BigDecimal.valueOf(
                            ThreadLocalRandom.current().nextDouble()
                    ).multiply(maxLimit)
                    .setScale(2, RoundingMode.HALF_UP);

            long delayMs = ThreadLocalRandom.current().nextLong(1000, 2001);
            Thread.sleep(delayMs);

            ResponseDTO responseDTO = new ResponseDTO(
                    RqUID,
                    clientID,
                    requestDTO.getAccount(),
                    currency,
                    balance,
                    maxLimit
            );

            log.info("*************** Запрос RequestDTO *******************" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.info("*************** Ответ ResponseDTO *******************" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));
            return responseDTO;
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
