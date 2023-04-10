BEGIN TRANSACTION;

DROP TABLE IF EXISTS users_books;
DROP TABLE IF EXISTS books_authors;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;

CREATE TABLE books
(
	book_id serial,
	title varchar(50) NOT NULL,
	genre varchar(20) NOT NULL,
	date_added timestamp NOT NULL,
	
	CONSTRAINT pk_books PRIMARY KEY (book_id)
);

CREATE TABLE users_books
(
	user_id int NOT NULL,
	book_id int NOT NULL,
	
	CONSTRAINT pk_users_books PRIMARY KEY (user_id, book_id),
	CONSTRAINT fk_users_books_users FOREIGN KEY (user_id) REFERENCES users (user_id),
	CONSTRAINT fk_users_books_books FOREIGN KEY (book_id) REFERENCES books (book_id)
);

CREATE TABLE authors
(
	author_id serial,
	first_name varchar(40),
	last_name varchar(40),
	
	CONSTRAINT pk_authors PRIMARY KEY (author_id)
);

CREATE TABLE books_authors
(
	book_id int NOT NULL,
	author_id int NOT NULL,
	
	CONSTRAINT pk_books_authors PRIMARY KEY (book_id, author_id),
	CONSTRAINT fk_books_authors_books FOREIGN KEY (book_id) REFERENCES books (book_id),
	CONSTRAINT fk_books_authors_authors FOREIGN KEY (author_id) REFERENCES authors (author_id)
);

COMMIT;