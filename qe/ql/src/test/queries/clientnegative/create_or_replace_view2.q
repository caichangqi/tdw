-- Can't combine IF NOT EXISTS and OR REPLACE.
drop view v;
create view v as select * from srcpart;
create or replace view if not exists v as select * from srcpart;