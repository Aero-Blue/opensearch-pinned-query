/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */
package org.opensearch.pinnedquery;

import org.opensearch.common.io.stream.Writeable;
import org.opensearch.index.query.QueryBuilder;
import org.opensearch.pinnedquery.query.PinnedQueryBuilder;
import org.opensearch.plugins.Plugin;
import org.opensearch.plugins.SearchPlugin;

import java.util.List;

import static java.util.Collections.singletonList;


public class PinnedQueryPlugin extends Plugin implements SearchPlugin {
    @Override
    public List<QuerySpec<?>> getQueries() {
        return singletonList(
                new QuerySpec<QueryBuilder>(
                        PinnedQueryBuilder.NAME,
                        PinnedQueryBuilder::new,
                        PinnedQueryBuilder::fromXContent));
    }
}
