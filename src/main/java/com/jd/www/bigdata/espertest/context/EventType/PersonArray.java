package com.jd.www.bigdata.espertest.context.EventType;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EventType;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by zhujinpeng on 16/3/28.
 */
public class PersonArray {
    public static void main(String[] args) {
        //数组模式
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();
        EPAdministrator admin = epService.getEPAdministrator();

        // Address定义
        String[] addressPropNames = { "road", "street", "houseNo" };
        Object[] addressPropTypes = { String.class, String.class, int.class };

        // Child定义
        String[] childPropNames = { "name", "age" };
        Object[] childPropTypes = { String.class, int.class };

        // Person定义
        String[] personPropNames = { "name", "age", "children", "phones", "address" };
        Object[] personPropTypes = { String.class, int.class, "Child[]", Map.class, "Address" };


        // 注册Address到Esper
        admin.getConfiguration().addEventType("Address", addressPropNames, addressPropTypes);
        // 注册Child到Esper
        admin.getConfiguration().addEventType("Child", childPropNames, childPropTypes);
        // 注册Person到Esper
        admin.getConfiguration().addEventType("Person", personPropNames, personPropTypes);

        // 新增一个gender属性
        admin.getConfiguration().updateObjectArrayEventType("Person", new String[] { "gender" }, new Object[] { int.class });

        /** 输出结果：
         * Person props: [name, age, children, phones, address, gender]
         */
        EventType event = admin.getConfiguration().getEventType("Person");

        //xml模式 没有研究过 。。。
    }
}
