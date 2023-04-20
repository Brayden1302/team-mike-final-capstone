INSERT INTO publishers (name)
VALUES ('Pottermore Publishing'), ('Scholastic Inc.'), ('Faber & Faber');

insert into books(title, publisher_id ,date_added, isbn, page_count, description, publish_date, image_link) 
values('Harry Potter and the Sorcerer''s Stone', 1, current_date, '9781781100486', 311, 'Turning the envelope over, his hand trembling, Harry saw a purple wax seal bearing a coat of arms; a lion, an eagle, a badger and a snake surrounding a large letter ''H''. Harry Potter has never even heard of Hogwarts when the letters start dropping on the doormat at number four, Privet Drive. Addressed in green ink on yellowish parchment with a purple seal, they are swiftly confiscated by his grisly aunt and uncle. Then, on Harry''s eleventh birthday, a great beetle-eyed giant of a man called Rubeus Hagrid bursts in with some astonishing news: Harry Potter is a wizard, and he has a place at Hogwarts School of Witchcraft and Wizardry. An incredible adventure is about to begin! Having become classics of our time, the Harry Potter eBooks never fail to bring comfort and escapism. With their message of hope, belonging and the enduring power of truth and love, the story of the Boy Who Lived continues to delight generations of new readers.', '2015-12-07', 'http://books.google.com/books/content?id=wrOQLV6xB-wC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api' );

insert into books(title, publisher_id ,date_added, isbn, page_count, description, publish_date, image_link) 
values('Harry Potter and the Goblet of Fire', 1, current_date, '9781781100523', 750, '''There will be three tasks, spaced throughout the school year, and they will test the champions in many different ways ... their magical prowess - their daring - their powers of deduction - and, of course, their ability to cope with danger.'' The Triwizard Tournament is to be held at Hogwarts. Only wizards who are over seventeen are allowed to enter - but that doesn''t stop Harry dreaming that he will win the competition. Then at Hallowe''en, when the Goblet of Fire makes its selection, Harry is amazed to find his name is one of those that the magical cup picks out. He will face death-defying tasks, dragons and Dark wizards, but with the help of his best friends, Ron and Hermione, he might just make it through - alive! Having become classics of our time, the Harry Potter eBooks never fail to bring comfort and escapism. With their message of hope, belonging and the enduring power of truth and love, the story of the Boy Who Lived continues to delight generations of new readers.', '2015-12-07', 'http://books.google.com/books/content?id=etukl7GfrxQC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api' );

insert into books(title, publisher_id ,date_added, isbn, page_count, description, publish_date, image_link) 
values('Catching Fire (Hunger Games, Book Two)', 2, current_date - 1, '9780545227247', 400, 'The second book in Suzanne Collins''s phenomenal and worldwide bestselling Hunger Games trilogy.', '2015-12-07', 'http://books.google.com/books/content?id=FN5wMOZKTYMC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api' );

insert into books(title, publisher_id ,date_added, isbn, page_count, description, publish_date, image_link) 
values('Lord of the Flies', 3, current_date, '9780571290581', 291, 'A plane crashes on a desert island and the only survivors, a group of schoolboys, assemble on the beach and wait to be rescued. By day they inhabit a land of bright fantastic birds and dark blue seas, but at night their dreams are haunted by the image of a terrifying beast. As the boys'' delicate sense of order fades, so their childish dreams are transformed into something more primitive, and their behaviour starts to take on a murderous, savage significance. First published in 1954, Lord of the Flies is one of the most celebrated and widely read of modern classics. Now fully revised and updated, this educational edition includes chapter summaries, comprehension questions, discussion points, classroom activities, a biographical profile of Golding, historical context relevant to the novel and an essay on Lord of the Flies by William Golding entitled ''Fable''. Aimed at Key Stage 3 and 4 students, it also includes a section on literary theory for advanced or A-level students. The educational edition encourages original and independent thinking while guiding the student through the text - ideal for use in the classroom and at home.', '2015-12-07', 'http://books.google.com/books/content?id=DKU1EsmnpMQC&printsec=frontcover&img=1&zoom=1&source=gbs_api' );



INSERT INTO authors (name)
VALUES ('J.K. Rowling'), ('Suzanne Collins'), ('William Golding');

INSERT INTO books_authors (book_id, author_id)
VALUES (1,1), (2,3), (3,2), (4,3);

INSERT INTO categories (name)
VALUES ('Juvenile Fiction'), ('Fiction'), ('Young Adult Fiction');

INSERT INTO books_categories (category_id, book_id)
VALUES (1,1), (2,2), (3,3), (2,4);

INSERT INTO reading_list (user_id, book_id, read)
VALUES(4,1,'true'),(4,3,'false'),(5,2,'false'),(5,1,'false'),(6,2,'false'),(6,3,'false');

INSERT INTO forum (user_id, forum_title, forum_description)
VALUES (4,'Good horror books', 'I''ve recently started reading some horror books however I''m having trouble finding some good books to read. I don''t have much time to read and really want to make sure the book I read is going to be a good one so any recommendations you have are awesome! Thank you!'),
	   (5,'Best book genres', 'What do you guys think is the best genre of books? I personally think its Fiction Harry Potter carries that genre!'),
	   (6,'Book recommendations', 'I need any and all book recommendations! I don''t really read that often and want to get into reading more so i would love some book recommendations');

INSERT INTO messages (user_id, forum_id, message_content)
VALUES (5,1,'I''ve really enoyed goosebumps!'),(4,1,'I''ll look into those'), (6,1,'I don''t like horror books so im no help here! Sorry!'),
	   (4,2,'Nonfiction is the best! Nothing beats learning about real events!'),(6,2,'I personally really enjoy fantasy! There''s something special about magical creatures!'),(5,2,'Oh man you both make ery good points!'),
	   (4,3,'Read the harry potter series! It''s the best series ever!'), (5,3,'I agree with Brayden on this one! Harry Potter is untouchable');


