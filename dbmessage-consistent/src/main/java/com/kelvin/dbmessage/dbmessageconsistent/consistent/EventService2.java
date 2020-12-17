package com.kelvin.dbmessage.dbmessageconsistent.consistent;

import com.kelvin.dbmessage.dbmessageconsistent.event.MyTransactionEvent2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Service
public class EventService2 {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit2(MyTransactionEvent2 event) {
        log.error("after commit then send event2 {}", event);
        log.error("after commit then send event2 {}", event.getName());
    }
}
