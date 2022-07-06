insert into authorities (authority)
values ('ROLE_USER');
insert into authorities (authority)
values ('ROLE_ADMIN');

insert into users (username, password, enabled, authority_id)
values ('admin', '$2a$10$oXa/cKL3yS3.51BoN5HKAO048ExAma/QPGTEqWGlQFGfjkGcjzZvW', true,
        (select id from authorities where authority = 'ROLE_ADMIN'));
