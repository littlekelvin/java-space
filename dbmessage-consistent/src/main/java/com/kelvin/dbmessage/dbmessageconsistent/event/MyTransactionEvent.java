package com.kelvin.dbmessage.dbmessageconsistent.event;

import com.kelvin.dbmessage.dbmessageconsistent.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyTransactionEvent extends BaseEvent {
    private String name;
}
