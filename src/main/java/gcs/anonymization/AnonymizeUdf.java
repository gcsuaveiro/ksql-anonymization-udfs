package gcs.anonymization;

import gcs.anonymization.filters.Filters;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

import java.lang.String;

@UdfDescription(name = "anonymize_nginx",
        author = "SARAI",
        version = "0.0.1",
        description = "Functions for SARAI data anonymization.")
public class AnonymizeUdf {

    @Udf(description = "Anonymize data coming from the Firewall.")
    public String anonymizeNginx(@UdfParameter String inputUnfiltered) {
        // String aux = Filters.removeIPs(inputUnfiltered);
        // aux = Filters.removeIDs(aux);
        // String aux = Filters.removeUserNames_v2(inputUnfiltered,"usrName");
        String aux = Filters.removeFieldContents(inputUnfiltered,"cookie");
        // Uncomment for final deployment
        // aux = Filters.removeUserNames_v2(inputUnfiltered,"src_user_name");
                    // aux = Filters.removeUserNames_v2(inputUnfiltered,"src_user_dn");
                    // aux = Filters.removeUserNames_v2(inputUnfiltered,"originsicname");
                    // aux = Filters.removeUserNames_v2(inputUnfiltered,"src_user_dn");

         return aux;
    }


}
