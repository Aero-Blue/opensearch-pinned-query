package org.opensearch.pinnedquery;

import org.opensearch.common.Nullable;
import org.opensearch.common.ParseField;
import org.opensearch.common.io.stream.StreamOutput;
import org.opensearch.common.xcontent.ConstructingObjectParser;
import org.opensearch.common.xcontent.XContentBuilder;
import org.opensearch.index.query.QueryRewriteContext;
import org.opensearch.index.query.QueryShardContext;
import org.opensearch.search.rescore.RescoreContext;
import org.opensearch.search.rescore.RescorerBuilder;

import java.io.IOException;

import static org.opensearch.common.xcontent.ConstructingObjectParser.constructorArg;
import static org.opensearch.common.xcontent.ConstructingObjectParser.optionalConstructorArg;

public class PinnedQueryBuilder extends RescorerBuilder<PinnedQueryBuilder> {
    public static final String NAME = "PinnedQueryBuilder";
    private static final ParseField FACTOR = new ParseField("factor");
    private static final ParseField FACTOR_FIELD = new ParseField("factor_field");
    private static final ConstructingObjectParser<PinnedQueryBuilder, Void> PARSER = new ConstructingObjectParser<>(
            NAME,
            args -> new PinnedQueryBuilder((float) args[0], (String) args[1])
    );

    static {
        PARSER.declareFloat(constructorArg(), FACTOR);
        PARSER.declareString(optionalConstructorArg(), FACTOR_FIELD);
    }

    private final float factor;
    private final String factorField;

    public PinnedQueryBuilder(float factor, @Nullable String factorField) {
        this.factor = factor;
        this.factorField = factorField;
    }

    @Override
    protected void doWriteTo(StreamOutput streamOutput) throws IOException {

    }

    @Override
    protected RescoreContext innerBuildContext(int i, QueryShardContext queryShardContext) throws IOException {
        return null;
    }

    @Override
    public String getWriteableName() {
        return null;
    }

    @Override
    public RescorerBuilder<PinnedQueryBuilder> rewrite(QueryRewriteContext queryRewriteContext) throws IOException {
        return null;
    }

    @Override
    protected void doXContent(XContentBuilder builder, Params params) throws IOException {
        builder.field(FACTOR.getPreferredName(), factor);
        if (factorField != null) {
            builder.field(FACTOR_FIELD.getPreferredName(), factorField);
        }
    }

    @Override
    public boolean isFragment() {
        return super.isFragment();
    }
}
