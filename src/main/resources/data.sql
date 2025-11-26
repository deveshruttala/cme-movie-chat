-- Movies
INSERT INTO movies (title, release_year, genre, director) VALUES ('The Matrix', 1999, 'Sci-Fi', 'Lana Wachowski, Lilly Wachowski');
INSERT INTO movies (title, release_year, genre, director) VALUES ('Inception', 2010, 'Sci-Fi', 'Christopher Nolan');
INSERT INTO movies (title, release_year, genre, director) VALUES ('The Dark Knight', 2008, 'Action', 'Christopher Nolan');
INSERT INTO movies (title, release_year, genre, director) VALUES ('Interstellar', 2014, 'Sci-Fi', 'Christopher Nolan');
INSERT INTO movies (title, release_year, genre, director) VALUES ('Parasite', 2019, 'Thriller', 'Bong Joon-ho');
INSERT INTO movies (title, release_year, genre, director) VALUES ('Avengers: Endgame', 2019, 'Action', 'Anthony Russo, Joe Russo');
INSERT INTO movies (title, release_year, genre, director) VALUES ('The Godfather', 1972, 'Crime', 'Francis Ford Coppola');
INSERT INTO movies (title, release_year, genre, director) VALUES ('Pulp Fiction', 1994, 'Crime', 'Quentin Tarantino');
INSERT INTO movies (title, release_year, genre, director) VALUES ('Spirited Away', 2001, 'Animation', 'Hayao Miyazaki');
INSERT INTO movies (title, release_year, genre, director) VALUES ('Dune: Part Two', 2024, 'Sci-Fi', 'Denis Villeneuve');

-- Artists
INSERT INTO artists (name, birth_date) VALUES ('Keanu Reeves', '1964-09-02');
INSERT INTO artists (name, birth_date) VALUES ('Leonardo DiCaprio', '1974-11-11');
INSERT INTO artists (name, birth_date) VALUES ('Christian Bale', '1974-01-30');
INSERT INTO artists (name, birth_date) VALUES ('Matthew McConaughey', '1969-11-04');
INSERT INTO artists (name, birth_date) VALUES ('Robert Downey Jr.', '1965-04-04');
INSERT INTO artists (name, birth_date) VALUES ('Al Pacino', '1940-04-25');
INSERT INTO artists (name, birth_date) VALUES ('Timothée Chalamet', '1995-12-27');
INSERT INTO artists (name, birth_date) VALUES ('Zendaya', '1996-09-01');

-- Relationships (Movie <-> Artist)
INSERT INTO movie_artists (movie_id, artist_id, role) VALUES (1, 1, 'Actor'); -- Matrix, Keanu
INSERT INTO movie_artists (movie_id, artist_id, role) VALUES (2, 2, 'Actor'); -- Inception, Leo
INSERT INTO movie_artists (movie_id, artist_id, role) VALUES (3, 3, 'Actor'); -- Dark Knight, Bale
INSERT INTO movie_artists (movie_id, artist_id, role) VALUES (4, 4, 'Actor'); -- Interstellar, McConaughey
INSERT INTO movie_artists (movie_id, artist_id, role) VALUES (6, 5, 'Actor'); -- Endgame, RDJ
INSERT INTO movie_artists (movie_id, artist_id, role) VALUES (7, 6, 'Actor'); -- Godfather, Pacino
INSERT INTO movie_artists (movie_id, artist_id, role) VALUES (10, 7, 'Actor'); -- Dune 2, Timothée
INSERT INTO movie_artists (movie_id, artist_id, role) VALUES (10, 8, 'Actor'); -- Dune 2, Zendaya