--RESTful Server oauth_client_details 
insert into oauth_client_details (client_id, client_secret, resource_ids, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) values ('rest_server_id', 'rest_server_secret', null, 'rest:admin', 'authorization_code,password,client_credentials,implicit,refresh_token', null, 'ROLE_REST_SERVER', 36000, 2592000, null, null);

insert into oauth_client_details (client_id, client_secret, resource_ids, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) values ('my_client_id', 'my_client_secret', null, 'read,write', 'authorization_code,password,client_credentials,implicit,refresh_token', null, 'ROLE_MY_CLIENT', 36000, 2592000, null, null);
insert into oauth_client_details (client_id, client_secret, resource_ids, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) values ('your_client_id', 'your_client_secret', null, 'read', 'authorization_code,implicit', null, 'ROLE_YOUR_CLIENT', 36000, 2592000, null, null);
INSERT INTO oauth.oauth_client_details(client_id,client_secret,scope,authorized_grant_types,authorities,access_token_validity,refresh_token_validity) VALUES ('rest_client_id','rest_client_secret','rest:admin','authorization_code,password,client_credentials,implicit,refresh_token','ROLE_REST_CLIENT',36000,2592000);

insert into userinfo (referenceid, username, password, enabled, credentialsexpired, expired, locked, version, createdby, createdat, updatedby, updatedat) values ('a07bd221-3ecd-4893-a0f0-78d7c0fbf94e', 'user', '$2a$10$qM9Sz6829/geMgkGgBsx/.N/fd4sTMdnHay0Mwg.1F4gpzhpCYyPS', true, false, false, false, 0, 'user', now(), null, null);
insert into userinfo (referenceid, username, password, enabled, credentialsexpired, expired, locked, version, createdby, createdat, updatedby, updatedat) values ('7bd137c8-ab64-4a45-bf2d-d9bae3574622', 'operations', '$2a$10$s.3M7ahEQcizUp0L6N89TOGY8gDbeJ9T152lLd9oOpgzryBr2S9Ae', true, false, false, false, 0, 'user', now(), null, null);

insert into role (id, code, label, ordinal, effectiveat, expiresat, createdat) values (1, 'role_user', 'user', 0, '2015-01-01 00:00:00', null, now());
insert into role (id, code, label, ordinal, effectiveat, expiresat, createdat) values (2, 'role_admin', 'admin', 1, '2015-01-01 00:00:00', null, now());
insert into role (id, code, label, ordinal, effectiveat, expiresat, createdat) values (3, 'role_sysadmin', 'system admin', 2, '2015-01-01 00:00:00', null, now());

insert into userinforole (userid, roleid) select a.id, r.id from userinfo a, role r where a.username = 'user' and r.id = 1;
insert into userinforole (userid, roleid) select a.id, r.id from userinfo a, role r where a.username = 'operations' and r.id = 3;