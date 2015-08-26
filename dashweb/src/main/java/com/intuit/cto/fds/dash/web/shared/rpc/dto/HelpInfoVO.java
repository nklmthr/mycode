package com.intuit.cto.fds.dash.web.shared.rpc.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * <p>Java class for helpInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="helpInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="helpText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "helpInfo", propOrder = {
    "url",
    "urlText",
    "helpText"
})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@XmlRootElement(name = "helpInfo")
public class HelpInfoVO implements Serializable{

	private static final long serialVersionUID = 1L;
	protected String url;
    protected String urlText;
    protected String helpText;


    public String getUrl() {
        return url;
    }


    public void setUrl(String value) {
        this.url = value;
    }


    public String getUrlText() {
        return urlText;
    }


    public void setUrlText(String value) {
        this.urlText = value;
    }


    public String getHelpText() {
        return helpText;
    }


    public void setHelpText(String value) {
        this.helpText = value;
    }


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HelpInfo [url=");
		builder.append(url);
		builder.append(", urlText=");
		builder.append(urlText);
		builder.append(", helpText=");
		builder.append(helpText);
		builder.append("]");
		return builder.toString();
	}

}
