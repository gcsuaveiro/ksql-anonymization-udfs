package gcs.anonymization;

import gcs.anonymization.filters.Filters;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;

import java.lang.String;

@UdfDescription(name = "anonymize",
        author = "SARAI",
        version = "0.0.1",
        description = "Functions for SARAI data anonimization.")
public class AnonymizeUdf {

    @Udf(description = "Anonimize data coming from the Firewall.")
    public String anonymize(@UdfParameter String inputUnfiltered) {
        // String aux = Filters.removeIPs(inputUnfiltered);
        // aux = Filters.removeIDs(aux);
        String aux = Filters.removeUserNames_v2(inputUnfiltered,"usrName");
                    aux= Filters.removeFieldContents(aux,"cookie");
        // Uncomment for final deployment
        // aux = Filters.removeUserNames_v2(inputUnfiltered,"src_user_name");
                    // aux = Filters.removeUserNames_v2(inputUnfiltered,"src_user_dn");
                    // aux = Filters.removeUserNames_v2(inputUnfiltered,"originsicname");
                    // aux = Filters.removeUserNames_v2(inputUnfiltered,"src_user_dn");

        return aux;
    }

    @Udf(description = "TODO")
    public String anonymize(@UdfParameter String filebeatType, @UdfParameter String inputUnfiltered) {
        return "";
    }
}
