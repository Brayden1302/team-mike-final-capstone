BEGIN TRANSACTION;

DROP TABLE IF EXISTS users_books;
DROP TABLE IF EXISTS books_authors;
DROP TABLE IF EXISTS books_categories;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS publishers;
DROP TABLE IF EXISTS books;

CREATE TABLE books
(
	book_id serial,
	publisher_id int NOT NULL,
	title varchar(50) NOT NULL,
	date_added date NOT NULL,
	isbn varchar(60) NOT NULL,
	page_count int NOT NULL,
	description varchar(1000),
	publish_date date NOT NULL,
	image_link varchar(200),
	
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
	name varchar(100),
	
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

CREATE TABLE categories
(
	category_id serial,
	name varchar(20),
	
	CONSTRAINT pk_categories PRIMARY KEY (category_id)
);

CREATE TABLE books_categories
(
	category_id int NOT NULL,
	book_id int NOT NULL,
	
	CONSTRAINT pk_books_categories PRIMARY KEY (book_id, category_id),
	CONSTRAINT fk_books_categories_books FOREIGN KEY (book_id) REFERENCES books (book_id),
	CONSTRAINT fk_books_categories_categories FOREIGN KEY (category_id) REFERENCES categories (category_id)
);

CREATE TABLE publishers
(
	publisher_id serial,
	name varchar(40),
	
	CONSTRAINT pk_publishers PRIMARY KEY (publisher_id)
);

COMMIT;

