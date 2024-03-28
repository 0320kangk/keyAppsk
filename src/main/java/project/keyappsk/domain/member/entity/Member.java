package project.keyappsk.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.oauth2.core.user.OAuth2User;
import project.keyappsk.domain.alarm.entity.AlarmMessage;
import project.keyappsk.domain.cart.entity.Cart;
import project.keyappsk.domain.member.entity.enumerate.Role;
import project.keyappsk.domain.member.entity.enumerate.SignType;
import project.keyappsk.domain.orders.entity.Order;
import project.keyappsk.domain.store.entity.Store;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SignType signType;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "member")
    private List<Store> members;

    @OneToMany(mappedBy = "memberBuyer")
    private List<Order> orders;

    @OneToMany(mappedBy = "member")
    private List<AlarmMessage> alarmMessages;

    @OneToMany(mappedBy = "member")
    private List<Cart> carts;


    public Member update(String name){
        this.name = name;
        return this;
    }
    public String getRoleKey(){
        return this.role.getKey();
    }


}
