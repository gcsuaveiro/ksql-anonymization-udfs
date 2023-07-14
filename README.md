# ksql-anonymization-udfs

This repository contains the dev environment for developing and testing UDFs that will be imported to KSQL for data anonymization.

## How-To's
### How to build the UDF Jar?
```
gradle shadowJar
```
### How to deploy a created UDF?

Helk: Copy the Jar created above to helk's /docker/helk-ksql/udfs folder. Restart container to import created function to Ksql (Hot Reloading is NOT supported.)

Local Testing: docker-compose up

### How to check if UDF was correctly imported?

Analyze container logs after restart and search for 'udaf' -- If present, there will be a line with the name of the function created.

## More Reading
[How to create a udf](https://docs.ksqldb.io/en/latest/how-to-guides/create-a-user-defined-function/#add-the-uberjar-to-ksqldb-server)

[UDF reference guide](https://docs.ksqldb.io/en/latest/reference/user-defined-functions/)
