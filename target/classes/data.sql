insert into users (
    id,
    avatar_id,
    register_date,
    descriptive_account_name,
    email,
    password,
    user_role,
    user_status,
    account_type,
    unique_account_name)
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
