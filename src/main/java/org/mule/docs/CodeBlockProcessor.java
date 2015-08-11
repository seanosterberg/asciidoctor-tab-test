package org.mule.docs;

import org.asciidoctor.ast.AbstractBlock;
import org.asciidoctor.ast.AbstractNode;
import org.asciidoctor.ast.Block;
import org.asciidoctor.extension.BlockProcessor;
import org.asciidoctor.extension.ContentModel;
import org.asciidoctor.extension.Contexts;
import org.asciidoctor.extension.Reader;

import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.runtime.builtin.IRubyObject;

import java.util.*;

@Contexts(Contexts.CONTEXT_LISTING)
@ContentModel(ContentModel.COMPOUND)
public class CodeBlockProcessor extends BlockProcessor {

    private AsciiDocProcessor processor;

    public CodeBlockProcessor() {
        processor = AsciiDocProcessor.getProcessorInstance();
    }

    @Override
    public Object process(AbstractBlock parent, Reader reader, Map<String, Object> attributes) {

        String foo = "bar"; // Hit this point

        return createBlock(parent, "pass", "", attributes, new HashMap<Object, Object>());
    }
}

