BEGIN TRANSACTION;

DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS forum;
DROP TABLE IF EXISTS reading_list;
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
	description varchar(5000),
	publish_date date NOT NULL,
	image_link varchar(200),
	
	CONSTRAINT pk_books PRIMARY KEY (book_id)
	
);

CREATE TABLE authors
(
	author_id serial,
	name varchar(50),
	
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

CREATE TABLE reading_list
(
	user_id int NOT NULL,
	book_id int NOT NULL,
	read boolean NOT NULL,
	
	CONSTRAINT pk_reading_list PRIMARY KEY (user_id, book_id),
	CONSTRAINT fk_reading_list_users FOREIGN KEY (user_id) REFERENCES users (user_id),
	CONSTRAINT fk_reading_list_books FOREIGN KEY (book_id) REFERENCES books (book_id)
);

CREATE TABLE forum
(
	forum_id serial,
	user_id int NOT NULL,
	forum_title varchar(500) NOT NULL,
	forum_description varchar(1000) NOT NULL,
	
	CONSTRAINT pk_forum PRIMARY KEY (forum_id),
	CONSTRAINT fk_forum_users FOREIGN KEY (user_id) REFERENCES users (user_id)

);

CREATE TABLE messages
(
	message_id serial,
	user_id int NOT NULL,
	forum_id int NOT NULL,
	message_content varchar(1000) NOT NULL,
	
	CONSTRAINT pk_messages PRIMARY KEY (message_id),
	CONSTRAINT fk_messages_users FOREIGN KEY (user_id) REFERENCES users (user_id),
	CONSTRAINT fk_messages_forum FOREIGN KEY (forum_id) REFERENCES forum (forum_id)

);

COMMIT;





