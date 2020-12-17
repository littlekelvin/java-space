package com.kelvin.dbmessage.dbmessageconsistent.event;

import org.springframework.context.ApplicationEvent;

public class MyTransactionEvent2 {
    private String name;

    public MyTransactionEvent2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
