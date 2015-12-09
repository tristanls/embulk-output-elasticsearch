package org.embulk.output.elasticsearch;

import org.elasticsearch.client.Client;

import java.util.HashMap;
import java.util.List;

public interface EmbulkElasticsearchClient
{
    Client createClient(String clusterName, List<HashMap<String, String>> nodes);
}
