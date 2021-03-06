/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */
package org.opensearch.pinnedquery;

import com.carrotsearch.randomizedtesting.annotations.ThreadLeakScope;
import org.apache.http.util.EntityUtils;
import org.opensearch.client.Request;
import org.opensearch.client.Response;
import org.opensearch.plugins.Plugin;
import org.opensearch.test.OpenSearchIntegTestCase;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@ThreadLeakScope(ThreadLeakScope.Scope.NONE)
@OpenSearchIntegTestCase.ClusterScope(scope = OpenSearchIntegTestCase.Scope.SUITE)
public class PinnedQueryPluginIT extends OpenSearchIntegTestCase {

    @Override
    protected Collection<Class<? extends Plugin>> nodePlugins() {
        return Collections.singletonList(PinnedQueryPlugin.class);
    }

    public void testPluginInstalled() throws IOException {
        Response response = createRestClient().performRequest(new Request("GET", "/_cat/plugins"));
        String body = EntityUtils.toString(response.getEntity());
        logger.info("response body: {}", body);
        assertTrue(body.contains("opensearch-pinned-query"));
    }

    public void testPluginQueryAttributes() throws IOException {
        Request request = new Request("GET", "/_search");
        request.setJsonEntity("{\"query\": {\"pinned\": {\"ids\": [\"1A\", \"2B\", \"3C\"]}}}");
        Response response = createRestClient().performRequest(request);
        String body = EntityUtils.toString(response.getEntity());
        logger.info("response body: {}", body);
    }
}
