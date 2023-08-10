package org.emoration.mybatis.utils;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import org.emoration.mybatis.constants.Constant;
import org.emoration.mybatis.constants.Constant.SqlType;
import org.emoration.mybatis.mapping.MappedStatement;
import org.emoration.mybatis.session.Configuration;


/**
 * @Author czh
 * @Description XML工具类
 * @Date 2023/8/4
 */
public final class XmlUtil {

    /**
     * readMapperXml
     *
     * @param fileName
     * @param configuration
     * @see
     */
    @SuppressWarnings("rawtypes")
    public static void readMapperXml(File fileName, Configuration configuration) {

        try {

            // 创建一个读取器
            SAXReader saxReader = new SAXReader();
            saxReader.setEncoding(Constant.CHARSET_UTF8);

            // 读取文件内容
            Document document = saxReader.read(fileName);

            // 获取xml中的根元素
            Element rootElement = document.getRootElement();

            // 不是beans根元素的，文件不对
            if (!Constant.XML_ROOT_LABEL.equals(rootElement.getName())) {
                System.err.println("mapper xml文件根元素不是mapper");
                return;
            }

            String namespace = rootElement.attributeValue(Constant.XML_SELECT_NAMESPACE);

            List<MappedStatement> statements = new ArrayList<>();
//            for (Iterator iterator = rootElement.elementIterator(); iterator.hasNext(); ) {
//                Element element = (Element) iterator.next();
            for (Element element : rootElement.elements()) {
                String eleName = element.getName();

                MappedStatement statement = new MappedStatement();

                if (SqlType.SELECT.value().equals(eleName)) {
                    String resultType = element.attributeValue(Constant.XML_SELECT_RESULT_TYPE);
                    statement.setResultType(resultType);
                    statement.setSqlCommandType(SqlType.SELECT);
                } else if (SqlType.UPDATE.value().equals(eleName)) {
                    statement.setSqlCommandType(SqlType.UPDATE);
                } else if (SqlType.INSERT.value().equals(eleName)) {
                    statement.setSqlCommandType(SqlType.INSERT);
                } else if (SqlType.DELETE.value().equals(eleName)) {
                    statement.setSqlCommandType(SqlType.DELETE);
                } else {
                    System.err.println("不支持此xml标签解析:" + eleName);
                    statement.setSqlCommandType(SqlType.DEFAULT);
                }
                String parameterType = element.attributeValue(Constant.XML_SELECT_PARAMETER_TYPE);
                statement.setParameterType(parameterType);

                //设置SQL的唯一ID
                String sqlId = namespace + "." + element.attributeValue(Constant.XML_ELEMENT_ID);

                statement.setSqlId(sqlId);
                statement.setNamespace(namespace);
                statement.setSql(element.getTextTrim());
                statements.add(statement);

                // 解析子标签
                for(Element element_ : element.elements()){
                    String eleName_ = element_.getName();
                    if (Constant.XML_IF_LABEL.equals(eleName_)) {
                        MappedStatement.IfSqlNode ifSqlNode = new MappedStatement.IfSqlNode();
                        String test = element_.attributeValue(Constant.XML_IF_TEST);
                        ifSqlNode.setTest(test);
                        ifSqlNode.setSql(element_.getTextTrim());
                        statement.getIfSqlNodeList().add(ifSqlNode);
                    } else {
                        System.err.println("不支持此xml标签解析:" + eleName);
                    }
                }

                System.out.println(statement);
                configuration.addMappedStatement(sqlId, statement);

                //这里其实是在MapperRegistry中生产一个mapper对应的代理工厂
                configuration.addMapper(Class.forName(namespace));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
