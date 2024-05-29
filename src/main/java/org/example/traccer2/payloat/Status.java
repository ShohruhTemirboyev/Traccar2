package org.example.traccer2.payloat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.traccer2.entity.Message;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    private Integer code;

    private String message_uz;
    private String message_eng;
    private String message_ru;


}
