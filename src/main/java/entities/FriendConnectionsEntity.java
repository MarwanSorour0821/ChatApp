package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "friend_connections", schema = "chatApp", catalog = "")
public class FriendConnectionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "connection_id1")
    private int connectionId1;
    @Basic
    @Column(name = "user1_id")
    private Integer user1Id;
    @Basic
    @Column(name = "user2_id")
    private Integer user2Id;

    public int getConnectionId1() {
        return connectionId1;
    }

    public void setConnectionId1(int connectionId1) {
        this.connectionId1 = connectionId1;
    }

    public Integer getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(Integer user1Id) {
        this.user1Id = user1Id;
    }

    public Integer getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(Integer user2Id) {
        this.user2Id = user2Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendConnectionsEntity that = (FriendConnectionsEntity) o;

        if (connectionId1 != that.connectionId1) return false;
        if (user1Id != null ? !user1Id.equals(that.user1Id) : that.user1Id != null) return false;
        if (user2Id != null ? !user2Id.equals(that.user2Id) : that.user2Id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = connectionId1;
        result = 31 * result + (user1Id != null ? user1Id.hashCode() : 0);
        result = 31 * result + (user2Id != null ? user2Id.hashCode() : 0);
        return result;
    }
}

//Testing commit Karim

