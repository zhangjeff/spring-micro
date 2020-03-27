package com.jeff.ioc.beans.beandefinition;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream  = classLoader.getResource(location).openStream();
        doLoadBeanDefinitions(inputStream);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(inputStream);
        Element root = doc.getDocumentElement();
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                processBeanDefinition(ele);
            }
        }

        inputStream.close();
    }

    protected void processBeanDefinition(Element ele){
        NamedNodeMap attributes = ele.getAttributes();
        BeanDefinition beanDefinition = new BeanDefinition();

        for (int i = 0; i < attributes.getLength(); i++) {
            Node node = attributes.item(i);
//            if (node.getNodeType() == Node.ELEMENT_NODE) {
            decorateIfRequired(node, beanDefinition);
//            }
        }
        processProperty(ele, beanDefinition);
    }

    protected void decorateIfRequired(Node node, BeanDefinition beanDefinition){
        switch (node.getNodeName()){
            case "class":
                beanDefinition.setBeanClassName(node.getNodeValue());
                break;
            case "init-method":
                beanDefinition.setInitMethod(node.getNodeValue());
                break;
            case "scope":
                beanDefinition.setScope(node.getNodeValue());
                break;
            case "id":
                getRegistry().put(node.getNodeValue(), beanDefinition);

        }
        String nodeName = node.getNodeName();
        if (nodeName.startsWith("p:")) {
            nodeName = nodeName.substring(2);
            beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(nodeName, node.getNodeValue()));
        }
    }

    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNode = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                } else {
                    String ref = propertyEle.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }

    //    protected void processBeanDefinition(Element ele) {
//        String name = ele.getAttribute("id");
//        String className = ele.getAttribute("class");
//        String initMethod = ele.getAttribute("init-method");
//        String scope = ele.getAttribute("scope");
//        BeanDefinition beanDefinition = new BeanDefinition();
//        processProperty(ele, beanDefinition);
//        beanDefinition.setBeanClassName(className);
//        beanDefinition.setInitMethod(initMethod);
//        beanDefinition.setScope(scope);
//        getRegistry().put(name, beanDefinition);
//    }

}
