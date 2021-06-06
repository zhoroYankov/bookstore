insert into roles(name)
values
('CLIENT'),
('VIP'),
('ADMIN');

insert into ganres(name)
values
('DRAMA'),
('ROMANCE'),
('NOVEL'),
('HORROR'),
('THRILLER'),
('MYSTERY'),
('DYSTOPIAN'),
('CLASSIC'),
('ADVENTURE'),
('WESTERN'),
('HISTORICAL'),
('SCIFI');


insert into authors(name)
values
('Victor Hugo'),
('J. R. R. Tolkien'),
('Ernest Hemingway'),
('Virginia Woolf'),
('Anton Chekhov'),
('Jonathan Swift'),
('Charles Dickens'),
('George Orwell'),
('Lewis Carroll'),
('Mark Twain'),
('Fyodor Dostoyevsky'),
('Dante Alighieri'),
('Homer'),
('William Shakespeare'),
('Leo Tolstoy'),
('Herman Melville'),
('Scott Fitzgerald'),
('Miguel de Cervantes');

insert into books(title, ganre_id, author_id)
values
('Les Misérables', 9, 1),
('The Idiot', 9, 11),
('The Lord of the Rings', 2, 2),
('David Copperfield', 10,7),
('The Sun Also Rises', 1,3),
('The Old Man and the Sea', 1,3),
('Mrs. Dalloway', 2,4),
('To the Lighthouse', 2,4),
('The Stories of Anton Chekhov', 9,5),
('Gulliver`s Travels', 10,6),
('Nineteen Eighty Four', 8,8),
('Great Expectations', 1,7),
('Alice`s Adventures in Wonderland', 10,9),
('The Adventures of Huckleberry Finn', 10,10),
('The Brothers Karamazov', 9,11),
('Crime and Punishment', 12,11),
('The Divine Comedy', 9,12),
('The Odyssey', 9,13),
('The Iliad', 9,13),
('Hamlet', 9,14),
('Anna Karenina',12 ,15),
('War and Peace',12 ,15),
('Moby Dick', 5,16),
('The Great Gatsby', 3,17),
('Don Quixote', 9,18);
