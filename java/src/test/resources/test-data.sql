BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role,last_search) VALUES ('user1','user1','ROLE_USER', current_date);
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER', current_date);
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER', current_date);

INSERT INTO PUBLISHERS (name)
VALUES ('test publisher'); --id of 1
INSERT INTO PUBLISHERS (name)
VALUES ('test publisher2'); --id of 2
INSERT INTO PUBLISHERS (name)
VALUES ('test publisher3'); --id of 3

INSERT INTO authors (name)
values ('test author'); --id of 1
INSERT INTO authors (name)
values ('test author2'); --id of 2
INSERT INTO authors (name)
values ('test author3'); --id of 3

INSERT INTO books (publisher_id, title, date_added, isbn, page_count, description, publish_date, image_link)
values ('1', 'test book', current_date , 1001, 100, 'test book 1 description', '4-1-2023', 'testlink.com'); --id of 1
INSERT INTO books (publisher_id, title, date_added, isbn, page_count, description, publish_date, image_link)
values ('1', 'test book2', current_date , 1002, 100, 'test book 2 description', '4-2-2023', 'testlink2.com'); --id of 2
INSERT INTO books (publisher_id, title, date_added, isbn, page_count, description, publish_date, image_link)
values ('2', 'test book3', current_date , 1003, 100, 'test book 3 description', '4-1-2023', 'testlink3.com'); --id of 3
INSERT INTO books (publisher_id, title, date_added, isbn, page_count, description, publish_date, image_link)
values ('3', 'test book4', current_date , 1004, 100, 'test book 4 description', '4-4-2023', 'testlink4.com'); --id of 4

INSERT INTO categories (name)
values ('test genre');  --id of 1
INSERT INTO categories (name)
values ('test genre2'); --id of 2
INSERT INTO categories (name)
values ('test genre3'); --id of 3

INSERT INTO books_categories (category_id, book_id)
values (1,1);
INSERT INTO books_categories (category_id, book_id)
values (2,1);
INSERT INTO books_categories (category_id, book_id)
values (2,2);
INSERT INTO books_categories (category_id, book_id)
values (3,3);
INSERT INTO books_categories (category_id, book_id)
values (3,4);

INSERT INTO books_authors (book_id, author_id)
values(1,1)
INSERT INTO books_authors (book_id, author_id)
values(1,2)
INSERT INTO books_authors (book_id, author_id)
values(2,1)
INSERT INTO books_authors (book_id, author_id)
values(3,2)
INSERT INTO books_authors (book_id, author_id)
values(4,3)

COMMIT TRANSACTION;
