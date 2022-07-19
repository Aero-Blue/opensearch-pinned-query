/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */
package org.opensearch.pinnedquery.query;

import org.apache.lucene.search.Query;
import org.opensearch.common.ParseField;
import org.opensearch.common.ParsingException;
import org.opensearch.common.io.stream.StreamInput;
import org.opensearch.common.io.stream.StreamOutput;
import org.opensearch.common.xcontent.ObjectParser;
import org.opensearch.common.xcontent.XContentBuilder;
import org.opensearch.common.xcontent.XContentParser;
import org.opensearch.index.query.AbstractQueryBuilder;
import org.opensearch.index.query.QueryShardContext;

import java.io.IOException;
import java.util.List;

public class PinnedQueryBuilder extends AbstractQueryBuilder<PinnedQueryBuilder> {
    public static final String NAME = "pinned";
    private static final ParseField FIELD_PINNED_IDS = new ParseField("ids");

    private static final ObjectParser<PinnedQueryBuilder, Void> PARSER = new ObjectParser<>(NAME, PinnedQueryBuilder::new);

    static {
        declareStandardFields(PARSER);
        PARSER.declareStringArray(PinnedQueryBuilder::setPinnedIds, FIELD_PINNED_IDS);
    }

    private List<String> pinnedIds;

    public PinnedQueryBuilder() {

    }

    public PinnedQueryBuilder(StreamInput in) throws IOException {
        super(in);
        setPinnedIds(in.readOptionalStringList());
    }

    public static PinnedQueryBuilder fromXContent(final XContentParser parser) {
        final PinnedQueryBuilder builder;
        try {
            builder = PARSER.apply(parser, null);
        } catch (final IllegalArgumentException e) {
            throw new ParsingException(parser.getTokenLocation(), e.getMessage(), e);
        }

        return builder;
    }

    @Override
    protected void doWriteTo(StreamOutput streamOutput) throws IOException {

    }

    @Override
    protected void doXContent(XContentBuilder xContentBuilder, Params params) throws IOException {

    }

    @Override
    protected Query doToQuery(QueryShardContext queryShardContext) throws IOException {
        return null;
    }

    @Override
    protected boolean doEquals(PinnedQueryBuilder pinnedQueryBuilder) {
        return false;
    }

    @Override
    protected int doHashCode() {
        return 0;
    }

    @Override
    public String getWriteableName() {
        return null;
    }

    public void setPinnedIds(final List<String> pinnedIds) {
        if (pinnedIds == null) {
            throw new IllegalArgumentException("Pinned results must not be null");
        }
        this.pinnedIds = pinnedIds;
    }

}
