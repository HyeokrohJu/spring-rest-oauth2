package com.hrpj.core.common;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hrpj.core.constants.StatusCode;
import com.hrpj.core.exception.BusinessLogicException;

import lombok.RequiredArgsConstructor;

@Service("sendMail")
@RequiredArgsConstructor
public class SendMail {

	private final JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@Value("${mail.from}")
	private String fromAddress;

	/**
	 * <pre>
	 * 1. 함수명 : mailSend
	 * 2. 작성일 : 2021. 2. 24. 오후 5:15:12
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 메일 발송
	 * </pre>
	 *
	 * @param mailMap
	 */
	public void mailSend( Map<String, String> mailMap ) {
		final MimeMessage message = mailSender.createMimeMessage( );
		try {
			message.addRecipient( RecipientType.TO, new InternetAddress( mailMap.get( "toAddress" ), mailMap.get( "toName" ), "UTF-8" ) );
			message.setFrom( new InternetAddress( this.fromAddress, "BaronDive", "UTF-8" ) );
			message.setSubject( mailMap.get( "title" ) );
			message.setText( mailMap.get( "cont" ), "UTF-8", "html" );
		} catch (final MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace( );
			throw new BusinessLogicException( "메일발송에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ) );
		}

		mailSender.send( message );;

	}

	/**
	 * <pre>
	 * 1. 함수명 : geContentFromTemplate
	 * 2. 작성일 : 2021. 2. 24. 오후 5:15:21
	 * 3. 작성자 : Ju Hyeokroh
	 * 4. 설명   : 템플릿 생성
	 * </pre>
	 *
	 * @param model
	 * @return
	 */
	public String getContentFromTemplate( Map<String, Object> model ) {
		final StringWriter content = new StringWriter( );
		try {
			final VelocityContext context = new VelocityContext( );
			context.put( "tmpPwd", model.get( "tmpPwd" ) );
			velocityEngine.mergeTemplate( "template/" + (String) model.get( "templateFile" ), "UTF-8", context, content );
		} catch (final Exception e) {
			e.printStackTrace( );
			throw new BusinessLogicException( "메일 템플릿 생성에 실패하였습니다.", StatusCode.DB_ERR_CD.getStatusCd( ) );
		}
		return content.toString( );
	}

}
