CREATE TABLE public.artist_album (
    album_id BIGINT NOT NULL,
    artist_id BIGINT NOT NULL,
    PRIMARY KEY (album_id, artist_id),
    FOREIGN KEY (album_id) REFERENCES public.albums(id),
    FOREIGN KEY (artist_id) REFERENCES public.artists(id)
);
