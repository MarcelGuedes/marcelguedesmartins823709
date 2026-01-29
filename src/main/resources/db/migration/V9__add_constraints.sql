-- As primary keys já foram definidas nos CREATE TABLE.
-- Aqui adicionamos apenas os relacionamentos que ainda não existem.

DO $$
BEGIN
    -- Adiciona FK album_id → albums.id se não existir
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'artist_album_album_id_fkey'
    ) THEN
        ALTER TABLE public.artist_album
            ADD CONSTRAINT artist_album_album_id_fkey
            FOREIGN KEY (album_id) REFERENCES public.albums(id);
    END IF;

    -- Adiciona FK artist_id → artists.id se não existir
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'artist_album_artist_id_fkey'
    ) THEN
        ALTER TABLE public.artist_album
            ADD CONSTRAINT artist_album_artist_id_fkey
            FOREIGN KEY (artist_id) REFERENCES public.artists(id);
    END IF;
END$$;
