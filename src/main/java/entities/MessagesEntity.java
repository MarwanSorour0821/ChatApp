package entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "messages", schema = "chatApp", catalog = "")
public class MessagesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "message_id")
    private int messageId;
    @Basic
    @Column(name = "sender_id")
    private Integer senderId;
    @Basic
    @Column(name = "receiver_id")
    private Integer receiverId;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "timestamp1")
    private Timestamp timestamp1;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp1() {
        return timestamp1;
    }

    public void setTimestamp1(Timestamp timestamp1) {
        this.timestamp1 = timestamp1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessagesEntity that = (MessagesEntity) o;

        if (messageId != that.messageId) return false;
        if (senderId != null ? !senderId.equals(that.senderId) : that.senderId != null) return false;
        if (receiverId != null ? !receiverId.equals(that.receiverId) : that.receiverId != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (timestamp1 != null ? !timestamp1.equals(that.timestamp1) : that.timestamp1 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = messageId;
        result = 31 * result + (senderId != null ? senderId.hashCode() : 0);
        result = 31 * result + (receiverId != null ? receiverId.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (timestamp1 != null ? timestamp1.hashCode() : 0);
        return result;
    }
}
