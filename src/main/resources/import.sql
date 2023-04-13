INSERT INTO db_fotoalbum.photos(description, title, url, visible)VALUES('La bellezza della solitudine della natura', 'Solitario', 'https://images.pexels.com/photos/16317494/pexels-photo-16317494.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 1);
INSERT INTO db_fotoalbum.photos(description, title, url, visible)VALUES('La sabbia scottava', 'Sardegna', 'https://images.pexels.com/photos/2567550/pexels-photo-2567550.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 1);
INSERT INTO db_fotoalbum.photos(description, title, url, visible)VALUES('Hanami (花見, "flower viewing") is the Japanese traditional custom of enjoying the transient beauty of cherry (桜, sakura) flowers', 'Hanami', 'https://images.unsplash.com/photo-1629137441025-c4b23def8bdd?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80', 1);
INSERT INTO db_fotoalbum.photos(description, title, url, visible)VALUES('Christopher Johnson McCandless trascorse gli ultimi 112 giorni della sua vita in Alaska avendo come rifugio un autobus abbandonato, che trovò e soprannominò Magic Bus.', 'Into the Wild', 'https://manintowncom.ams3.digitaloceanspaces.com/2020/05/Into_the_Wild.jpg', 1);
INSERT INTO db_fotoalbum.photos(description, title, url, visible)VALUES('Bamboo forest, Kyoto', 'Bamboo', 'https://images.unsplash.com/photo-1607619662634-3ac55ec0e216?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=735&q=80', 1);

INSERT INTO db_fotoalbum.categories(name)VALUES('landscape');
INSERT INTO db_fotoalbum.categories(name)VALUES('japan');
INSERT INTO db_fotoalbum.categories(name)VALUES('loneliness');
INSERT INTO db_fotoalbum.categories(name)VALUES('sardinia');
INSERT INTO db_fotoalbum.categories(name)VALUES('trees');

INSERT INTO db_fotoalbum.categories_photos(photo_id, category_id)VALUES(1, 1);
INSERT INTO db_fotoalbum.categories_photos(photo_id, category_id)VALUES(1, 3);
INSERT INTO db_fotoalbum.categories_photos(photo_id, category_id)VALUES(1, 5);
INSERT INTO db_fotoalbum.categories_photos(photo_id, category_id)VALUES(2, 4);
INSERT INTO db_fotoalbum.categories_photos(photo_id, category_id)VALUES(3, 2);
INSERT INTO db_fotoalbum.categories_photos(photo_id, category_id)VALUES(3, 1);
INSERT INTO db_fotoalbum.categories_photos(photo_id, category_id)VALUES(3, 5);
INSERT INTO db_fotoalbum.categories_photos(photo_id, category_id)VALUES(4, 1);
INSERT INTO db_fotoalbum.categories_photos(photo_id, category_id)VALUES(4, 3);
INSERT INTO db_fotoalbum.categories_photos(photo_id, category_id)VALUES(5, 1);
INSERT INTO db_fotoalbum.categories_photos(photo_id, category_id)VALUES(5, 2);
INSERT INTO db_fotoalbum.categories_photos(photo_id, category_id)VALUES(5, 5);

INSERT INTO db_fotoalbum.users(email, nickname , password) VALUES('carlo@gmail.com', 'carlo', '{noop}carlo');
INSERT INTO db_fotoalbum.users(email, nickname , password) VALUES('gibba@gmail.com', 'gibba', '{noop}gibba');
INSERT INTO db_fotoalbum.roles(name)VALUES('ADMIN');
INSERT INTO db_fotoalbum.users_roles(users_id, roles_id)VALUES(1, 1)
INSERT INTO db_fotoalbum.users_roles(users_id, roles_id)VALUES(2, 1);
