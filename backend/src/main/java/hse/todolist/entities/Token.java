package entities;

import jakarta.persistence.*;

@Entity
public class Token {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "token_id")
    private int tokenId;
    @Basic
    @Column(name = "token_value")
    private String tokenValue;
    @Basic
    @Column(name = "revoked")
    private Boolean revoked;
    @Basic
    @Column(name = "expired")
    private Boolean expired;
    @Basic
    @Column(name = "user_id")
    private int userId;

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public Boolean getRevoked() {
        return revoked;
    }

    public void setRevoked(Boolean revoked) {
        this.revoked = revoked;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (tokenId != token.tokenId) return false;
        if (userId != token.userId) return false;
        if (tokenValue != null ? !tokenValue.equals(token.tokenValue) : token.tokenValue != null) return false;
        if (revoked != null ? !revoked.equals(token.revoked) : token.revoked != null) return false;
        if (expired != null ? !expired.equals(token.expired) : token.expired != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tokenId;
        result = 31 * result + (tokenValue != null ? tokenValue.hashCode() : 0);
        result = 31 * result + (revoked != null ? revoked.hashCode() : 0);
        result = 31 * result + (expired != null ? expired.hashCode() : 0);
        result = 31 * result + userId;
        return result;
    }
}
