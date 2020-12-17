package com.kelvin.dbmessage.dbmessageconsistent.consistent;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="EVENT_FOO")
@Data
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FooEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fooName;

    private String fooValue;
}
