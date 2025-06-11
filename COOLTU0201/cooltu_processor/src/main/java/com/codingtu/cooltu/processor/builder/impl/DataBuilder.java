package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.FullName;
import com.codingtu.cooltu.constant.Pkg;
import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tools.ClassTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.builder.base.DataBuilderBase;
import com.codingtu.cooltu.processor.lib.log.Logs;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.path.CurrentPath;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import javax.lang.model.element.ExecutableElement;

public class DataBuilder extends DataBuilderBase {


    public static DataBuilder BUILDER = new DataBuilder();
    private BaseEs<ExecutableElement> methodEs = Es.es();

    private DataBuilder() {
        super(CurrentPath.javaInfo(FullName.DATA_DM));
    }

    @Override
    protected boolean isBuild() {
        return true;
    }

    @Override
    protected void dealLines() {
        addTag(pkg, Pkg.CORE_DATA);
        methodEs.ls(new Es.EachEs<ExecutableElement>() {
            @Override
            public boolean each(int position, ExecutableElement ee) {
                String methodName = ElementTools.simpleName(ee);
                Params paramKvs = ElementTools.getMethodParamKvs(ee);

                String returnType = ee.getReturnType().toString();

                StringBuilder methodParamSb = new StringBuilder();
                BaseEs<String> runFanxingEs = Es.es();
                BaseEs<String> taskParamEs = Es.es();
                BaseEs<String> runParamsEs = Es.es();
                paramKvs.ls(new Es.EachEs<KV<String, String>>() {
                    @Override
                    public boolean each(int position, KV<String, String> kv) {
                        methodParamSb.append(kv.k).append(" ").append(kv.v).append(", ");
                        taskParamEs.add(kv.v);

                        String k = ClassTool.covert(kv.k);
                        runFanxingEs.add(k);
                        runParamsEs.add(k + " " + kv.v);
                        return false;
                    }
                });

                String runFanxing = esToString(runFanxingEs);
                if (StringTool.isNotBlank(runFanxing)) {
                    runFanxing = "<" + runFanxing + ">";
                }

                String parentType = ElementTools.getParentType(ee);

                if (paramKvs.count() == 0) {
                    addLnTag(methods, "    public static void [methodName]([TaskResult1]<[User]> taskResult) {", methodName, FullName.TASK_RESULT_1, returnType);
                } else {
                    addLnTag(methods, "    public static void [methodName]([params]", methodName, methodParamSb.toString());
                    addLnTag(methods, "                            [TaskResult1]<[User]> taskResult) {", FullName.TASK_RESULT_1, returnType);
                }


                addLnTag(methods, "        [TaskDM]", FullName.TASK_DM);
                addLnTag(methods, "                .task([name])", esToString(taskParamEs));
                addLnTag(methods, "                .run(new [pkgTaskRun].TaskRun[1][String]() {", Pkg.LIB4A_TASK_RUN, paramKvs.count(), runFanxing);
                addLnTag(methods, "                    @Override");
                addLnTag(methods, "                    public void run([params]) {", esToString(runParamsEs));
                addLnTag(methods, "                        addResult(new [UserDataConfig]().[user]([name]));", parentType, methodName, esToString(taskParamEs));
                addLnTag(methods, "                    }");
                addLnTag(methods, "                })");
                addLnTag(methods, "                .result(taskResult)");
                addLnTag(methods, "                .error(new [OnError]() {", FullName.ON_ERROR);
                addLnTag(methods, "                    @Override");
                addLnTag(methods, "                    public void onError(Throwable throwable) {");
                addLnTag(methods, "                        if (taskResult != null) {");
                addLnTag(methods, "                            taskResult.result(null);");
                addLnTag(methods, "                        }");
                addLnTag(methods, "                    }");
                addLnTag(methods, "                }).add();");
                addLnTag(methods, "    }");

                return false;
            }
        });
    }

    private String esToString(BaseEs<String> es) {
        StringBuilder sb = new StringBuilder();
        es.ls(new Es.EachEs<String>() {
            @Override
            public boolean each(int position, String s) {
                if (position != 0) {
                    sb.append(", ");
                }
                sb.append(s);
                return false;
            }
        });
        return sb.toString();
    }

    public void addMethod(ExecutableElement ee) {
        methodEs.add(ee);
    }
}
/* model_temp_start
package [[pkg]];

public class DataDM {
[[methods]]
}
model_temp_end */