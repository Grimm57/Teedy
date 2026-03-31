merge into T_CONFIG(CFG_ID_C, CFG_VALUE_C) key(CFG_ID_C) values('GUEST_LOGIN', 'true');
update T_CONFIG set CFG_VALUE_C = '32' where CFG_ID_C = 'DB_VERSION';
