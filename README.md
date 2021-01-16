# CountriesSize

1. Сборка:
sbt assembly

2. Запуск:
spark-submit \
--class ru.otus.hadoop.sgribkov.CountriesSize \
--master local[*] \
/path/to/x.jar \
--input-file-path "/path/to/y.json" \
--output-file-path "/path/to/z.json"
