FROM srcpart src1 JOIN srcpart src2 ON (src1.ds = src2.ds and src1.ds = '2008-04-08')
INSERT OVERWRITE TABLE dest1 SELECT src1.key, src2.value