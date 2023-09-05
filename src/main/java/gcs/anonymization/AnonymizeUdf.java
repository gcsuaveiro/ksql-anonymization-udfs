package gcs.anonymization;

import gcs.anonymization.filters.Filters;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

import java.lang.String;

@UdfDescription(name = "anonymize",
        author = "example user",
        version = "1.0.2",
        description = "A custom formula for important business logic.")
public class AnonymizeUdf {

    @Udf(description = "The standard version of the formula with integer parameters.")
    public String anonymize(@UdfParameter String inputUnfiltered) {
        // String aux = Filters.removeIPs(inputUnfiltered);
        // aux = Filters.removeIDs(aux);
        String aux = Filters.removeUserNames_v2(inputUnfiltered,"usrName");
        // Uncomment for final deployment
        // aux = Filters.removeUserNames_v2(inputUnfiltered,"src_user_name");
                    // aux = Filters.removeUserNames_v2(inputUnfiltered,"src_user_dn");

        return aux;
    }

    @Udf(description = "A special variant of the formula, handling double parameters.")
    public String anonymize(@UdfParameter String filebeatType, @UdfParameter String inputUnfiltered) {
        return "";
    }
}
