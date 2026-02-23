public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        String body = n.body != null ? n.body : "";
        System.out.println("SMS -> to=" + n.phone + " body=" + body);
        audit.add("sms sent");
    }
}
