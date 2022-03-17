drop table if exists book CASCADE;
create table book as select * from csvread('/Users/yoonseoha/book_data.csv');