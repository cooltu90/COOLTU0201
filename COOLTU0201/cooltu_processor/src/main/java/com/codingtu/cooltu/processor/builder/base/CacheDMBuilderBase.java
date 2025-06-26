package com.codingtu.cooltu.processor.builder.base;
import java.util.ArrayList;
import java.util.List;

public abstract class CacheDMBuilderBase extends com.codingtu.cooltu.processor.builder.core.CoreBuilder {
    protected StringBuilder pkg;
    protected StringBuilder className;
    protected StringBuilder methods;

    public CacheDMBuilderBase(com.codingtu.cooltu.lib4j.data.java.JavaInfo info) {
        super(info);
        pkg = map.get("pkg");
        className = map.get("className");
        methods = map.get("methods");

    }



    @Override
    protected void dealLinesInParent() {

    }

    @Override
    protected List<String> getTempLines() {
        List<String> lines = new ArrayList<>();
        lines.add("package [[pkg]];");
        lines.add("");
        lines.add("import com.codingtu.cooltu.lib4a.dm.BaseCacheDM;");
        lines.add("import com.codingtu.cooltu.lib4j.destory.Destroys;");
        lines.add("");
        lines.add("public class [[className]] {");
        lines.add("");
        lines.add("[[methods]]");
        lines.add("}");

        return lines;
    }
}
