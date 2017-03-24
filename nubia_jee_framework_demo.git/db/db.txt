
mysqldump -uztemt -pztemt library --default-character-set=utf8 > E:\eclipse\workspace\base_framework\db\tbl_library_data.sql

mysql -uztemt -pztemt library --default-character-set=utf8 < E:\eclipse\workspace\base_framework\db\tbl_library.sql