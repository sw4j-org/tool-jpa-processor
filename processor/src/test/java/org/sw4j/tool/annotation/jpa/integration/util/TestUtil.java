/*
 * Copyright (C) 2016 Uwe Plonus
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sw4j.tool.annotation.jpa.integration.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * An utility class to support testing of the annotation processor.
 *
 * @author Uwe Plonus
 */
public class TestUtil {

    /** The target folder for the compiled classes. */
    private static final String TARGET_FOLDER = "target/jpa-classes/";

    /** The document that is used to testResultFile the result. */
    private Document resultDocument;

    /** */
    private final Set<Node> visitedNodes;

    public TestUtil() {
        visitedNodes = new HashSet<>();
    }

    /**
     * Method to compile all classes in the given folder. The generated classes are placed in the
     * folder {@code target/jpa-classes/}. After compilation the resulting XML file will be read.
     *
     * @param folder the folder that contains the JPA classes to process.
     * @param resultFile the resulting file of the generator. Must match the option of the
     *  annotation processor.
     * @param options the options for the compiling (primary the options for the annotation
     *  processor).
     * @throws ParserConfigurationException when the parser cannot be created.
     * @throws SAXException when the parsing fails.
     * @throws IOException when the IO fails.
     */
    public void compileClasses(String folder, String resultFile, String[] options)
            throws ParserConfigurationException, SAXException, IOException {
        File entityFolder = new File(folder);
        List<File> files = new LinkedList<>();
        getFiles(entityFolder, files);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        Assert.assertNotNull(compiler, "Need a java compiler for executing the tests.");
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, Charset.forName("UTF-8"));

        File classesFolder = new File(TARGET_FOLDER);
        classesFolder.mkdirs();

        Iterable<? extends JavaFileObject> entities =
                fileManager.getJavaFileObjectsFromFiles(files);
        List<String> opts = new LinkedList<>();
        opts.add("-d");
        opts.add(TARGET_FOLDER);
        opts.addAll(Arrays.asList(options));
        compiler.getTask(null, fileManager, null, opts, null, entities).call();

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        resultDocument = builder.parse(new File(resultFile));
    }

    /**
     * Helper method to collect all java files (with suffix {@code .java}) inside a folder. If the
     * folder contains subfolders this are also scanned for java classes.
     *
     * @param folder the folder to scan for java classes.
     * @param javaFiles the resulting list that contains all java files.
     */
    private void getFiles(File folder, List<File> javaFiles) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            for (File file: files) {
                getFiles(file, javaFiles);
            }
        } else if (folder.getName().endsWith(".java")) {
            javaFiles.add(folder);
        }
    }

    /**
     * Traverses the complete DOM and checks that every node is retrieved with {@link #getNode(java.lang.String)}. The
     * check is done with {@link Assert}.
     */
    public void checkVisitedNodes() {
        checkVisitedNodes(resultDocument.getDocumentElement());
    }

    /**
     * Check that the given node is tested with the method {@link #getNode(java.lang.String)}. If the current node is an
     * element then each embedded element will recursively be checked.
     *
     * @param node the node to check.
     */
    private void checkVisitedNodes(Element node) {
        if (!visitedNodes.contains(node)) {
            StringBuilder path = new StringBuilder();
            NamedNodeMap attributes = node.getAttributes();
            if (attributes.getLength() > 0) {
                path.append("[");
                for (int i = 0; i < attributes.getLength(); i++) {
                    if (i > 0) {
                        path.append(" and ");
                    }
                    Node attribute = attributes.item(i);
                    path.append("@").append(attribute.getNodeName()).append("='")
                            .append(attribute.getNodeValue()).append("'");
                }
                path.append("]");
            }
            path.insert(0, node.getNodeName());
            Node parent = node.getParentNode();
            while (parent != null) {
                path.insert(0, "/");
                if (parent.getNodeType() != Node.DOCUMENT_NODE) {
                    path.insert(0, parent.getNodeName());
                }
                parent = parent.getParentNode();
            }
            Assert.fail(new StringBuilder("Expected the node \"").append(path)
                        .append("\" to be tested.").toString());
        }
        Assert.assertTrue(visitedNodes.contains(node),
                new StringBuilder("Expected the node \"").append(node.toString())
                        .append("\" to be tested.").toString());
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                checkVisitedNodes((Element)child);
            }
        }
    }

    /**
     * Returns the root element of the document.
     *
     * @return the root element.
     */
    public Element getRootElement() {
        Element root = resultDocument.getDocumentElement();
        visitedNodes.add(root);
        return root;
    }

    /**
     * Returns the node with the given XPath.
     *
     * @param path the XPath of the node to retrieve.
     * @return the node denoted by the XPath.
     * @throws XPathExpressionException if the XPath expression is invalid.
     */
    public Node getNode(String path) throws XPathExpressionException {
        XPath xpath = XPathFactory.newInstance().newXPath();
        Node result = (Node)xpath.evaluate(path, resultDocument, XPathConstants.NODE);
        visitedNodes.add(result);
        return result;
    }

}
