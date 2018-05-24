package org.groupevents.service.connectivity;

public interface EmailService {
	void sendSimpleMessage(String to, String subject, String text);
}
