delete from notification_entity;
update hibernate_sequence set next_val=1;
insert into notification_entity(id,status,type,created,modified,created_by,modified_by,description) values
(1,0,0,'2021-08-08 15:00:00',null,'argusgun',null,'ok');
update hibernate_sequence set next_val=2;