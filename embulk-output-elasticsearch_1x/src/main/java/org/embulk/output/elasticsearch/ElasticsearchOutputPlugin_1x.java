package org.embulk.output.elasticsearch;

import java.util.List;
import java.util.HashMap;
import java.net.InetAddress;
import java.net.UnknownHostException;
import com.google.common.base.Throwables;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class ElasticsearchOutputPlugin_1x
        implements EmbulkElasticsearchClient
{
    public Client createClient(String clusterName, List<HashMap<String, String>> nodes)
    {
        //  @see http://www.elasticsearch.org/guide/en/elasticsearch/client/java-api/current/client.html
        Settings settings = ImmutableSettings.settingsBuilder()
                .classLoader(Settings.class.getClassLoader())
                .put("cluster.name", clusterName)
                .build();
        TransportClient client = new TransportClient(settings);
        for (HashMap<String, String> node : nodes) {
            try {
                client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(node.get("Host")), Integer.parseInt(node.get("Port"))));
            } catch (UnknownHostException e) {
                Throwables.propagate(e);
            }
        }
        return client;
    }
}
