drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

drop table if exists userinforole;
drop table if exists userinfo;
drop table if exists role;

create table if not exists userinfo (
  id bigint not null AUTO_INCREMENT,
  referenceid varchar(255) not null,
  username varchar(100) not null,
  password varchar(200) not null,
  enabled boolean default true not null,
  credentialsexpired boolean default false not null,
  expired boolean default false not null,
  locked boolean default false not null,
  version int not null,
  createdby varchar(100) not null,
  createdat datetime not null,
  updatedby varchar(100) default null,
  updatedat datetime default null,
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

create table if not exists role (
  id bigint not null,
  code varchar(50) not null,
  label varchar(100) not null,
  ordinal int not null,
  effectiveat datetime not null,
  expiresat datetime default null,
  createdat datetime not null,
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

create table if not exists userinforole (
  userid bigint not null,
  roleid bigint not null,
  primary key (userid, roleid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;