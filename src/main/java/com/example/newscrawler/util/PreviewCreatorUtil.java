package com.example.newscrawler.util;

import com.example.newscrawler.model.Source;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PreviewCreatorUtil {

    private static final String HTML = "html";
    private static final String RSS = "rss";

    private static final String IMAGE_TEMPLATE =
            "<figure >\n" +
                    "   <img >\n" +
                    "</figure>\n";
    private static final String VIDEO_TEMPLATE =
            "<figure >\n" +
                    "   <video autoplay=\"\">\n" +
                    "       <source >\n" +
                    "   </video>\n" +
                    "</figure>\n";

    public static String createPreview(Element element, Source source) {
        Elements previewElements = element.select(source.getPreviewTag());
        if (previewElements.isEmpty()) return "";
        switch (source.getContentType()) {
            case HTML:
                return createPreviewFromHTML(previewElements, source);
            case RSS:
                return createPreviewFromRSS(previewElements, source);
            default:
                return ""; // todo: log warn - ne robit iz za nevernogo tipa kek
        }
    }

    private static String createPreviewFromHTML(Elements elements, Source source) {
        Elements figures = elements.select("figure");
        StringBuilder previewResult = new StringBuilder();
        for (Element figure : figures) {
            Elements images = figure.select(source.getImagePreviewTag());
            Elements videos = figure.select(source.getVideoPreviewTag());
            if (images.size() > 0) {
                Element image = images.first();
                String src = image.attr(source.getImagePreviewAttribute());
                Element newFigure = Jsoup.parse(IMAGE_TEMPLATE).selectFirst("figure");
                newFigure.select("img").
                        attr("src", src).
                        attr("alt", "").
                        attr("style", "width: 100%");
                newFigure.attr("style", "position: relative; display: block; width: 80%");
                previewResult.append(newFigure.toString()).append("\n");
            } else if (videos.size() > 0) {
                String src = videos.first().attr(source.getVideoPreviewAttribute());
                Element newFigure = Jsoup.parse(VIDEO_TEMPLATE).selectFirst("figure");
                newFigure.select("source").attr("src", src);
                newFigure.select("video").attr("autoplay");
                newFigure.select("video").attr("style", "width: 100%");
                newFigure.attr("style", "position: relative; display: block; width: 80%");
                newFigure.select("video").attr("controls", "controls");
                newFigure.select("video").attr("preload", "auto");
                previewResult.append(newFigure.toString()).append("\n");
            }
        }
        return previewResult.toString();
    }

    private static String createPreviewFromRSS(Elements elements, Source source) {

        StringBuilder previewResult = new StringBuilder();
        Element newFigure = Jsoup.parse(IMAGE_TEMPLATE).selectFirst("figure");
        newFigure.select("img").
                attr("src", elements.attr(source.getImagePreviewAttribute())).
                attr("alt", "").
                attr("style", "width: 100%");
        newFigure.attr("style", "position: relative; display: block; width: 80%");
        previewResult.append(newFigure.toString()).append("\n");
        return previewResult.toString();
    }

}
