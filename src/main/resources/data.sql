INSERT INTO user (user_id, username, firstname, lastname, email, image, password, birthdate)
VALUES (1, "user", "userprenom", "usernom", "user@email.com", "anonyme.png", "$2y$12$W2s00p1iieYhHDpJEaWQVuAZJzRIuGJuJYYbLhuUyUg7kVT1Rb8h.", "2013-09-22"),
       (2, "admin", "adminprenom", "adminnom", "admin@email.com", "anonyme.png", "$2y$12$W2s00p1iieYhHDpJEaWQVuAZJzRIuGJuJYYbLhuUyUg7kVT1Rb8h.", "2013-09-22"),
       (3, "sadmin", "sadminprenom", "sadminnom", "sadmin@email.com", "anonyme.png", "$2y$12$W2s00p1iieYhHDpJEaWQVuAZJzRIuGJuJYYbLhuUyUg7kVT1Rb8h.", "2013-09-22");

INSERT INTO role (role_id, name) VALUES (1, "ROLE_MEMBER"),
                                        (2, "ROLE_ADMIN"),
                                        (3, "ROLE_SUPER_ADMIN");

INSERT INTO user_role (id, user_id, role_id) VALUES (1, 1, 1),
                                                    (2, 2, 2),
                                                    (3, 3, 3),
                                                    (4, 2, 1),
                                                    (5, 3, 1);

INSERT INTO permission (permission_id, name) VALUES (1, "member:read"),
                                                    (2, "member:write"),
                                                    (3, "admin:read"),
                                                    (4, "admin:write"),
                                                    (5, "sadmin:read"),
                                                    (6, "sadmin:write"),
                                                    (7, "post:read"),
                                                    (8, "post:write"),
                                                    (9, "category:read"),
                                                    (10, "category:write"),
                                                    (11, "role:read"),
                                                    (12, "role:write");

INSERT INTO user_permission (id, user_id, permission_id, start_date) VALUES (1, 1, 1, "2013-09-22 16:19:43"),
                                                                            (2, 2, 1, "2013-09-22 16:19:43"),
                                                                            (3, 2, 2, "2013-09-22 16:19:43"),
                                                                            (4, 2, 3, "2013-09-22 16:19:43"),
                                                                            (5, 3, 1, "2013-09-22 16:19:43"),
                                                                            (6, 3, 2, "2013-09-22 16:19:43"),
                                                                            (7, 3, 3, "2013-09-22 16:19:43"),
                                                                            (8, 3, 4, "2013-09-22 16:19:43"),
                                                                            (9, 3, 5, "2013-09-22 16:19:43"),
                                                                            (10, 3, 6, "2013-09-22 16:19:43"),

                                                                            (11, 2, 7, "2013-09-22 16:19:43"),
                                                                            (12, 2, 8, "2013-09-22 16:19:43"),
                                                                            (13, 3, 7, "2013-09-22 16:19:43"),
                                                                            (14, 3, 8, "2013-09-22 16:19:43"),
                                                                            (15, 2, 9, "2013-09-22 16:19:43"),
                                                                            (16, 2, 10, "2013-09-22 16:19:43"),
                                                                            (17, 3, 9, "2013-09-22 16:19:43"),
                                                                            (18, 3, 10, "2013-09-22 16:19:43"),
                                                                            (19, 3, 11, "2013-09-22 16:19:43"),
                                                                            (20, 3, 12, "2013-09-22 16:19:43");

INSERT INTO role_permission (id, role_id, permission_id) VALUES (1, 1, 1),
                                                                (2, 2, 1),
                                                                (3, 2, 2),
                                                                (4, 2, 3),
                                                                (5, 3, 1),
                                                                (6, 3, 2),
                                                                (7, 3, 3),
                                                                (8, 3, 4),
                                                                (9, 3, 5),
                                                                (10, 3, 6),

                                                                (11, 2, 7),
                                                                (12, 2, 8),
                                                                (13, 3, 7),
                                                                (14, 3, 8),
                                                                (15, 2, 9),
                                                                (16, 2, 10),
                                                                (17, 3, 9),
                                                                (18, 3, 10);
INSERT INTO category (category_id, name) VALUES (1, "Front-End"),
                                                (2, "Back-End");

INSERT INTO post (post_id, title, content, date, image, category_id, user_id) VALUES (1, "Titre article 1", "Sed ut perspiciatis  iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?", "2013-09-22 16:19:43", "post_cover1.png", 1, 2),
                                                                                (2, "Titre article 2", "Sed utt perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?", "2013-09-22 16:20:43", "post_cover2.png", 2, 2),
                                                                                (3, "Titre article 3", "Sed ut pterspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?", "2013-09-22 16:21:43", "post_cover3.png", 1, 2),
                                                                                (4, "Titre article 4", "Sed ut perstpiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?", "2013-09-22 16:22:43", "post_cover4.png", 2, 2);