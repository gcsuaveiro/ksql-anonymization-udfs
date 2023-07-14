# ksql-anonymization-udfs

## How-To's
### How to build the UDF Jar?
```
gradle shadowJar
```
### How to deploy a created UDF?

Copy the Jar created above to helk's /docker/helk-ksql/udfs folder. Restart pod to import created function to Ksql (Hot Reloading is NOT supported.)

### How to check if UDF was correctly imported?

Analyze pod logs after restart and search for 'udaf' -- If present, there will be a line with the name of the function created.

## More Reading
[How to create a udf](https://docs.ksqldb.io/en/latest/how-to-guides/create-a-user-defined-function/#add-the-uberjar-to-ksqldb-server)
[UDF reference guide](https://docs.ksqldb.io/en/latest/reference/user-defined-functions/)
