CREATE TABLE quotes
  (
      quote_id    SERIAL PRIMARY KEY,
      text        varchar(300) NOT NULL,
      author_name varchar(60) NOT NULL,
      used_times  varchar(60) NOT NULL

  );

CREATE TABLE images
(
    image_id      SERIAL PRIMARY KEY,
    content_type  varchar(255),
    name          varchar(255),
    image         oid

);

CREATE TABLE used_pixabay_images
(
    id          SERIAL PRIMARY KEY,
    pixabay_id  varchar(60) NOT NULL
);

CREATE TABLE current_quote
(
    quote_id   integer NOT NULL REFERENCES quotes (quote_id)

);

CREATE TABLE current_image
(
    image_id   integer NOT NULL REFERENCES images (image_id)
);
