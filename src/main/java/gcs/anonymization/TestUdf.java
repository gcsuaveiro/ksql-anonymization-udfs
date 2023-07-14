package gcs.anonymization;

import io.confluent.common.Configurable;
import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

import java.util.Map;

@UdfDescription(name ="testFilter",
                author = "sarai",
                version = "0.0.1",
                description = "Test for data filtering for topic filebeat.")
public class TestUdf implements Configurable,FlowInterface {

    @Override
    public void configure(final Map<String, ?> map){
        //String s = (String) map.get("ksql.functions.formula.base.value");

        // unsure what to do here? Do we need any values from env vars?
    }


    @Udf(description = "Test implementation")
    public String testFilter(@UdfParameter String message){
        System.out.println("UDF RECEIVED: "+message);
        return message;
    }

    @Override
    public String getContents(String message){
        // Maybe here we verify what topic this came from? Validate content?
        return "Not Implemented";
    }


}
