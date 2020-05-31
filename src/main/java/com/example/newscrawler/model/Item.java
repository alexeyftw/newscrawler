package com.example.newscrawler.model;

import lombok.*;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "items")
@Data
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String preview;
    private String link;
    @Temporal(TemporalType.TIMESTAMP)
    private Date pubDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_id")
    private Source source;

    @Tolerate
    public Item() {
    }
}
