package com.guanke.vinda.sasync.siebel.core;

import com.guanke.vinda.samodels.model.entity.GkWsLogEntity;
import com.guanke.vinda.samodels.model.utils.UUIDUtils;
import com.guanke.vinda.sasync.config.WebServiceConfig;
import com.guanke.vinda.sasync.mapper.GkWsLogMapper;
import com.guanke.vinda.sasync.service.GkWsLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * @author Nicemorning
 */
@Service
public class WebServiceRequest {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebServiceRequest.class);

    private final WebServiceConfig config;

    private final GkWsLogService gkWsLogService;

    private String tagPrefix;
    private String bodyPrefix;
    private String bodyUri;
    private String headerPrefix;
    private String headerUri;
    private String username;
    private String password;
    private GkWsLogEntity gkWsLogEntity;

    public WebServiceRequest(WebServiceConfig config,
                             GkWsLogService gkWsLogService) {
        this.config = config;
        this.initParams();
        this.gkWsLogService = gkWsLogService;
    }

    private void initParams() {
        this.tagPrefix = config.getTagPrefix();
        this.bodyPrefix = config.getBodyPrefix();
        this.bodyUri = config.getBodyUri();
        this.headerPrefix = config.getHeaderPrefix();
        this.headerUri = config.getHeaderUri();
        this.username = config.getUsername();
        this.password = config.getPassword();
        this.gkWsLogEntity = new GkWsLogEntity();
    }

    /**
     * 发送web Service请求到CRM，并获取响应结果
     *
     * @param action             SOAPAction地址
     * @param interfaceNameInput 接口名称
     * @param urlString          远端真实路径
     * @param param              参数
     * @return 响应结果的第一个节点值
     */
    public String sendRequestToCrm(String action, String interfaceNameInput, String urlString,
                                   Map<String, Object> param) {
        gkWsLogEntity.setId(UUIDUtils.generateUuid());
        if (param == null) {
            LOGGER.info("Given illegal parameters: Params is null object.");
            return "传参错误";
        }
        Node responseNode = null;

        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage();
            MimeHeaders headers = soapMessage.getMimeHeaders();
            headers.setHeader("SOAPAction", action);
            SOAPPart soapPart = soapMessage.getSOAPPart();

            // 定义消息句柄
            SOAPEnvelope envelope = soapPart.getEnvelope();
            envelope.setPrefix(tagPrefix);
            envelope.removeNamespaceDeclaration("SOAP-ENV");
            envelope.addNamespaceDeclaration(bodyPrefix, bodyUri);
            envelope.addNamespaceDeclaration(headerPrefix, headerUri);

            // 定义Header
            SOAPHeader header = envelope.getHeader();
            if (header == null) {
                header = envelope.addHeader();
            }
            header.setPrefix(tagPrefix);
            header.removeNamespaceDeclaration("SOAP-ENV");
            header.addChildElement("SessionToken", headerPrefix).addTextNode("");
            header.addChildElement("SessionType", headerPrefix).addTextNode("None");
            header.addChildElement("PasswordText", headerPrefix).addTextNode(username);
            header.addChildElement("UsernameToken", headerPrefix).addTextNode(password);

            // 定义Body
            SOAPBody body = envelope.getBody();
            body.setPrefix(tagPrefix);
            body.removeNamespaceDeclaration("SOAP-ENV");

            // 定义Element
            SOAPElement element = body.addChildElement(interfaceNameInput, bodyPrefix);
            param.forEach((k, v) -> {
                try {
                    element.addChildElement(k, bodyPrefix).setValue(String.valueOf(v));
                } catch (SOAPException e) {
                    e.printStackTrace();
                }
            });
            soapMessage.saveChanges();

            soapMessage.writeTo(System.out);

            URL url = new URL(urlString);
            SOAPMessage reply = soapConnection.call(soapMessage, url);

            // 消息转换对象
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            Source source = reply.getSOAPPart().getContent();
            StreamResult result = new StreamResult(System.out);
            try {
                transformer.transform(source, result);
            } catch (TransformerException e) {
                e.printStackTrace();
            }
            reply.getSOAPPart();
            // 关闭连接
            soapConnection.close();

            SOAPBody responseBody = reply.getSOAPBody();
            LOGGER.info("Web service response body: \n" + responseBody);
            responseNode = responseBody.getFirstChild();
        } catch (SOAPException | IOException | TransformerConfigurationException e) {
            LOGGER.info("Web service to crm throw an exception: " + e.getMessage());
            gkWsLogEntity.setErrmessage(e.getMessage());
            gkWsLogEntity.setMethodname(String.valueOf(param.get("objectname")));
            gkWsLogEntity.setRecordid(String.valueOf(param.get("attr01")));
            gkWsLogService.insertSelective(gkWsLogEntity);
            e.printStackTrace();
        }
        return responseNode == null ? "null" : responseNode.getTextContent();
    }
}
