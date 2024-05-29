package org.example.traccer2.payloat;

import lombok.Data;
import org.example.traccer2.entity.Message;

@Data
public class Respons {

    private Object data;
    private Status status;

    public void setStatus(Message message) {
        status=new Status(
                message.getCode(),
                message.getMessage_uz(),
                message.getMessage_eng(),
                message.getMessage_ru()   );
    }
}
