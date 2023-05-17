-- create database java3final;
use java3final;

create table summerplan (  
  id int not null,
  category varchar(20) not null,
  activity varchar(100) not null,
  primary key (id)
);

insert into summerplan values 
(1,'Study', 'Learn python'),
(2,'Study', 'Learn deep learning'),
(3,'Job', 'Quit current in one week'),
(4,'Job', 'Get a job in BMO or TD'),
(5,'Family', 'Visit parents every Friday'),
(6,'Family', 'August 1st, dad 44th birthday'),
(7, 'Travel', 'Go back yard if COVID-19 becomes better');

