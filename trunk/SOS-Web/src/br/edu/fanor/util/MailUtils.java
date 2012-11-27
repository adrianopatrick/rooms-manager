package br.edu.fanor.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.edu.fanor.entity.Usuario;

public class MailUtils {

	private static Session session;
	
	public static void sendMail(Usuario usuario, String assunto, String texto) throws MessagingException
	{
		try {
			Message message = new MimeMessage(getSession());
			// Seta o remetente
			message.setFrom(new InternetAddress("javatar.system@gmail.com"));
			// Define o destinatario
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(usuario.getEmail()));
			// Define o assunto
			message.setSubject(assunto);
			// Mensagem do email
			message.setText(texto);
			// Envia o email
			Transport.send(message); 
		} catch (MessagingException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static Authenticator getAuthenticator() {
		Authenticator autenticacao = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("javatar.system@gmail.com",
						"ap3javatar");
			}
		};
		return autenticacao;
	}

	public static Properties getPropriedades() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP do seu servidor
														// de email
		props.put("mail.smtp.socketFactory.port", "465"); // Porta do servidor
															// smtp
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); // Define a conexao do tipo
													// SSL
		props.put("mail.smtp.auth", "true"); // Define que e necessario
												// autenticacao
		props.put("mail.smtp.port", "465"); // Porta do seu servidor smtp
		return props;
	}



	public static Session getSession() {
		if (session == null) {
			setSession(Session.getInstance(getPropriedades(), getAuthenticator()));
		}
		return session;
	}

	public static void setSession(Session session) {
		MailUtils.session = session;
	}

}
