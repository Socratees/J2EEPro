package entity;

import javax.persistence.*;

@Entity
@Table(name = "counter", schema = "j2ee", catalog = "")
public class CounterEntity {
    private int counterId;
    private int userNum;
    private int visitorNum;

    @Id
    @Column(name = "counterId", nullable = false)
    public int getCounterId() {
        return counterId;
    }

    public void setCounterId(int counterId) {
        this.counterId = counterId;
    }

    @Basic
    @Column(name = "userNum", nullable = false)
    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    @Basic
    @Column(name = "visitorNum", nullable = false)
    public int getVisitorNum() {
        return visitorNum;
    }

    public void setVisitorNum(int visitorNum) {
        this.visitorNum = visitorNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CounterEntity that = (CounterEntity) o;

        if (counterId != that.counterId) return false;
        if (userNum != that.userNum) return false;
        if (visitorNum != that.visitorNum) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = counterId;
        result = 31 * result + userNum;
        result = 31 * result + visitorNum;
        return result;
    }
}
