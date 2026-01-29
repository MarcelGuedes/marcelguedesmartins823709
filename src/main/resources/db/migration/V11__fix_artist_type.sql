ALTER TABLE public.artists ALTER COLUMN type SET DEFAULT 'singer';
UPDATE public.artists SET type = 'singer' WHERE type IS NULL;
