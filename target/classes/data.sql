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
    unigue_acc_name)
values (
           1,
           1,
           CURRENT_DATE,
           'masterAdmin',
           'test@test.pl',
           'admin',
           'MASTER_ADMIN',
           'ACTIVE',
           'PUBLIC',
           'realMasterAdmin'
       );
