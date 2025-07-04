package com.codingtu.cooltu.processor;

import com.codingtu.cooltu.processor.annotation.ModuleInfo;
import com.codingtu.cooltu.processor.annotation.create.CreateAct;
import com.codingtu.cooltu.processor.annotation.create.CreateAdapter;
import com.codingtu.cooltu.processor.annotation.create.CreateFragment;
import com.codingtu.cooltu.processor.annotation.data.Data;
import com.codingtu.cooltu.processor.annotation.delete.DeleteAct;
import com.codingtu.cooltu.processor.annotation.dm.CacheConfig;
import com.codingtu.cooltu.processor.annotation.msthread.MsThread;
import com.codingtu.cooltu.processor.annotation.net.Apis;
import com.codingtu.cooltu.processor.annotation.res.ResFor;
import com.codingtu.cooltu.processor.annotation.res.ResForFragment;
import com.codingtu.cooltu.processor.annotation.ui.ActBase;
import com.codingtu.cooltu.processor.annotation.ui.DefaultCode;
import com.codingtu.cooltu.processor.annotation.ui.FragmentBase;
import com.codingtu.cooltu.processor.annotation.ui.VH;
import com.codingtu.cooltu.processor.deal.ActBaseDeal;
import com.codingtu.cooltu.processor.deal.CacheConfigDeal;
import com.codingtu.cooltu.processor.deal.CreateActDeal;
import com.codingtu.cooltu.processor.deal.CreateAdapterDeal;
import com.codingtu.cooltu.processor.deal.CreateFragmentDeal;
import com.codingtu.cooltu.processor.deal.DataDeal;
import com.codingtu.cooltu.processor.deal.DefaultCodeDeal;
import com.codingtu.cooltu.processor.deal.DeleteActDeal;
import com.codingtu.cooltu.processor.deal.FragmentBaseDeal;
import com.codingtu.cooltu.processor.deal.ModuleInfoDeal;
import com.codingtu.cooltu.processor.deal.MsThreadDeal;
import com.codingtu.cooltu.processor.deal.NetDeal;
import com.codingtu.cooltu.processor.deal.ResForDeal;
import com.codingtu.cooltu.processor.deal.ResForFragmentDeal;
import com.codingtu.cooltu.processor.deal.VHDeal;

/**************************************************
 *
 * 此处的顺序决定了读取顺序
 *
 **************************************************/
public class SupportTypes {

    public static Class[] types() {
        return new Class[]{
                ModuleInfo.class, ModuleInfoDeal.class,
                DefaultCode.class, DefaultCodeDeal.class,
                VH.class, VHDeal.class,
                ActBase.class, ActBaseDeal.class,
                FragmentBase.class, FragmentBaseDeal.class,
                ResFor.class, ResForDeal.class,
                ResForFragment.class, ResForFragmentDeal.class,
                MsThread.class, MsThreadDeal.class,
                Apis.class, NetDeal.class,
                CreateAct.class, CreateActDeal.class,
                CreateAdapter.class, CreateAdapterDeal.class,
                CreateFragment.class, CreateFragmentDeal.class,
                CacheConfig.class, CacheConfigDeal.class,
                DeleteAct.class, DeleteActDeal.class,
                Data.class, DataDeal.class
        };
    }

}
