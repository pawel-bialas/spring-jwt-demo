insert into users (
    id,
    avatar_id,
    creation_date,
    desc_acc_name,
    email,
    password,
    role,
    status,
    type,
    unique_acc_name)
values (
           1,
           1,
           CURRENT_DATE,
           'masterAdmin',
           'admin@domain.com',
           '$2a$10$7jFvRqWCUwhwZhy.OrsxaO/53FKbJyAgP/Ud7c2ilkiVHwqUfM5mS',
           'MASTER_ADMIN',
           'ACTIVE',
           'PUBLIC',
           'realMasterAdmin'
       );
