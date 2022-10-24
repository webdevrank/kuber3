package com.rank.ccms.config.ccmstablerelation.xmlconfig;

import com.rank.ccms.config.callroutingprops.jaxb.CRProperty;
import com.rank.ccms.config.callroutingprops.jaxb.CallRoutingProperties;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("ccmsCallRoutingConfigUtil")
public class CCMSCallRoutingConfigUtil {

    Logger logger = Logger.getLogger(this.getClass());
    private HashMap<String, String> callRoutingConfigMap = new HashMap<>();

    public HashMap<String, String> getCallRoutingConfigMap() {
        return callRoutingConfigMap;
    }

    public CCMSCallRoutingConfigUtil() throws Exception {
        readAndUpdateCallROutingProperty();
    }

    private HashMap<String, String> readAndUpdateCallROutingProperty() throws Exception {

        String projectHome = System.getenv("VIDEOBANKING_HOME");
        if (null == projectHome) {
            logger.error("" + this.getClass().getSimpleName() + ":ERROR: Environment Variable 'VIDEOBANKING_HOME' is not defined.");
        }
        String xmlConfigLocation = projectHome + File.separator + "configuration";
        JAXBContext jaxbContext = JAXBContext.newInstance(CallRoutingProperties.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        CallRoutingProperties callRoutingProperties
                = (CallRoutingProperties) jaxbUnmarshaller.unmarshal(new File(xmlConfigLocation + File.separator + "CallRoutingProperties.xml"));
        List<CRProperty> crPropertyList = callRoutingProperties.getCRProperty();
        callRoutingConfigMap = new HashMap<>();
        for (CRProperty crProperty : crPropertyList) {
            callRoutingConfigMap.put(crProperty.getPropertySequence() + "", crProperty.getPropertyName());
        }
        callRoutingConfigMap = (HashMap<String, String>) sortByKeys(callRoutingConfigMap);
        return callRoutingConfigMap;
    }

    private static <K extends Comparable, V extends Comparable> Map<K, V> sortByKeys(Map<K, V> map) {
        List<K> keys = new LinkedList<>(map.keySet());
        Collections.sort(keys);

        //LinkedHashMap will keep the keys in the order they are inserted
        //which is currently sorted on natural ordering
        Map<K, V> sortedMap = new LinkedHashMap<>();
        for (K key : keys) {
            sortedMap.put(key, map.get(key));
        }

        return sortedMap;
    }

    private static <K extends Comparable, V extends Comparable> Map<K, V> sortByValues(Map<K, V> map) {
        List<Map.Entry<K, V>> entries = new LinkedList<>(map.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {

            @Override
            public int compare(Entry<K, V> o1, Entry<K, V> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        Map<K, V> sortedMap = new LinkedHashMap<>();

        for (Map.Entry<K, V> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

}
