package com.example.newscrawler.model;

import lombok.*;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Entity
@Table(name = "sources")
@Data
@Builder
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sourceName;
    private String uri;
    private String uriTag;
    private String contentType; // rss / html
    private String newsContainerTag;
    private String titleTag;
    @Column(columnDefinition="TEXT")
    private String descriptionTag;
    private String previewTag;
    private String imagePreviewTag;
    private String imagePreviewAttribute;
    private String videoPreviewTag;
    private String videoPreviewAttribute;
    private String pubDateTag;
    private String pubDateAttribute;
    private String pubDateFormat;

    @Tolerate
    public Source() {
    }
}