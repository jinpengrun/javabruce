package com.jd.www.bigdata.espertest.context.EventType;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhujinpeng on 16/3/28.
 */
public class PersonMap{
    public static void main(String[] args) {
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();
        EPAdministrator admin = epService.getEPAdministrator();

        // Person定义
        Map<String,Object> person = new HashMap<String,Object>();
        person.put("name", String.class);
        person.put("age", int.class);
        person.put("children", List.class);
        person.put("phones", Map.class);

        // 注册Person到Esper
        admin.getConfiguration().addEventType("Person", person);

        //select age , chrildren from Person where name="luonanqin"


    }
}
