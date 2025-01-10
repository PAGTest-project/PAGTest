
package org.jsoup.nodes;

import org.jsoup.helper.ChangeNotifyingArrayList;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.jsoup.parser.TokenQueue;
import org.jsoup.select.Collector;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import org.jsoup.select.QueryParser;
import org.jsoup.select.Selector;
import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.jsoup.internal.Normalizer.normalize;
import static org.jsoup.nodes.TextNode.lastCharIsWhitespace;
import static org.jsoup.parser.Parser.NamespaceHtml;
import static org.jsoup.parser.TokenQueue.escapeCssIdentifier;

public class Element_getElementsByAttributeStartingTest {
    private static final List<Element> EmptyChildren = Collections.emptyList();
    private static final Pattern ClassSplit = Pattern.compile("\\s+");
    private static final String BaseUriKey = Attributes.internalKey("baseUri");
    private Tag tag;
    private @Nullable WeakReference<List<Element>> shadowChildrenRef;
    List<Node> childNodes;
    @Nullable Attributes attributes;

    public Element(String tag, String namespace) {
        this(Tag.valueOf(tag, namespace, ParseSettings.preserveCase), null);
    }

    public Element(String tag) {
        this(Tag.valueOf(tag, Parser.NamespaceHtml, ParseSettings.preserveCase);
    }

    public Element(Tag tag, @Nullable String baseUri, @Nullable Attributes attributes) {
        Validate.notNull(tag);
        childNodes = EmptyNodes;
        this.attributes = attributes;
        this.tag = tag;
        if (baseUri != null)
            this.setBaseUri(baseUri);
    }

    public Element(Tag tag, @Nullable String baseUri) {
        this(tag, baseUri, null);
    }

    public Elements getElementsByAttributeStarting(String keyPrefix) {
        Validate.notEmpty(keyPrefix);
        keyPrefix = keyPrefix.trim();

        return Collector.collect(new Evaluator.AttributeStarting(keyPrefix), this);
    }
}
