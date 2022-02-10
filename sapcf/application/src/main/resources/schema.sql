--drop table if exists institution;
create table institution(institution_id uuid default random_uuid() primary key, name varchar(100));

--drop table if exists account_type;
create table account_type(account_type_id uuid default random_uuid() primary key, name varchar(100));

--drop table if exists file_type;
create table file_type(file_type_id uuid default random_uuid() primary key, name varchar(100));

--drop table if exists uploaded_files;
create table uploaded_files (id uuid default random_uuid() primary key, 
							file_name varchar2(200), contents blob, institution_id varchar(50), 
							file_type_id varchar2(50), account_type_id varchar2(50),
							status varchar2(50) default 'NEW',
							foreign key (institution_id) REFERENCES  institution(institution_id),
							foreign key (file_type_id) REFERENCES  file_type(file_type_id),
							foreign key (account_type_id) REFERENCES  account_type(account_type_id));
							
--drop table if exists accounts;
create table accounts ( id uuid default random_uuid() primary key, account_type_id varchar2(50),
						institution_id varchar2(50), name varchar2(100),
						foreign key (account_type_id) REFERENCES  account_type(account_type_id),
						foreign key (institution_id) REFERENCES  institution(institution_id));

--drop table if exists account_transactions;
create table account_transactions(id uuid default random_uuid() primary key, account_id varchar2(50), 
									tran_date timestamp, amount numeric, is_debit boolean,
									foreign key (account_id) references accounts(id));
									
									