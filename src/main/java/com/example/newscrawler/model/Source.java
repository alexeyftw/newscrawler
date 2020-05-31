package com.example.newscrawler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

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
  @Column(columnDefinition = "TEXT")
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