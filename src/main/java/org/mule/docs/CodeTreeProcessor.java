package org.mule.docs;

import org.asciidoctor.ast.AbstractBlock;
import org.asciidoctor.ast.Block;
import org.asciidoctor.ast.Document;
import org.asciidoctor.extension.ContentModel;
import org.asciidoctor.extension.Contexts;
import org.asciidoctor.extension.Treeprocessor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C) MuleSoft, Inc - All Rights Reserved
 * Created by Sean Osterberg on 8/10/15.
 */

public class CodeTreeProcessor extends Treeprocessor {

    private Document document;

    public CodeTreeProcessor(Map<String, Object> config) {
        super(config);
    }

    @Override
    public Document process(Document document) {

        this.document = document;

        List<AbstractBlock> blocks = this.document.blocks();

        for(AbstractBlock block : blocks) {
            if (block instanceof Block) {
                String style = block.getStyle();
                if (style != null) {
                    if (style.equals("source")) {
                        Map<String, Object> attributes = block.getAttributes();
                        System.out.println("BEFORE:");
                        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
                            System.out.println("Key: " + entry.getKey());
                            System.out.println("Value: " + entry.getValue() + "\n");
                        }
                        //block.setAttr("style", "source", false);
                        block.setAttr("language", "java", true);
                        block.setAttr("linenums", "linenums", false);

                        Map<String, Object> attribs = block.getAttributes();
                        System.out.println("AFTER:");
                        for (Map.Entry<String, Object> entry : attribs.entrySet()) {
                            System.out.println("Key: " + entry.getKey());
                            System.out.println("Value: " + entry.getValue() + "\n");
                        }

                    }
                }
            }
        }/*
        for (int i = 0; i < blocks.size(); i++) {
            final AbstractBlock currentBlock = blocks.get(i);
            if(currentBlock instanceof Block) {
                Block block = (Block)currentBlock;
                List<String> lines = block.getLines();
                String style = block.getStyle();
                if (style != null) {
                    if (style.equals("source")) {
                        blocks.set(i, addNewBlock(block));
                    }
                }
            }
        }*/

        return this.document;
    }

    public Block addNewBlock(Block block) {
        List<String> lines = block.getLines();
        Map<String, Object> attributes = block.getAttributes();
        attributes.put("style", "source");
        attributes.put("linenums", "linenums");
        return createBlock(this.document, "listing", lines, attributes, new HashMap<Object, Object>());
    }

        public Block convertToTerminalListing(Block block) {

        Map<String, Object> attributes = block.getAttributes();
        attributes.put("role", "terminal");
        StringBuilder resultLines = new StringBuilder();

        List<String> lines = block.getLines();

        for (String line : lines) {
            if (line.startsWith("$")) {
                resultLines.append("<span class=\"command\">")
                        .append(line.substring(2, line.length()))
                        .append("</command");
            }
            else {
                resultLines.append(line);
            }
        }

        return createBlock(this.document, "listing", Arrays.asList(resultLines.toString()), attributes,
                new HashMap<Object, Object>());
    }

}
