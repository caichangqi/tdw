EXPLAIN
SELECT x.* FROM SRCPART x WHERE ds = '2008-04-08' LIMIT 10;

SELECT x.* FROM SRCPART x WHERE ds = '2008-04-08' sort by x.key, x.hr LIMIT 10;
