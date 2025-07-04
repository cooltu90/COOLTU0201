package com.codingtu.cooltu.processor.builder.impl;

import com.codingtu.cooltu.constant.Constant;
import com.codingtu.cooltu.lib4j.data.java.JavaInfo;
import com.codingtu.cooltu.lib4j.data.map.StringBuilderValueMap;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tools.ConvertTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;
import com.codingtu.cooltu.processor.BuilderType;
import com.codingtu.cooltu.processor.annotation.msthread.MainThread;
import com.codingtu.cooltu.processor.annotation.msthread.SubThread;
import com.codingtu.cooltu.processor.builder.base.FragmentBaseBuilderBase;
import com.codingtu.cooltu.processor.builder.core.UiBaseBuilder;
import com.codingtu.cooltu.processor.builder.core.UiBaseInterface;
import com.codingtu.cooltu.processor.lib.param.Params;
import com.codingtu.cooltu.processor.lib.tools.BaseTools;
import com.codingtu.cooltu.processor.lib.tools.ElementTools;

import java.util.List;

import javax.lang.model.element.ExecutableElement;

public class FragmentBaseBuilder extends FragmentBaseBuilderBase implements UiBaseInterface {

    /**************************************************
     *
     * 初始化
     *
     **************************************************/
    private final UiBaseBuilder uiBaseBuilder;

    public String msThreadInterfaceFullName;
    public String msThreadFullName;
    public String msThreadFieldName;
    public BaseEs<ExecutableElement> msThreadMethodEs = Es.es();

    private StringBuilder onCompleteOtherLineSb = new StringBuilder();


    private StringBuilder otherLineSb = new StringBuilder();

    public FragmentBaseBuilder(JavaInfo info) {
        super(info);
        uiBaseBuilder = new UiBaseBuilder(this) {
            @Override
            protected BaseTools.GetThis<UiBaseBuilder> getChildGetter() {
                return BaseTools.getFragBaseChildGetter();
            }

            @Override
            protected BaseTools.GetParent<UiBaseBuilder> getParentGetter() {
                return BaseTools.getFragBaseParentGetter();
            }

        };
    }

    @Override
    protected BuilderType getBuilderType() {
        return BuilderType.fragment;
    }

    @Override
    protected boolean isBuild() {
        return true;
    }

    @Override
    protected void beforeBuild(List<String> lines) {
        super.beforeBuild(lines);
        //Logs.i(lines);
    }

    @Override
    public String obtainSymbol() {
        return javaInfo.fullName;
    }

    public UiBaseBuilder getUiBaseBuilder() {
        return uiBaseBuilder;
    }

    @Override
    public StringBuilderValueMap<String> getMap() {
        return map;
    }

    @Override
    public JavaInfo getJavaInfo() {
        return javaInfo;
    }

    public boolean addField(String sign, String type, String name) {
        return uiBaseBuilder.addField(sign, type, name);
    }

    /**************************************************
     *
     * 设置数据
     *
     **************************************************/
    @Override
    protected void dealLines() {
        uiBaseBuilder.dealLines();

        if (StringTool.isNotBlank(msThreadInterfaceFullName)) {
            addLnTag(msThreadInterface, ", " + msThreadInterfaceFullName);
            addField(Constant.SIGN_PROTECTED, msThreadFullName, msThreadFieldName);

            addLnTag(onCompleteOtherLineSb, "        [ftpPlayActivityMSThread] = [FtpPlayActivityMSThread].obtain().dealer(this);",
                    msThreadFieldName, msThreadFullName);
            addLnTag(onCompleteOtherLineSb, "        [ftpPlayActivityMSThread].start();", msThreadFieldName);

            addLnTag(otherLineSb, "    /**************************************************");
            addLnTag(otherLineSb, "     * MsThread");
            addLnTag(otherLineSb, "     **************************************************/");


            msThreadMethodEs.ls(new Es.EachEs<ExecutableElement>() {
                @Override
                public boolean each(int position, ExecutableElement element) {
                    Params params = ElementTools.getMethodParamKvs(element);

                    String simpleName = ElementTools.simpleName(element);
                    String methodParams = params.getMethodParams();
                    String params1 = params.getParams();
                    String sendMethodName = ConvertTool.toClassType(simpleName);

                    boolean isDelay = false;
                    long defaultDelayMillis = -1;
                    MainThread mainThread = element.getAnnotation(MainThread.class);
                    if (mainThread != null) {
                        isDelay = mainThread.isDelay();
                        defaultDelayMillis = mainThread.defaultDelayMillis();
                    }

                    SubThread subThread = element.getAnnotation(SubThread.class);
                    if (subThread != null) {
                        isDelay = subThread.isDelay();
                        defaultDelayMillis = subThread.defaultDelayMillis();
                    }

                    StringBuilder delayParamSb = new StringBuilder();
                    StringBuilder delayParamSb1 = new StringBuilder();
                    if (isDelay) {
                        if (defaultDelayMillis < 0) {
                            delayParamSb.append("long delayMillis");
                            delayParamSb1.append("delayMillis");
                            if (StringTool.isNotBlank(methodParams)) {
                                delayParamSb.append(", ");
                            }
                            if (StringTool.isNotBlank(params1)) {
                                delayParamSb1.append(", ");
                            }
                        }
                    }


                    addLnTag(otherLineSb, "    @Override");
                    addLnTag(otherLineSb, "    public void [dealDataStart]([params]) {",
                            simpleName, methodParams);
                    addLnTag(otherLineSb, "    }");
                    addLnTag(otherLineSb, "");

                    addLnTag(otherLineSb, "    protected boolean sendMessageFor[DealToast]([delayParam][String str]) {",
                            sendMethodName, delayParamSb.toString(), methodParams);
                    addLnTag(otherLineSb, "        if ([ftpPlayActivityMSThread] != null) {", msThreadFieldName);
                    addLnTag(otherLineSb, "            return [ftpPlayActivityMSThread].sendMessageFor[DealToast]([delayParam][str]);",
                            msThreadFieldName, sendMethodName, delayParamSb1.toString(), params1);
                    addLnTag(otherLineSb, "        }");
                    addLnTag(otherLineSb, "        return true;");
                    addLnTag(otherLineSb, "    }");

                    return false;
                }
            });

            addLnTag(otherLineSb, "    protected void stopMsThread() {");
            addLnTag(otherLineSb, "        if ([tesUdMsThread] != null)", msThreadFieldName);
            addLnTag(otherLineSb, "            [tesUdMsThread].stop();", msThreadFieldName);
            addLnTag(otherLineSb, "        [tesUdMsThread] = null;", msThreadFieldName);
            addLnTag(otherLineSb, "    }");

            addLnTag(onDestroySb, "        stopMsThread();");

        }

        otherIf(otherLineSb.toString());
        onCreateCompleteOtherIf(onCompleteOtherLineSb.toString());
    }

    @Override
    public String getDefulatViewParent() {
        return "view.";
    }

    @Override
    public void isCheckForm(int index, boolean isCheckForm) {

    }

    @Override
    public void addOthers(String others) {
        otherLineSb.append(others);
    }

    @Override
    public void onClickCheckFormIf(int clickViewInfoIndex, String s) {

    }

}
/* model_temp_start
package [[pkg]];

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

public abstract class [[name]] extends [[baseClass]] implements View.OnClickListener, View.OnLongClickListener, [[netBackIFullName]][[msThreadInterface]]{
                                                                                                    [<sub>][for][field]
    [sign] [type] [name];
                                                                                                    [<sub>][for][field]
    public String baseClassName = "[[name]]";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
                                                                                                    [<sub>][if][layout]
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = [inflateToolFullName].inflate(inflater, [layout], container);
                                                                                                    [<sub>][for][findView]
        [fieldName] = [parent]findViewById([rPkg].R.id.[id]);
                                                                                                    [<sub>][for][findView]
                                                                                                    [<sub>][for][colorStrInit]
        [name] = android.graphics.Color.parseColor("[color]");
                                                                                                    [<sub>][for][colorStrInit]
                                                                                                    [<sub>][for][colorResInit]
        [name] = [resourceToolFullName].getColor([id]);
                                                                                                    [<sub>][for][colorResInit]
                                                                                                    [<sub>][for][dpInit]
        [name] = [mobileToolFullName].dpToPx([value]);
                                                                                                    [<sub>][for][dpInit]
                                                                                                    [<sub>][for][dimenInit]
        [name] = [resourceToolFullName].getDimen([id]);
                                                                                                    [<sub>][for][dimenInit]
                                                                                                    [<sub>][for][startInit]
        [name] = [passFullName].[name](getIntent());
                                                                                                    [<sub>][for][startInit]
        String nowBaseClassName = getClass().getSimpleName() + "Base";
        if (nowBaseClassName.equals(baseClassName)) {
            onCreateComplete();
        }
        return view;
    }
                                                                                                    [<sub>][if][layout]

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();
[[useAdapters]]
                                                                                                    [<sub>][for][listAdapter]
                                                                                                    [<sub>][if][defaultListAdapter]
        // [adapterName]
        [adapterName] = new [adapterFullName]();
                                                                                                    [<sub>][if][defaultListAdapter]
                                                                                                    [<sub>][if][defaultListMoreAdapter]
        // [adapterName]
        [adapterName] = new [adapterFullName]() {
            @Override
            protected void loadMore(int page) {
                [adapterName]LoadMore(page);
            }
        };
                                                                                                    [<sub>][if][defaultListMoreAdapter]
        [adapterName].setVH([vhFullName].class);
        [adapterName].setClick(this);
        [rvName].setAdapter([adapterName]);
        new [configName]().config(getAct(), [rvName], () -> [rvName]Obj());
                                                                                                    [<sub>][for][listAdapter]
                                                                                                    [<sub>][for][setOnClick]
        [fieldName].setOnClickListener(this);
                                                                                                    [<sub>][for][setOnClick]
                                                                                                    [<sub>][for][setOnLongClick]
        [fieldName].setOnLongClickListener(this);
                                                                                                    [<sub>][for][setOnLongClick]
                                                                                                    [<sub>][if][onCreateCompleteOther]
[onCreateCompleteOther]
                                                                                                    [<sub>][if][onCreateCompleteOther]
    }

[[adapters]]

    @Override
    public void onDestroyView() {
        super.onDestroyView();
                                                                                                    [<sub>][if][onDestroy]
[onDestroy]
                                                                                                    [<sub>][if][onDestroy]
    }
                                                                                                    [<sub>][for][adapterObjs]
    protected Object [rvName]Obj() {
        return null;
    }
                                                                                                    [<sub>][for][adapterObjs]

    @Override
    public void onClick(View v) {
                                                                                                    [<sub>][if][superOnClick]
        super.onClick(v);
                                                                                                    [<sub>][if][superOnClick]
        try {
            switch (v.getId()) {
                                                                                                    [<sub>][for][onClickSwith]
                                                                                                    [<sub>][for][onClickCase]
                case [id]:
                                                                                                    [<sub>][for][onClickCase]
                                                                                                    [<sub>][if][onClickCheckLogin]
                    if (!isLogin(getAct())) {
                        return;
                    }
                                                                                                    [<sub>][if][onClickCheckLogin]
                    [methodName](
                                                                                                    [<sub>][if][onClickSwitchParams]
                            v[divider]
                                                                                                    [<sub>][if][onClickSwitchParams]
                                                                                                    [<sub>][for][onClickSwitchParams]
                            ([type]) v.getTag([pkg].R.id.tag_[index])[divider]
                                                                                                    [<sub>][for][onClickSwitchParams]
                    );
                    break;
                                                                                                    [<sub>][for][onClickSwith]
            }
        } catch (Exception e) {
            com.codingtu.cooltu.lib4a.log.Logs.e(e);
            if (!(e instanceof com.codingtu.cooltu.lib4a.exception.NotToastException)) {
                toast(e.getMessage());
            }
        }
    }
                                                                                                    [<sub>][for][onClickMethods]
    protected void [methodName]([params]) throws Exception {}
                                                                                                    [<sub>][for][onClickMethods]

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
                                                                                                    [<sub>][for][onLongClickSwith]
                                                                                                    [<sub>][for][onLongClickCase]
            case [id]:
                                                                                                    [<sub>][for][onLongClickCase]
                                                                                                    [<sub>][if][onLongClickCheckLogin]
                if (!isLogin(getAct())) {
                    return false;
                }
                                                                                                    [<sub>][if][onLongClickCheckLogin]
                return [methodName](
                                                                                                    [<sub>][if][onLongClickSwitchParams]
                        v[divider]
                                                                                                    [<sub>][if][onLongClickSwitchParams]
                                                                                                    [<sub>][for][onLongClickSwitchParams]
                        ([type]) v.getTag([pkg].R.id.tag_[index])[divider]
                                                                                                    [<sub>][for][onLongClickSwitchParams]
                );
                                                                                                    [<sub>][for][onLongClickSwith]
        }
                                                                                                    [<sub>][if][superOnLongClick]
        return super.onLongClick(v);
                                                                                                    [<sub>][if][superOnLongClick]
                                                                                                    [<sub>][if][superOnLongClickFalse]
        return false;
                                                                                                    [<sub>][if][superOnLongClickFalse]

    }
                                                                                                    [<sub>][for][onLongClickMethods]
    protected boolean [methodName]([params]) {return false;}
                                                                                                    [<sub>][for][onLongClickMethods]


                                                                                                    [<sub>][for][loadMore]
    protected abstract void [adapterName]LoadMore(int page);
                                                                                                    [<sub>][for][loadMore]
    @Override
    public void accept(String code, Result<ResponseBody> result, [[coreSendParamsFullName]] params, List objs) {
                                                                                                    [<sub>][if][superAccept]
        super.accept(code, result, params, objs);
                                                                                                    [<sub>][if][superAccept]

                                                                                                    [<sub>][for][accept]
        if ("[methodName]".equals(code)) {
            new [netBackFullName]() {
                @Override
                public void accept(String code, Result<ResponseBody> result, [coreSendParamsFullName] params, List objs) {
                    super.accept(code, result, params, objs);
                    [methodName]([params]);
                }
            }.accept(code, result, params, objs);
        }
                                                                                                    [<sub>][for][accept]
    }
                                                                                                    [<sub>][for][acceptMethod]
    protected void [methodName]([params]) {}
                                                                                                    [<sub>][for][acceptMethod]
    @Override
    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == android.app.Activity.RESULT_OK) {
                                                                                                    [<sub>][for][actBack]
            [ifSign] (requestCode == [code4RequestFullName].[code]) {
                [methodName]([for:actBackParam][passFullName].[name](data)[if:actBackParamDivider], [if:actBackParamDivider][for:actBackParam]);
            }
                                                                                                    [<sub>][for][actBack]
        }
    }
                                                                                                    [<sub>][for][actBackMethod]
    protected void [methodName]([params]) {}
                                                                                                    [<sub>][for][actBackMethod]
                                                                                                    [<sub>][if][toastDialog]
    private [toastDialogFullName] toastDialog;

    protected [toastDialogFullName] getToastDialog() {
        if (toastDialog == null)
            toastDialog = new [toastDialogFullName](getAct())
                    .destroys(this)
                    .setLayout([layout])
                    .build();
        return toastDialog;
    }
                                                                                                    [<sub>][if][toastDialog]
                                                                                                    [<sub>][if][noticeDialog]
    private [noticeDialogFullName] noticeDialog;

    protected [noticeDialogFullName] getNoticeDialog() {
        if (noticeDialog == null)
            noticeDialog = new [noticeDialogFullName](getAct())
                    .destroys(this)
                    .setLayout([layout])
                    .onClick(v -> {
                        noticeDialogYes(noticeDialog.obtainData());
                    })
                    .build();
        return noticeDialog;
    }

    public void noticeDialogYes(Object data) {
        noticeDialog.hidden();
    }
                                                                                                    [<sub>][if][noticeDialog]
                                                                                                    [<sub>][for][editDialog]
    protected [editDialogFullName] [edName];

    protected void show[edClassName](String text[if:edShowParam], [type] [name][if:edShowParam]) {
        if ([edName] == null)
            [edName] = new [editDialogFullName](getAct())
                    .destroys(this)
                    .setTitle("[title]")
                    .setHint("[hint]")
                    .setInputType([inputType])
                    .setLayout([layout])
                                                                                                    [<sub>][if][setTextWatcher]
                    .setTextWatcher(get[edClassName]TextWatcher())
                                                                                                    [<sub>][if][setTextWatcher]
                                                                                                    [<sub>][if][stopAnimation]
                    .stopAnimation()
                                                                                                    [<sub>][if][stopAnimation]
                    .setOnBtClick(new [editDialogFullName].OnBtClick() {
                        @Override
                        public void onCancel(Object obj) {

                        }

                        @Override
                        public boolean check(String text, Object obj) {
                            return [edName]Yes(text[if:edUseYes], [if:edUseYesConvert]([type])[if:edUseYesConvert]obj[if:edUseYes]);
                        }

                        @Override
                        public void onCheckPass(Object obj) {

                        }
                    })
                    .build();
        [edName].setEditText(text);
        [edName].setObject([setObject]);
        [edName].show();
    }


    protected boolean [edName]Yes(String text[if:edYesParam], [type] [name][if:edYesParam]) {
        return false;
    }
                                                                                                    [<sub>][if][setTextWatcherMethod]
    protected [edTextWatcherFullName] get[edClassName]TextWatcher() {
        return null;
    }
                                                                                                    [<sub>][if][setTextWatcherMethod]
                                                                                                    [<sub>][for][editDialog]
                                                                                                    [<sub>][for][dialog]
    protected [dialogFullName] [dialogName];
                                                                                                    [<sub>][for][showDialog]
    protected void show[dialogClassName]([showDialogParam]) {
        if ([dialogName] == null) {
            [dialogName] = new [dialogFullName](getAct())
                    .destroys(this)
                    .setTitle("[title]")
                                                                                                    [<sub>][if][showDialogSetContentStr]
                    .setContent("[content]")
                                                                                                    [<sub>][if][showDialogSetContentStr]
                                                                                                    [<sub>][if][showDialogSetContent]
                    .setContent(content)
                                                                                                    [<sub>][if][showDialogSetContent]
                    .setLeftBtText("[left]")
                    .setRighBtText("[right]")
                    .setLayout([layout])
                    .setOnBtClick(new [onBtClickFullName]() {
                        @Override
                        public void onLeftClick(Object obj) {
                            [dialogName]Left([if:showDialogLeftObj][if:showDialogLeftObjConvert]([type])[if:showDialogLeftObjConvert]obj[if:showDialogLeftObj]);
                        }

                        @Override
                        public void onRightClick(Object obj) {
                            [dialogName]Right([if:showDialogRightObj][if:showDialogRightObjConvert]([type])[if:showDialogRightObjConvert]obj[if:showDialogRightObj]);
                        }
                    })
                    .build();
        }[if:showDialogElse] else {[if:showDialogElse]
                                                                                                    [<sub>][if][showDialogUpdataContent]
            [dialogName].updateContent(content);
        }
                                                                                                    [<sub>][if][showDialogUpdataContent]
        [dialogName].setObject([obj]);
        [dialogName].show();
    }
                                                                                                    [<sub>][for][showDialog]
    protected void [dialogName]Left([if:leftParam][type] [name][if:leftParam]) { }
    protected void [dialogName]Right([if:rightParam][type] [name][if:rightParam]) { }
                                                                                                    [<sub>][for][dialog]
                                                                                                    [<sub>][for][initMethod]
    protected [typeFullName] [methodName]() {
        if ([field] == null) {
            [field] = new [typeFullName]();
                                                                                                    [<sub>][if][initAddDestory]
            [destoryToolFullName].onDestory(getAct(), [field]);
                                                                                                    [<sub>][if][initAddDestory]
            [initMethodName]([field]);
        }
        return [field];
    }

    protected void [initMethodName]([typeFullName] [field]) {}
                                                                                                    [<sub>][for][initMethod]
                                                                                                    [<sub>][if][other]
[other]
                                                                                                    [<sub>][if][other]
}
model_temp_end */
