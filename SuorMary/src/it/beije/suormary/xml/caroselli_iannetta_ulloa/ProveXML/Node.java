package it.beije.suormary.xml.caroselli_iannetta_ulloa.ProveXML;
import java.util.*;


public class Node {
    private String tagName;
    private String value;
    private final Map<String, Node> children;

    public Node(String tagName) {
        this.tagName = tagName;
        this.children = new HashMap<>();
    }

    public Node(String tagName, String value) {
        this.tagName = tagName;
        this.value = value;
        this.children = new HashMap<>();
    }

    public void addChild(String tagName, Node child) {
        this.children.put(tagName, child);
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Map<String, Node> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Node{" +
                "tagName='" + tagName + '\'' +
                ", value='" + value + '\'' +
                ", children=" + children +
                '}';
    }
}
