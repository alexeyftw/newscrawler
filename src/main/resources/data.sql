insert into sources (id, source_name, uri, uri_tag, content_type, news_container_tag, title_tag, description_tag, preview_tag, image_preview_tag, image_preview_attribute, video_preview_tag, video_preview_attribute, pub_date_tag, pub_date_attribute, pub_date_format) values (1, 'tjournal', 'https://tjournal.ru/', '.t-link', 'html', '.feed__item', '.content-header__title', 'div > div.content.content--short > div', 'figure', '.andropov_image','data-image-src', '.andropov_video', 'data-video-mp4', 'time','data-date', 'timestamp');
insert into sources (id, source_name, uri, uri_tag, content_type, news_container_tag, title_tag, description_tag, preview_tag, image_preview_tag, image_preview_attribute,
	video_preview_tag, video_preview_attribute, pub_date_tag, pub_date_attribute, pub_date_format)
values (2, 'Ведомости', 'https://www.vedomosti.ru/rss/news', 'link', 'rss', 'item', 'title', null, 'enclosure', null, 'url',
 null, null, 'pubDate', null, 'E, d MMM yyyy HH:mm:ss Z');