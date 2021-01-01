package com.JavaSelenium.Automation.core.utils;

import com.JavaSelenium.Automation.core.exception.FrameworkExceptions;
import com.JavaSelenium.Automation.core.helper.Constants;
import com.JavaSelenium.Automation.core.logger.Logs;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Config {
    private static Map<String, String> configValues = null;

    private Config() throws FrameworkExceptions {
    }

    static {
        configValues = new HashMap<>();
        try {
            readConfigContent();
        } catch (FrameworkExceptions frameworkExceptions) {
            throw new Error(frameworkExceptions);
        }
    }

    /**
     * Gets the config content.
     *
     * @return the config content
     * @throws Exception the exception
     */
    private static void readConfigContent() throws FrameworkExceptions {
        Logs.trace("Executing the function to read the Config.xml file content mentioned as:"
                + Constants.CONFIGFILEPATH);
        Logs.info("Reading the Configuration file");
        File file = new File(Constants.CONFIGFILEPATH);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            NodeList configLines = document.getElementsByTagName("Config");
            for (int i = 0; i < configLines.getLength(); i++) {
                Node configLine = configLines.item(i);
                if (configLine.hasAttributes()) {
                    NamedNodeMap nameValuePair = configLine.getAttributes();
                    Logs.trace("Fetching for the name and value attribute values from the Coniguration file");
                    configValues.put(nameValuePair.getNamedItem("name").getNodeValue().toUpperCase(),
                            nameValuePair.getNamedItem("value").getNodeValue());
                }
            }
            Logs.info("Completed the reading of the Configuration file");
        } catch (Exception e) {
            Logs.error("Exception occured while reading the contents from Configuration file. The excetpion messge is:" + e);
            throw new FrameworkExceptions("An exception occured while getting the content from config file: " + e.getMessage());
        }
    }

    /**
     * Gets the config property.
     *
     * @param configName the config name
     * @return the config property
     * @throws Exception the exception
     */
    public static String getConfigProperty(String configName) throws FrameworkExceptions {
        Logs.trace("Try to fetch the content form Configuration file with key" + configName);
        String configValue = configValues.get(configName.toUpperCase());
        Logs.trace("Fetching the value from the Configuration file with key '" +
                configName + "' and the result is '" + configValue + "'");
        if (null == configValue) {
            Logs.error("The specified '" + configName + "'configuration property is not available");
            throw new FrameworkExceptions("The required config value '" + configName + "' is not available");
        }
        return configValue;
    }
}
